import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Problem: Week 1 - Sum Array
 *
 * <p>Description:</p>
 * Given a sequence of integers a<sub>1</sub>, a<sub>2</sub>, ..., a<sub>n</sub>,
 * this class computes the sum Q of the elements in this sequence.
 *
 * <p>Input:</p>
 * The input consists of:
 * <ul>
 *   <li>Line 1: Contains an integer n (1 <= n <= 1,000,000), the number of elements in the sequence.</li>
 *   <li>Line 2: Contains n integers a<sub>1</sub>, a<sub>2</sub>, ..., a<sub>n</sub> (-10,000 <= a<sub>i</sub> <= 10,000).</li>
 * </ul>
 *
 * <p>Output:</p>
 * The output will be the value of Q, which is the sum of the elements in the sequence.
 *
 * <p>Example:</p>
 * <pre>
 * Input:
 * 4
 * 3 2 5 4
 *
 * Output:
 * 14
 * </pre>
 */
public class SUM_ARRAY {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        String[] elements = br.readLine().trim().split(" ");
        long sum = 0;

        for (int i = 0; i < n; i++) {
            sum += Integer.parseInt(elements[i]);
        }

        System.out.println(sum);
    }
}
