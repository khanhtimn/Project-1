package store.teamti.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Problem: Week 3 - Simulation Stack
 * <p>Description:</p>
 * Perform a sequence of operations over a stack, each element is an integer:
 * PUSH v: push a value v into the stack
 * POP: remove an element out of the stack and print this element to stdout (print NULL if the stack is empty)
 * <p>Input:</p>
 * Each line contains a command (operration) of type
 * PUSH  v
 * POP
 * <p>Output:</p>
 * Write the results of POP operations (each result is written in a line)
 * <p>Example:</p>
 * <pre>
 * Input:
 * PUSH 1
 * PUSH 2
 * PUSH 3
 * POP
 * POP
 * PUSH 4
 * PUSH 5
 * POP
 * #
 * Output
 * 3
 * 2
 * 5
 * </pre>
 */
public class STACK_SIM {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Integer> stack = new LinkedList<>();
        String line;

        while (!(line = br.readLine()).equals("#")) {
            String[] parts = line.split(" ");
            String operation = parts[0];

            switch (operation) {
                case "PUSH":
                    int value = Integer.parseInt(parts[1]);
                    stack.push(value);
                    break;
                case "POP":
                    if (stack.isEmpty()) {
                        System.out.println("NULL");
                    } else {
                        System.out.println(stack.pop());
                    }
                    break;
            }
        }
        br.close();
    }
}
