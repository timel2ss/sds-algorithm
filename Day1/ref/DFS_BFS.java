import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * - 노드번호
 *         0
 *   1     2     3
 *  4     5 6   7
 *       8
 * 
 * - Depth First Search 탐색순서
 *         1
 *   2     4     8
 *  3     5 7   9
 *       6
 * 
 * - Breadth First Search 탐색순서
 *         1
 *   2     3     4
 *  5     6 7   8
 *       9
 *       
 */
public class Main {

    static final int NODE_CNT = 9;
    // adjacent list 
    static ArrayList<Integer>[] adjList; 
    // check if the vertex is visited 
    static boolean visited[]; 

    static void dfs(int curr_vertex) { 
        visited[curr_vertex] = true; //방문완료 표시
        System.out.print(curr_vertex + " ");
        // find the next vertex and go. 
        for(int i = 0; i < adjList[curr_vertex].size(); i++) { 
            if(!visited[adjList[curr_vertex].get(i)]) { //다음에 갈 수 있는 정점이 방문한 적이 없다면,
                dfs(adjList[curr_vertex].get(i)); // recursion 
            }
        } 
    }

    static void dfsStack(int startIdx) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(startIdx);
        visited[startIdx] = true;

        while(!stack.isEmpty()) {
            int curr_vertex = stack.pop();
            System.out.print(curr_vertex + " ");
            for(int i = 0; i < adjList[curr_vertex].size(); i++) { 
                if(!visited[adjList[curr_vertex].get(i)]) { //다음에 갈 수 있는 정점이 방문한 적이 없다면,
                    stack.push(adjList[curr_vertex].get(i));
                    visited[adjList[curr_vertex].get(i)] = true;
                } 
            } 
        }

    }

    static void bfs(int start_vertex) {
        ArrayDeque<Integer> Q = new ArrayDeque<Integer>(); 
        Q.add(start_vertex); 
        visited[start_vertex] = true;   

        while(!Q.isEmpty()) 
        { 
            int curr_vertex = Q.poll(); 
            System.out.print(curr_vertex + " ");
            // find the next vertex and go.   
            for (int i = 0; i < adjList[curr_vertex].size(); i++) { 
                if (!visited[adjList[curr_vertex].get(i)]) {   
                    visited[adjList[curr_vertex].get(i)] = true; 
                    Q.add(adjList[curr_vertex].get(i));    // add queue 
                }   
            }   
        } 
    }

    public static void main(String[] args) {
        adjList = new ArrayList[NODE_CNT];	// 인접리스트
        visited = new boolean[NODE_CNT];	// 방문여부 체크 배열

        // input
        for(int i=0; i<NODE_CNT; i++) {
            adjList[i] = new ArrayList<Integer>();
        }

        adjList[0].add(1);
        adjList[0].add(2);
        adjList[0].add(3);

        adjList[1].add(4);

        adjList[2].add(5);
        adjList[2].add(6);

        adjList[3].add(7);
        adjList[5].add(8);

        System.out.print("DFS 방문순서 : ");
        dfs(0);
        Arrays.fill(visited, false);
        System.out.print("\nDFS stack 방문순서 : ");
        dfsStack(0);
        Arrays.fill(visited, false);
        System.out.print("\nBFS 방문순서 : ");
        bfs(0);

    }

}
