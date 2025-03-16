#![no_std]
#![no_main]

use esp32c6_wifi::sht3x::{Address, ClockStretch, Error, Repeatability, Sht3x};
use esp_wifi::{
    init,
    wifi::{
        ClientConfiguration, Configuration, WifiController, WifiDevice, WifiEvent, WifiStaDevice,
        WifiState,
    },
    EspWifiController,
};

use rust_mqtt::{
    client::{client::MqttClient, client_config::ClientConfig},
    packet::v5::reason_codes::ReasonCode,
    utils::rng_generator::CountingRng,
};

use embassy_executor::Spawner;
use embassy_net::{
    tcp::TcpSocket,
    {Config as EmbassyNetConfig, Stack, StackResources},
};
use embassy_time::{Duration, Timer};
use esp_backtrace as _;
use esp_hal::prelude::*;
use log::{info,debug,error};
use core::fmt::Write;
use heapless::String;

extern crate alloc;

const SSID: &str = env!("SSID");
const PASSWORD: &str = env!("PASSWORD");
// const MQTT_IP4: &str = env!("MQTT_IP4");
// const MQTT_PORT: &str = env!("MQTT_PORT");
const MQTT_USERNAME: &str = env!("MQTT_USERNAME");
const MQTT_PASSWORD: &str = env!("MQTT_PASSWORD");

macro_rules! mk_static {
    ($t:ty,$val:expr) => {{
        static STATIC_CELL: static_cell::StaticCell<$t> = static_cell::StaticCell::new();
        #[deny(unused_attributes)]
        let x = STATIC_CELL.uninit().write(($val));
        x
    }};
}
// A background task, to process network events - when new packets, they need to processed, embassy-net, wraps smoltcp
#[embassy_executor::task]
async fn net_task(stack: &'static Stack<WifiDevice<'static, WifiStaDevice>>) {
    stack.run().await
}

// maintains wifi connection, when it disconnects it tries to reconnect
#[embassy_executor::task]
async fn connection(mut controller: WifiController<'static>) {
    info!("start connection task");
    debug!("Device capabilities: {:?}", controller.capabilities());
    loop {
        if esp_wifi::wifi::wifi_state() == WifiState::StaConnected {
            controller.wait_for_event(WifiEvent::StaDisconnected).await;
            Timer::after(Duration::from_millis(5000)).await
        }

        if !matches!(controller.is_started(), Ok(true)) {
            let client_config = Configuration::Client(ClientConfiguration {
                ssid: SSID.try_into().unwrap(),
                password: PASSWORD.try_into().unwrap(),
                ..Default::default()
            });
            controller.set_configuration(&client_config).unwrap();
            info!("Starting wifi");
            controller.start_async().await.unwrap();
            info!("Wifi started!");
        }
        info!("About to connect...");

        match controller.connect_async().await {
            Ok(_) => info!("Wifi connected!"),
            Err(e) => {
                error!("Failed to connect to wifi: {e:?}");
                Timer::after(Duration::from_millis(5000)).await
            }
        }
    }
}

