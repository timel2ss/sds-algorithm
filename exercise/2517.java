import java.util.*;
import java.io.*;

public class Main {	
    private static int[] tree;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] queryOrder = new int[N + 1];
        int[] sorted = new int[N + 1];
        tree = new int[4 * N];

        for (int i = 1; i <= N; i++) {
            queryOrder[i] = Integer.parseInt(br.readLine());
            sorted[i] = queryOrder[i];
        }

        Arrays.sort(sorted);
        Map<Integer, Integer> indexMap = new HashMap<>(N + 1);
        for (int i = 1; i <= N; i++) {
            indexMap.put(sorted[i], i);
        }

        for (int i = 1; i <= N; i++) {
            int index = indexMap.get(queryOrder[i]);
            sb.append(query(index + 1, N, 1, 1, N) + 1).append('\n');
            update(index, 1, 1, N);
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static void update(int index, int node, int nodeLeft, int nodeRight) {
        if (nodeRight < index || index < nodeLeft) {
            return;
        }

        tree[node]++;
        if (nodeLeft == nodeRight) {
            return;
        }

        int mid = (nodeLeft + nodeRight) / 2;
        update(index, node * 2, nodeLeft, mid);
        update(index, node * 2 + 1, mid + 1, nodeRight);
    }

    private static int query(int left, int right, int node, int nodeLeft, int nodeRight) {
        if (nodeRight < left || right < nodeLeft) {
            return 0;
        }

        if (left <= nodeLeft && nodeRight <= right) {
            return tree[node];
        }

        int mid = (nodeLeft + nodeRight) / 2;
        return query(left, right, node * 2, nodeLeft, mid) + query(left, right, node * 2 + 1, mid + 1, nodeRight);
    }
} 