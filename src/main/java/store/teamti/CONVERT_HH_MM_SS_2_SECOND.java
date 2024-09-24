package store.teamti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem: Week 1 - Convert hh:mm:ss to seconds
 *
 * <p>Description:</p>
 * Given a time moment which is a string under the format hh:mm:ss, this class converts the time
 * to the equivalent total number of seconds. The conversion is performed by calculating
 * seconds = hh * 3600 + mm * 60 + ss, where:
 * <ul>
 *   <li>hh (0 <= hh <= 23) represents the hours.</li>
 *   <li>mm (0 <= mm <= 59) represents the minutes.</li>
 *   <li>ss (0 <= ss <= 59) represents the seconds.</li>
 * </ul>
 *
 * <p>Input:</p>
 * <ul>
 *   <li>Line 1: contains a string s representing the time in the format hh:mm:ss.</li>
 * </ul>
 *
 * <p>Output:</p>
 * The program writes the equivalent time in seconds if the input is valid.
 * If the input is not under the format hh:mm:ss, the program writes "INCORRECT".
 *
 * <p>Example:</p>
 * <pre>
 * Input:
 * 13:05:26
 *
 * Output:
 * 47126
 * </pre>
 *
 * <pre>
 * Input:
 * 13:05:6
 *
 * Output:
 * INCORRECT
 * </pre>
 *
 * <pre>
 * Input:
 * 13:05 26
 *
 * Output:
 * INCORRECT
 * </pre>
 */
public class CONVERT_HH_MM_SS_2_SECOND {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            System.out.println("INCORRECT");
            return;
        }

        String timeString = line.trim();

        String regex = "^(\\d{2}):(\\d{2}):(\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(timeString);

        if (matcher.matches()) {
            int hh = Integer.parseInt(matcher.group(1));
            int mm = Integer.parseInt(matcher.group(2));
            int ss = Integer.parseInt(matcher.group(3));

            if (hh >= 0 && hh <= 23 && mm >= 0 && mm <= 59 && ss >= 0 && ss <= 59) {
                int totalSeconds = hh * 3600 + mm * 60 + ss;
                System.out.println(totalSeconds);
            } else {
                System.out.println("INCORRECT");
            }
        } else {
            System.out.println("INCORRECT");
        }
    }
}
