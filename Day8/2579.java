import java.util.*;
import java.io.*;

public class Main {	
    private static final int MAX_SIZE = 305;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] scores = new int[MAX_SIZE];
        for (int i = 1; i <= N; i++) {
            scores[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[MAX_SIZE];
        dp[1] = scores[1];
        dp[2] = scores[1] + scores[2];
        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + scores[i - 1]) + scores[i];
        }
        System.out.print(dp[N]);
    }
}