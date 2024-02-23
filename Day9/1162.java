import java.util.*;
import java.io.*;

public class Main {	
    private static final long INF = 1_000_000_000_000L;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<List<Node>> graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(node1).add(new Node(node2, 0, cost));
            graph.get(node1).add(new Node(node2, 1, 0));
            graph.get(node2).add(new Node(node1, 0, cost));
            graph.get(node2).add(new Node(node1, 1, 0));
        }

        long[][] dist = new long[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
        }

        for (int i = 0; i <= K; i++) {
            dist[1][i] = 0;
        }

        Queue<Node> pq = new PriorityQueue<>(M);
        pq.add(new Node(1, 0, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (dist[node.id][node.coverCnt] < node.cost) {
                continue;
            }

            for (Node adj : graph.get(node.id)) {
                int cover = node.coverCnt + adj.coverCnt;
                if (cover > K) {
                    continue;
                }

                long distance = dist[node.id][node.coverCnt] + adj.cost;
                if (distance < dist[adj.id][cover]) {
                    dist[adj.id][cover] = distance;
                    pq.add(new Node(adj.id, cover, distance));
                }
            }
        }

        long minTime = INF;
        for (int i = 0; i <= K; i++) {
            minTime = Math.min(minTime, dist[N][i]);
        }
        System.out.print(minTime);
    }

    static class Node implements Comparable<Node> {
        int id;
        int coverCnt;
        long cost;

        public Node(int id, int coverCnt, long cost) {
            this.id = id;
            this.coverCnt = coverCnt;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return Long.compare(this.cost, node.cost);
        }
    }
}