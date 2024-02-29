import java.util.*;
import java.io.*;

public class Main {
    private static final int MAX = 30;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[][] dp = new int[MAX + 1][MAX + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= MAX; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }
        
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            sb.append(dp[M][N]).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}