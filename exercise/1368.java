import java.util.*;
import java.io.*;

public class Main {
    private static int[] parent;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Queue<Edge> pq = new PriorityQueue<>(N * N);
        for (int i = 1; i <= N; i++) {
            pq.add(new Edge(i, 0, Integer.parseInt(br.readLine())));
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int connectCost = Integer.parseInt(st.nextToken());
                if (i < j) {
                    pq.add(new Edge(i, j, connectCost));
                }
            }
        }

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int cnt = 0;
        int result = 0;
        while (cnt < N) {
            Edge edge = pq.poll();
            int rootA = find(edge.node1);
            int rootB = find(edge.node2);
            if (rootA != rootB) {
                parent[rootA] = rootB;
                result += edge.cost;
                cnt++;
            }
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