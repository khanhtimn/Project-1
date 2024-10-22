package store.teamti.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <p>Description:</p>
 * Given a string s[1…k] which is a sequence of characters taken from {‘a’, . . ., ‘z’}. Given a positive integer m, the hash code of s is defined by the formula:
 * H(s) =  (s[1]*256
 * k-1
 *  + s[2]*256
 * k-2
 *  + . . . + s[k]*256
 * 0
 * ) mod m  (the contant integer m is a parameter)
 * Given a sequence of strings k1, k2, …, kn, compute the corresponding hash codes
 * <p>Input:</p>
 * Line 1: n and m (1 <= n,m <= 100000)
 * Line i+1 (i = 1,2,…,n): contains the string ki (the length of each string is less than or equal to 200)
 * <p>Output:</p>
 * Each line contains the corresponding hash code of n given strings
 * <p>Example:</p>
 * <pre>
 * Input
 * 4 1000
 * a
 * ab
 * abc
 * abcd
 * Output
 * 97
 * 930
 * 179
 * 924
 * </pre>
 * */
public class HASH_FUNCTION_STRING {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n; i++) {
                String s = br.readLine().trim();
                System.out.println(computeHash(s, m));
            }

        } catch (IOException ignored) {
        }
    }

    private static long computeHash(String s, int m) {
        int k = s.length();
        long result = 0;
        long power = 1;

        for (int i = k - 1; i >= 0; i--) {
            result = (result + ((s.charAt(i) * power) % m)) % m;

            power = (power * 256) % m;
        }

        return result;
    }
}
