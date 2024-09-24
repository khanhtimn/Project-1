package store.teamti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Problem: Week 1 - Count Words
 *
 * <p>Description:</p>
 * This class reads a text input and counts the number of words in the text.
 * Words are considered sequences of characters separated by any whitespace
 * (spaces, tabs, or line breaks).
 *
 * <p>Input:</p>
 * The input consists of multiple lines of text.
 *
 * <p>Output:</p>
 * The program outputs the number of words in the input text. Words are defined
 * as non-empty sequences of characters separated by spaces, tabs, or line breaks.
 *
 * <p>Example:</p>
 * <pre>
 * Input:
 * Hanoi University Of Science and Technology
 * School of Information and Communication Technology
 *
 * Output:
 * 12
 * </pre>
 */
public class COUNT_WORD {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder text = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            text.append(line).append(" ");
        }

        String[] words = text.toString().trim().split("\\s+");

        int wordCount = words.length == 1 && words[0].isEmpty() ? 0 : words.length;

        System.out.println(wordCount);
    }
}
