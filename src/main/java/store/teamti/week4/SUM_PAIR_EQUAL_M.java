package store.teamti.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description:</p>
 * Cho dãy a1, a2, ..., an trong đó các phần tử đôi một khác nhau và 1 giá trị nguyên dương M. Hãy đếm số Q các cặp (i,j) sao cho 1 <= i < j <= n và ai + aj = M.
 *
 * <p>Input:</p>
 * Dòng 1: ghi n và M (1 <= n, M <= 1000000)
 * Dòng 2: ghi a1, a2, ..., an
 * <p>Output:</p>
 * Ghi ra giá trị Q
 * <p>Example:</p>
 * <pre>
 * Dữ liệu
 * 5 6
 * 5 2 1 4 3
 * Kết quả
 * 2
 * </pre>
 * */
public class SUM_PAIR_EQUAL_M {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().trim().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        String[] input = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        Set<Integer> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            int complement = k - arr[i];
            if (set.contains(complement)) {
                count++;
            }
            set.add(arr[i]);
        }
        System.out.println(count);
    }
}
