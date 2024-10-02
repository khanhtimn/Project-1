package store.teamti.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Problem: Week 2 - Compute C_k_n
 *
 * <p>Description:</p>
 * Chương trình tính toán số cách chọn k phần tử từ n phần tử cho trước, hay còn gọi là hệ số nhị thức C(k,n).
 * Kết quả trả về sẽ là giá trị của C(k,n) lấy phần dư cho 10^9 + 7.
 *
 * <p>Input:</p>
 * Một dòng chứa hai số nguyên dương k và n (1 <= k,n <= 999).
 *
 * <p>Output:</p>
 * Kết quả là giá trị của C(k,n) modulo 10^9 + 7.
 *
 * <p>Example:</p>
 *
 * <pre>
 * Input:
 * 3 5
 *
 * Output:
 * 10
 * </pre>
 */
public class C_K_N {
    private static final int MOD = 1_000_000_007;
    private static final int MAX_N = 1000;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] input = br.readLine().split(" ");
            int k = Integer.parseInt(input[0]);
            int n = Integer.parseInt(input[1]);

            long[][] c = new long[MAX_N + 1][MAX_N + 1];

            for (int i = 0; i <= n; i++) {
                c[i][0] = 1;
                c[i][i] = 1;
            }

            for (int i = 2; i <= n; i++) {
                for (int j = 1; j < i; j++) {
                    c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % MOD;
                }
            }

            System.out.println(c[n][k]);
        }
    }
}
