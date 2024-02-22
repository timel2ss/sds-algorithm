import java.util.*;
import java.io.*;

public class Main {		
    private static int N, K;
    private static int[] depth;
    private static int[][] parent;
    private static List<List<Integer>> tree;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        int count = N;
        K = -1;
        while (count > 0) {
            K++;
            count >>= 1;
        }

        tree = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            tree.get(node1).add(node2);
            tree.get(node2).add(node1);
        }

        parent = new int[N + 1][K + 1];
        depth = new int[N + 1];
        Arrays.fill(depth, -1);

        bfs();
        fillParent();

        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            sb.append(lca(node1, node2)).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static void bfs() {
        Queue<Node> queue = new ArrayDeque<>(N);
        queue.add(new Node(1, 0));
        depth[1] = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int adj : tree.get(node.id)) {
                if (depth[adj] != -1) {
                    continue;
                }
                depth[adj] = depth[node.id] + 1;
                parent[adj][0] = node.id;
                queue.add(new Node(adj, depth[adj]));
            }
        }
    }

    private static void fillParent() {
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }

    private static int lca(int node1, int node2) {
        if (depth[node1] < depth[node2]) {
            int temp = node1;
            node1 = node2;
            node2 = temp;
        }

        for (int i = K; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[node1] - depth[node2]) {
                node1 = parent[node1][i];
            }
        }

        if (node1 == node2) {
            return node1;
        }

        for (int i = K; i >= 0; i--) {
            if (parent[node1][i] != parent[node2][i]) {
                node1 = parent[node1][i];
                node2 = parent[node2][i];
            }
        }
        return parent[node1][0];

    }

    static class Node {
        int id;
        int depth;

        Node(int id, int depth) {
            this.id = id;
            this.depth = depth;
        }
    }
}