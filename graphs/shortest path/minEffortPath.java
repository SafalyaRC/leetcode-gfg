// LC-1631: https://leetcode.com/problems/path-with-minimum-effort/description/

// TC: O(E log V) = O (M*N log M*N) & SC: O(M*N)

import java.util.*;
public class minEffortPath {
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int dist[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], (int) 1e9);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // storing [effort,row,col] sorted by effort
        dist[0][0] = 0;
        pq.offer(new int[] { 0, 0, 0 });  // start dijkstra from (0,0)
        int dirs[][] = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } }; // to get 4-directional neighbors

        while (!pq.isEmpty()) {
            int curr[] = pq.poll();
            int r = curr[1], c = curr[2], effort = curr[0];

            if (r == m - 1 && c == n - 1)
                return effort; // destination reached
            if (effort > dist[r][c])
                continue; // to discard stale/outdated entries

            for (int dir[] : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int edgeEffort = Math.abs(heights[r][c] - heights[nr][nc]); // absolute difference between current and neighbor
                    int newEffort = Math.max(effort, edgeEffort);

                    if (newEffort < dist[nr][nc]) {
                        dist[nr][nc] = newEffort;
                        pq.offer(new int[] { dist[nr][nc], nr, nc });
                    }
                }
            }
        }
        return 0;
    }
}