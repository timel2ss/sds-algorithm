package Chap5_Graph_3th;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class LCA2 {	
    public static int N, M, LCASize;
    public static int[][] parents;
    public static ArrayList<Integer>[] adj;
    public static int[] depth;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for(int i = 1; i <= N; i = i * 2)
            LCASize++;

        //parents = new int[N + 1][N + 1];
        parents = new int[N + 1][LCASize + 1];
        adj = new ArrayList[N + 1];		
        depth = new int[N + 1];
        for(int i = 1; i <= N; i++)
            adj[i] = new ArrayList<>();


        for(int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            adj[s].add(f);
            adj[f].add(s);
        }
        BFS(1);
        makeParents();

        M = Integer.parseInt(br.readLine());
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int answer = calcLCA(A, B);
            bw.write(answer + "\n");
        }
        bw.flush();
        bw.close();	
    }
    //depth구하고, parent[node][0] 부모저장
    public static void BFS(int root) {
        Queue<Integer> que = new LinkedList<>();
        depth[root] = 0;
        parents[root][0] = 0;
        que.add(root);
        while(!que.isEmpty()){
            int now = que.poll();			
            for(int i : adj[now]) {
                int next = i;
                //if(next == 1 && parents[next][0] != 0) continue;
                if(next == 1 || parents[next][0] != 0) continue;
                parents[next][0] = now;
                //depth[next]++;
                depth[next] = depth[now] + 1;
                que.add(next);
            }
        }
    }	

    public static void makeParents() {
        for(int i = 1; i < N; i++) {
            for(int j = 1; j <= LCASize; j++) {
                parents[i][j] = parents[parents[i][j-1]][j-1];	//점화식 : i번째 노드의 2^j번째 조상 구하기
                //예) 2^i번째 조상은 n
            }
        }
    }
    public static int calcLCA(int A, int B) {
        //A의 Depth가 더 깊도록 설정 
        if(depth[A] < depth[B]) {
            int temp = B;
            B = A;
            A = temp;

/*			int temp = depth[B];
            depth[B] = depth[A];
            depth[A] = temp;*/
        }

        //depth를 맞추어준다.
        for(int i = LCASize; i >= 0; i--) {
            int shift = 1 << i;
            //int shift = i << i;
            if(depth[A] >= depth[B] + shift) {
                A = parents[A][i];
            }				
        }

        // A와 B가 같은지 확인
        if(A == B)		return A;

        // 동시에 루트를 향하여 2^K번째로 이동
        for(int i = LCASize; i >= 0; i--) {
            if(parents[A][i] != parents[B][i]) {
                A = parents[A][i];
                B = parents[B][i];
            }
        }	
        return parents[A][0];
    }
}
