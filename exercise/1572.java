import java.util.*;
import java.io.*;

public class Main {
    private static final int MAX = 65537;
    
    private static int[] tree;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        tree = new int[4 * MAX];

        Queue<Integer> queue = new ArrayDeque<>(K);

        long result = 0;
        for (int i = 0; i < N - K + 1; i++) {
            while (queue.size() < K) {
                int val = Integer.parseInt(br.readLine());
                update(val, 1, 1, 0, MAX);
                queue.add(val);
            }
            result += query((K + 1) / 2, 1, 0, MAX);
            update(queue.poll(), -1, 1, 0, MAX);
        }
        
        System.out.print(result);
    }

    private static void update(int index, int value, int node, int nodeLeft, int nodeRight) {
        if (index < nodeLeft || nodeRight < index) {
            return;
        }

        tree[node] += value;
        if (nodeLeft == nodeRight) {
            return;
        }
        
        int mid = (nodeLeft + nodeRight) / 2;
        update(index, value, node * 2, nodeLeft, mid);
        update(index, value, node * 2 + 1, mid + 1, nodeRight);
    }

    private static int query(int index, int node, int nodeLeft, int nodeRight) {
        if (nodeLeft == nodeRight) {
            return nodeLeft;
        }

        int mid = (nodeLeft + nodeRight) / 2;
        if (index <= tree[node * 2]) {
            return query(index, node * 2, nodeLeft, mid);
        }
        return query(index - tree[node * 2], node * 2 + 1, mid + 1, nodeRight);
    }
}