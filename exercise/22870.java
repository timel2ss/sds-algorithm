import java.util.*;
import java.io.*;

public class Main {	
    private static final int INF = 2_000_000_000;

    private static int N, M;
    private static Set<Integer> visited;
    private static List<List<Node>> graph;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new HashSet<>(N + 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[] distFromS = dijkstra(S);
        int[] distFromE = dijkstra(E);

        int curr = S; int prev = S;
        while (curr != E) {
            int min = Integer.MAX_VALUE;
            for (Node adj : graph.get(curr)) {
                if (adj.id == prev) {
                    continue;
                }
                if (distFromS[curr] + adj.cost + distFromE[adj.id] == distFromS[E]) {
                    min = Math.min(min, adj.id);
                }
            }
            prev = curr;
            curr = min;
            visited.add(curr);
        }
        System.out.print(distFromS[E] + dijkstra(E)[S]);
    }

    private static int[] dijkstra(int S) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[S] = 0;

        Queue<Node> pq = new PriorityQueue<>(M);
        pq.add(new Node(S, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (dist[node.id] < node.cost) {
                continue;
            }

            for (Node adj : graph.get(node.id)) {
                if (visited.contains(adj.id)) {
                    continue;
                }

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

        public Node(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(this.cost, node.cost);
        }
    }
}