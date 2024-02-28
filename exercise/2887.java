import java.util.*;
import java.io.*;

public class Main {	
    private static int[] parent;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Coord[] coords = new Coord[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            coords[i] = new Coord(i, x, y, z);
        }

        Queue<Edge> pq = new PriorityQueue<>(3 * N);

        Arrays.sort(coords, (c1, c2) -> c1.x - c2.x);
        for (int i = 0; i < N - 1; i++) {
            int diff = Math.abs(coords[i].x - coords[i + 1].x);
            pq.add(new Edge(coords[i].id, coords[i + 1].id, diff));
        }

        Arrays.sort(coords, (c1, c2) -> c1.y - c2.y);
        for (int i = 0; i < N - 1; i++) {
            int diff = Math.abs(coords[i].y - coords[i + 1].y);
            pq.add(new Edge(coords[i].id, coords[i + 1].id, diff));
        }

        Arrays.sort(coords, (c1, c2) -> c1.z - c2.z);
        for (int i = 0; i < N - 1; i++) {
            int diff = Math.abs(coords[i].z - coords[i + 1].z);
            pq.add(new Edge(coords[i].id, coords[i + 1].id, diff));
        }

        parent = new int[N];
        for (int i = 1; i < N; i++) {
            parent[i] = i;
        }

        int cnt = N - 1;
        long answer = 0;
        while (cnt > 0) {
            Edge edge = pq.poll();
            int rootA = find(edge.node1);
            int rootB = find(edge.node2);
            if (rootA != rootB) {
                parent[rootA] = rootB;
                answer += edge.cost;
                cnt--;
            }
        }

        System.out.print(answer);
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static class Coord {
        int id;
        int x;
        int y;
        int z;

        Coord(int id, int x, int y, int z) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Edge implements Comparable<Edge> {
        int node1;
        int node2;
        int cost;

        Edge(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.cost, edge.cost);
        }
    }
}