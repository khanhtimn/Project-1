package store.teamti.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Problem: Week 1 - Count odd and even numbers from a sequence
 *
 * <p>Description:</p>
 * Given a sequence of integers a<sub>1</sub>, a<sub>2</sub>, ..., a<sub>n</sub>, this class counts the number of odd
 * and even elements in the sequence.
 *
 * <p>Input:</p>
 * <ul>
 *   <li>Line 1: contains a positive integer n (1 <= n <= 100000) representing the number of elements in the sequence.</li>
 *   <li>Line 2: contains n integers a<sub>1</sub>, a<sub>2</sub>, ..., a<sub>n</sub> (1 <= a<sub>i</sub> <= 1000000) representing the sequence.</li>
 * </ul>
 *
 * <p>Output:</p>
 * The program outputs two integers separated by a SPACE character:
 * <ul>
 *   <li>The first integer represents the number of odd elements.</li>
 *   <li>The second integer represents the number of even elements.</li>
 * </ul>
 *
 * <p>Example:</p>
 * <pre>
 * Input:
 * 6
 * 2 3 4 3 7 1
 *
 * Output:
 * 4 2
 * </pre>
 *
 * <p>Constraints:</p>
 * <ul>
 *   <li>1 <= n <= 100000</li>
 *   <li>1 <= a<sub>i</sub> <= 1000000</li>
 * </ul>
 *
 * <p>Explanation:</p>
 * <ul>
 *   <li>For an input sequence of 6 elements: {2, 3, 4, 3, 7, 1}, there are 4 odd numbers (3, 3, 7, 1) and 2 even numbers (2, 4).</li>
 * </ul>
 */
public class COUNT_ODD_EVEN_ELEMENTS_FROM_SEQ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            System.out.println();
            return;
        }

        int n = Integer.parseInt(line.trim());

        line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            System.out.println();
            return;
        }

        String[] numbers = line.trim().split(" ");
        int oddCount = 0;
        int evenCount = 0;

        for (int i = 0; i < n; i++) {
            if ((Integer.parseInt(numbers[i]) & 1) == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        System.out.println(oddCount + " " + evenCount);
    }
}
