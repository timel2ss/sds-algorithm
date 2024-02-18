import java.util.*;
import java.io.*;

public class Main {
    private static int N, M, K;
    private static int[][] pascal;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        pascal = new int[N + M + 1][N + M + 1];
        init();

        while (true) {
            int startsWithA = pascal[N + M - 1][M];
            int startsWithB = pascal[N + M - 1][N];

            if (K > startsWithA + startsWithB) {
                System.out.print(-1);
                return;
            }

            if (K <= startsWithA) {
                sb.append('a');
                N--;
            } else {
                sb.append('z');
                K -= startsWithA;
                M--;
            }

            if (N == 0 || M == 0) {
                while (N-- > 0) {
                    sb.append('a');
                }

                while (M-- > 0) {
                    sb.append('z');
                }
                break;
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static void init() {
        pascal[0][0] = 1;
        for (int i = 1; i <= N + M; i++) {
            pascal[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                pascal[i][j] = Math.min(pascal[i - 1][j] + pascal[i - 1][j - 1], 1_000_000_001);
            }
        }
    }
}
