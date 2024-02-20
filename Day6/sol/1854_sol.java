import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    public static int n, m, k;
    //public static int[] DP;
    public static ArrayList<Node>[] adj;
    public static PriorityQueue<Integer>[] distPQ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());	//k번째 최단경로기준
        //DP = new int[V + 1];
        adj = new ArrayList[n + 1];
        distPQ = new PriorityQueue[n + 1];
        for(int i = 1; i <= n; i++) {
            //DP[i] = Integer.MAX_VALUE;
            adj[i] = new ArrayList<>();
            distPQ[i] = new PriorityQueue<>(Collections.reverseOrder());
        }

        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());	//출발지
            int b = Integer.parseInt(st.nextToken());	//도착지
            int c = Integer.parseInt(st.nextToken());	//가중치
            adj[a].add(new Node(b, c));
        }
        dijkstra(1);		

        for(int i = 1; i <= n; i++) {
            if(distPQ[i].size() == k)
                bw.write(distPQ[i].peek() + "\n");
            else
                bw.write("-1\n");			

        }		

        bw.flush();
        bw.close();

    }
    public static void dijkstra(int sNo) {
        distPQ[sNo].add(0);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        //DP[sNo] = 0;
        pq.add(new Node(sNo, 0));		
        while(!pq.isEmpty()) {
            Node a = pq.poll();
            int now = a.to;
            int nowCost = a.cost;			
            //if(nowCost > DP[now])	continue;			
            for(Node e : adj[now]) {
                int next = e.to;
                int nextCost = e.cost + nowCost;
                if(distPQ[next].size() < k) {
                    distPQ[next].add(nextCost);
                    pq.add(new Node(next, nextCost));
                }else if(nextCost < distPQ[next].peek()) {
                    distPQ[next].poll();
                    distPQ[next].add(nextCost);
                    pq.add(new Node(next, nextCost));
                }			
            }	
        }
    }

}
