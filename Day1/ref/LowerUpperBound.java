import java.util.Arrays;

public class Main {
    static int[] input = new int[]{1, 2, 2, 2, 3, 4, 5};
    // static int[] input = new int[]{1, 3, 4, 5};

    public static void main(String[] args) throws Exception{
//        System.out.println("# API index:" + Arrays.binarySearch(input, 4));
        System.out.println("# lowerbound index:" + lowerBound(2));
        System.out.println("# upperbound index:" + upperBound(2));
    }

    static int lowerBound(int target){
        int low = 0;
        int high = input.length;
        int mid;

        while(low < high){
            mid = (low + high)/2;
            // 
            if(input[mid] >= target){
                high = mid;
            }else{
                low = mid + 1;
            }
        }
        return high;
    }

    static int upperBound(int target){
        int low = 0;
        int high = input.length;
        int mid;

        while(low < high){
            mid = (low + high)/2;
            // 
            if(input[mid] > target){
                high = mid;
            }else{
                low = mid + 1;
            }
        }
        return high;
    }
}