#[esp_hal_embassy::main]
async fn main(spawner: Spawner) -> ! {
    esp_println::logger::init_logger_from_env();

    let peripherals = esp_hal::init({
        let mut config = esp_hal::Config::default();
        config.cpu_clock = CpuClock::max();
        config
    });

    esp_alloc::heap_allocator!(72 * 1024);

    let timg0 = esp_hal::timer::timg::TimerGroup::new(peripherals.TIMG0);
    let mut rng = esp_hal::rng::Rng::new(peripherals.RNG);

    let init = &*mk_static!(
        EspWifiController<'static>,
        init(timg0.timer0, rng, peripherals.RADIO_CLK).unwrap()
    );

    let wifi = peripherals.WIFI;
    let (wifi_interface, controller) = esp_wifi::wifi::new_with_mode(init, wifi, WifiStaDevice).unwrap();

    let systimer = esp_hal::timer::systimer::SystemTimer::new(peripherals.SYSTIMER)
        .split::<esp_hal::timer::systimer::Target>();
    esp_hal_embassy::init(systimer.alarm0);

    // Create a new peripheral object with the described wiring
    // and standard I2C clock speed
    let i2c0 = esp_hal::i2c::master::I2c::new(
        peripherals.I2C0,
        esp_hal::i2c::master::Config {
            frequency: 800u32.kHz(),
            timeout: Some(10),
            }
        )
        .with_sda(peripherals.GPIO23)
        .with_scl(peripherals.GPIO22)
        .into_async();

    let config = EmbassyNetConfig::dhcpv4(Default::default());

    let seed = (rng.random() as u64) << 32 | rng.random() as u64;

    // Init network stack
    let stack = &*mk_static!(
        Stack<WifiDevice<'_, WifiStaDevice>>,
        Stack::new(
            wifi_interface,
            config,
            mk_static!(StackResources<3>, StackResources::<3>::new()),
            seed
        )
    );

    spawner.spawn(connection(controller)).ok();
    spawner.spawn(net_task(stack)).ok();

    let mut rx_buffer = [0; 4096];
    let mut tx_buffer = [0; 4096];

    //wait until wifi connected
    loop {
        if stack.is_link_up() {
            break;
        }
        Timer::after(Duration::from_millis(500)).await;
    }

    info!("Waiting to get IP address...");

    loop {
        if let Some(config) = stack.config_v4() {
            info!("Got IP: {}", config.address); //dhcp IP address
            break;
        }
        Timer::after(Duration::from_millis(500)).await;
    }

    loop {
        Timer::after(Duration::from_millis(1_000)).await;

        let mut socket = TcpSocket::new(stack, &mut rx_buffer, &mut tx_buffer);

        socket.set_timeout(Some(embassy_time::Duration::from_secs(10)));

        // Hardcoded for now
        let remote_endpoint = (embassy_net::Ipv4Address::new(192, 168, 102, 50), 1883);
        // let remote_endpoint = (
        //     embassy_net::Ipv4Address::from_str(MQTT_IP4).expect("Invalid MQTT Broker Address"),
        //     MQTT_PORT.parse::<u16>().expect("Invalid MQTT Broker Port")
        // );

        info!("connecting to...");
        let connection = socket.connect(remote_endpoint).await;
        if let Err(e) = connection {
            error!("connect error: {:?}", e);
            continue;
        }
        info!("connected to: {:?}", remote_endpoint);

        let mut config = ClientConfig::new(
            rust_mqtt::client::client_config::MqttVersion::MQTTv5,
            CountingRng(20000),
        );
        config.add_max_subscribe_qos(rust_mqtt::packet::v5::publish_packet::QualityOfService::QoS1);
        config.add_client_id("clientId-8rhWgBODCl");
        config.add_username(MQTT_USERNAME);
        config.add_password(MQTT_PASSWORD);
        config.max_packet_size = 100;
        let mut recv_buffer = [0; 80];
        let mut write_buffer = [0; 80];

        let mut client =
            MqttClient::<_, 5, _>::new(socket, &mut write_buffer, 80, &mut recv_buffer, 80, config);

        match client.connect_to_broker().await {
            Ok(()) => {}
            Err(mqtt_error) => match mqtt_error {
                ReasonCode::NetworkError => {
                    error!("MQTT Network Error");
                    continue;
                }
                _ => {
                    error!("Other MQTT Error: {:?}", mqtt_error);
                    continue;
                }
            },
        }

        let mut sht3x = Sht3x::new(i2c0, Address::Low, sleep);
        loop {
            info!("Raw status: {:?}", sht3x.status().await.unwrap());
            match sht3x.measure(ClockStretch::Enabled, Repeatability::High).await {
                Ok(measurement) => {
                    info!(
                        "Temperature: {:.2} °C, Humidity: {:.2} %",
                        measurement.temperature as f32 / 100.0,
                        measurement.humidity as f32 / 100.0
                    );

                    // Format the measurement data into the message buffer
                    let mut sensor_data: String<64> = String::new();
                    write!(
                        sensor_data,
                        "Temperature: {:.2} °C, Humidity: {:.2} %",
                        measurement.temperature as f32 / 100.0,
                        measurement.humidity as f32 / 100.0
                    ).expect("write! failed!");

                    // Send the formatted message as an MQTT publish
                    match client
                        .send_message(
                            "temperature/1",
                            sensor_data.as_bytes(),
                            rust_mqtt::packet::v5::publish_packet::QualityOfService::QoS1,
                            true,
                        )
                        .await
                    {
                        Ok(()) => {
                            info!("MQTT message sent successfully");
                        }
                        Err(mqtt_error) => match mqtt_error {
                            ReasonCode::NetworkError => {
                                error!("MQTT Network Error");
                                continue;
                            }
                            _ => {
                                error!("Other MQTT Error: {:?}", mqtt_error);
                                continue;
                            }
                        },
                    }
                },
                Err(Error::Crc) => {
                    error!("CRC Error: Invalid data received from sensor");
                },
                Err(Error::I2c(e)) => {
                    error!("I2C Error: {:?}", e);
                },
            }
            Timer::after(Duration::from_millis(3000)).await;
        }
    }
}

pub async fn sleep(millis: u32) {
    Timer::after(Duration::from_millis(millis as u64)).await;
}
