import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] heights = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int lo = 1;
        int hi = 2_000_000_000;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (solve(heights, mid, M)) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        System.out.print(hi);
    }

    private static boolean solve(int[] arr, int H, int M) {
        long count = 0;
        for (int i = 0; i < arr.length; i++) {
            count += Math.max(arr[i] - H, 0);
        }
        return count < M;
    }
}