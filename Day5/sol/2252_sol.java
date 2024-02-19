import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int[] sDegree;
    public static ArrayList<Integer>[] adj;
    public static int N, M;	//학생들의 번호N, 비교건수 M
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sDegree = new int[N + 1];
        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++)
            adj[i] = new ArrayList<>();

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adj[A].add(B);
            sDegree[B]++;			
        }

        Queue<Integer> que = new LinkedList<>();		
        for(int i = 1; i <= N; i++) {
            if(sDegree[i] == 0) {
                que.add(i);
            }			
        }

        while(!que.isEmpty()) {
            int now = que.poll();
            bw.write(now + " ");

            for(Integer next : adj[now]) {
                sDegree[next]--;
                if(sDegree[next] == 0) {
                    que.add(next);					
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

}
