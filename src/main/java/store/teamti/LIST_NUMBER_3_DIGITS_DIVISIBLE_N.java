import java.util.Scanner;

/**
 * Problem: Week 1 - List sequence of integers having 3 digits divisible by n
 *
 * <p>Description:</p>
 * Given a positive integer n, this class finds all three-digit integers
 * that are divisible by n.
 *
 * <p>Input:</p>
 * The input consists of:
 * <ul>
 *   <li>Line 1: Contains a positive integer n (1 <= n <= 999).</li>
 * </ul>
 *
 * <p>Output:</p>
 * The output will be a sequence of three-digit integers that are divisible by n,
 * with elements separated by SPACE characters.
 *
 * <p>Example:</p>
 * <pre>
 * Input:
 * 200
 *
 * Output:
 * 200 400 600 800
 * </pre>
 */
public class LIST_NUMBER_3_DIGITS_DIVISIBLE_N {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        if (n < 1 || n > 999) {
            return;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 100; i < 1000; i++) {
            if (i % n == 0) {
                if (!result.isEmpty()) {
                    result.append(" ");
                }
                result.append(i);
            }
        }

        System.out.println(result);

        scanner.close();
    }
}
