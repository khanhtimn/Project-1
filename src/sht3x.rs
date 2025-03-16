//! Async Driver for Sensirion SHT3x-DIS Digital Temperature/Humidity Sensors

use core::future::Future;
use embedded_hal_async::i2c::I2c;
use bitflags::bitflags;

/// Timing Specification for the Sensor System (in milliseconds)
// TODO: Support longer times needed with lower voltage (Table 5).
const SOFT_RESET_TIME_MS: u32 = 1;

/// SHT3x I2C device addresses
#[derive(Debug, Copy, Clone)]
pub enum Address {
    High = 0x45,
    Low = 0x44,
}

#[derive(Debug)]
pub enum Error<E> {
    /// CRC check failed
    Crc,
    /// I2C communication error
    I2c(E),
}

#[derive(Debug)]
pub struct Measurement {
    pub temperature: i32, // Temperature in milli-degrees Celsius
    pub humidity: u16,    // Humidity in tenths of percent
}

pub struct Sht3x<I2C, D, DFut>
where
    I2C: I2c,
    D: Fn(u32) -> DFut,
    DFut: Future<Output = ()>,
{
    i2c: I2C,
    address: Address,
    sleep_fn: D,
}

impl<I2C, D, DFut> Sht3x<I2C, D, DFut>
where
    I2C: I2c,
    D: Fn(u32) -> DFut,
    DFut: Future<Output = ()>,
{
    /// Creates a new async driver
    pub fn new(i2c: I2C, address: Address, sleep_fn: D) -> Self {
        Self {
            i2c,
            address,
            sleep_fn,
        }
    }

    /// Send an I2C command asynchronously
    async fn command(
        &mut self,
        command: Command,
        wait_time: Option<u32>,
    ) -> Result<(), Error<I2C::Error>> {
        let cmd_bytes = command.value().to_be_bytes();
        self.i2c
            .write(self.address as u8, &cmd_bytes)
            .await
            .map_err(Error::I2c)?;

        // Await the sleep future if wait_time is specified
        if let Some(time_ms) = wait_time {
            (self.sleep_fn)(time_ms).await;
        }

        Ok(())
    }

    /// Take a temperature and humidity measurement
    pub async fn measure(
        &mut self,
        cs: ClockStretch,
        rpt: Repeatability,
    ) -> Result<Measurement, Error<I2C::Error>> {
        // Send a Single Shot Measurement Command
        self.command(Command::SingleShot(cs, rpt), Some(rpt.max_duration()))
            .await?;

        // Read 6 bytes of data (Temperature MSB, Temperature LSB, Temp CRC, Humidity MSB, Humidity LSB, Humidity CRC)
        let mut buf = [0u8; 6];
        self.i2c
            .read(self.address as u8, &mut buf)
            .await
            .map_err(Error::I2c)?;

        // Validate CRC for Temperature and do conversion
        let temperature = check_crc([buf[0], buf[1]], buf[2]).map(convert_temperature)?;

        // Validate CRC for Humidity and do conversion
        let humidity = check_crc([buf[3], buf[4]], buf[5]).map(convert_humidity)?;

        Ok(Measurement {
            temperature,
            humidity,
        })
    }

    /// Soft reset the sensor
    pub async fn reset(&mut self) -> Result<(), Error<I2C::Error>> {
        self.command(Command::SoftReset, Some(SOFT_RESET_TIME_MS))
            .await
    }

    /// Read the status register
    pub async fn status(&mut self) -> Result<Status, Error<I2C::Error>> {
        self.command(Command::Status, None).await?;

        let mut buf = [0u8; 3];
        self.i2c
            .read(self.address as u8, &mut buf)
            .await
            .map_err(Error::I2c)?;

        let status_raw = check_crc([buf[0], buf[1]], buf[2])?;
        Ok(Status::from_bits_truncate(status_raw))
    }

    /// Clear the status register
    pub async fn clear_status(&mut self) -> Result<(), Error<I2C::Error>> {
        self.command(Command::ClearStatus, None).await
    }

}

#[derive(Debug)]
pub enum ClockStretch {
    Enabled,
    Disabled,
}

#[derive(Debug, Clone, Copy)]
pub enum Repeatability {
    High,
    Medium,
    Low,
}

impl Repeatability {
    /// Maximum measurement duration in miliseconds
    const fn max_duration(&self) -> u32 {
        match *self {
            Repeatability::Low => 4,
            Repeatability::Medium => 6,
            Repeatability::High => 15,
        }
    }
}

/// SHT3x Commands
#[allow(unused)]
enum Command {
    SingleShot(ClockStretch, Repeatability),
    Periodic(Rate, Repeatability),
    FetchData,
    PeriodicWithART,
    Break,
    SoftReset,
    HeaterEnable,
    HeaterDisable,
    Status,
    ClearStatus,
}

