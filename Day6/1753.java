import java.util.*;
import java.io.*;

public class Main {
    private static final int INF = 987_654_321;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<List<Node>> graph = new ArrayList<>(V + 1);
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        int K = Integer.parseInt(br.readLine());
        while (E-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        int[] dist = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            dist[i] = INF;
        }
        dist[K] = 0;

        Queue<Node> pq = new PriorityQueue<>(V);
        pq.add(new Node(K, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (dist[node.id] < node.cost) {
                continue;
            }

            for (Node adj : graph.get(node.id)) {
                int distance = dist[node.id] + adj.cost;
                if (distance < dist[adj.id]) {
                    dist[adj.id] = distance;
                    pq.add(new Node(adj.id, distance));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            sb.append(dist[i] != INF ? dist[i] : "INF").append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Node implements Comparable<Node> {
        int id;
        int cost;

        Node(int node, int cost) {
            this.id = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node edge) {
            return this.cost - edge.cost;
        }	
    }
}
