import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int T, K, C;
    static final long MAX = 1000000000L;
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        long[] result;
        long x, y;
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            // Cy = Kx + 1
            // Cy + K(-x) = 1

            // K와 C가 서로소여야 만족하는 정수해가 존재한다
            if(gcd(K, C) != 1){
                bw.write("IMPOSSIBLE");
                bw.newLine();
                continue;
            }
            result = eec(K, C);

            // Cy + K(-x) = 1
            // 1명당 Kx+1 개의 사탕 (-x가 양수일때까지 = x가 음수일때까지 -C)
            x = result[0];
            // 사장 y 봉지 (y가 양수일때까지 +K)
            y = result[1];

            // bw.write(tc + ":" + x + " " + y + "\n");
            // Cy + K(-x)
            // = (Cy + KC) + (K(-x) - KC)
            // = C(y + K) + K(-x - C)
            while(y <= 0 || x >= 0){
                y+=K;
                x-=C;
            }

            if(y > MAX){
                bw.write("IMPOSSIBLE");
                bw.newLine();
            }else{
                bw.write(y + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static long[] eec(long a, long b){
        // K(-x) + Cy = 1
        long s2, t2, r2;
        long s1, t1, r1;
        long s = 0, t = 0, r = 0, q = 0;

        s2 = 1; t2 = 0; r2 = a;
        s1 = 0; t1 = 1; r1 = b;

        /* System.out.println("s  t  r  q");
        System.out.println("==========");
        System.out.println(s2 + " " + t2 + " " + r2);
        System.out.println(s1 + " " + t1 + " " + r1); */

        while(r1 != 0){
            q = r2 / r1;
            r = r2 % r1;
            s = s2 - (q*s1);
            t = t2 - (q*t1);

            // System.out.println(s + " " + t + " " + r + " " + q);

            s2 = s1; t2 = t1; r2 = r1;
            s1 = s;  t1 = t;  r1 = r;
        }

        return new long[]{s2, t2};
    }

    static long gcd(long a, long b) {
        if(b==0) return a;
        return gcd(b, a%b);
    }
}
