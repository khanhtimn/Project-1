package store.teamti.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * <p>Description:</p>
 * Cho dãy số nguyên A1, A2, . . . , An với mỗi số nguyên Ai kiểm tra xem có số Aj nào bằng Ai hay không với j<i.
 * <p>Input:</p>
 * Dòng đầu chứa số n (1≤n≤100,000)
 * Dòng hai chứa nn số nguyên A1, A2, ..., An (1≤Ai≤1000,000,000)
 * <p>Output:</p>
 * Ghi ra n dòng, dòng thứ i in ra 1 nếu tồn tại Aj=Ai với j<i, ngược lại in ra 0.
 * <p>Example:</p>
 * <pre>
 * input
 * 5
 * 1 4 3 1 4
 * output
 * 0
 * 0
 * 0
 * 1
 * 1
 * </pre>
 * */
public class IPMAC_SET_EXISTS {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine().trim());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            processArray(arr);

        } catch (IOException ignored) {
        }
    }

    private static void processArray(int[] arr) {
        HashSet<Integer> seen = new HashSet<>();

        System.out.println(0);
        seen.add(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            if (seen.contains(arr[i])) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
            seen.add(arr[i]);
        }
    }
}
