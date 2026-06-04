// LC-1091: https://leetcode.com/problems/shortest-path-in-binary-matrix/description/

// TC & SC: O(N^2) in a NxN grid

import java.util.*;
public class shortestPathBinaryMatrix {
    public int shortestPathBinaryMatrixx(int[][] grid) {
        int n = grid.length;

        // impossible case: when source/destination cell is blocked (1)
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
            return -1;

        if (n == 1)
            return 1; // if it's a single cell and open(0), we have reached destination at one step

        int dirs[][] = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } }; // to get 8-directional neighbors

        Queue<int[]> q = new LinkedList<>(); // store [row,col,steps_so_far] inside our queue
        boolean vis[][] = new boolean[n][n];

        // start BFS from (0,0) with a single step
        q.offer(new int[] { 0, 0, 1 });
        vis[0][0] = true;

        while (!q.isEmpty()) {
            int curr[] = q.poll();
            int r = curr[0], c = curr[1], steps = curr[2];

            if (r == n - 1 && c == n - 1)
                return steps; // reached the destination so return the steps

            // check every 8-directional neighbor that is open for a possible path
            for (int dir[] : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !vis[nr][nc] && grid[nr][nc] == 0) {
                    vis[nr][nc] = true;
                    q.offer(new int[] { nr, nc, steps + 1 });
                }
            }
        }
        return -1; // couldn't have reached the destination cell
    }
}
