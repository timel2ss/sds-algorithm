import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int[] arr;
    private static int[] tree;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        tree = new int[4 * N];

        Map<Integer, Queue<Integer>> map = new HashMap<>();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            
            Queue<Integer> queue = map.getOrDefault(arr[i], new ArrayDeque<>());
            queue.add(i);
            map.put(arr[i], queue);
        }

        Arrays.sort(arr);

        long answer = 0;
        for (int i = 1; i <= N; i++) {
            Queue<Integer> queue = map.get(arr[i]);
            while (!queue.isEmpty()) {
                int pos = queue.poll();
                answer += query(pos, N, 1, 1, N);
                update(pos, 1, 1, N);
            }
        }

        System.out.print(answer);
        
        br.close();
    }

    private static void update(int pos, int node, int nodeLeft, int nodeRight) {
        if (nodeRight < pos || pos < nodeLeft) {
            return;
        }
        
        tree[node]++;
        if (nodeLeft == nodeRight) {
            return;
        }

        int mid = (nodeLeft + nodeRight) / 2;
        update(pos, node * 2, nodeLeft, mid);
        update(pos, node * 2 + 1, mid + 1, nodeRight);
    }

    private static int query(int left, int right, int node, int nodeLeft, int nodeRight) {
        if (right < nodeLeft || nodeRight < left) {
            return 0;
        }

        if (left <= nodeLeft && nodeRight <= right) {
            return tree[node];
        }

        int mid = (nodeLeft + nodeRight) / 2;
        return query(left, right, node * 2, nodeLeft, mid) + query(left, right, node * 2 + 1, mid + 1, nodeRight);
    }
}