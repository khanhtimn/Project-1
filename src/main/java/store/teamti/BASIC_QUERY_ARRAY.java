package store.teamti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Problem: Week 1 - Basic queries on array
 *
 * <p>Description:</p>
 * Given a sequence of integers a<sub>1</sub>, a<sub>2</sub>, ..., a<sub>n</sub>.
 * This class performs a sequence of queries over this sequence, including:
 * <ul>
 *   <li><b>find-max:</b> Returns the maximum element of the given sequence.</li>
 *   <li><b>find-min:</b> Returns the minimum element of the given sequence.</li>
 *   <li><b>sum:</b> Returns the sum of the elements of the given sequence.</li>
 *   <li><b>find-max-segment i j:</b> Returns the maximum element of the subsequence from index i to index j (i <= j).</li>
 * </ul>
 *
 * <p>Input:</p>
 * The first block contains the information about the given sequence with the following format:
 * <ul>
 *   <li>Line 1: contains a positive integer n (1 <= n <= 10000)</li>
 *   <li>Line 2: contains n integers a<sub>1</sub>, a<sub>2</sub>, ..., a<sub>n</sub> (-1000 <= a<sub>i</sub> <= 1000)</li>
 *   <li>The first block is terminated by a character *.</li>
 * </ul>
 * The second block contains a sequence of queries defined above, each query is in a line.
 * The second block is terminated by three characters ***.
 *
 * <p>Output:</p>
 * The result of each query is written in a corresponding line.
 *
 * <p>Example:</p>
 * <pre>
 * Input:
 * 5
 * 1 4 3 2 5
 * *
 * find-max
 * find-min
 * find-max-segment 1 3
 * find-max-segment 2 5
 * sum
 * ***
 *
 * Output:
 * 5
 * 1
 * 4
 * 5
 * 15
 * </pre>
 */
public class BASIC_QUERY_ARRAY {
    static class SegmentTree {
        private final int[] tree;
        private final int n;

        public SegmentTree(int[] arr) {
            n = arr.length;
            tree = new int[4 * n];
            build(arr, 0, 0, n - 1);
        }

        private void build(int[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
            } else {
                int mid = (start + end) / 2;
                build(arr, 2 * node + 1, start, mid);
                build(arr, 2 * node + 2, mid + 1, end);
                tree[node] = Math.max(tree[2 * node + 1], tree[2 * node + 2]);
            }
        }

        public int query(int left, int right) {
            return query(0, 0, n - 1, left, right);
        }

        private int query(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return Integer.MIN_VALUE;
            }
            if (left <= start && end <= right) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            int leftMax = query(2 * node + 1, start, mid, left, right);
            int rightMax = query(2 * node + 2, mid + 1, end, left, right);
            return Math.max(leftMax, rightMax);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.readLine();

        SegmentTree segTree = new SegmentTree(arr);
        int min = Arrays.stream(arr).min().orElseThrow();
        int max = Arrays.stream(arr).max().orElseThrow();
        long sum = Arrays.stream(arr).sum();

        String query;
        while (!(query = br.readLine()).equals("***")) {
            String[] parts = query.split(" ");
            switch (parts[0]) {
                case "find-max":
                    System.out.println(max);
                    break;
                case "find-min":
                    System.out.println(min);
                    break;
                case "sum":
                    System.out.println(sum);
                    break;
                case "find-max-segment":
                    int i = Integer.parseInt(parts[1]) - 1;
                    int j = Integer.parseInt(parts[2]) - 1;
                    System.out.println(segTree.query(i, j));
                    break;
            }
        }
    }
}