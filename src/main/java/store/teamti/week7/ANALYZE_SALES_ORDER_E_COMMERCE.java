package store.teamti.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <p>Description:</p>
 * Data about sales in an e-commerce company (the e-commerce company has several shops) consists a sequence of lines, each line (represents an order) has the following information:
 * <CustomerID> <ProductID> <Price> <ShopID> <TimePoint>
 * in which the customer <CustomerID> buys a product <ProductID> with price <Price> at the shop <ShopID> at the time-point <TimePoint>
 * <CustomerID>: string of length from 3 to 10
 * <ProductID>: string of length from 3 to 10
 * <Price>: a positive integer from 1 to 1000
 * <ShopID>: string of length from 3 to 10
 * <TimePoint>: string representing time-point with the format HH:MM:SS (for example, 09:45:20 means the time-point 9 hour 45 minutes 20 seconds)
 * Perform a sequence of queries of following types:
 * ?total_number_orders: return the total number of orders
 * ?total_revenue: return the total revenue the e-commerce company gets
 * ?revenue_of_shop <ShopID>: return the total revenue the shop <ShopID> gets
 * ?total_consume_of_customer_shop <CustomerID> <ShopID>: return the total revenue the shop <ShopID> sells products to customer <CustomerID>
 * ?total_revenue_in_period <from_time> <to_time>: return the total revenue the e-commerce gets of the period from <from_time> to <to_time> (inclusive)
 *
 * <p>Input:</p>
 * The input consists of two blocks of data:
 * The first block is the operational data, which is a sequence of lines (number of lines can be upto 100000), each line contains the information of a submission with above format
 * The first block is terminated with a line containing the character #
 * The second block is the query block, which is a sequence of lines (number of lines can be upto 100000), each line is a query described above
 * The second block is terminated with a line containing the character #
 *
 * <p>Output:</p>
 * Write in each line, the result of the corresponding query
 *
 * <p>Example:</p>
 * <pre>
 * Input
 * C001 P001 10 SHOP001 10:30:10
 * C001 P002 30 SHOP001 12:30:10
 * C003 P001 40 SHOP002 10:15:20
 * C001 P001 80 SHOP002 08:40:10
 * C002 P001 130 SHOP001 10:30:10
 * C002 P001 160 SHOP003 11:30:20
 * #
 * ?total_number_orders
 * ?total_revenue
 * ?revenue_of_shop SHOP001
 * ?total_consume_of_customer_shop C001 SHOP001
 * ?total_revenue_in_period 10:00:00 18:40:45
 * #
 *
 *
 * Output
 * 6
 * 450
 * 170
 * 40
 * 370
 * </pre>
 */
public class ANALYZE_SALES_ORDER_E_COMMERCE {
    static class Order {
        String customerId;
        String productId;
        int price;
        String shopId;
        int timeInSeconds;

        Order(String customerId, String productId, int price, String shopId, String timePoint) {
            this.customerId = customerId;
            this.productId = productId;
            this.price = price;
            this.shopId = shopId;
            this.timeInSeconds = convertTimeToSeconds(timePoint);
        }
    }

    static List<Order> orders = new ArrayList<>();
    static Map<String, Integer> shopRevenue = new HashMap<>();
    static Map<String, Map<String, Integer>> customerShopRevenue = new HashMap<>();
    static int totalRevenue = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        // Process orders
        while (!(line = br.readLine()).equals("#")) {
            processOrder(line);
        }

        // Process queries
        while (!(line = br.readLine()).equals("#")) {
            processQuery(line);
        }

        br.close();
    }

    private static void processOrder(String line) {
        String[] parts = line.split(" ");
        String customerId = parts[0];
        String productId = parts[1];
        int price = Integer.parseInt(parts[2]);
        String shopId = parts[3];
        String timePoint = parts[4];

        Order order = new Order(customerId, productId, price, shopId, timePoint);
        orders.add(order);

        totalRevenue += price;

        shopRevenue.merge(shopId, price, Integer::sum);

        customerShopRevenue
                .computeIfAbsent(customerId, k -> new HashMap<>())
                .merge(shopId, price, Integer::sum);
    }

    private static void processQuery(String line) {
        String[] parts = line.split(" ");
        String queryType = parts[0];

        switch (queryType) {
            case "?total_number_orders":
                System.out.println(orders.size());
                break;

            case "?total_revenue":
                System.out.println(totalRevenue);
                break;

            case "?revenue_of_shop":
                String shopId = parts[1];
                System.out.println(shopRevenue.getOrDefault(shopId, 0));
                break;

            case "?total_consume_of_customer_shop":
                String customerId = parts[1];
                String shop = parts[2];
                int customerShopTotal = customerShopRevenue
                        .getOrDefault(customerId, Collections.emptyMap())
                        .getOrDefault(shop, 0);
                System.out.println(customerShopTotal);
                break;

            case "?total_revenue_in_period":
                int fromTime = convertTimeToSeconds(parts[1]);
                int toTime = convertTimeToSeconds(parts[2]);
                int periodRevenue = calculatePeriodRevenue(fromTime, toTime);
                System.out.println(periodRevenue);
                break;
        }
    }

    private static int convertTimeToSeconds(String timePoint) {
        String[] parts = timePoint.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        return hours * 3600 + minutes * 60 + seconds;
    }

    private static int calculatePeriodRevenue(int fromTime, int toTime) {
        int totalRevenue = 0;
        for (Order order : orders) {
            if (order.timeInSeconds >= fromTime && order.timeInSeconds <= toTime) {
                totalRevenue += order.price;
            }
        }
        return totalRevenue;
    }
}
