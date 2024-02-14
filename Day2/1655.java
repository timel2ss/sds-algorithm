import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> max = new PriorityQueue<>(100_000, Collections.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>(100_000);

        int N = Integer.parseInt(br.readLine());

        max.add(Integer.parseInt(br.readLine()));
        sb.append(max.peek()).append('\n');
        
        for (int i = 1; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num <= max.peek()) {
                max.add(num);
            }
            else if (num > max.peek()) {
                min.add(num);
            }

            if (max.size() < min.size()) {
                max.add(min.poll());
            } else if (max.size() > min.size() + 1) {
                min.add(max.poll());
            }

            sb.append(max.peek()).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}