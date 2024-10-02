package store.teamti.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem: Week 1 - Extract Year, Month, Date from a String in YYYY-MM-DD format
 *
 * <p>Description:</p>
 * Given a date string formatted as YYYY-MM-DD, this class extracts the year, month,
 * and date components. The format requires YYYY to represent the year, MM to represent
 * the month (1 to 12), and DD to represent the date (1 to 31).
 *
 * <p>Input:</p>
 * The input consists of:
 * <ul>
 *   <li>Line 1: Contains a string s representing the date.</li>
 * </ul>
 *
 * <p>Output:</p>
 * If the input string s is not in the format YYYY-MM-DD, the output will be "INCORRECT".
 * Otherwise, the output will contain the year, month, and date separated by SPACE characters.
 *
 * <p>Example:</p>
 * <pre>
 * Input:
 * 2023-10-04
 *
 * Output:
 * 2023 10 4
 *
 * Input:
 * 2023-10-4
 *
 * Output:
 * INCORRECT
 *
 * Input:
 * 2023-10 04
 *
 * Output:
 * INCORRECT
 * </pre>
 */
public class EXTRACT_YYYY_MM_DD {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String dateString = reader.readLine().trim();
        String regex = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateString);

        if (matcher.matches()) {
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int date = Integer.parseInt(matcher.group(3));

            System.out.printf("%d %d %d%n", year, month, date);
        } else {
            System.out.println("INCORRECT");
        }

        reader.close();
    }
}
