import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int T, N, M;
    static int[] A, B;
    static long ANSWER = 0L;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine()); // 목표값

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++){
            A[n] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int m=0; m<M; m++){
            B[m] = Integer.parseInt(st.nextToken());
        }

        br.close();

        // Way #1. two pointers
        twoPointers();

        // Way #2. binary search
        // upper_bound : x보다 큰 값이 나타나는 첫번째 인덱스
        // lower_bound : x값이 나타나는 첫번째 인덱스
        // upper_bound - lower_bound = target 값의 갯수
//        binarySearch();

        // Way #3. hash
//        hashway();


        bw.write(ANSWER + "\n");
        bw.close();
    }

    static void twoPointers(){
        ArrayList<Integer> sumListA = new ArrayList<>();
        ArrayList<Integer> sumListB = new ArrayList<>();

        // A 부배열
        for(int i=0; i<N; i++){
            int sum = 0;
            for(int j=i; j<N; j++){
                sum += A[j];
                sumListA.add(sum);
            }
        }

        // B 부배열
        for(int i=0; i<M; i++){
            int sum = 0;
            for(int j=i; j<M; j++){
                sum += B[j];
                sumListB.add(sum);
            }
        }

        // sort
        Collections.sort(sumListA);
        Collections.sort(sumListB);

        int aListSize = sumListA.size();
        int bListSize = sumListB.size();

        int left = 0;
        int right = bListSize-1;
        while(left < aListSize && right >= 0){
            int tempSum = sumListA.get(left) + sumListB.get(right);

            // #1. 목표값(T) 보다 부배열의 합이 클때 -> right 포인터를 감소
            if(tempSum > T){
                right--;
            }
            // #2. 목표값(T) 보다 부배열의 합이 작을때 -> left 포인터를 증가
            else if(tempSum < T){
                left++;
            }
            // #3. 목표값(T)과 같으면 -> A, B 부배열에서 같은 숫자 갯수 세기 + 포인터 옮기기
            else{
                int a = sumListA.get(left);
                int b = sumListB.get(right);
                long countA = 0, countB = 0;

                while (left < aListSize && sumListA.get(left) == a) {
                    countA++;
                    left++;
                }
                while (right >= 0 && sumListB.get(right) == b) {
                    countB++;
                    right--;
                }
                ANSWER += countA * countB;
            }

        } // end of while

    } // end of two pointers

    static void binarySearch(){
        // 배열의 크기는 등차수열의 합 만큼의 크기
        // 1,2,3 => 1, 2, 3, 12, 13, 23, 123
        int[] sumListA = new int[N*(N+1)/2];
        int[] sumListB = new int[M*(M+1)/2];

        // A 부배열
        int index = 0;
        for(int i=0; i<N; i++){
            int sum = 0;
            for(int j=i; j<N; j++){
                sum += A[j];
                sumListA[index++] = sum;
            }
        }

        // B 부배열
        index = 0;
        for(int i=0; i<M; i++){
            int sum = 0;
            for(int j=i; j<M; j++){
                sum += B[j];
                sumListB[index++] = sum;
            }
        }

        // sort
        Arrays.sort(sumListA);
        Arrays.sort(sumListB);

        for(int i=0; i<sumListA.length; ) {
            int aValue = sumListA[i];
            long aCount = upperBound(sumListA, aValue) - lowerBound(sumListA, aValue);
            long bCount = upperBound(sumListB, T-aValue)- lowerBound(sumListB, T-aValue);
            ANSWER += aCount * bCount;
            i += aCount;
        }
    }

    /* upper_bound : x보다 큰 값이 나타나는 첫번째 인덱스 */
    static int upperBound(int[] arr, int x){
        int left = 0;
        int right = arr.length;
        int mid;

        while(left < right){
            mid = (left + right)/2;
            if(arr[mid] > x){
                right = mid;
            }else{
                // 버리는 부분
                left = mid + 1;
            }
        }
        return right;
    }

    /* lower_bound : x값이 나타나는 첫번째 인덱스 */
    static int lowerBound(int[] arr, int x){
        int left = 0;
        int right = arr.length;
        int mid;

        while(left < right){
            mid = (left + right)/2;
            if(arr[mid] >= x){
                right = mid;
            }else{
                // 버리는 부분
                left = mid + 1;
            }
        }
        return right;
    }


    static void hashway(){
        HashMap<Integer, Integer> mapA = new HashMap<>();
        // A 부배열
        for(int i=0; i<N; i++){
            int sum = 0;
            for(int j=i; j<N; j++){
                sum += A[j];
                if(mapA.containsKey(sum)){
                    int count = mapA.get(sum) + 1;
                    mapA.put(sum, count);
                }else{
                    mapA.put(sum, 1);
                }
            }
        }

        // B 부배열
        for(int i=0; i<M; i++){
            int sum = 0;
            for(int j=i; j<M; j++){
                sum += B[j];
                if(mapA.containsKey(T-sum)){
                    ANSWER += mapA.get(T-sum);
                }
            }
        }
    }

}
