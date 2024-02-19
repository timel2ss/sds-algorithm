import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int[] bDegree;
    public static int[] bTime;
    public static int[] bResult;
    public static ArrayList<Integer>[] adj;
    public static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        bDegree = new int[N + 1];
        bTime = new int[N + 1];
        bResult = new int[N + 1];
        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++)
            adj[i] = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            bTime[i] = time;
            while(st.hasMoreTokens()) {
                int cBuilding = Integer.parseInt(st.nextToken());
                if(cBuilding == -1)	break;
                adj[cBuilding].add(i);
                bDegree[i]++;
            }
        }

        Queue<Integer> que = new LinkedList<>();		
        for(int i = 1; i <= N; i++) {
            if(bDegree[i] == 0) {
                que.add(i);
                bResult[i] = bTime[i];
            }
        }

        while(!que.isEmpty()) {
            int now = que.poll();

            for(Integer next : adj[now]) {
                bDegree[next]--;
                bResult[next]= Math.max(bResult[next], bResult[now]+bTime[next]);
                if(bDegree[next] == 0)
                    que.add(next);			

            }
        }

        for(int i = 1; i <= N; i++) {
            bw.write(bResult[i] + "\n");
        }


        bw.flush();
        bw.close();
        br.close();
    }

}
