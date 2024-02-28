import java.util.*;
import java.io.*;

public class Main {		
    private static int N;
    private static int[] maxTree;
    private static int[] minTree;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            maxTree = new int[4 * N];
            minTree = new int[4 * N];
            init(1, 1, N);

            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                int Q = Integer.parseInt(st.nextToken());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                if (Q == 0) {
                    int AValue = maxTree[findIndex(A + 1, 1, 1, N)];
                    int BValue = maxTree[findIndex(B + 1, 1, 1, N)];
                    update(A + 1, BValue, 1, 1, N);
                    update(B + 1, AValue, 1, 1, N);
                } else {
                    if (A == queryMin(A + 1, B + 1, 1, 1, N) && B == queryMax(A + 1, B + 1, 1, 1, N)) {
                        sb.append("YES\n");
                    } else {
                        sb.append("NO\n");
                    }
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static void init(int node, int nodeLeft, int nodeRight) {
        if (nodeLeft == nodeRight) {
            maxTree[node] = nodeLeft - 1;
            minTree[node] = nodeLeft - 1;
            return;
        }
        int mid = (nodeLeft + nodeRight) / 2;
        init(node * 2, nodeLeft, mid);
        init(node * 2 + 1, mid + 1, nodeRight);
        maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
        minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
    }

    private static void update(int index, int value, int node, int nodeLeft, int nodeRight) {
        if (index < nodeLeft || nodeRight < index) {
            return;
        }

        if (nodeLeft == nodeRight) {
            maxTree[node] = value;
            minTree[node] = value;
            return;
        }
        int mid = (nodeLeft + nodeRight) / 2;
        update(index, value, node * 2, nodeLeft, mid);
        update(index, value, node * 2 + 1, mid + 1, nodeRight);
        maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
        minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
    }

    private static int findIndex(int index, int node, int nodeLeft, int nodeRight) {
        if (index < nodeLeft || nodeRight < index) {
            return -1;
        }

        if (nodeLeft == nodeRight) {
            return node;
        }
        int mid = (nodeLeft + nodeRight) / 2;
        return Math.max(findIndex(index, node * 2, nodeLeft, mid),
                findIndex(index, node * 2 + 1, mid + 1, nodeRight));
    }

    private static int queryMax(int left, int right, int node, int nodeLeft, int nodeRight) {
        if (right < nodeLeft || nodeRight < left) {
            return Integer.MIN_VALUE;
        }

        if (left <= nodeLeft && nodeRight <= right) {
            return maxTree[node];
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
            return minTree[node];
        }

        int mid = (nodeLeft + nodeRight) / 2;
        return Math.min(queryMin(left, right, node * 2, nodeLeft, mid),
                queryMin(left, right, node * 2 + 1, mid + 1, nodeRight));
    }
}