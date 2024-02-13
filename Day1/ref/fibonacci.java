public class Main {

    static final int input = 10;
    public static void main(String[] args) {

        for(int i = 1; i <= input; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();
        for(int i = 1; i <= input; i++) {
            System.out.print(fibonacci2(i) + " ");
        }
        System.out.println();
        for(int i = 1; i <= input; i++) {
            System.out.print(fibonacci3(i) + " ");
        }

    }

    // ex) fibonacci(5) = fibonacci(3) + fibonacci(4)
    static int fibonacci(int n) {
        // 종료조건
        if(n <= 1) return n;
        // recursion
        else return fibonacci(n-2) + fibonacci(n-1);
    }

    static int fibonacci2(int n) {
        int a = 1, b = 1, c = 1;
        for(int i=3; i<=n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    static int[] D;
    static int fibonacci3(int n) {
        D= new int[input+1];
        D[1] = D[2] =1;
        for(int i=3; i<=n; i++) {
            D[i] = D[i-1] + D[i-2];
        }
        return D[n];
    }

}