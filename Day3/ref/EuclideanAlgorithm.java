public class Main {
    public static void main(String[] args) throws Exception {
        int a=128, b=16;	//두 개의 자연수
        System.out.println(gcd(a, b));
        System.out.println(gcd2(a, b));
        System.out.println(lcm(a, b));
    }

    // 참고: 0은 어떤 수와 곱해도 0이기 때문에 0의 약수는 정의상 모든 정수가 된다.
    // 따라서, 0과 어떤 수의 최대공약수는 어떤 수 자기 자신이 된다.
    // gcd(n, 0) = 0

    //재귀로 하는 방법.
    static int gcd(int a, int b){

        // System.out.println("a = " + a + " b = " + b);
        // 재귀하다보면 gcd(b, a%b)에서 a라는 숫자를 b가 나누어 떨어뜨리는 순간이 온다.
        // 나누어 떨어졌다는 것은 그때 나눈 b라는 숫자가 최대공약수라는 뜻이다.
        if(b==0) return a;

        return gcd(b, a%b);
    }

    //반복문으로 하는 방법.
    static int gcd2(int a, int b) {
        int tmp;
        while(b!=0) {
            tmp=a%b;
            a=b;
            b=tmp;
        }
        return a;
    }

    //LCM 최소공배수 = (a*b)/gcd(a,b)
    static int lcm(int a, int b) {
        return a * b / gcd(a,b);
    }
}
