import java.util.Arrays;

/*
 * N * (1/2)^K = 1 -> K = logN
 * O(logN)
 */
public class Main {

    public static void main(String[] args) {
//		int[] input = new int[] {4, 6, 11, 19, 21, 50, 77, 81, 99, 100};
        int[] input = new int[] {4, 6, 11, 19, 21, 50, 77, 81, 99, 99, 100};
        Arrays.sort(input);
        binarySearch(input, 99);

        // 중복값이 있을 경우 먼저 찾은 값의 인덱스를 반환
        System.out.println("API : " + Arrays.binarySearch(input, 99));

        // 없는 값을 넣으면 그 값이 위치할 곳을 음수로 리턴한다. input의 최소값보다 작은 구간에 있으면 -1
        System.out.println("API : " + Arrays.binarySearch(input, 102));
    }

    static void binarySearch(int[] array, int target) {
        int low = 0, high = array.length-1, mid = 0;

        while(low <= high) {
            mid = (low + high)/2; //pivot 잡기
            printArray(low, mid, high, array); //탐색 과정을 보여주기 위한 method

            // target이 중간값이면 찾은 것!
            if(target == array[mid]) {
                System.out.println(target + "의 index : " + mid);
                break;
            }

            // target 값이 중간값보다 작으면, 다음에는 아래쪽 절반을 탐색
            if(target < array[mid]) {
                high = mid - 1;
            } 
            // target 값이 중간값보다 크면, 다음에는 위쪽 절반을 탐색
            else {
                low = mid + 1;
            }
        }
    }

    static void printArray(int low, int mid, int high, int[] array) {
        System.out.print("low = "+ low + " mid = "+ mid + " high = "+ high +" [");
        for(int i = low; i <= high; i++ ) {
            if(i == high) System.out.print(array[i]);
            else System.out.print(array[i]+", ");
        }
        System.out.println("]");
    }


}