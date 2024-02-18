public class Main {
    static int[][] pascalsTriangle;

    static final int MAX = 10;
    static final int MOD = 1000000007;
    static final int MAX_VALUE = 1000000001;

    public static void main(String args[]) throws Exception {
        makePascalsTriangle();
        printPascalsTriangle();
    }

    static void makePascalsTriangle(){
        pascalsTriangle = new int[MAX+1][MAX+1];
        pascalsTriangle[0][0] = 1;

        for(int i=1; i<=MAX; i++){
            pascalsTriangle[i][0] = 1;
            for(int j=1; j<=i; j++){
                pascalsTriangle[i][j] = pascalsTriangle[i-1][j-1] + pascalsTriangle[i-1][j];

                // #1 사이즈 넘어갈 가능성이 있는 경우 문제에 MOD값 제시
                // pascalsTriangle[i][j] = (pascalsTriangle[i-1][j-1] + pascalsTriangle[i-1][j]) % MOD;

                // #2 최대값 제한이 있으면 아래와 같이
                // pascalsTriangle[i][j] = Math.min(pascalsTriangle[i-1][j-1] + pascalsTriangle[i-1][j], MAX_VALUE);
            }
        }
    }

    static void printPascalsTriangle(){
        for(int i=0; i<=MAX; i++){
            for(int j=0; j<=MAX; j++){
                if(pascalsTriangle[i][j] != 0) System.out.print(pascalsTriangle[i][j] + " ");
            }
            System.out.println();
        }
    }
}
