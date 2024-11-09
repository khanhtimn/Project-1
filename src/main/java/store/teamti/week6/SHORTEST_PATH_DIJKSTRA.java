package store.teamti.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * <p>Description:</p>
 * Given a directed graph G = (V,E) in which V = {1,2,...,n) is the set of nodes. Each arc (u,v) has a non-negative weight w(u,v). Given two nodes s and t of G. Find the shortest path from s to t on G.
 * <p>Input:</p>
 * Line 1: contains two integers n and m which are the number of nodes and the number of arcs of G (1 <= n <= 100000)
 * Line i + 1(i = 1,2,...,m): contains 3 integers u, v, w in which w is the weight of arc(u,v) (0 <= w <= 100000)
 * Line m+2: contains two integers s and t
 * <p>Output:</p>
 * Write the weight of the shortest path found or write -1 if no path from s to t was found
 * <p>Example:</p>
 * <pre>
 * Input
 * 5 7
 * 2 5 87
 * 1 2 97
 * 4 5 78
 * 3 1 72
 * 1 4 19
 * 2 3 63
 * 5 1 18
 * 1 5
 *
 * Output
 * 97
 * </pre>
 * */
public class SHORTEST_PATH_DIJKSTRA {
    static class Edge {
        int to;
        long weight;

        Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex;
        long distance;

        Node(int vertex, long distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.distance, other.distance);
        }
    }

    static ArrayList<Edge>[] graph;
    static int n, m;
    static long[] dist;
    static boolean[] visited;
    static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().trim().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().trim().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            long w = Long.parseLong(edge[2]);

            graph[u].add(new Edge(v, w));
        }

        String[] st = br.readLine().trim().split(" ");
        int source = Integer.parseInt(st[0]);
        int target = Integer.parseInt(st[1]);

        long result = dijkstra(source, target);
        System.out.println(result == INF ? -1 : result);

        br.close();
    }

    static long dijkstra(int source, int target) {
        dist = new long[n + 1];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        visited = new boolean[n + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            if (visited[u]) continue;

            visited[u] = true;

            if (u == target) {
                return dist[u];
            }

            for (Edge edge : graph[u]) {
                int v = edge.to;
                long weight = edge.weight;

                if (visited[v]) continue;

                if (dist[u] != INF && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }

        return INF;
    }
}
