import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int[] arr;
    private static long[] tree;
    private static long[] lazyValue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1]; 

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        tree = new long[4 * N];
        lazyValue = new long[4 * N];
        init(1, 1, N);

        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());

            if (query == 1) {
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                update(i, j, k, 1, 1, N);
            } else {
                int x = Integer.parseInt(st.nextToken());
                sb.append(get(x, 1, 1, N)).append('\n');
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static void init(int node, int nodeLeft, int nodeRight) {
        if (nodeLeft == nodeRight) {
            tree[node] = arr[nodeLeft];
            return;
        }

        int mid = (nodeLeft + nodeRight) / 2;
        init(node * 2, nodeLeft, mid);
        init(node * 2 + 1, mid + 1, nodeRight);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static void update(int left, int right, int value, int node, int nodeLeft, int nodeRight) {
        pushDown(node, nodeLeft, nodeRight);

        if (right < nodeLeft || nodeRight < left) {
            return;
        }

        if (left <= nodeLeft && nodeRight <= right) {
            tree[node] += value * (nodeRight - nodeLeft + 1);
            if (nodeLeft != nodeRight) {
                lazyValue[node * 2] += value;
                lazyValue[node * 2 + 1] += value;
            }
            return;
        }

        int mid = (nodeLeft + nodeRight) / 2;
        update(left, right, value, node * 2, nodeLeft, mid);
        update(left, right, value, node * 2 + 1, mid + 1, nodeRight);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static long get(int index, int node, int nodeLeft, int nodeRight) {
        pushDown(node, nodeLeft, nodeRight);

        if (index < nodeLeft || nodeRight < index) {
            return 0;
        }

        if (nodeLeft == nodeRight) {
            return tree[node];
        }

        int mid = (nodeLeft + nodeRight) / 2;
        return get(index, node * 2, nodeLeft, mid) + get(index, node * 2 + 1, mid + 1, nodeRight);
    }

    private static void pushDown(int node, int nodeLeft, int nodeRight) {
        if (lazyValue[node] == 0) {
            return;
        }

        tree[node] += lazyValue[node] * (nodeRight - nodeLeft + 1);
        if (nodeLeft != nodeRight) {
            lazyValue[node * 2] += lazyValue[node];
            lazyValue[node * 2 + 1] += lazyValue[node];
        }
        lazyValue[node] = 0;
    }
}