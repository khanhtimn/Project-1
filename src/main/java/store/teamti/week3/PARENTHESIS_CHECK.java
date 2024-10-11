package store.teamti.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * <p><b>Description:</b></p>
 * Given a string containing only characters '(', ')', '[', ']', '{', '}', determine if the sequence
 * of brackets is correct. A sequence is correct if each opening bracket has a corresponding
 * closing bracket in the proper order, with no mismatches.
 *
 * <p><b>Input:</b></p>
 * The input consists of a single line containing a string with up to 10^6 characters.
 *
 * <p><b>Output:</b></p>
 * Outputs '1' if the sequence of brackets is correct, and '0' if it is incorrect.
 *
 * <p><b>Example:</b></p>
 * <pre>
 * Input: ([]{()}()[])
 * Output: 1
 *
 * Input: ([]{()]()[])
 * Output: 0
 * </pre>
 */
public class PARENTHESIS_CHECK {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().trim();
        br.close();

        System.out.println(isValidBracketSequence(input) ? 1 : 0);
    }

    private static boolean isValidBracketSequence(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            switch (ch) {
                case '(':
                case '[':
                case '{':
                    stack.push(ch);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                    break;
                default:
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
