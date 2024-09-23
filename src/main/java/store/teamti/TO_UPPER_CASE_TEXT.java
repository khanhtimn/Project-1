import java.util.*;

/**
 * Problem: Week 1 - Convert a TEXT to Upper-Case
 *
 * <p>Description:</p>
 * Given a TEXT input, this class provides functionality to convert the entire TEXT to upper-case characters.
 *
 * <p>Input:</p>
 * The input consists of:
 * <ul>
 *   <li>The TEXT that needs to be converted to upper-case.</li>
 * </ul>
 *
 * <p>Output:</p>
 * The output will be the same TEXT in which all characters are converted to upper-case.
 *
 * <p>Example:</p>
 * <pre>
 * Input:
 * Hello John,
 * How are you?
 *
 * Bye,
 *
 * Output:
 * HELLO JOHN,
 * HOW ARE YOU?
 *
 * BYE,
 * </pre>
 */
public class TO_UPPER_CASE_TEXT {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            StringBuilder text = new StringBuilder();
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine()).append(System.lineSeparator());
            }

            String upperCaseText = text.toString().toUpperCase();

            System.out.print(upperCaseText);
        }
    }
}
