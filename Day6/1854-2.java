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

        List<Queue<Integer>> dist = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            dist.add(new PriorityQueue<>(k + 1, Collections.reverseOrder()));
        }

        Queue<Node> pq = new PriorityQueue<>(n);
        pq.add(new Node(1, 0));
        dist.get(1).add(0);
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (Node adj : graph.get(node.id)) {
                int distance = node.cost + adj.cost;
                if (dist.get(adj.id).size() < k) {
                    dist.get(adj.id).add(distance);
                    pq.add(new Node(adj.id, distance));
                } else if (dist.get(adj.id).peek() > distance) {
                    dist.get(adj.id).poll();
                    dist.get(adj.id).add(distance);
                    pq.add(new Node(adj.id, distance));
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            sb.append(dist.get(i).size() == k ? dist.get(i).peek() : "-1").append('\n');
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
