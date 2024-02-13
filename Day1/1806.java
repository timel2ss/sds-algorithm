import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        int i = 0;
        int sum = 0;
        for (int j = 0; j < N; j++) {
            sum += arr[j];

            while (sum >= S) {
                min = Math.min(min, j - i + 1);
                sum -= arr[i++];
            }
        }

        if (min == Integer.MAX_VALUE) {
            System.out.print(0);
        }
        else {
            System.out.print(min);
        }
    }
}