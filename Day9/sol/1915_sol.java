import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {		
    public static int[][] DP;
    public static int[][] square;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));		
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        DP = new int[n+1][m+1];
        square = new int[n+1][m+1];

        for(int i = 1; i <= n; i++) {
            String inputStr = br.readLine();
            for(int j = 1; j <= inputStr.length(); j++) {
                square[i][j] = inputStr.charAt(j - 1) - '0';
            }
        }

        int answer = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(square[i][j] != 0) {
                    DP[i][j] = Math.min(Math.min(DP[i-1][j], DP[i][j-1]), DP[i-1][j-1]) + 1;
                    answer = Math.max(answer, DP[i][j]);
                }
            }
        }
        System.out.println(answer * answer);

    }
}
