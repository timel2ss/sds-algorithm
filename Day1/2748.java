import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long dp0 = 0;
        long dp1 = 1;
        for (int i = 1; i <= n; i++) {
            long temp = dp0 + dp1;
            dp0 = dp1;
            dp1 = temp;
        }

        System.out.print(dp0);
    }
}