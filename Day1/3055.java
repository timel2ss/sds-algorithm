import java.util.*;
import java.io.*;

public class Main {
    private static int R, C;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static char[][] map;
    private static boolean[][] visited;
    private static Queue<int[]> queue;
    private static int[] S;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];
        queue = new ArrayDeque<>(2500);

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '*') {
                    queue.add(new int[] {i, j, 0, 0}); // 0 == '*'
                    visited[i][j] = true;
                }
                else if (map[i][j] == 'S') {
                    S = new int[] {i, j, 1, 0}; // 1 == 'S'
                }
            }
        }

        queue.add(S);
        visited[S[0]][S[1]] = true;

        int result = bfs();
        if (result == -1) {
            bw.write("KAKTUS");
        } else {
            bw.write(String.valueOf(result));
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static int bfs() {
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    continue;
                }
                if (map[nx][ny] == 'X' || visited[nx][ny]) {
                    continue;
                }
                if (map[nx][ny] == 'D' && pos[2] == 0) {
                    continue;
                }
                if (map[nx][ny] == 'D' && pos[2] == 1) {
                    return pos[3] + 1;
                }

                queue.add(new int[] {nx, ny, pos[2], pos[3] + 1});
                visited[nx][ny] = true;
            }
        }
        return -1;
    }
}