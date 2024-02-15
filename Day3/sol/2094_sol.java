import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, M, X, Y;

    static class YearInfo{
        int year;
        int rain;
        public YearInfo(int year, int rain){
            this.year = year;
            this.rain = rain;
        }
    }

    static YearInfo[] yearArr;
    static int[] tree;
    static int leafPointer;

    static class IndexInfo{
        int index;
        int rain;
        public IndexInfo(int index, int rain){
            this.index = index;
            this.rain = rain;
        }
    }

    static Map<Integer, IndexInfo> indexMap = new HashMap<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        initTree();
        yearArr = new YearInfo[N];

        StringTokenizer st;
        int year, rain;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            year = Integer.parseInt(st.nextToken());
            rain = Integer.parseInt(st.nextToken());

            // y가 증가하는 순서대로 주어진다 = 정렬된 상태
            yearArr[i] = new YearInfo(year, rain);
            tree[leafPointer + i] = rain;
            indexMap.put(year, new IndexInfo(i, rain));
        }

        initMaxTree();

        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            bw.append(question(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static String question(int y, int x){
        String result;

        boolean containsY = indexMap.containsKey(y);
        boolean containsX = indexMap.containsKey(x);

        // Case #1 : Y, X 년 정보가 둘 다 없으면 -> maybe
        if(!containsY && !containsX){
            result = "maybe";
        }
        // Case #2 : X 년 정보만 있으면
        //  IF: (Y바로 이후 년도:pY) <= Z < X 범위의 Z의 max 값이 X보다 작으면 (Y년 rain 값 조작해서) = maybe
        //  ELSE: false
        else if(!containsY && containsX){
            int xTreeIndex = indexMap.get(x).index;

            // 1. Y 바로 이후의 값이 있는 년도 찾기
            // 1-1. Y의 upperbound = Y 보다 큰, 값이 있는 min year
            int pyTreeIndex = upperBoundYear(y);

            // 2. pY <= Z < X 사이 max 값 찾기
            int max = query(pyTreeIndex, xTreeIndex - 1);

            // 3. 결과 판단
            if(max >= indexMap.get(x).rain){
                result = "false";
            }else{
                result = "maybe";
            }
        }
        // Case #3 : Y 년 정보만 있으면
        //  IF: Y < Z  < (X바로 다음 년도:pX)의 max 값이 X보다 작으면 (X년 rain 값 조작해서) = maybe
        //  ELSE: false
        else if(containsY && !containsX){
            int yTreeIndex = indexMap.get(y).index;

            // 1. X 바로 다음의 값이 있는 년도 찾기
            // 1-1. X의 upperbound = X 보다 큰, 값이 있는 min year
            // 1-2. X의 upperbound - 1 해서 X바로 이전의 값이 있는 년도 구함
            int pxTreeIndex = upperBoundYear(x) - 1;

            // 2. Y < Z < pX 사이 max 값 찾기
            int max = query(yTreeIndex + 1, pxTreeIndex);

            // 3. 결과 판단
            if(max < indexMap.get(y).rain){
                result = "maybe";
            }else{
                result = "false";
            }
        }
        // Case #4 : X, Y 년 정보 둘 다 있으면(containsX && containsY)
        // IF: X ~ Y 년 사이가 전부 있으면 true 일 가능성(max값 쿼리해보고 만족안하면 false)
        // ELSE: maybe
        else{
            int yTreeIndex = indexMap.get(y).index;
            int xTreeIndex = indexMap.get(x).index;

            int max = query(yTreeIndex + 1, xTreeIndex - 1);
            if(
                max >= indexMap.get(x).rain ||
                indexMap.get(y).rain < indexMap.get(x).rain
            ){
                result = "false";
            }
            else if((y - x) == yTreeIndex - xTreeIndex){
                result = "true";
            }else{
                result = "maybe";
            }
        }

        return result;
    }

    static int query(int left, int right){
        // 범위 넘어가지 않도록
        left = Math.max(left, 0);
        right = Math.min(right, N-1);

        left += leafPointer;
        right += leafPointer;
        int result = 0;

        while(left <= right){
            if(left % 2 == 1){
                result = Math.max(result, tree[left]);
                left++;
            }

            if(right % 2 == 0){
                result = Math.max(result, tree[right]);
                right--;
            }

            left /= 2;
            right /= 2;
        }

        return result;
    }

    static void initMaxTree(){
        for(int i=leafPointer-1; i>0; i--){
            // tree 올라가면서 max rain 으로 채우기
            tree[i] = Math.max(tree[i*2], tree[(i*2)+1]);
        }
    }

    static void initTree(){
        int leafCount = 1;

        while(N > leafCount){
            // leafCount *= 2;
            leafCount <<= 1;
        }

        tree = new int[leafCount * 2];
        leafPointer = leafCount;
    }

    static int upperBoundYear(int target){
        int low = 0;
        int high = yearArr.length;
        int mid;

        while(low < high){
            mid = (low + high)/2;
            // 
            if(yearArr[mid].year > target){
                high = mid;
            }else{
                low = mid + 1;
            }
        }
        return high;
    }
}
