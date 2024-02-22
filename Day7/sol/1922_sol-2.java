import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Computer implements Comparable<Computer>{
        int to;
        int cost;
        Computer(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Computer arg0) {
            // TODO Auto-generated method stub
            return this.cost - arg0.cost;
        }
    }
    public static ArrayList<Computer>[] adj;
    public static int N, M;				//N : 컴퓨터의수, M 선의수
    public static boolean[] visited;	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++)
            adj[i] = new ArrayList<>();

        for(int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[a].add(new Computer(b, c));
            adj[b].add(new Computer(a, c));
        }		

        int answer = 0;
        PriorityQueue<Computer> pq = new PriorityQueue<>();		
        pq.add(new Computer(1, 0));
        while(!pq.isEmpty()) {
            Computer c = pq.poll();
            int now = c.to;
            int nowCost = c.cost;
            if(visited[now] == true) continue; 
            answer += nowCost;
            visited[now] = true;

            for(Computer e : adj[now]) {
                if(!visited[e.to])					
                    pq.add(e);
            }	
        }		

        System.out.println(answer);
    }

}
