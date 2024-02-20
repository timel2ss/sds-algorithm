import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int[] parents;
    public static int n, m;		//n 원소, m 연산의 개수
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];
        for(int i = 1; i <= n; i++)
            parents[i] = i;

        for(int i = 1; i <= m; i++) {			
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(cmd == 0)
                union(a, b);
            else {
                if(find(a) == find(b))
                    bw.write("YES\n");
                else
                    bw.write("NO\n");
            }
        }		
        bw.flush();
        bw.close();
        br.close();
    }
    public static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        parents[bRoot] = aRoot;
    }

    public static int find(int a) {
        if(parents[a] == a)
            return a;

        return parents[a] = find(parents[a]);
    }	

}
