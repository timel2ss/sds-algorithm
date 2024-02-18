import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static final int MAX = 1000;
    static int[] count = new int[MAX+1];

    static int T;
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        memo();

        T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());

            System.out.println(count[N]);
        }
        br.close();
        bw.close();
    }

    static int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b, a%b);
    }

    static void memo(){
        count[1] = 3;
        for(int i=2; i<count.length; i++){
            int addCount = 0;

            // j는 가로세로의 길이 일 때(N까지 달려가는)
            for(int j=i; j>0; j--){
                if(i==j) continue;

                // (j, i) -> (x, y) -> x와 y가 서로소여야지만 보인다
                /*
                // 윗변에 추가되는 관측가능한 점
                if(gcd(j, i) == 1){
                    addCount++;
                }
                // 오른쪽변에 추가되는 관측가능한 점
                if(gcd(i, j) == 1){
                    addCount++;
                }
                */

                // 윗변 + 오른쪽변 합치면
                if(gcd(i, j) == 1){
                    addCount+=2;
                }
            }

            count[i] = count[i-1] + addCount;
        }
    }
}
