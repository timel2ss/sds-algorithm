import java.io.*;

public class Main {
    private static boolean[] cols = new boolean[16];
    private static boolean[] upper = new boolean[32];
    private static boolean[] lower = new boolean[32];

    private static int N, count;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        solve(0);
        System.out.print(count);
    }

    private static void solve(int row) {
        if (row == N) {
            count++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (cols[col] || upper[row + col] || lower[col - row + N - 1]) {
                continue;
            }
            cols[col] = true;
            upper[row + col] = true;
            lower[col - row + N - 1] = true;
            solve(row + 1);
            cols[col] = false;
            upper[row + col] = false;
            lower[col - row + N - 1] = false;
        }
    }
}