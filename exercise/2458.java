import java.util.*;
import java.io.*;

public class Main {		
    private static final int INF = 987_654_321;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] path = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(path[i], INF);
            path[i][i] = 0;
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            path[a][b] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    path[i][j] = Math.min(path[i][j], path[i][k] + path[k][j]);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (path[i][j] != INF && path[i][j] != 0) {
                    count++;
                }
                if (path[j][i] != INF && path[j][i] != 0) {
                    count++;
                }
            }
            if (count == N - 1) {
                answer++;
            }
        }

        System.out.print(answer);
    }
}