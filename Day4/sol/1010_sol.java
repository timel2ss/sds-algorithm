import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // N <= M 이므로, 그냥 강 동쪽의 사이트(M개) 중에 순서없이 N개를 고르는 경우의 수와 같다 -> mCn
    // + 동쪽사이트:서쪽사이트 1:1 연결이므로 위 처럼하면 다리끼리 겹쳐지는 건 고려할 필요가 없다. 고르고 나면 겹치지 않는 경우는 한가지뿐이기 떄문

    static final int MAX = 30;
    static final int MOD = 1000000007;

    static int T;
    static int N, M;
    // 파스칼의 삼각형 만들어두고 활용
    static int[][] pascalsTriangle = new int[MAX+1][MAX+1];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        makePascalsTriangle();
        printPascalsTriangle();

        T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            // mCn
            System.out.println(pascalsTriangle[M][N]);
        }
        br.close();

    }

    static void makePascalsTriangle() {
        pascalsTriangle[0][0] = 1;
        for(int i=1;i<=MAX;i++) {
            pascalsTriangle[i][0] = 1;
            for(int j=1;j<=i;j++) {
                // !!! 여기서 터지는 경우를 고려해서 작업해야 한다.
                pascalsTriangle[i][j]=(pascalsTriangle[i-1][j-1] + pascalsTriangle[i-1][j]);
                // pascalsTriangle[i][j]=(pascalsTriangle[i-1][j-1] + pascalsTriangle[i-1][j]) % MOD; // 범위를 벗어나는 값을 보정하기 위해 %MOD
            }
        }
    }

    static void printPascalsTriangle() {
        for(int i=0;i<=MAX;i++) {
            for(int j=0;j<=MAX;j++) {
                System.out.print(pascalsTriangle[i][j] == 0 ? " " : pascalsTriangle[i][j] + " ");
            }
            System.out.println();
        }
    }
}
