import java.util.*;
import java.io.*;

public class Main {	
    private static final long INF = 1_000_000_000_000L;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        List<List<Node>> graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, 1, w));
            graph.get(b).add(new Node(a, 1, w));
        }

        long[][] dist = new long[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
        }

        for (int i = 0; i <= N; i++) {
            dist[S][i] = 0;
        }

        Queue<Node> pq = new PriorityQueue<>(M);
        pq.add(new Node(S, 0, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (dist[node.id][node.count] < node.cost) {
                continue;
            }

            for (Node adj : graph.get(node.id)) {
                int pathCount = node.count + adj.count;
                if (pathCount > N) {
                    continue;
                }

                long distance = dist[node.id][node.count] + adj.cost;
                if (distance < dist[adj.id][pathCount]) {
                    dist[adj.id][pathCount] = distance;
                    pq.add(new Node(adj.id, pathCount, distance));
                }
            }
        }

        int p = 0;
        for (int i = 0; i <= K; i++) {	
            long min = INF;
            for (int j = 1; j <= N; j++) {
                if (dist[D][j] == INF) {
                    continue;
                }
                dist[D][j] += j * p;
                min = Math.min(min, dist[D][j]);
            }
            if (i != K) {
                p = Integer.parseInt(br.readLine());
            }
            sb.append(min).append('\n');
        }


        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    static class Node implements Comparable<Node> {
        int id;
        int count;
        long cost;

        public Node(int id, int count, long cost) {
            this.id = id;
            this.count = count;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return Long.compare(this.cost, node.cost);
        }
    }
}