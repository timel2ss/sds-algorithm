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

        arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        tree = new long[4 * N];
        init(1, 0, N - 1);

        M += K;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 1) {
                long c = Long.parseLong(st.nextToken());
                update(b - 1, c - arr[b - 1], 1, 0, N - 1);
                arr[b - 1] = c;
                continue;
            }

            if (a == 2) {
                int c = Integer.parseInt(st.nextToken());
                sb.append(query(b - 1, c - 1, 1, 0, N - 1)).append('\n');
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static long init(int index, int left, int right) {
        if (left == right) {
            return tree[index] = arr[left];
        }

        int mid = (left + right) / 2;
        long leftValue = init(index * 2, left, mid);
        long rightValue = init(index * 2 + 1, mid + 1, right);

        return tree[index] = leftValue + rightValue;
    }

    private static long query(int start, int end, int index, int left, int right) {
        if (end < left || right < start) {
            return 0;
        }

        if (start <= left && right <= end) {
            return tree[index];
        }

        int mid = (left + right) / 2;
        long leftValue = query(start, end, index * 2, left, mid);
        long rightValue = query(start, end, index * 2 + 1, mid + 1, right);
        return leftValue + rightValue;
    }

    private static void update(int position, long diff, int index, int left, int right) {
        if (right < position || position < left) {
            return;
        }

        tree[index] += diff;
        if (left == right) {
            return;
        }

        int mid = (left + right) / 2;
        update(position, diff, index * 2, left, mid);
        update(position, diff, index * 2 + 1, mid + 1, right);
    }
}