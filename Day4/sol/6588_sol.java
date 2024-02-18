import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    static final int MAX = 1000000;
    static boolean[] isPrime = new boolean[MAX+1];

    static int N;
    static boolean isWrong;

    public static void main(String[] args) throws Exception{
        Arrays.fill(isPrime, true);
        getPrimeNum();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            N = Integer.parseInt(br.readLine());

            if(N==0){
                break;
            }

            isWrong = true;

            // b-a가 가장 큰 것을 출력한다 >> a가 작을수록 유리
            for(int i=2; i<=N/2; i++){
                if(isPrime[i] && isPrime[N-i]){
                    bw.write(N + " = " + i + " + " + (N-i));
                    bw.newLine();

                    isWrong = false;
                    break;
                }
            }

            if(isWrong){
                bw.write("Goldbach's conjecture is wrong.");
                bw.newLine();
            }
        } // end of while

        br.close();
        bw.flush();
        bw.close();

    }

    static void getPrimeNum() {
        isPrime[0]=isPrime[1]=false; //1은 소수가 아니므로 제외

        // 5의 배수를 가정하면 5*5 보다 작은 5의 배수는 5*1, 5*2, 5*3, 5*4이다
        // 5*2, 5*4의 경우에는 i=2일때 이미 체크가 되었을 것이다.
        // 또 5*3은 i=3일때 체크가 되었을 것이다.
        for(int i=2;i*i<=MAX;i++){ //100의 제곱근인 10 까지, MAX=a*b -> a<sqrt(MAX)<b -> b로 체크하기 전에 a의 배수에서 체크된다.
            if(isPrime[i]){//소수이면
                //for loop : 소수i의 배수들을 모두 제외
                for(int j=i*i;j<=MAX;j+=i) { // i*i 보다 작은 값들은 이미 이전 i의 배수를 체크할 때 다 체크했음
                    isPrime[j] = false;//소수의 배수는 모두 소수가 아니다.
                }
            }
        }
    }
}
