import java.util.*;
import java.io.*;

public class Main {
    private static int n;
    private static int[] year;
    private static int[] amount;
    private static int[] tree;
    private static Map<Integer, Integer> indexMap;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        indexMap = new HashMap<>(n);
        year = new int[n + 1];
        amount = new int[n + 1];
        tree = new int[4 * n];
        
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            year[i] = y;
            int r = Integer.parseInt(st.nextToken());
            amount[i] = r;
            indexMap.put(y, i);
        }

        init(1, 1, n);

        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int Y = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());

            boolean hasY = indexMap.containsKey(Y);
            boolean hasX = indexMap.containsKey(X);

            if (hasY && hasX) {
                int idxY = indexMap.get(Y);
                int idxX = indexMap.get(X);
                int maxAmount = query(idxY + 1, idxX - 1, 1, 1, n);
                if (maxAmount >= amount[idxX] || amount[idxY] < amount[idxX]) {
                    sb.append("false\n");
                }
                else if (Y - X == idxY - idxX) {
                    sb.append("true\n");
                }
                else {
                    sb.append("maybe\n");
                }
            } else if (!hasY && hasX) {
                int idxY = upperBound(Y);
                int idxX = indexMap.get(X);
                int maxAmount = query(idxY, idxX - 1, 1, 1, n);
                if (maxAmount < amount[idxX]) {
                    sb.append("maybe\n");
                    continue;
                }
                sb.append("false\n");
            } else if (hasY && !hasX) {
                int idxY = indexMap.get(Y);
                int idxX = upperBound(X);
                int maxAmount = query(idxY + 1, idxX - 1, 1, 1, n);
                if (maxAmount < amount[idxY]) {
                    sb.append("maybe\n");
                    continue;
                }
                sb.append("false\n");
            } else {
                sb.append("maybe\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static int query(int start, int end, int index, int left, int right) {
        if (right < start || end < left) {
            return 0;
        }

        if (left == right) {
            return tree[index];
        }

        int mid = (left + right) / 2;
        int leftMax = query(start, end, index * 2, left, mid);
        int rightMax = query(start, end, index * 2 + 1, mid + 1, right);
        return Math.max(leftMax, rightMax);
    }

    private static int init(int index, int left, int right) {
        if (left == right) {
            return tree[index] = amount[left];
        }

        int mid = (left + right) / 2;
        int leftMax = init(index * 2, left, mid);
        int rightMax = init(index * 2 + 1, mid + 1, right);
        return tree[index] = Math.max(leftMax, rightMax);
    }

    private static int upperBound(int X) {
        int lo = 1;
        int hi = n;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (year[mid] > X) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

}