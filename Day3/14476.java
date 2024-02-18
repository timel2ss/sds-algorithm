import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] left = new int[N];
        int[] right = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        left[0] = arr[0];
        for (int i = 1; i < N; i++) {
            left[i] = gcd(left[i - 1], arr[i]);
        }

        right[N - 1] = arr[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            right[i] = gcd(right[i + 1], arr[i]);
        }

        int max = -1;
        int chosen = -1;
        for (int i = 0; i < N; i++) {
            int gcd = -1;
            if (i == 0) {
                gcd = right[1];
            } else if (i == N - 1) {
                gcd = left[N - 2];
            } else {
                gcd = gcd(left[i - 1], right[i + 1]);
            }

            if (max < gcd && arr[i] % gcd != 0) {
                max = gcd;
                chosen = arr[i];
            }
        }

        sb.append(max);
        if (max != -1) {
            sb.append(' ').append(chosen);
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
