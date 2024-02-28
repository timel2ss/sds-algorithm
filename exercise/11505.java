import java.util.*;
import java.io.*;

public class Main {
    private static final int MOD = 1_000_000_007;
    
    private static int N;
    private static int[] arr;
    private static long[] tree;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        tree = new long[4 * N];
        init(1, 1, N);
        
        M += K;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                update(b, c, 1, 1, N);
            } else {
                sb.append(query(b, c, 1, 1, N)).append('\n');
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static long init(int node, int nodeLeft, int nodeRight) {
        if (nodeLeft == nodeRight) {
            return tree[node] = arr[nodeLeft];
        }

        int mid = (nodeLeft + nodeRight) / 2;
        return tree[node] = (init(node * 2, nodeLeft, mid) % MOD)
            * (init(node * 2 + 1, mid + 1, nodeRight) % MOD) % MOD;
    }

    private static long query(int left, int right, int node, int nodeLeft, int nodeRight) {
        if (right < nodeLeft || nodeRight < left) {
            return 1;
        }

        if (left <= nodeLeft && nodeRight <= right) {
            return tree[node];
        }

        int mid = (nodeLeft + nodeRight) / 2;
        return (query(left, right, node * 2, nodeLeft, mid) % MOD)
            * (query(left, right, node * 2 + 1, mid + 1, nodeRight) % MOD) % MOD;
    }

    private static long update(int index, int value, int node, int nodeLeft, int nodeRight) {
        if (nodeRight < index || index < nodeLeft) {
            return tree[node];
        }
        
        if (nodeLeft == nodeRight) {
            return tree[node] = value;
        }

        int mid = (nodeLeft + nodeRight) / 2;
        return tree[node] = (update(index, value, node * 2, nodeLeft, mid) % MOD) * (update(index, value, node * 2 + 1, mid + 1, nodeRight) % MOD) % MOD;
    }
}