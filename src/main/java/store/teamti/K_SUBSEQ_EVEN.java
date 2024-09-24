package store.teamti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class K_SUBSEQ_EVEN {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nk = br.readLine().trim().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        int[] sequence = Arrays.stream(br.readLine().trim().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        long result = countEvenWeightSubsequences(sequence, n, k);

        System.out.println(result);
    }

    private static long countEvenWeightSubsequences(int[] sequence, int n, int k) {
        long count = 0;
        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += sequence[i];
        }

        if (sum % 2 == 0) {
            count++;
        }

        for (int i = k; i < n; i++) {
            sum = sum - sequence[i - k] + sequence[i];

            if (sum % 2 == 0) {
                count++;
            }
        }

        return count;
    }
}
