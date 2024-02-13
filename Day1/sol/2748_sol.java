import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static final int MAX = 91;
    static long[] arr = new long[MAX];
    static final long[] example = new long[]{0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597};

    static int N;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        Arrays.fill(arr, -1L);
        System.arraycopy(example, 0, arr, 0, example.length);

        System.out.println(fibonacci(N));
    }

    static long fibonacci(int N){
        if(arr[N] == -1){
            arr[N] = fibonacci(N-1) + fibonacci(N-2);
        }
        return arr[N];
    }

}

