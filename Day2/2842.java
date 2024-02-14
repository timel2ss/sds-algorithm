import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static char[][] map;
    private static boolean[][] visited;
    private static int[][] heights;
    private static int[] P;
    private static List<int[]> Ks = new ArrayList<>(2500);

    private static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        heights = new int[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
                
                if (map[i][j] == 'P') {
                    P = new int[] {i, j};
                }
                else if (map[i][j] == 'K') {
                    Ks.add(new int[] {i, j});
                }
            }
        }

        int[] H = new int[N * N];
        int count = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                heights[i][j] = Integer.parseInt(st.nextToken());
                H[count++] = heights[i][j];
            }
        }
        
        Arrays.sort(H);
        
        int length = 0;
        for (int i = 1; i < H.length; i++) {
            if (H[length] != H[i]) {
                H[++length] = H[i];
            }
        }

        int answer = Integer.MAX_VALUE;
        
        int left = 0;
        int right = 0;
        while (left <= right && left <= length && right <= length) {
            if (bfs(H[left], H[right])) {
                answer = Math.min(answer, H[right] - H[left]);
                left++;
            }
            else {
                right++;
            }
        }
        
        System.out.print(answer);
    }

    private static boolean bfs(int min, int max) {
        if (heights[P[0]][P[1]] < min || heights[P[0]][P[1]] > max) {
            return false;
        }
        
        visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>(2500);

        queue.add(P);
        visited[P[0]][P[1]] = true;
        
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            
            for (int i = 0; i < 8; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                if (heights[nx][ny] > max || heights[nx][ny] < min) {
                    continue;
                }

                queue.add(new int[] {nx, ny});
                visited[nx][ny] = true;
            }
        }

        for (int[] K : Ks) {
            if (!visited[K[0]][K[1]]) {
                return false;
            }
        }
        return true;
    }
}