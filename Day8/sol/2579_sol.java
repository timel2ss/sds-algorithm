import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int[] DP;
    public static int[] inputVal;	
    public static int N;

    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        DP = new int[N + 5];
        inputVal = new int[N + 5];


        for(int i = 1; i <= N; i++ )
            inputVal[i] = Integer.parseInt(br.readLine());


        DP[1] = inputVal[1];
        DP[2] = inputVal[1] + inputVal[2];
        //DP[3] = Math.max(DP[1], DP[2]) + inputVal[3];

        for(int i = 3; i <= N; i++ )
            DP[i] = Math.max(DP[i-2] + inputVal[i], DP[i-3] + inputVal[i-1] + inputVal[i]);								

        System.out.println(DP[N]);		
    }
}
