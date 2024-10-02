package store.teamti.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Problem: Week 1 - So sánh chênh lệch giá điện theo đề xuất mới của EVN
 *
 * <p>Description:</p>
 * Chương trình so sánh chênh lệch giá điện tiêu thụ theo mô hình bậc thang hiện hành (6 bậc)
 * và mô hình bậc thang 5 bậc theo đề xuất mới của EVN.
 *
 * <p>Input:</p>
 * Nhập vào một số nguyên là số kWh điện tiêu thụ của hộ gia đình.
 *
 * <p>Output:</p>
 * In ra chênh lệch giữa số tiền điện theo đề xuất mới và số tiền điện theo mô hình hiện hành.
 * Chênh lệch được tính thêm VAT 10% và in ra với 2 chữ số thập phân.
 *
 * <p>Example:</p>
 *
 * <pre>
 * Input:
 * 540
 *
 * Output:
 * -22176.00
 *
 * Input:
 * 70
 *
 * Output:
 * -1276.00
 * </pre>
 */
public class DIFF_ELECTRIC_PRICE {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int kWh = Integer.parseInt(br.readLine().trim());

        double currentPrice = calculateCurrentPrice(kWh);

        double proposedPrice = calculateProposedPrice(kWh);

        double difference = proposedPrice - currentPrice;

        // VAT (10%)
        difference += difference * 0.1;

        System.out.printf("%.2f%n", difference);
    }


    /**
     * Tính toán tiền điện theo mô hình bậc thang 6 bậc hiện hành.
     *
     * @param kWh số kWh điện tiêu thụ.
     * @return tổng tiền điện tính theo mô hình 6 bậc.
     */
    private static double calculateCurrentPrice(int kWh) {
        double total = 0;
        if (kWh > 400) {
            total += (kWh - 400) * 3015;
            kWh = 400;
        }
        if (kWh > 300) {
            total += (kWh - 300) * 2919;
            kWh = 300;
        }
        if (kWh > 200) {
            total += (kWh - 200) * 2612;
            kWh = 200;
        }
        if (kWh > 100) {
            total += (kWh - 100) * 2074;
            kWh = 100;
        }
        if (kWh > 50) {
            total += (kWh - 50) * 1786;
            kWh = 50;
        }
        total += kWh * 1728;
        return total;
    }

    /**
     * Tính toán tiền điện theo mô hình bậc thang 5 bậc đề xuất.
     *
     * @param kWh số kWh điện tiêu thụ.
     * @return tổng tiền điện tính theo mô hình 5 bậc đề xuất.
     */
    private static double calculateProposedPrice(int kWh) {
        double total = 0;
        if (kWh > 700) {
            total += (kWh - 700) * 3457;
            kWh = 700;
        }
        if (kWh > 400) {
            total += (kWh - 400) * 3111;
            kWh = 400;
        }
        if (kWh > 200) {
            total += (kWh - 200) * 2612;
            kWh = 200;
        }
        if (kWh > 100) {
            total += (kWh - 100) * 2074;
            kWh = 100;
        }
        total += kWh * 1728;
        return total;
    }
}
