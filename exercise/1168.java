import java.util.*;
import java.io.*;

public class Main {
    private static int N, index;
    private static int[] tree;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        tree = new int[4 * N];
        init(1, 1, N);

        int index = K;
        sb.append('<');
        sb.append(pop(index, 1, 1, N));
        
        for (int size = N - 1; size > 0; size--) {
            index = (index + K - 1) % size;
            if (index == 0) {
                index = size;
            }
            sb.append(", ").append(pop(index, 1, 1, N));
        }
        sb.append('>');
        
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static void init(int node, int nodeLeft, int nodeRight) {
        if (nodeLeft == nodeRight) {
            tree[node] = 1;
            return;
        }

        int mid = (nodeLeft + nodeRight) / 2;
        init(node * 2, nodeLeft, mid);
        init(node * 2 + 1, mid + 1, nodeRight);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static int pop(int index, int node, int nodeLeft, int nodeRight) {
        tree[node]--;
        if (nodeLeft == nodeRight) {
            return nodeLeft;
        }

        int mid = (nodeLeft + nodeRight) / 2;
        if (tree[node * 2] >= index) {
            return pop(index, node * 2, nodeLeft, mid);
        }
        return pop(index - tree[node * 2], node * 2 + 1, mid + 1, nodeRight);
    }
}