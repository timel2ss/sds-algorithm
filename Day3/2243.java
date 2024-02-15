import java.util.*;
import java.io.*;

public class Main {
    private static final int TASTE_KIND = 1_000_000;
    private static int[] tree;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        tree = new int[4 * TASTE_KIND];
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (A == 1) {
                sb.append(pop(B, 1, 1, TASTE_KIND)).append('\n');
            } else {
                int C = Integer.parseInt(st.nextToken());
                update(B, C, 1, 1, TASTE_KIND);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static int pop(int rank, int index, int left, int right) {
        tree[index]--;
        if (left == right) {
            return left;
        }

        int mid = (left + right) / 2;
        if (tree[index * 2] >= rank) {
            return pop(rank, index * 2, left, mid);
        }
        return pop(rank - tree[index * 2], index * 2 + 1, mid + 1, right);
    }

    private static void update(int taste, int value, int index, int left, int right) {
        if (right < taste || taste < left) {
            return;
        }

        tree[index] += value;
        if (left == right) {
            return;
        }

        int mid = (left + right) / 2;
        update(taste, value, index * 2, left, mid);
        update(taste, value, index * 2 + 1, mid + 1, right);
    }
}