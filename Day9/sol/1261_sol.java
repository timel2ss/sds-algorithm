import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static class Room implements Comparable<Room>{
        int rY;
        int rX;
        int cnt;
        Room(int rY, int rX, int cnt){
            this.rY = rY;
            this.rX = rX;
            this.cnt = cnt;
        }
        @Override
        public int compareTo(Room arg0) {
            // TODO Auto-generated method stub
            return this.cnt - arg0.cnt;
        }
    }
    public static int[][] Maps;	
    public static int N, M;		//세로 N, 가로 M
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());		
        M = Integer.parseInt(st.nextToken());	//가로 == 열
        N = Integer.parseInt(st.nextToken());	//세로 == 행
        Maps = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        for(int i = 1; i <= N; i++) {
            String inputStr = br.readLine();
            for(int j = 1; j <= inputStr.length(); j++)
                Maps[i][j] = inputStr.charAt(j-1) - '0';
        }

        int answer = dijkstra(1, 1);

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
    public static int dijkstra(int startY, int startX) {
        PriorityQueue<Room> pq = new PriorityQueue<>();		
        pq.add(new Room(startY, startX, 0));		
        visited[startY][startX] = true;

        while(!pq.isEmpty()) {
            Room r = pq.poll();
            int nowY = r.rY;
            int nowX = r.rX;
            int nowCnt = r.cnt;

            if(nowY == N && nowX == M)
                return nowCnt;			

            for(int i = 0; i <= 3; i++) {
                int nextY = nowY + dy[i];
                int nextX = nowX + dx[i];

                if(nextY < 1 || nextX < 1 || nextY > N || nextX > N)	continue;

                if(visited[nextY][nextX] == true)	continue;
                visited[nextY][nextX] = true;

                if(Maps[nextY][nextX] == 0) {
                    pq.add(new Room(nextY, nextX, nowCnt));				
                }else {
                    pq.add(new Room(nextY, nextX, nowCnt + 1));					
                }
            }
        }

        return 0;
    }
}
