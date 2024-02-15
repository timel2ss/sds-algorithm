import java.util.*;
import java.io.*;

public class Main {
    private static long[] arr;
    private static long[] tree;
    private static int leafPointer;
    
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

        init(N);

        M += K;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 1) {
                long c = Long.parseLong(st.nextToken());
                update(b, c - arr[b - 1]);
                arr[b - 1] = c;
            } else {
                int c = Integer.parseInt(st.nextToken());
                sb.append(query(b, c)).append('\n');
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static void init(int size) {
        int leafCnt = 1;
        while (size > leafCnt) {
            leafCnt <<= 1;
        }

        tree = new long[leafCnt << 1];
        leafPointer = leafCnt;

        for (int i = 0; i < size; i++) {
            tree[leafPointer + i] = arr[i];
        }

        for (int i = leafPointer - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    private static long query(int left, int right) {
        long result = 0;
        
        left += leafPointer - 1;
        right += leafPointer - 1;
        
        while (left <= right) {
            if (left % 2 == 1) {
                result += tree[left];
                left++;
            }

            if (right % 2 == 0) {
                result += tree[right];
                right--;
            }

            left /= 2;
            right /= 2;
        }
        return result;
    }

    private static void update(int index, long value) {
        index += leafPointer - 1;
        while (index >= 1) {
            tree[index] += value;
            index /= 2;
        }
    }
}