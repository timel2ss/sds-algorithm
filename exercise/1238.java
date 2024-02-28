import java.util.*;
import java.io.*;

public class Main {		
    private static final int INF = 987_654_321;

    private static int N, M;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<List<Node>> path = new ArrayList<>(N + 1);
        List<List<Node>> reversePath = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            path.add(new ArrayList<>());
            reversePath.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            path.get(A).add(new Node(B, T));
            reversePath.get(B).add(new Node(A, T));
        }

        int[] outDist = dijkstra(X, path);
        int[] inDist = dijkstra(X, reversePath);

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, inDist[i] + outDist[i]);
        }
        System.out.print(max);
    }

    private static int[] dijkstra(int start, List<List<Node>> path) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        Queue<Node> pq = new PriorityQueue<>(M);
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (dist[node.id] < node.cost) {
                continue;
            }

            for (Node adj : path.get(node.id)) {
                int distance = dist[node.id] + adj.cost;
                if (distance < dist[adj.id]) {
                    dist[adj.id] = distance;
                    pq.add(new Node(adj.id, distance));
                }
            }
        }
        return dist;
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