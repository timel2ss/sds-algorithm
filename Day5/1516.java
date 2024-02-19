import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        List<List<int[]>> graph = new ArrayList<>(N);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        int[] minTime = new int[N + 1];
        int[] degree = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            minTime[i] += time;

            while (st.hasMoreTokens()) {
                int prev = Integer.parseInt(st.nextToken());
                if (prev == -1) {
                    break;
                }

                graph.get(prev).add(new int[] {i, time});
                degree[i]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>(N);
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int index = queue.poll();
            for (int[] adj : graph.get(index)) {
                minTime[adj[0]] = Math.max(minTime[adj[0]], minTime[index] + adj[1]);

                if (--degree[adj[0]] == 0) {
                    queue.add(adj[0]);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            sb.append(minTime[i]).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}