import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, MAX_GCD;
    static int[] input;
    static int[] ltor;
    static int[] rtol;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        input = new int[N];
        ltor = new int[N];
        rtol = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            input[i] = Integer.parseInt(st.nextToken());
            if(i==0){
                ltor[i] = input[i];
            }else{
                // L -> R 방향으로 gcd를 채움
                ltor[i] = gcd(ltor[i-1], input[i]);
            }
        }
        br.close();

        for(int i=N-1; i>=0; i--){
            if(i==N-1){
                rtol[i] = input[i];
            }else{
                // R -> L 방향으로 gcd를 채움
                rtol[i] = gcd(rtol[i+1], input[i]);
            }
        }

        K = 0;
        MAX_GCD = 0;

        int gcd = 0;
        for(int i=0; i<N; i++){
            if(i==0){
                gcd = rtol[1];
            }else if(i==N-1){
                gcd = ltor[N-2];
            }else{
                gcd = gcd(ltor[i-1], rtol[i+1]);
            }

            // 답(MAX_GCD, 그때 뺀 수 K) 갱신
            // 뺀 수(K = input[i]) 일때, 나머지 수의 gcd가 K의 약수가 되면 안된다 -> input[i]%gcd != 0
            if(MAX_GCD < gcd && input[i]%gcd != 0){
                K = input[i];
                MAX_GCD = gcd;
            }
        }

        if(MAX_GCD == 0){
            System.out.println(-1);
        }else{
            System.out.println(MAX_GCD + " " + K);
        }

    }

    static int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b, a%b);
    }
}
