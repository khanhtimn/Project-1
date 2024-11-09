package store.teamti.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <p>Description:</p>
 * Given a directed graph G = (V, E) in which V = {1, 2, ..., n} is the set of nodes, and w(u,v) is the weight (length) of the arc(u,v). Compute d(u,v) - the length of the shortest path from u to v in G, for all u,v in V.
 * <p>Input:</p>
 * Line 1: contains 2 positive integers n and m (1 <= n,m <= 10000)
 * Line i+1 (i = 1, 2, ..., m): contains 3 positive integers u, v, w in which w is the weight of the arc (u,v) (1 <= w <= 1000)
 * <p>Output:</p>
 * Line i (i = 1, 2, ..., n): wirte the ith row of the matrix d (if there is not any path from node i to node j, then d(i,j) = -1)
 * <p>Example:</p>
 * <pre>
 * Input
 * 4 9
 * 1 2 9
 * 1 3 7
 * 1 4 2
 * 2 1 1
 * 2 4 5
 * 3 4 6
 * 3 2 2
 * 4 1 5
 * 4 2 8
 * Output
 * 0 9 7 2
 * 1 0 8 3
 * 3 2 0 5
 * 5 8 12 0
 * </pre>
 * */
public class ALL_PAIR_SHORTEST_PATH {
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] dist = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) {
                        dist[i][j] = 0;
                    } else {
                        dist[i][j] = INF;
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                dist[u][v] = w;
            }

            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (dist[i][k] != INF && dist[k][j] != INF) {
                            long newDist = (long)dist[i][k] + dist[k][j];
                            if (newDist < INF && newDist < dist[i][j]) {
                                dist[i][j] = (int)newDist;
                            }
                        }
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (j > 1) sb.append(" ");
                    sb.append(dist[i][j] == INF ? -1 : dist[i][j]);
                }
                sb.append("\n");
            }
            System.out.print(sb);

        } catch (IOException ignore) {
        }
    }
}
