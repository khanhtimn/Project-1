package store.teamti.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Problem: Week 1 - List all numbers from 1 to n and its squares
 *
 * <p>Description:</p>
 * Given a positive integer n, this class prints numbers from 1 to n along with their squares.
 *
 * <p>Input:</p>
 * <ul>
 *     <li>Line 1: contains a positive integer n (1 <= n <= 100).</li>
 * </ul>
 *
 * <p>Output:</p>
 * For each number i from 1 to n, output the number i and its square i^2. Each output is on a new line.
 * <ul>
 *     <li>Each line contains i and i^2, separated by one SPACE character.</li>
 * </ul>
 *
 * <p>Example:</p>
 * <pre>
 * Input:
 * 3
 *
 * Output:
 * 1 1
 * 2 4
 * 3 9
 * </pre>
 *
 * <p>Constraints:</p>
 * <ul>
 *     <li>1 <= n <= 100.</li>
 * </ul>
 *
 * <p>Explanation:</p>
 * <ul>
 *     <li>For an input of 3, the program outputs:
 *     <pre>
 *     1 1
 *     2 4
 *     3 9
 *     </pre>
 *     It lists each number i from 1 to 3, followed by its square i^2.</li>
 * </ul>
 */
public class LIST_NBR_AND_SQUARE {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        if (n < 1 || n > 100) {
            return;
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(i + " " + (i * i));
        }
    }
}
