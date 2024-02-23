import java.util.*;
import java.io.*;

public class Main {	

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String l1 = br.readLine();
        String l2 = br.readLine();

        int N = l1.length();
        int M = l2.length();

        int max = 0;
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (l1.charAt(i - 1) == l2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        System.out.print(max);
    }
}