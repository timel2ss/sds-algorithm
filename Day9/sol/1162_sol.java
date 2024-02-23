import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static class City implements Comparable<City>{
        int to;
        long time;
        int pave;
        City(int to, long time, int pave){
            this.to = to;
            this.time = time;
            this.pave = pave;
        }
        public int compareTo(City arg0) {
            return (int)(this.time - arg0.time);
        }		
    }

    public static int N, M, K;
    public static ArrayList<City>[] adj;
    public static long[][] DP;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        DP = new long[N + 1][K + 1];
        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            Arrays.fill(DP[i], Long.MAX_VALUE);
        }		

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            adj[A].add(new City(B, T, 0));
            adj[B].add(new City(A, T, 0));
        }		
        dijkstra(1);

        long answer = Long.MAX_VALUE;
        for(int i = 0; i <= K; i++)
            answer = Math.min(answer,  DP[N][i]);

        System.out.println(answer);
    }

    public static void dijkstra(int start) {
        PriorityQueue<City> pq = new PriorityQueue<>();

        DP[start][0] = 0;		
        pq.add(new City(start, 0, 0));

        while(!pq.isEmpty()) {
            City c = pq.poll();
            int now = c.to;
            long nowTime = c.time;
            int nowPave = c.pave;

            if(nowTime > DP[now][nowPave]) continue;

            for(City e : adj[now]) {
                int next = e.to;
                long nextTime = e.time + DP[now][nowPave];

                //Pave 사용하지 않을 경우
                if(nextTime < DP[next][nowPave]) {
                    DP[next][nowPave] = nextTime;
                    pq.add(new City(next, nextTime, nowPave));
                }

                //Pave 사용할 경우
                if(nowPave < K && nowTime < DP[next][nowPave + 1]) {
                    DP[next][nowPave + 1] = nowTime;
                    pq.add(new City(next, nowTime, nowPave+1));
                }
            }			
        }
    }
}
