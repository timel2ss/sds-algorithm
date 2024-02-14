import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int S;
    static int[] arr;
    static int ANSWER = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        br.close();

        twoPointers();

        System.out.println(ANSWER == Integer.MAX_VALUE ? 0 : ANSWER);
    }

    static void twoPointers(){
        // ANSWER : 부분합이 S이상인 수열 중 가장 짧은것의 길이

        int subtotal = 0, left = 0, right = 0;
        while(true){
            // 부분합이 S보다 작으면 right 포인터 ++
            if(subtotal < S){
                // right pointer가 마지막 칸에 갔는데 subtotal이 목표치보다 작으면 끝
                if(right == arr.length){
                    break;
                }
                subtotal += arr[right];
                right++;
            }
            // 부분합이 S보다 크거나 같으면 left 포인터 ++
            else{
                subtotal -= arr[left];
                left++;
                ANSWER = Math.min(ANSWER, (right - left + 1));
            }

        }
    }

}
