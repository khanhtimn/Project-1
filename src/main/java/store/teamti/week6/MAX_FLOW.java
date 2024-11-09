package store.teamti.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>Description:</p>
 * Given a network G = (V, E) which is a directed weighted graph. Node s is the source and node t is the target. c(u,v) is the capacity of the arc (u,v). Find the maximum flow on G.
 * <p>Input:</p>
 * •Line 1: two positive integers N and M (1 <= N <= 10
 * 4
 * , 1 <= M <= 10
 * 6
 * )
 * •Line 2: contains 2 positive integers s and t
 * •Line i+2 (I = 1,. . ., M): contains two positive integers u and v which are endpoints of i
 * th
 *  arc
 * <p>Output:</p>
 *   Write the value of the max-flow found
 * <p>Example:</p>
 * <pre>
 * Input
 * 7 12
 * 6 7
 * 1 7 7
 * 2 3 6
 * 2 5 6
 * 3 1 6
 * 3 7 11
 * 4 1 7
 * 4 2 4
 * 4 5 5
 * 5 1 4
 * 5 3 4
 * 6 2 8
 * 6 4 10
 * Output
 * 17
 * </pre>
 * */
public class MAX_FLOW {
    static class Edge {
        int v, u;
        long capacity;
        int rev; // Index of reverse edge

        public Edge(int v, int u, long capacity, int rev) {
            this.v = v;
            this.u = u;
            this.capacity = capacity;
            this.rev = rev;
        }
    }

    static ArrayList<Edge>[] graph;
    static int N, M, source, target;
    static int[] level, ptr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().trim().split(" ");
        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);

        String[] st = br.readLine().trim().split(" ");
        source = Integer.parseInt(st[0]);
        target = Integer.parseInt(st[1]);

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            String[] edge = br.readLine().trim().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            long c = edge.length > 2 ? Long.parseLong(edge[2]) : 1;

            addEdge(u, v, c);
        }

        System.out.println(maxFlow());

        br.close();
    }

    static void addEdge(int u, int v, long capacity) {
        Edge forward = new Edge(v, u, capacity, graph[v].size());
        Edge reverse = new Edge(u, v, 0, graph[u].size());

        graph[u].add(forward);
        graph[v].add(reverse);
    }

    static boolean bfs() {
        level = new int[N + 1];
        Arrays.fill(level, -1);
        level[source] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (Edge edge : graph[u]) {
                if (level[edge.v] < 0 && edge.capacity > 0) {
                    level[edge.v] = level[u] + 1;
                    queue.offer(edge.v);
                }
            }
        }

        return level[target] >= 0;
    }

    static long dfs(int u, long flow) {
        if (u == target) {
            return flow;
        }

        for (; ptr[u] < graph[u].size(); ptr[u]++) {
            Edge edge = graph[u].get(ptr[u]);

            if (level[edge.v] == level[u] + 1 && edge.capacity > 0) {
                long pushed = dfs(edge.v, Math.min(flow, edge.capacity));

                if (pushed > 0) {
                    edge.capacity -= pushed;
                    graph[edge.v].get(edge.rev).capacity += pushed;
                    return pushed;
                }
            }
        }

        return 0;
    }

    static long maxFlow() {
        long flow = 0;

        while (bfs()) {
            ptr = new int[N + 1];
            long pushed;

            while ((pushed = dfs(source, Long.MAX_VALUE)) > 0) {
                flow += pushed;
            }
        }

        return flow;
    }
}