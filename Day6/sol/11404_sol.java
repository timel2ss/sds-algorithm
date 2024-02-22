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
    public static long[][] Maps;
    public static int n, m;	//도시, 버스
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        Maps = new long[n + 1][n + 1];
        for(int i = 1; i <= n; i++)
            //Arrays.fill(Maps[i], 0);
            Arrays.fill(Maps[i], Integer.MAX_VALUE);						

        for(int i = 1; i <= m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());	//시작도시
            int b = Integer.parseInt(st.nextToken());	//도착도시
            int c = Integer.parseInt(st.nextToken());	//비용

            Maps[a][b] = Math.min(Maps[a][b], c);
        }

        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                if(i == k)	continue;
                for(int j = 1; j <= n; j++) {
                    if(k == j || i == j )	continue;
                    if(Maps[i][j] > Maps[i][k] + Maps[k][j])
                        Maps[i][j] = Maps[i][k] + Maps[k][j];
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(Maps[i][j] == Integer.MAX_VALUE)
                    Maps[i][j] = 0;
            }				
        }


        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                bw.write(Maps[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}
