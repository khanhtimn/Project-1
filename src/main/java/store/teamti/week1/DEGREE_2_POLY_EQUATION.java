package store.teamti.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Problem: Week 1 - Solve degree-2 polynomial equation
 *
 * <p>Description:</p>
 * Given a quadratic equation of the form ax^2 + bx + c = 0,
 * this class finds the solution(s) to the given equation.
 *
 * <p>Input:</p>
 * <ul>
 *     <li>Line 1 contains three integers a, b, and c representing the coefficients of the equation (ax^2 + bx + c = 0).</li>
 * </ul>
 *
 * <p>Output:</p>
 * <ul>
 *     <li>If the equation has no real solution, output "NO SOLUTION".</li>
 *     <li>If the equation has one solution x0, output x0 formatted to two decimal places (e.g., 1.00).</li>
 *     <li>If the equation has two distinct solutions x1 and x2, output the solutions x1 and x2 with x1 < x2, formatted to two decimal places (e.g., 2.00 5.00).</li>
 * </ul>
 *
 * <p>Constraints:</p>
 * <ul>
 *     <li>a, b, c are integers.</li>
 *     <li>Solutions should be rounded to two decimal places.</li>
 * </ul>
 *
 * <p>Example:</p>
 * <pre>
 * Input:
 * 1 1 8
 *
 * Output:
 * NO SOLUTION
 *
 * Input:
 * 1 -2 1
 *
 * Output:
 * 1.00
 *
 * Input:
 * 1 -7 10
 *
 * Output:
 * 2.00 5.00
 * </pre>
 *
 * <p>Explanation:</p>
 * <ul>
 *     <li>For the input "1 1 8", the discriminant is negative, so there are no real solutions ("NO SOLUTION").</li>
 *     <li>For the input "1 -2 1", the equation has one repeated solution (x0 = 1.00).</li>
 *     <li>For the input "1 -7 10", the equation has two distinct solutions x1 = 2.00 and x2 = 5.00.</li>
 * </ul>
 */
public class DEGREE_2_POLY_EQUATION {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().trim().split(" ");
        double a = Double.parseDouble(input[0]);
        double b = Double.parseDouble(input[1]);
        double c = Double.parseDouble(input[2]);

        solveQuadraticEquation(a, b, c);
    }

    private static void solveQuadraticEquation(double a, double b, double c) {
        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    System.out.println("INFINITE SOLUTIONS");
                } else {
                    System.out.println("NO SOLUTION");
                }
            } else {
                double x = -c / b;
                System.out.printf("%.2f%n", x);
            }
            return;
        }

        double discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            double x1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            System.out.printf("%.2f %.2f%n", Math.min(x1, x2), Math.max(x1, x2));
        } else if (discriminant == 0) {
            double x = -b / (2 * a);
            System.out.printf("%.2f%n", x);
        } else {
            System.out.println("NO SOLUTION");
        }
    }
}
