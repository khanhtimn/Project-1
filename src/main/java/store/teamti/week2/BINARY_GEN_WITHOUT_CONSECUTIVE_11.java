package store.teamti.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Program to generate all binary sequences of a specified length without consecutive "11" in lexicographic order.
 *
 * <p>Description:</p>
 * Given an integer n, this program outputs all possible binary sequences of length n
 * where no two adjacent characters are both '1'. Each valid binary sequence is printed
 * on a new line in lexicographic order.
 *
 * <p>Input:</p>
 * - A single integer n (1 <= n <= 20), representing the length of each binary sequence.
 *
 * <p>Output:</p>
 * - All binary sequences of length n without consecutive '1's, each sequence on a new line
 *   in lexicographic order.
 *
 * <p>Example:</p>
 *
 * <pre>
 * Input:
 * 3
 *
 * Output:
 * 000
 * 001
 * 010
 * 100
 * 101
 * </pre>
 *
 * <p>Note:</p>
 * This program generates each valid binary sequence by recursively constructing combinations
 * of '0's and '1's while checking to ensure no two adjacent '1's are included.
 */
public class BINARY_GEN_WITHOUT_CONSECUTIVE_11 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        br.close();

        generateBinarySequences(n, new StringBuilder(), false);
    }

    private static void generateBinarySequences(int n, StringBuilder sequence, boolean lastWasOne) {
        if (sequence.length() == n) {
            System.out.println(sequence);
            return;
        }

        sequence.append('0');
        generateBinarySequences(n, sequence, false);
        sequence.deleteCharAt(sequence.length() - 1);

        if (!lastWasOne) {
            sequence.append('1');
            generateBinarySequences(n, sequence, true);
            sequence.deleteCharAt(sequence.length() - 1);
        }
    }
}
