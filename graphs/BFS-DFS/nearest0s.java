// LC-542: https://leetcode.com/problems/01-matrix/description/

/*
Intuition:
- This problem asks us to compute, for every cell containing 1, the minimum distance to the nearest 0. Since movement is allowed only in four directions and each move costs exactly 1, this is a classic shortest path problem in an unweighted grid.
- A naive approach would be to run BFS from every 1 cell separately to find the nearest 0. However, this would be inefficient because for each 1, we may end up scanning a large portion of the matrix, leading to quadratic time complexity.
- Instead, we reverse the perspective. Rather than asking: “How far is this 1 from a 0?”, we ask: “If all 0s start expanding simultaneously, how long does it take to reach each 1?” Since BFS guarantees the shortest path in an unweighted graph, the first time we reach a cell during BFS gives us its minimum distance from the nearest 0.
- This is called multi-source BFS, because instead of starting from one source, we start from all 0 cells at once. The wave-like expansion ensures that distances are computed optimally and efficiently in linear time.
*/

import java.util.*;
public class nearest0s {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        Queue<int[]> q = new LinkedList<>();
        int ans[][] = new int[m][n];
        boolean visited[][] = new boolean[m][n];

        // step-1: push all 0s into our queue
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    q.offer(new int[] { i, j });
                    visited[i][j] = true;
                }
            }
        }

        int directions[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        // step-2: multi-source BFS
        while (!q.isEmpty()) {
            int curr[] = q.poll();
            int r = curr[0], c = curr[1];

            for (int dir[] : directions) {
                int nr = r + dir[0], nc = c + dir[1];

                if (nr >= 0 && nc >= 0 && nr < m && nc < n && !visited[nr][nc]) {
                    ans[nr][nc] = ans[r][c] + 1;
                    visited[nr][nc] = true;
                    q.offer(new int[] { nr, nc });
                }
            }
        }
        return ans;
    }
}
