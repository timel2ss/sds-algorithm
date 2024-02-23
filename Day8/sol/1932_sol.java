import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int[][] DP;
    public static int[][] inputVal;	
    public static int n;

    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        DP = new int[n + 1][n + 1];
        inputVal = new int[n + 1][n + 1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= i; j++ )
                inputVal[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                DP[i][j] = Math.max(DP[i-1][j-1], DP[i-1][j]) + inputVal[i][j];
            }
        }

        int result = 0;
        for(int i = 1; i <= n; i++)
            result = Math.max(result, DP[n][i]);

        System.out.println(result);
    }

}
