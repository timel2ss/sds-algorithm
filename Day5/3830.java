import java.util.*;
import java.io.*;

public class Main {
    private static int[] parent;
    private static int[] weights;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            parent = new int[N + 1];
            weights = new int[N + 1];
            for (int i = 0; i <= N; i++) {
                parent[i] = i;
            }

            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                String operation = st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (operation.equals("!")) {
                    int weight = Integer.parseInt(st.nextToken());
                    union(a, b, weight);
                } else {
                    if (find(a) == find(b)) {
                        sb.append(weights[b] - weights[a]).append('\n');
                    } else {
                        sb.append("UNKNOWN\n");
                    }
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        int root = find(parent[x]);
        weights[x] += weights[parent[x]];
        return parent[x] = root;
    }

    private static void union(int a, int b, int weight) {
        int rootA = find(a);
        int rootB = find(b);
        parent[rootB] = rootA;
        weights[rootB] = weight - weights[b] + weights[a];
    }
}