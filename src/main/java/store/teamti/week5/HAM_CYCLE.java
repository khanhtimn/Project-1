package store.teamti.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <p>Description:</p>
 * Given an undirected graph G = (V,E). Write a program to check if G is a Hamiltonian graph.
 * <p>Input:</p>
 * Line 1: a positive integer T (number of graphs)
 * Subsequent lines are information about T graphs, each has the following format:
 * Line 1: n and m (number of nodes and edges)
 * Line i+1 (i = 1, 2, ..., m): u and v : two end points of the ith edge
 * <p>Output:</p>
 * In the i
 * th
 *  line, write 1 if the corresponding is a Hamiltonian graph, and write 0, otherwise
 * <p>Example:</p>
 * <pre>
 * Input
 * 2
 * 5 5
 * 1 2
 * 1 3
 * 2 4
 * 2 5
 * 3 5
 * 7 13
 * 1 3
 * 1 5
 * 1 7
 * 2 4
 * 2 5
 * 2 6
 * 3 4
 * 3 5
 * 3 7
 * 4 6
 * 4 7
 * 5 7
 * 6 7
 *
 * Output
 * 0
 * 1
 * </pre>
 * */
public class HAM_CYCLE {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int n;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine().trim());

            for (int t = 0; t < T; t++) {
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

                System.out.println(isHamiltonian() ? 1 : 0);
            }

        } catch (IOException ignored) {
        }
    }

    private static boolean isHamiltonian() {
        for (int start = 1; start <= n; start++) {
            visited = new boolean[n + 1];
            visited[start] = true;

            if (hamiltonianUtil(start, start, 1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hamiltonianUtil(int current, int start, int count) {
        if (count == n && graph[current].contains(start)) {
            return true;
        }

        for (int next : graph[current]) {
            if (!visited[next]) {
                visited[next] = true;

                if (hamiltonianUtil(next, start, count + 1)) {
                    return true;
                }

                visited[next] = false;
            }
        }

        return false;
    }
}
