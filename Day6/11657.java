import java.util.*;
import java.io.*;

public class Main {	
    private static final int INF = 987_654_321;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>(M);

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edges.add(new Edge(A, B, C));
        }

        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        boolean hasNegCycle = false;
        for (int i = 0; i < N; i++) {
            for (Edge edge : edges) {
                if (dist[edge.from] == INF) {
                    continue;
                }

                long distance = dist[edge.from] + edge.cost;
                if (distance < dist[edge.to]) {
                    dist[edge.to] = distance;
                    if (i == N - 1) {
                        hasNegCycle = true;
                        break;
                    }
                }
            }
        }

        if (hasNegCycle) {
            sb.append("-1");
        } else {
            for (int i = 2; i <= N; i++) {
                sb.append(dist[i] != INF ? dist[i] : -1).append('\n');
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Edge {
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
