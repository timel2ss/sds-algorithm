import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int[] arr;
    private static int[] max;
    private static int[] min;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        max = new int[4 * N];
        min = new int[4 * N];
        init(1, 1, N);

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(queryMin(a, b, 1, 1, N))
                .append(' ')
                .append(queryMax(a, b, 1, 1, N))
                .append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static void init(int node, int nodeLeft, int nodeRight) {
        if (nodeLeft == nodeRight) {
            max[node] = min[node] = arr[nodeLeft];
            return;
        }

        int mid = (nodeLeft + nodeRight) / 2;
        init(node * 2, nodeLeft, mid);
        init(node * 2 + 1, mid + 1, nodeRight);
        max[node] = Math.max(max[node * 2], max[node * 2 + 1]);
        min[node] = Math.min(min[node * 2], min[node * 2 + 1]);
    }

    private static int queryMax(int left, int right, int node, int nodeLeft, int nodeRight) {
        if (right < nodeLeft || nodeRight < left) {
            return Integer.MIN_VALUE;
        }

        if (left <= nodeLeft && nodeRight <= right) {
            return max[node];
        }

        int mid = (nodeLeft + nodeRight) / 2;
        return Math.max(queryMax(left, right, node * 2, nodeLeft, mid),
                       queryMax(left, right, node * 2 + 1, mid + 1, nodeRight));
    }

    private static int queryMin(int left, int right, int node, int nodeLeft, int nodeRight) {
        if (right < nodeLeft || nodeRight < left) {
            return Integer.MAX_VALUE;
        }

        if (left <= nodeLeft && nodeRight <= right) {
            return min[node];
        }

        int mid = (nodeLeft + nodeRight) / 2;
        return Math.min(queryMin(left, right, node * 2, nodeLeft, mid),
                       queryMin(left, right, node * 2 + 1, mid + 1, nodeRight));
    }
}