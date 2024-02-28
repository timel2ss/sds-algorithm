import java.util.*;
import java.io.*;

public class Main {
    private static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        List<List<Node>> graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(node1).add(new Node(node2, cost));
            graph.get(node2).add(new Node(node1, cost));
        }

        int[] shame = new int[N + 1];
        Arrays.fill(shame, Integer.MAX_VALUE);
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);

        Queue<Node> pq = new PriorityQueue<>(M);
        pq.add(new Node(A, Integer.MAX_VALUE));
        dist[A] = 0;
        shame[A] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (shame[node.id] < node.cost && node.id != A) {
                continue;
            }

            for (Node adj : graph.get(node.id)) {
                long distance = dist[node.id] + adj.cost;
                if (distance > C) {
                    continue;
                }

                int val = Math.max(shame[node.id], adj.cost);
                if (val < shame[adj.id]) {
                    shame[adj.id] = val;
                    dist[adj.id] = distance;
                    pq.add(new Node(adj.id, val));
                }
            }
        }

        System.out.print(shame[B] != Integer.MAX_VALUE ? shame[B] : -1);
    }

    static class Node implements Comparable<Node> {
        int id;
        int cost;

        Node(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(this.cost, node.cost);
        }
    }
}