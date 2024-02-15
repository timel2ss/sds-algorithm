import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, M, V;

    static class Jewel{
    // static class Jewel implements Comparable<Jewel>{
        int m, v;
        public Jewel(int m, int v){
            this.m = m;
            this.v = v;
        }
        /* @Override
        public int compareTo(Jewel o) {
            if(this.m == o.m){
                return Integer.compare(o.v, this.v);
            }
            return Integer.compare(this.m, o.m);
        } */
    }

    static Jewel[] jewelArr;
    static int[] bagArr;
    static long ANSWER;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N =Integer.parseInt(st.nextToken());
        K =Integer.parseInt(st.nextToken());

        jewelArr = new Jewel[N];
        bagArr = new int[K];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            M =Integer.parseInt(st.nextToken());
            V =Integer.parseInt(st.nextToken());
            jewelArr[i] = new Jewel(M, V);
        }

        for(int i=0; i<K; i++){
            bagArr[i] = Integer.parseInt(br.readLine());
        }

        br.close();

        // 보석 무게(M) 오름차순으로 정렬
        Arrays.sort(jewelArr, new Comparator<Jewel>(){
            @Override
            public int compare(Jewel o1, Jewel o2) {
                return Integer.compare(o1.m, o2.m);
            }
        });
        // 가방에 담을 수 있는 무게(C) 오름차순으로 정렬
        Arrays.sort(bagArr);

        // pq에는 전부 가방에 담을 수 있는 보석만 존재하므로 가격(V)에 대해 내림차순으로 정렬
        PriorityQueue<Jewel> pq = new PriorityQueue<>(new Comparator<Jewel>(){
            @Override
            public int compare(Jewel o1, Jewel o2) {
                return Integer.compare(o2.v, o1.v);
            }
        });
        int jewelArrIndex = 0;
        for(int i=0; i<K; i++){
            // i번째 가방에 넣을 수 있는 무게의 보석들을 pq에 추가한다.
            while (jewelArrIndex < N && jewelArr[jewelArrIndex].m <= bagArr[i]) {
                pq.add(jewelArr[jewelArrIndex++]);
            }
            if(!pq.isEmpty()){
                // 보석 가격의 합
                ANSWER += pq.poll().v;
            }
        }

        System.out.println(ANSWER);
    }

}
