import java.util.*;
import java.io.*;

public class Main {
    private static final int INF = 987_654_321;

    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) == '1' ? true : false;
            }
        }

        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[0][0] = 0;

        Queue<Coord> pq = new PriorityQueue<>(N * M);
        pq.add(new Coord(0, 0, 0));

        while (!pq.isEmpty()) {
            Coord c = pq.poll();

            if (dist[c.x][c.y] < c.cost) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }

                int distance = dist[c.x][c.y] + (map[nx][ny] ? 1 : 0);
                if (distance < dist[nx][ny]) {
                    dist[nx][ny] = distance;
                    pq.add(new Coord(nx, ny, distance));
                }
            }
        }

        System.out.print(dist[N - 1][M - 1]);
    }

    static class Coord implements Comparable<Coord> {
        int x;
        int y;
        int cost;

        Coord(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Coord coord) {
            return Integer.compare(this.cost, coord.cost);
        }
    }
}