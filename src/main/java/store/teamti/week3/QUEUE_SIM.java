package store.teamti.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>Description:</p>
 * Perform a sequence of operations over a queue, each element is an integer:
 * PUSH v: push a value v into the queue
 * POP: remove an element out of the queue and print this element to stdout (print NULL if the queue is empty)
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
 * 1
 * 2
 * 3
 *
 * Input
 * PUSH 1
 * POP
 * POP
 * PUSH 4
 * POP
 * #
 * Output
 * 1
 * NULL
 * 4
 * </pre>
 */
public class QUEUE_SIM {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> queue = new LinkedList<>();
        String line;

        while (!(line = br.readLine()).equals("#")) {
            String[] parts = line.split(" ");
            String operation = parts[0];

            switch (operation) {
                case "PUSH":
                    int value = Integer.parseInt(parts[1]);
                    queue.offer(value);
                    break;
                case "POP":
                    if (queue.isEmpty()) {
                        System.out.println("NULL");
                    } else {
                        System.out.println(queue.poll());
                    }
                    break;
            }
        }
        br.close();
    }
}
