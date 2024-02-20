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
    public static int[] weight;
    public static int N, M;		//N 샘플의 종류, M 연산의 개수
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0)	break;

            parents = new int[N + 1];
            for(int i = 1; i <= N; i++)
                parents[i] = i;
            weight = new int[N + 1];

            for(int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(cmd.equals("!")) {
                    int w = Integer.parseInt(st.nextToken());				
                    union(a, b, w);
                } else {
                    if(find(a) != find(b))
                        bw.write("UNKNOWN\n");
                    else
                        bw.write((weight[b] - weight[a]) + "\n" );
                }
            }			
        }		
        bw.flush();
        bw.close();
        br.close();
    }
    public static void union(int a, int b, int w) {
        int aRoot = find(a);
        int bRoot = find(b);
        parents[bRoot] = aRoot;
        weight[bRoot] += (w + weight[a] - weight[b]);
    }

    public static int find(int a) {
        if(parents[a] == a)
            return a;

        int temp = find(parents[a]);
        weight[a] += weight[parents[a]];
        return parents[a] = temp;
    }

}
