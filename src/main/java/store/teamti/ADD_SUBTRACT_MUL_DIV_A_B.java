package store.teamti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Problem: Week 1 - Basic arithmetic operations on two integers
 *
 * <p>Description:</p>
 * Given two integers a and b, this class computes the following arithmetic operations:
 * <ul>
 *   <li><b>a + b:</b> The sum of a and b.</li>
 *   <li><b>a - b:</b> The difference of a and b.</li>
 *   <li><b>a * b:</b> The product of a and b.</li>
 *   <li><b>a / b:</b> The quotient of a divided by b.</li>
 * </ul>
 *
 * <p>Input:</p>
 * The input consists of:
 * <ul>
 *   <li>Line 1: Contains two integers a and b (1 <= a, b <= 1000).</li>
 * </ul>
 *
 * <p>Output:</p>
 * The result will be the four computed values: a + b, a - b, a * b, and a / b,
 * all separated by SPACE characters.
 *
 * <p>Example:</p>
 * <pre>
 * Input:
 * 9 4
 *
 * Output:
 * 13 5 36 2
 * </pre>
 */
public class ADD_SUBTRACT_MUL_DIV_A_B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] input = reader.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);

            System.out.printf("%d %d %d %d%n", a + b, a - b, a * b, a / b);
        }
    }
}
