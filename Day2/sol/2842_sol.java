import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int N;

    static class Point{
        int x, y;
        char type;
        int altitude;

        public Point(int x, int y, char type){
            this.x = x;
            this.y = y;
            this.type = type;
            this.altitude = 0;
        }
    }
    static Point[][] map;

    // P(우체국) = bfs 시작좌표
    static int px;
    static int py;
    // K(집) 갯수
    static int kCount = 0;

    // sort가 가능한 treeSet
    static TreeSet<Integer> altitudeSet =  new TreeSet<>();
    static Integer[] altitudeArr;

    // 이동가능한 곳(상하좌우) (0,1), (-1,0), (1,0), (0,-1)
    // 이동가능한 곳(대각) (-1,1), (-1,-1), (1,1), (1,-1)
    static int[] dx = {0, -1, 1, 0, -1, -1, 1, 1};
    static int[] dy = {1, 0, 0, -1, 1, -1, 1, -1};

    // min = 1, max = 1000000
    static int ANSWER = 1000000 - 1;

    static int MIN = Integer.MAX_VALUE, MAX = Integer.MIN_VALUE;
    static int MIN_INDEX, MAX_INDEX;


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new Point[N][N];

        String row;
        for(int i=0; i<N; i++){
            row = br.readLine();
            for(int j=0; j<N; j++){
                map[i][j] = new Point(i, j, row.charAt(j));
                if(map[i][j].type == 'P'){
                    px = i;
                    py = j;
                }else if(map[i][j].type == 'K'){
                    kCount++;
                }
            }
        }

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j].altitude = Integer.parseInt(st.nextToken());
                altitudeSet.add(map[i][j].altitude);
                if(map[i][j].type == 'P' || map[i][j].type == 'K'){
                    // P와 K는 방문할 수 있어야만 한다.
                    MIN = Math.min(MIN, map[i][j].altitude);
                    MAX = Math.max(MAX, map[i][j].altitude);
                }
            }
        }

        br.close();

        // alt 구간에서 bfs로 모든 K가 방문가능하면 만족
        // new Integer[0] : 배열 크기 자동지정
        altitudeArr = altitudeSet.toArray(new Integer[0]);
        MIN_INDEX = Arrays.binarySearch(altitudeArr, MIN);
        MAX_INDEX = Arrays.binarySearch(altitudeArr, MAX);

        twoPointers();

        System.out.println(ANSWER);
    }

    static void twoPointers(){
        int left = 0;
        int right = MAX_INDEX;  // P와 K는 방문할 수 있어야만 한다.

        while(left <= right && right < altitudeArr.length
        && left <= MIN_INDEX    // P와 K는 방문할 수 있어야만 한다.
        // && right <= MAX_INDEX
        ){
            if(isPossible(altitudeArr[left], altitudeArr[right])){
                ANSWER = Math.min(ANSWER, altitudeArr[right] - altitudeArr[left]);
                left++;
            }else{
                right++;
            }
        }
    }

    static boolean isPossible(int altLow, int altHigh){
        Queue<Point> q = new ArrayDeque<>();
        q.add(map[px][py]);
        boolean[][] visited = new boolean[N][N];
        visited[px][py] = true;
        int kVisitedCount = 0;

        while(!q.isEmpty()){
            Point currentPoint = q.poll();
            for(int i=0; i<8; i++){
                int nextX = currentPoint.x + dx[i];
                int nextY = currentPoint.y + dy[i];

                if(nextX>=0 && nextX<N && nextY>=0 && nextY<N
                    && map[nextX][nextY].altitude >= altLow
                    && map[nextX][nextY].altitude <= altHigh
                ) {
                    if(visited[nextX][nextY]) continue;

                    visited[nextX][nextY] = true;
                    if(map[nextX][nextY].type == 'K'){
                        kVisitedCount++;
                        if(kCount == kVisitedCount){
                            return true;
                        }
                    }
                    q.add(map[nextX][nextY]);
                }
            }
        }

        return false;
    }
}
