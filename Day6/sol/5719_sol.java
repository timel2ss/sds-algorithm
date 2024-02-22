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
        int dist;
        Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
        public int compareTo(Node arg0) {
            return this.dist - arg0.dist;
        }
    }
    public static int N, M, S, E;
    public static int[] DP;
    public static ArrayList<Node>[] adj;
    public static ArrayList<Integer>[] parents;
    public static boolean[][] checkShortPath;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 장소의수
            M = Integer.parseInt(st.nextToken()); // 도로의 수			
            if(N == 0 && M == 0) break;
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken()); // 출발
            E = Integer.parseInt(st.nextToken()); // 도착
            DP = new int[N];
            adj = new ArrayList[N];
            parents = new ArrayList[N];
            checkShortPath = new boolean[N][N];

            for(int i = 0; i < N; i++) {
                //DP[i] = Integer.MAX_VALUE;
                adj[i] = new ArrayList<>();
                parents[i] = new ArrayList<>();				
            }			

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                adj[u].add(new Node(v, p));			
            }			
            dijkstra(S);
            backtracking(E);
            dijkstra(S);

            if(DP[E] == Integer.MAX_VALUE)
                bw.write("-1\n");
            else
                bw.write(DP[E] + "\n");
        }
        bw.flush();
        bw.close();
    }
    public static void backtracking(int end) {
        for(int i : parents[end]) {
            int next = i;
            if(checkShortPath[next][end] == false) {
                checkShortPath[next][end] = true;
                backtracking(next);
            }
        }
    }

    public static void dijkstra(int sNo) {
        for(int i = 0; i < N; i++)
            DP[i] = Integer.MAX_VALUE;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        DP[sNo] = 0;
        pq.add(new Node(sNo, 0));
        while(!pq.isEmpty()) {
            Node o = pq.poll();
            int now = o.to;
            int nowDist = o.dist;

            if(nowDist > DP[now])	continue;

            for(Node e : adj[now]) {
                int next = e.to;				
                if(checkShortPath[now][next] == true)	continue;				
                int nextDist = e.dist + DP[now];
                if(nextDist == DP[next]) {
                    parents[next].add(now);
                }else if(nextDist < DP[next]) {
                    parents[next].clear();
                    parents[next].add(now);					
                    DP[next] = nextDist;
                    pq.add(new Node(next, nextDist));
                }
            }
        }
    }
}
