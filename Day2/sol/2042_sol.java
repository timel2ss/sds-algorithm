import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static long[] input, tree;
    static int leafPointer;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        input = new long[N];
        for(int i=0; i<N; i++) {
            input[i]= Long.parseLong(br.readLine());
        }

        // tree 만들기
        initTree();

        int a, b;
        for(int i=0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if(a==1) { //1이면 update
                // b번째 수를 c로 바꾼다
                update(b, Long.parseLong(st.nextToken()));
            }else{ //2면 구간합 query
                bw.write(query(b, Integer.parseInt(st.nextToken())) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static long query(int left, int right){
        // left, right 포인터를 tree배열에서 찾을 수 있는 leafNode의 인덱스로 바꿔준다.
        left += leafPointer - 1;
        right += leafPointer - 1;
        long result = 0L;

        // left, right 포인터가 교차될 때까지
        while(left <= right){
            // left 포인터가 right child면 현재 값을 result에 더하고 left포인터를 오른쪽으로 하나 당긴다.
            if(left % 2 == 1){
                result += tree[left];
                left++;
            }
            // right 포인터가 left child면 현재 값을 result에 더하고 right포인터를 왼쪽으로 하나 당긴다.
            if(right % 2 == 0){
                result += tree[right];
                right--;
            }
            // 부모로 이동
            left /= 2;
            right /= 2;
        }

        return result;
    }

    //3. 조건에 따라 리프노드의 부모노드를 따라 루트노드까지 값을 업데이트 한다.
    // 구간합일 경우, 부모 = left child + right child
    // min/max heap일 경우, 부모 = min/max(left child, right child)
    // ex) tree[i] = tree[i*2] + tree[i*2+1]
    static void update(int index, long value){
        //index로 들어온 값을 tree배열에서 찾을 수 있는 leafNode의 인덱스로 바꿔준다.
        int treeIndex = leafPointer + index - 1 ;

        //리프노드의 값을 바꾸고
        tree[treeIndex] = value;
        //부모노드로 간다
        treeIndex /= 2;

        //부모노드->루트노드까지 값 update
        //구간합일 경우, 부모 = left child + right child
        //min/max heap일 경우, 부모 = min/max(left child, right child)
        while(treeIndex > 0){
            // tree 올라가면서 sum 으로 채우기
            tree[treeIndex] = tree[treeIndex*2] + tree[(treeIndex*2)+1];
            treeIndex /= 2;
        }
    }

    static void initTree(){
        int leafCount = 1;

        //1. input 데이터가 N개인 경우, 리프노드의 수 K 구하기
        //N <= 2^K 를 만족하는 최소값 K = leafCount
        //ex) N = 9일 경우, K = leafCount = 16
        while(N > leafCount){
             leafCount *= 2;
//            leafCount <<= 1;
        }

        //2. 리프노드의 사이즈를 바탕으로 전체 인덱스 트리의 크기를 구한다.
        //전체 인덱스 트리의 크기 = 2^(K+1)
        tree = new long[leafCount * 2];
        leafPointer = leafCount;

        for(int i=0; i<N; i++){
            tree[leafPointer + i] = input[i];
        }

        //3. 조건에 따라 리프노드의 부모노드를 따라 루트노드까지 값을 업데이트 한다.
        // 구간합일 경우, 부모 = left child + right child
        // min/max heap일 경우, 부모 = min/max(left child, right child)
        // ex) tree[i] = tree[i*2] + tree[i*2+1]
        for(int i=leafPointer-1; i>0; i--){
            // tree 올라가면서 sum 으로 채우기
            tree[i] = tree[i*2] + tree[(i*2)+1];
        }
    }
}
