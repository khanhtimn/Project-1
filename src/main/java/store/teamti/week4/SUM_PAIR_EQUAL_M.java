package store.teamti.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

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
