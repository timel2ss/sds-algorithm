import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Node {
        int from;
        int to;
        int cost;

        Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static int N, M; // N : 도시의 개수, M : 버스의 개수
    public static ArrayList<Node> adj;
    public static long[] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adj = new ArrayList<>();
        DP = new long[N + 1];
        Arrays.fill(DP, Integer.MAX_VALUE);

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // 시작도시
            int B = Integer.parseInt(st.nextToken()); // 도착도시
            int C = Integer.parseInt(st.nextToken()); // 시간
            adj.add(new Node(A, B, C));
        }
        boolean checked = false;
        DP[1] = 0;
        for (int i = 1; i <= N; i++) {
            for (Node e : adj) {
                int start = e.from;
                int end = e.to;
                int time = e.cost;
                if (DP[start] != Integer.MAX_VALUE && DP[start] + time < DP[end]) {
                    DP[end] = DP[start] + time;

                    if (i == N) // 음수 싸이클 있는것
                        checked = true;
                }
            }
        }

        if (checked)
            bw.write("-1\n");
        else {
            for (int i = 2; i <= N; i++) {
                if (DP[i] == Integer.MAX_VALUE)
                    bw.write("-1\n");
                else
                    bw.write(DP[i] + "\n");
            }

            bw.flush();
            bw.close();
        }
    }
}
