import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    static int N;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        // pq 두 개의 element 갯수를 맞춰가면서 써보자.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0; i<N; i++){
            int x = Integer.parseInt(br.readLine());

            if(maxHeap.size() == minHeap.size()){
                maxHeap.offer(x);
            }else{
                minHeap.offer(x);
            }

            if(!maxHeap.isEmpty() && !minHeap.isEmpty()){
                // [1,2,7] [5,10] -> [1,2,5] [7,10]
                if(maxHeap.peek() > minHeap.peek()){
                    // swap
                    int temp = maxHeap.poll();
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(temp);
                }
            }

            sb.append(maxHeap.peek() + "\n");
        }

        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();

    }
}

