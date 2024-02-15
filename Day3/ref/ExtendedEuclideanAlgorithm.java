public class Main {
    public static void main(String args[]) throws Exception {
        int a = 9;
        int b = 5;

        // a와 n가 서로소여야 만족하는 정수해가 존재한다
        if(gcd(a, b) != 1){
            System.out.println("정수해가 존재하지 않음");
            return;
        }

        eec(a, b);
    }

    static int[] eec(int a, int b){
        int s2, t2, r2;
        int s1, t1, r1;
        int s = 0, t = 0, r = 0, q = 0;

        s2 = 1; t2 = 0; r2 = a;
        s1 = 0; t1 = 1; r1 = b;

        System.out.println("s  t  r  q");
        System.out.println("==========");
        System.out.println(s2 + " " + t2 + " " + r2);
        System.out.println(s1 + " " + t1 + " " + r1);

        while(r1 != 0){
            q = r2 / r1;
            r = r2 % r1;
            s = s2 - (q*s1);
            t = t2 - (q*t1);

            System.out.println(s + " " + t + " " + r + " " + q);

            s2 = s1; t2 = t1; r2 = r1;
            s1 = s;  t1 = t;  r1 = r;
        }

        return new int[]{s2, t2};
    }

    static int gcd(int a, int b) {
        if(b==0) return a;
        return gcd(b, a%b);
    }
}
