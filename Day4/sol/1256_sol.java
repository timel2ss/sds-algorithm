import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] pascalsTriangle;
    static final int MAX = 200;
    static final int MAX_VALUE = 1000000001; // K값 제한=10억 / 10억 1

    public static void main(String args[]) throws Exception {
        makePascalsTriangle();
        // printPascalsTriangle();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // a
        M = Integer.parseInt(st.nextToken()); // z
        K = Integer.parseInt(st.nextToken()); // k번째 문자열

        if(pascalsTriangle[N+M][M] < K){
            System.out.println("-1");
            return;
        }

        while(true){
            if(K <= pascalsTriangle[N+M-1][M]){
                bw.append("a");
                N--;
            }
            else{
                bw.append("z");
                // pascalsTriangle[N+M-1][M] 에 포함되지 않으므로 K에서 그만큼 빼준다
                K = K - pascalsTriangle[N+M-1][M];
                M--;
            }

            // 한 알파벳 다 썼으면 다른 알파벳 붙여넣고 끝냄
            if(M==0){
                for(int i=0; i<N; i++){
                    bw.append("a");
                }
                break;
            }
            if(N==0){
                for(int i=0; i<M; i++){
                    bw.append("z");
                }
                break;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void makePascalsTriangle(){
        pascalsTriangle = new int[MAX+1][MAX+1];
        pascalsTriangle[0][0] = 1;

        for(int i=1; i<=MAX; i++){
            pascalsTriangle[i][0] = 1;
            for(int j=1; j<=i; j++){
                pascalsTriangle[i][j] = Math.min(pascalsTriangle[i-1][j-1] + pascalsTriangle[i-1][j], MAX_VALUE);
            }
        }
    }

    static void printPascalsTriangle(){
        for(int i=0; i<=MAX; i++){
            for(int j=0; j<=MAX; j++){
                System.out.print(pascalsTriangle[i][j] + " ");
            }
            System.out.println();
        }
    }
}
