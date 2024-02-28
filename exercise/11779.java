import java.util.*;
import java.io.*;

public class Main {	
    private static final int INF = 987_654_321;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<List<Node>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end= Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        Queue<Node> pq = new PriorityQueue<>(m);
        int[] prev = new int[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        pq.add(new Node(S, 0));
        dist[S] = 0;

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
                    prev[adj.id] = node.id;
                }
            }
        }

        sb.append(dist[D]).append('\n');

        int curr = D;
        Deque<Integer> stack = new ArrayDeque<>(n);
        while (curr != S) {
            stack.push(curr);
            curr = prev[curr];
        }
        stack.push(S);

        sb.append(stack.size()).append('\n');
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(' ');
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
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