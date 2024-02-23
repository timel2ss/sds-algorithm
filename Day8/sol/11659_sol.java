import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int[] DP;
    public static int[] inputVal;	
    public static int N, M;

    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());				

        DP = new int[N + 1];
        inputVal = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++)
            inputVal[i] = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= N; i++) {
            DP[i] = DP[i-1] + inputVal[i];
        }	

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());			
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int answer = DP[to] - DP[from - 1];
            bw.write(answer + "\n");
        }
        bw.flush();
        bw.close();
    }	
}
