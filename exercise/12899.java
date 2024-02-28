import java.util.*;
import java.io.*;

public class Main {
    private static final int MAX = 2_000_000;
    
    private static int[] tree = new int[4 * MAX];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());

            if (T == 1) {
                insert(X, 1, 1, MAX);
            } else {
                sb.append(delete(X, 1, 1, MAX)).append('\n');
            }
        }
        
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static void insert(int index, int node, int nodeLeft, int nodeRight) {
        if (nodeRight < index || index < nodeLeft) {
            return;
        }

        tree[node]++;
        if (nodeLeft == nodeRight) {
            return;
        }

        int mid = (nodeLeft + nodeRight) / 2;
        insert(index, node * 2, nodeLeft, mid);
        insert(index, node * 2 + 1, mid + 1, nodeRight);
    }

    private static int delete(int index, int node, int nodeLeft, int nodeRight) {
        tree[node]--;
        
        if (nodeLeft == nodeRight) {
            return nodeLeft;
        }

        int mid = (nodeLeft + nodeRight) / 2;
        if (tree[node * 2] >= index) {
            return delete(index, node * 2, nodeLeft, mid);
        } else {
            return delete(index - tree[node * 2], node * 2 + 1, mid + 1, nodeRight);
        }
    }
}