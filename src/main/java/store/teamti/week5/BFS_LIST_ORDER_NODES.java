package store.teamti.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <p>Description:</p>
 * Given undirected graph G = (V,E) in which V = {1, 2, ..., n} is the set of nodes, and E is the set of m edges.
 * Write a program that computes the sequence of nodes visited using a BFS algorithm (the nodes are considered in a lexicographic order)
 * <p>Input:</p>
 * Line 1: contains 2 integers n and m (1 <= n,m <= 100000)
 * Line i+1: contains u and v which are two end-points of the ith edge
 *
 * <p>Output:</p>
 Write the sequence of nodes visited by a BFS procedure (nodes a are separated by a SPACE character)
 * <p>Example:</p>
 * <pre>
 * Input
 * 6 7
 * 2 4
 * 1 3
 * 3 4
 * 5 6
 * 1 2
 * 3 5
 * 2 3
 * Output
 * 1 2 3 5 4 6
 * </pre>
 */
public class BFS_LIST_ORDER_NODES {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static StringBuilder result;
    static int n;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            for (int i = 1; i <= n; i++) {
                Collections.sort(graph[i]);
            }

            visited = new boolean[n + 1];
            result = new StringBuilder();

            completeBFS();

            System.out.println(result.toString().trim());

        } catch (IOException ignored) {
        }
    }

    private static void completeBFS() {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.append(current).append(" ");

            for (int neighbor : graph[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }

            // If queue is empty but there are still unvisited nodes,
            // find the next unvisited node and continue BFS
            if (queue.isEmpty()) {
                for (int i = 1; i <= n; i++) {
                    if (!visited[i]) {
                        visited[i] = true;
                        queue.add(i);
                        break;
                    }
                }
            }
        }
    }
}