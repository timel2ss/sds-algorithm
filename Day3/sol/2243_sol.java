import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, A, B, C;
    static final int MAX_TASTE = 1000000;
    static int[] tree;
    static int leafPointer;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        initTree();

        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            if(A == 1) { // 1이면 사탕 꺼내기
                B = Integer.parseInt(st.nextToken());
                // 사탕 꺼내기, B번째
                bw.write(pickup() + "\n");
            }else { // 2면 사탕 넣기
                B = Integer.parseInt(st.nextToken());
                C = Integer.parseInt(st.nextToken());
                // B맛 C개
                insert(B, C);
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int pickup(){
        // 시작 포인트 = 루트노드
        int i = 1;
        while(i < leafPointer){
            if(tree[i*2] >= B){
                // 좌측 서브트리로
                i *= 2;
            }else{
                // 우측 서브트리로
                B = B - tree[i*2];
                i = (i*2)+1;
            }
        }

        insert(i - leafPointer + 1, -1);
        return i - leafPointer + 1;
    }    

    static void insert(int index, int value){
        int treeIndex = leafPointer + index - 1 ;

        tree[treeIndex] += value;
        treeIndex /= 2;

        while(treeIndex > 0){
            // tree 올라가면서 sum 으로 채우기
            tree[treeIndex] = tree[treeIndex*2] + tree[(treeIndex*2)+1];
            treeIndex /= 2;
        }
    }

    static void initTree(){
        int leafCount = 1;

        while(MAX_TASTE > leafCount){
            // leafCount *= 2;
            leafCount <<= 1;
        }

        tree = new int[leafCount * 2];
        leafPointer = leafCount;
    }
}
