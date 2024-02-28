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

        Position[] positions = new Position[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            positions[i] = new Position(X, Y);
        }

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int cnt = N - 1;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int rootA = find(A);
            int rootB = find(B);
            if (rootA != rootB) {
                parent[rootA] = rootB;
                cnt--;
            }
        }

        Queue<Edge> pq = new PriorityQueue<>(N * N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int diffX = positions[i].x - positions[j].x;
                int diffY = positions[i].y - positions[j].y;
                double distance = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
                pq.add(new Edge(i + 1, j + 1, distance));
            }
        }

        double result = 0;
        while (cnt > 0) {
            Edge edge = pq.poll();
            int rootA = find(edge.node1);
            int rootB = find(edge.node2);
            if (rootA != rootB) {
                parent[rootA] = rootB;
                result += edge.cost;
                cnt--;
            }
        }

        System.out.print(String.format("%.2f", result));
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int node1;
        int node2;
        double cost;

        Edge(int node1, int node2, double cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge edge) {
            return Double.compare(this.cost, edge.cost);
        }
    }
}