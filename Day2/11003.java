import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Deque<int[]> deque = new ArrayDeque<>(N);
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int A = Integer.parseInt(st.nextToken());

            while (!deque.isEmpty() && deque.peekLast()[0] >= A) {
                deque.pollLast();
            }
            if (!deque.isEmpty() && deque.peek()[1] <= i - L) {
                deque.poll();
            }
            
            deque.add(new int[] {A, i});
            sb.append(deque.peek()[0]).append(' ');
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}