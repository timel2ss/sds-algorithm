import java.util.*;
import java.io.*;

public class Main {
    private static final int INF = 987_654_321;

    private static int n, m;
    private static List<List<Node>> graph;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph.get(a).add(new Node(b, d));
                graph.get(b).add(new Node(a, d));
            }

            int[] candidates = new int[t];
            for (int i = 0; i < t; i++) {
                candidates[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(candidates);

            int[] fromS = dijkstra(s);

            int entry = fromS[g] < fromS[h] ? h : g;

            int[] fromEntry = dijkstra(entry);

            for (int candidate : candidates) {
                if (fromS[candidate] != fromS[entry] + fromEntry[candidate]) {
                    continue;
                }
                sb.append(candidate).append(' ');
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        Queue<Node> pq = new PriorityQueue<>(m + 1);
        pq.add(new Node(start, 0));
        dist[start] = 0;

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
            return this.cost - node.cost;
        }
    }
}