import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static class A {
        int index;
        int value;
        public A(int index, int value){
            this.index = index;
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        // L 크기의 윈도우 내에서 최솟값을 구하라
        // -> dequeue 을 L 크기로 유지
        // -> 현재 최솟값은 deque의 첫번째 객체
        ArrayDeque<A> deque = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        int input = 0;
        for(int i=0; i<N; i++){
            input = Integer.parseInt(st.nextToken());

            // 1. 들어오는 node 값 보다 deque의 마지막 객체의 값이 크면 해당 객체 삭제
            while(!deque.isEmpty() && deque.peekLast().value >  input){
                deque.removeLast();
            }

            deque.add(new A(i, input));

            // 2. 윈도우 범위에서 index가 벗어나면 deque에서 첫번째 객체 삭제
            if(deque.peekFirst().index <= i-L){
                deque.removeFirst();
            }

            sb.append(deque.peekFirst().value + " ");
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
