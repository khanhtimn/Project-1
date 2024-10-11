package store.teamti.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Program to generate all binary sequences of a specified length in lexicographic order.
 *
 * <p>Description:</p>
 * Given an integer n, this program outputs all possible binary sequences of length n.
 * Each binary sequence is printed on a new line in lexicographic order, starting from 0 to 2^n - 1.
 *
 * <p>Input:</p>
 * - A single integer n (1 <= n <= 20), representing the length of each binary sequence.
 *
 * <p>Output:</p>
 * - All binary sequences of length n, each sequence on a new line in lexicographic order.
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
 * 011
 * 100
 * 101
 * 110
 * 111
 * </pre>
 *
 * <p>Note:</p>
 * This program generates each binary sequence in lexicographic order by incrementing
 * from 0 to the maximum binary value of n bits (2^n - 1) and printing each as an n-length
 * binary string.
 */
public class BINARY_SEQUENCE_GEN {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        br.close();

        generateBinarySequences(n);
    }

    private static void generateBinarySequences(int n) {
        int totalSequences = 1 << n;

        for (int i = 0; i < totalSequences; i++) {
            StringBuilder sequence = new StringBuilder();
            int num = i;

            for (int j = 0; j < n; j++) {
                sequence.append(num & 1);
                num >>= 1;
            }

            sequence.reverse();

            System.out.println(sequence);
        }
    }
}
