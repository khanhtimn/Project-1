package store.teamti.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Computes the Fibonacci sequence element at a specified position.
 *
 * <p>Description:</p>
 * Given the Fibonacci sequence defined as:
 * a[0] = 0, a[1] = 1, and a[n] = a[n-1] + a[n-2] for all n >= 2,
 * this program calculates the term a[n-1] for a given n.
 *
 * <p>Input:</p>
 * A single line containing a positive integer n (2 <= n <= 21), representing
 * the position in the sequence.
 *
 * <p>Output:</p>
 * The (n-1)th term of the Fibonacci sequence.
 *
 * <p>Example:</p>
 *
 * <pre>
 * Input:
 * 9
 *
 * Output:
 * 21
 * </pre>
 */
public class FIBONACCI {
    private static long[] createFibonacciArray(int size) {
        if (size < 2) {
            size = 2;
        }

        long[] dp = new long[size];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < size; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine().trim());

            long[] dp = createFibonacciArray(n);

            System.out.println(dp[n-1]);
        }
    }
}
