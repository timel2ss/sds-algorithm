import java.util.*;
import java.io.*;

public class Main {
    private static long[] arr;
    private static long[] tree;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        tree = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
            add(i, arr[i]);
        }
        
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if (a == 1) {
                long c = Long.parseLong(st.nextToken());
                add(b, c - arr[b]);
                arr[b] = c;
                continue;
            }

            if (a == 2) {
                int c = Integer.parseInt(st.nextToken());
                sb.append(rangeSum(b, c)).append('\n');
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static void add(int index, long value) {
        while (index < tree.length) {
            tree[index] += value;
            index += (index & -index);
        }
    }

    private static long rangeSum(int start, int end) {
        return sum(end) - sum(start - 1);
    }

    private static long sum(int index) {
        long sum = 0;
        while (index > 0) {
            sum += tree[index];
            index &= index - 1;
        }
        return sum;
    }
}