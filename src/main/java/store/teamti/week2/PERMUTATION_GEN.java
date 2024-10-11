package store.teamti.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Program to generate all permutations of integers from 1 to n in lexicographic order.
 *
 * <p>Description:</p>
 * Given an integer n, this program outputs all possible permutations of the sequence
 * 1, 2, ..., n. Each permutation is printed in lexicographic order, with elements of
 * each permutation separated by a single SPACE character.
 *
 * <p>Input:</p>
 * - A single integer n (1 <= n <= 9) representing the upper limit of the sequence to permute.
 *
 * <p>Output:</p>
 * - All permutations of the sequence 1 to n, each permutation on a new line.
 *
 * <p>Example:</p>
 *
 * <pre>
 * Input:
 * 3
 *
 * Output:
 * 1 2 3
 * 1 3 2
 * 2 1 3
 * 2 3 1
 * 3 1 2
 * 3 2 1
 * </pre>
 *
 * <p>Note:</p>
 * The program generates the permutations in lexicographic order to ensure all outputs
 * follow a natural sequence.
 */
public class PERMUTATION_GEN {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        br.close();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        do {
            printArray(arr);
        } while (nextPermutation(arr));
    }

    private static void printArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    private static boolean nextPermutation(int[] arr) {
        int i = arr.length - 2;

        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }

        if (i < 0) {
            return false;
        }

        int j = arr.length - 1;
        while (arr[j] <= arr[i]) {
            j--;
        }

        swap(arr, i, j);

        reverse(arr, i + 1, arr.length - 1);

        return true;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start++, end--);
        }
    }
}
