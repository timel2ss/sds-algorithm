import java.util.*;
import java.io.*;

public class Main {	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<List<Node>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
        }

        int[] count = new int[n + 1];
        int[] dist = new int[n + 1];

        Queue<Node> pq = new PriorityQueue<>(n);
        pq.add(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (count[node.id] == k) {
                continue;
            }

            dist[node.id] = node.cost;
            count[node.id]++;

            for (Node adj : graph.get(node.id)) {
                int distance = dist[node.id] + adj.cost;
                pq.add(new Node(adj.id, distance));
            }
        }

        for (int i = 1; i <= n; i++) {
            sb.append(count[i] == k ? dist[i] : "-1").append('\n');
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
