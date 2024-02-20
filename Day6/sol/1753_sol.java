import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Node implements Comparable<Node> {
        int to;
        int cost;
        Node(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node arg0) {
            return this.cost - arg0.cost;
        }
    }

    public static int V, E, sNo;
    public static int[] DP;
    public static ArrayList<Node>[] adj;	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E= Integer.parseInt(st.nextToken());
        sNo = Integer.parseInt(br.readLine());
        DP = new int[V + 1];
        adj = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++) {
            DP[i] = Integer.MAX_VALUE;
            adj[i] = new ArrayList<>();			
        }

        for(int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());	//출발지
            int v = Integer.parseInt(st.nextToken());	//도착지
            int w = Integer.parseInt(st.nextToken());	//가중치
            adj[u].add(new Node(v, w));
        }
        dijkstra(sNo);

        for(int i = 1; i <= V; i++) {
            if(DP[i] == Integer.MAX_VALUE)
                bw.write("INF\n");
            else
                bw.write(DP[i] + "\n");
        }
        bw.flush();
        bw.close();

    }
    public static void dijkstra(int sNo) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        DP[sNo] = 0;
        pq.add(new Node(sNo, 0));

        boolean[] visited = new boolean[V + 1];
        Arrays.fill(visited, false);
        while(!pq.isEmpty()) {
            Node a = pq.poll();
            int now = a.to;
            int nowCost = a.cost;

            if(nowCost > DP[now])	continue;

            /*if(visited[now] == true)	continue;
            visited[now] = false;*/


            for(Node e : adj[now]) {
                int next = e.to;
                int nextCost = e.cost + DP[now];
                if(nextCost < DP[next]) {
                    DP[next] = nextCost;
                    pq.add(new Node(next, nextCost));
                }
            }	
        }
    }

}