impl Command {
    /// Returns the command value based on the command variant
    const fn value(&self) -> u16 {
        use ClockStretch::Disabled as CSDisabled;
        use ClockStretch::Enabled as CSEnabled;
        use Rate::*;
        use Repeatability::*;
        match *self {
            // 4.3 Measurement Commands for Single Shot Data Acquisition Mode
            // Table 8
            Command::SingleShot(CSEnabled, High) => 0x2C06,
            Command::SingleShot(CSEnabled, Medium) => 0x2C0D,
            Command::SingleShot(CSEnabled, Low) => 0x2C10,
            Command::SingleShot(CSDisabled, High) => 0x2400,
            Command::SingleShot(CSDisabled, Medium) => 0x240B,
            Command::SingleShot(CSDisabled, Low) => 0x2416,

            // 4.5 Measurement Commands for Periodic Data Acquisition Mode
            // Table 9
            Command::Periodic(R0_5, High) => 0x2032,
            Command::Periodic(R0_5, Medium) => 0x2024,
            Command::Periodic(R0_5, Low) => 0x202F,
            Command::Periodic(R1, High) => 0x2130,
            Command::Periodic(R1, Medium) => 0x2126,
            Command::Periodic(R1, Low) => 0x212D,
            Command::Periodic(R2, High) => 0x2236,
            Command::Periodic(R2, Medium) => 0x2220,
            Command::Periodic(R2, Low) => 0x222B,
            Command::Periodic(R4, High) => 0x2334,
            Command::Periodic(R4, Medium) => 0x2322,
            Command::Periodic(R4, Low) => 0x2329,
            Command::Periodic(R10, High) => 0x2737,
            Command::Periodic(R10, Medium) => 0x2721,
            Command::Periodic(R10, Low) => 0x272A,

            // 4.6 Readout of Measurement Results for Periodic Mode
            // Table 10
            Command::FetchData => 0xE000,

            // 4.7 ART command
            // Table 11
            Command::PeriodicWithART => 0x2B32,

            // 4.8 Break command
            // Table 12
            Command::Break => 0x3093,

            // 4.9 Reset
            // Table 13
            Command::SoftReset => 0x30A2,

            // 4.10 Heater
            // Table 15
            Command::HeaterEnable => 0x306D,
            Command::HeaterDisable => 0x3066,

            // 4.11 Status register
            // Table 16
            Command::Status => 0xF32D,
            // Table 18
            Command::ClearStatus => 0x3041,
        }
    }
}

#[allow(non_camel_case_types, unused)]
#[derive(Debug)]
enum Rate {
    R0_5,
    R1,
    R2,
    R4,
    R10,
}

bitflags! {
    /// Status register flags
    #[derive(Debug)]
    pub struct Status: u16 {
        /// Alert pending status
        const ALERT_PENDING         = 1 << 15;
        /// Heater status
        const HEATER                = 1 << 13;
        /// RH tracking alert
        const RH_TRACKING_ALERT     = 1 << 11;
        /// T tracking alert
        const T_TRACKING_ALERT      = 1 << 10;
        /// System reset detected
        const SYSTEM_RESET_DETECTED = 1 << 4;
        /// Command status
        const COMMAND               = 1 << 1;
        /// Write data checksum status
        const WRITE_DATA_CHECKSUM   = 1 << 0;
    }
}

/// Convert raw temperature data to milli-degrees Celsius
const fn convert_temperature(raw: u16) -> i32 {
    -4500 + (17500 * raw as i32) / 65535
}

/// Convert raw humidity data to tenths of percent
const fn convert_humidity(raw: u16) -> u16 {
    ((10000 * raw as u32) / 65535) as u16
}

/// Compare the CRC of the input array to the given CRC checksum.
fn check_crc<E>(data: [u8; 2], crc: u8) -> Result<u16, Error<E>> {
    let calculated_crc = crc8(data);

    if calculated_crc == crc {
        Ok(u16::from_be_bytes(data))
    } else {
        Err(Error::Crc)
    }
}

/// Performs a CRC8 calculation on the supplied values.
/// CRC8 formula from page 14 of SHT spec pdf
fn crc8(data: [u8; 2]) -> u8 {
    let mut crc: u8 = 0xff;

    /// Polynomial 0x31 (x8 + x5 + x4 + 1)
    const POLYNOMIAL: u8 = 0x31;

    for byte in &data {
        crc ^= byte;

        for _ in 0..8 {
            if crc & 0x80 != 0 {
                crc = (crc << 1) ^ POLYNOMIAL;
            } else {
                crc <<= 1;
            }
        }
    }

    crc
}
