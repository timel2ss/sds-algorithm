import java.util.*;
import java.io.*;

public class Main {
    private static final int MAX = 1000;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[] dp = new int[MAX + 1];
        dp[1] = 3;
        for (int i = 2; i <= MAX; i++) {
            int count = 0;
            for (int j = 0; j <= i; j++) {
                if (gcd(i, j) == 1) {
                    count += 2;
                }
            }
            dp[i] = dp[i - 1] + count;
        }
        
        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}