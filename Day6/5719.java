import java.util.*;
import java.io.*;

public class Main {
    private static final int INF = 987_654_321;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());

            List<List<Node>> graph = new ArrayList<>(N + 1);
            List<List<Integer>> rm = new ArrayList<>(N + 1);
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
                rm.add(new ArrayList<>());
            }

            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());

                graph.get(U).add(new Node(V, P));
            }

            int[] dist = new int[N + 1];
            Arrays.fill(dist, INF);
            dist[S] = 0;

            Queue<Node> pq = new PriorityQueue<>(N + 1);
            pq.add(new Node(S, 0));

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
                        rm.get(adj.id).clear();
                        rm.get(adj.id).add(node.id);
                    } else if (distance == dist[adj.id]) {
                        rm.get(adj.id).add(node.id);
                    }
                }
            }

            boolean[][] isShortest = new boolean[N + 1][N + 1];
            Queue<Integer> queue = new ArrayDeque<>(N + 1);
            queue.add(D);

            while (!queue.isEmpty()) {
                int id = queue.poll();
                for (int adj : rm.get(id)) {
                    if (isShortest[adj][id]) {
                        continue;
                    }
                    isShortest[adj][id] = true;
                    queue.add(adj);
                }
            }

            dist = new int[N + 1];
            Arrays.fill(dist, INF);
            dist[S] = 0;

            pq = new PriorityQueue<>(N + 1);
            pq.add(new Node(S, 0));

            while (!pq.isEmpty()) {
                Node node = pq.poll();

                if (dist[node.id] < node.cost) {
                    continue;
                }

                for (Node adj : graph.get(node.id)) {
                    if (isShortest[node.id][adj.id]) {
                        continue;
                    }

                    int distance = dist[node.id] + adj.cost;
                    if (distance < dist[adj.id]) {
                        dist[adj.id] = distance;
                        pq.add(new Node(adj.id, distance));
                    }
                }
            }

            sb.append(dist[D] != INF ? dist[D] : -1).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
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
