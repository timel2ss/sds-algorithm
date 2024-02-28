import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int[] parent;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];

        Queue<Edge> minHeap = new PriorityQueue<>(M, (x, y) -> x.cost - y.cost);
        Queue<Edge> maxHeap = new PriorityQueue<>(M, (x, y) -> y.cost - x.cost);
        while (M-- >= 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            Edge edge = new Edge(A, B, C);
            minHeap.add(edge);
            maxHeap.add(edge);
        }

        System.out.print(calc(minHeap) - calc(maxHeap));
    }

    private static int calc(Queue<Edge> pq) {
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        int result = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int rootA = find(edge.node1);
            int rootB = find(edge.node2);
            if (rootA != rootB) {
                parent[rootA] = rootB;
                if (edge.cost == 0) {
                    result++;
                }
            }
        }
        return result * result;
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static class Edge {
        int node1;
        int node2;
        int cost;

        Edge(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }
    }
}