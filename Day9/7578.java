import java.util.*;
import java.io.*;

public class Main {	
    private static int[] tree;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] queryOrder = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            queryOrder[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> indexMap = new HashMap<>(N + 1);
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            indexMap.put(Integer.parseInt(st.nextToken()), i);
        }

        long result = 0; 
        tree = new int[4 * N];
        for (int i = 1; i <= N; i++) {
            int index = indexMap.get(queryOrder[i]);
            result += query(index + 1, N, 1, 1, N);
            update(index, 1, 1, N);
        }
        System.out.print(result);
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