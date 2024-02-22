import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static class Computer implements Comparable<Computer>{
        int from;
        int to;
        int cost;
        Computer(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Computer arg0) {
            // TODO Auto-generated method stub
            return this.cost - arg0.cost;
        }
    }
    public static ArrayList<Computer> adj;
    public static int N, M;		//N : 컴퓨터의수, M 선의수
    public static int[] parents;	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adj = new ArrayList<>();
        parents = new int[N + 1];
        for(int i = 1; i <= N; i++)
            parents[i] = i;

        for(int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj.add(new Computer(a, b, c));
        }

        //정렬
        Collections.sort(adj);
        long answer = 0;
        for(Computer e : adj) {
            int start = e.from;
            int end = e.to;
            int value = e.cost;
            int sRoot = find(start);
            int eRoot = find(end);			
            if(sRoot == eRoot)	continue;
            answer += value;
            parents[eRoot] = sRoot;
        }
        System.out.println(answer);
    }

    public static int find(int n) {
        if(parents[n] == n)
            return n;

        return parents[n] = find(parents[n]);
    }

}
