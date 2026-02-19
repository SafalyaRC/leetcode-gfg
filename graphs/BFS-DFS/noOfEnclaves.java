// LC-1020: https://leetcode.com/problems/number-of-enclaves/description/

/*
Intuition (Detailed Explanation): 
We are given a grid where: 1 represents land, 0 represents water. We must count how many land cells are enclaves, meaning: Land cells that cannot reach the boundary by moving 4-directionally.

Key Observation: 
- If a land cell is connected (directly or indirectly) to a boundary land cell, then it is not an enclave, because from there it can walk outside the grid.
- So instead of checking every land cell individually to see if it reaches the boundary (which would be expensive), we reverse the thinking.

Reverse Engineering Strategy:
- Count total land cells.
- Find all land cells connected to the boundary.
- Subtract boundary-connected land cells from total land cells.
- Remaining land cells are enclaves.
This is identical in philosophy to LC-130 (Surrounded Regions).

Why This Works
- Any land connected to boundary is invalid.
- DFS from boundary land marks entire connected component.
- Each land cell is visited once.
- Linear time solution.

TC & SC: O(m*n)
*/

public class noOfEnclaves {
    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean visited[][] = new boolean[m][n];

        int totalOnes = 0; // total no. of landcells (1s)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    totalOnes++;
            }
        }

        int boundaryOnes = 0; // total no. of boundary 1s (land cells) or 1s connected to boundary 1s

        // now perform a DFS from all boundary 1s
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // since all 1s connected to boundary 1s can also reach the boundary 4-directionally
                if (grid[i][j] == 1 && !visited[i][j] && (i == 0 || i == m - 1 || j == 0 || j == n - 1)) {
                    boundaryOnes += dfs(grid, visited, i, j, m, n); // count the total boundary 1s using the dfs function
                }
            }
        }

        return totalOnes - boundaryOnes; // this will be our ans
    }

    public int dfs(int grid[][], boolean visited[][], int r, int c, int m, int n) {
        if (r < 0 || c < 0 || r >= m || c >= n || visited[r][c] || grid[r][c] != 1) {
            return 0; // base case
        }

        visited[r][c] = true;

        // simulate the dfs and count the required 1s and other 1s connected to it in all 4 directions
        int count = 1;
        count += dfs(grid, visited, r - 1, c, m, n);
        count += dfs(grid, visited, r + 1, c, m, n);
        count += dfs(grid, visited, r, c - 1, m, n);
        count += dfs(grid, visited, r, c + 1, m, n);

        return count;
    }
}
