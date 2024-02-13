import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    // Queen은 각 row에 한 column에만 존재할 수 있으므로, 1차원 array에 위치를 저장할 수 있다.
    // ex) N=4 일때, arr[0]=2 => 위에서 첫번째 row의 세번째 column에 queen이 위치함
    static int[] arr;
    static int N;
    static int count = 0;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        dfs(0);
        bw.write(""+count);
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int depth){
        // Termination Condition
        // 마지막 줄 까지 recursion의 depth가 내려오면 퀸 배치할 수 있는 가짓수++, return
        if(depth == N){
            count++;
            return;
        }

        // recursion
        for(int i=0; i<N; i++){
            // depth row의 i번째 칸(col)마다 queen을 놓아보자
            arr[depth] = i;
            // 윗 줄 코드의 queen의 위치가 가능하니? 가능하면 다음 row로 한 depth더 이동
            if(isGood(depth)){
                dfs(depth+1);
            }
        }
    }

    static boolean isGood(int row){
        for(int i=0; i<row; i++){
            // 0~row 번째 행 중에 같은 칸(col)에 queen이 위치한 행이 있는가?
            if(arr[i] == arr[row]){
                return false;
            }
            // (row, arr[row]) <-> (i, arr[i]) 좌표의 dx, dy
            // 좌표 차이의 절대값이 같으면 같은 대각선상에 존재하는 것으로 판단
            else if(Math.abs(row-i) == Math.abs(arr[row]-arr[i])){
                return false;
            }
        }
        return true;
    }

}