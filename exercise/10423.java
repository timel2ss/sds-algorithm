import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int[] parent;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Set<Integer> generator = new HashSet<>(K);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int index = Integer.parseInt(st.nextToken());
            generator.add(index);
        }

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        Queue<Edge> pq = new PriorityQueue<>(M);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Edge(u, v, w));
        }

        int result = 0;
        label: while (true) {
            Edge edge = pq.poll();
            int rootA = find(edge.node1);
            int rootB = find(edge.node2);
            if (generator.contains(rootA) && generator.contains(rootB)) {
                continue;
            }

            if (rootA != rootB) {
                if (generator.contains(rootA)) {
                    parent[rootB] = rootA;
                } else {
                    parent[rootA] = rootB;
                }
                result += edge.cost;
            }

            for (int i = 1; i <= N; i++) {
                if (!generator.contains(find(i))) {
                    continue label;
                }
            }
            break;
        }

        System.out.print(result);
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static class Edge implements Comparable<Edge> {
        int node1;
        int node2;
        int cost;

        Edge(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.cost, edge.cost);
        }
    }
}