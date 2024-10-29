package store.teamti.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * <p>Description:</p>
 * Given a undirected graph =(V,E) in which V = {1,2,..,n} is the set of nodes. Write a program that visit nodes of G by a DFS (consider a lexicorgraphic order of nodes).
 * <p>Input:</p>
 * Line 1: contains 2 integers n and m (1 <= n,m <= 100000)
 * Line i+1: contains u and v which are two end-points of the ith edge
 *
 * <p>Output:</p>
 * Sequence of nodes visited by DFS
 * <p>Example:</p>
 * <pre>
 * Input
 * 7 12
 * 1 2
 * 1 3
 * 2 3
 * 2 4
 * 2 7
 * 3 5
 * 3 7
 * 4 5
 * 4 6
 * 4 7
 * 5 6
 * 5 7
 * Output
 * 1 2 3 5 4 6 7
 * </pre>
 */
public class DFS_LIST_ORDER_NODES {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static StringBuilder result;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
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

            dfs(1);

            System.out.println(result.toString().trim());

        } catch (IOException ignored) {
        }
    }

    private static void dfs(int node) {
        visited[node] = true;
        result.append(node).append(" ");

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
}
