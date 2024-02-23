import java.util.*;
import java.io.*;

public class Main {	

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String l1 = br.readLine();
        String l2 = br.readLine();

        int N = l1.length();
        int M = l2.length();

        int[][] trace = new int[N + 1][M + 1]; // 1: diagonal, 2: left, 3: up
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (l1.charAt(i - 1) == l2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    trace[i][j] = 1;
                    continue;
                }
                if (dp[i - 1][j] < dp[i][j - 1]) {
                    dp[i][j] = dp[i][j - 1];
                    trace[i][j] = 2;
                } else {
                    dp[i][j] = dp[i - 1][j];
                    trace[i][j] = 3;
                }
            }
        }

        int currX = N;
        int currY = M;
        Deque<Character> queue = new ArrayDeque<>(1000);
        while (dp[currX][currY] != 0) {
            if (trace[currX][currY] == 1) {
                queue.push(l1.charAt(currX - 1));
                currX--; currY--;
            } else if (trace[currX][currY] == 2) {
                currY--;
            } else if (trace[currX][currY] == 3) {
                currX--;
            }
        }

        sb.append(dp[N][M]).append('\n');
        while (!queue.isEmpty()) {
            sb.append(queue.pop());
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}