import java.util.*;
import java.io.*;

public class Main {
    private static final int MAX_SIZE = 1_000_000;
    private static boolean[] isPrime = new boolean[MAX_SIZE + 1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        init();

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            boolean flag = true;
            for (int i = n - 1; i >= 3; i -= 2) {
                if (isPrime[i] && isPrime[n - i]) {
                    sb.append(n).append(" = ").append(n - i).append(" + ").append(i).append('\n');
                    flag = false;
                    break;
                }
            }

            if (flag) {
                sb.append("Goldbach's conjecture is wrong.\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static void init() {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= MAX_SIZE; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= MAX_SIZE; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
}
