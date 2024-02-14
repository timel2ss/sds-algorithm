import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>(300_000, (x, y) -> y[1] - x[1]);
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            pq.add(new int[] {M, V});
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < K; i++) {
            int C = Integer.parseInt(br.readLine());
            map.put(C, map.getOrDefault(C, 0) + 1);
        }

        long max = 0;
        while (K > 0) {
            if (pq.isEmpty()) {
                break;
            }

            int[] jewel = pq.poll();
            
            Integer key = map.ceilingKey(jewel[0]);
            if (key != null) {
                int value = map.get(key);
                if (value > 1) {
                    map.put(key, value - 1);
                } else if (value == 1) {
                    map.remove(key);
                }
                max += jewel[1];
                K--;
            }
        }

        System.out.print(max);
    }
}