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

        List<List<Node>> graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.get(V).add(new Node(U, C));
        }

        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);

        Queue<Node> pq = new PriorityQueue<>(M);

        st = new StringTokenizer(br.readLine());
        while (K-- > 0) {
            int index = Integer.parseInt(st.nextToken());
            dist[index] = 0;
            pq.add(new Node(index, 0));
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (dist[node.id] < node.cost) {
                continue;
            }

            for (Node adj : graph.get(node.id)) {
                long distance = dist[node.id] + adj.cost;
                if (distance < dist[adj.id]) {
                    dist[adj.id] = distance;
                    pq.add(new Node(adj.id, distance));
                }

            }
        }

        int index = -1;
        long maxDist = -1;
        for (int i = 1; i <= N; i++) {
            if (maxDist < dist[i] && dist[i] != INF) {
                maxDist = dist[i];
                index = i;
            }
        }

        sb.append(index).append('\n').append(maxDist);

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    static class Node implements Comparable<Node> {
        int id;
        long cost;

        Node(int id, long cost) {
            this.id = id;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return Long.compare(this.cost, node.cost);
        }
    }
}