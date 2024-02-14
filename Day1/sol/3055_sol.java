import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;

    // 이동가능한 곳(상하좌우) (0,1), (-1,0), (1,0), (0,-1)
    static int[] dx = {0, -1, 1, 0};
    static int[] dy = {1, 0, 0, -1};

    static class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static Queue<Point> waterQueue;
    static Queue<Point> sQueue;
    static int answer = 0;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R =Integer.parseInt(st.nextToken());
        C =Integer.parseInt(st.nextToken());
        map = new char[R][C];

        waterQueue = new ArrayDeque<>();
        sQueue = new ArrayDeque<>();

        for(int i=0; i<R; i++){
            String input = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = input.charAt(j);

                if(map[i][j] == '*'){
                    waterQueue.add(new Point(i, j));
                }else if(map[i][j] == 'S'){
                    sQueue.add(new Point(i, j));
                }
            }
        }

        bfs();
        System.out.println(answer == Integer.MAX_VALUE ? "KAKTUS" : answer);
        br.close();

    }

    static void bfs(){
        // 1분 지남
        while(true){
            answer++;

            // 1분마다 queue에 있는 레코드 만큼만 poll 하도록 한다.
            int wSize = waterQueue.size();
            int sSize = sQueue.size();
            if(sSize == 0){
                // 움직일 수 있는 곳이 없으면 끝내기
                answer = Integer.MAX_VALUE;
                break;
            }

            // 물이 늘어난다 -> 물 bfs
            // 다음 타이밍에 물이 차오르는 곳으로 고슴도치는 갈 수 없다 -> 물 bfs 먼저해서 물 위치 마킹 update
            while(wSize > 0){
                wSize--;
                Point current = waterQueue.poll();

                for(int i=0; i<4; i++){
                    int nextX = current.x + dx[i];
                    int nextY = current.y + dy[i];

                    if(nextX>=0 && nextX<R && nextY>=0 && nextY<C
                        && map[nextX][nextY] == '.'
                    ) {
                        // 물 추가
                        map[nextX][nextY] = '*';
                        waterQueue.add(new Point(nextX, nextY));
                    }
                }
            } // end of water

            // 고슴도치(S) -> 비버(D) 길찾기 bfs
            // .으로만 한칸 이동하고 도달하면 브레이크
            while(sSize > 0){
                sSize--;
                Point current = sQueue.poll();

                for(int i=0; i<4; i++){
                    int nextX = current.x + dx[i];
                    int nextY = current.y + dy[i];

                    if(nextX>=0 && nextX<R && nextY>=0 && nextY<C) {
                        if(map[nextX][nextY] == '.'){
                            // 고슴도치 이동
                            map[nextX][nextY] = 'S';
                            sQueue.add(new Point(nextX, nextY));
                        }else if(map[nextX][nextY] == 'D'){
                            // 고슴도치가 비버에 도착(끝)
                            return;
                        }
                    }
                }
            } // end of 고슴도치

        }
    } // end of bfs

}


