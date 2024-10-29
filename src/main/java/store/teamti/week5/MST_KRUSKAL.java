package store.teamti.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <p>Description:</p>
 * Given a undirected connected graph G=(V,E) where V={1,…,N}. Each edge (u,v)∈E(u,v)∈E has weight w(u,v)w(u,v). Compute minimum spanning tree of G.
 * <p>Input:</p>
 * Line 1: N and M (1≤N,M≤10
 * 5
 * ) in which NN is the number of nodes and MM is the number of edges.
 * Line i+1 (i=1,…,M): contains 3 positive integers u, v, and w where w is the weight of edge (u,v)
 * <p>Output:</p>
 * Write the weight of the minimum spanning tree found.
 * <p>Example:</p>
 * <pre>
 * Input
 * 5 8
 * 1 2 1
 * 1 3 4
 * 1 5 1
 * 2 4 2
 * 2 5 1
 * 3 4 3
 * 3 5 3
 * 4 5 2
 * Output
 * 7
 * </pre>
 */
public class MST_KRUSKAL {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    static class UnionFind {
        int[] parent, rank;

        UnionFind(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int n) {
            if (parent[n] != n) {
                parent[n] = find(parent[n]);
            }
            return parent[n];
        }

        void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px == py) return;

            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else if (rank[px] > rank[py]) {
                parent[py] = px;
            } else {
                parent[py] = px;
                rank[px]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges.add(new Edge(u, v, w));
            }

            Collections.sort(edges);

            UnionFind uf = new UnionFind(N);

            long mstWeight = 0;
            int edgesUsed = 0;

            for (Edge edge : edges) {
                if (edgesUsed == N - 1) break;
                if (uf.find(edge.src) != uf.find(edge.dest)) {
                    mstWeight += edge.weight;
                    uf.union(edge.src, edge.dest);
                    edgesUsed++;
                }
            }

            System.out.println(mstWeight);

        } catch (IOException ignored) {
        }
    }
}
