// LC-200: https://leetcode.com/problems/number-of-islands/description/

/*
Intuition:

- The grid can be interpreted as a graph where each cell represents a node. A cell containing '1' represents land, while '0' represents water. Two land cells belong to the same island if they are connected horizontally or vertically. Therefore, the problem reduces to identifying how many connected components of land exist in the grid.

- When scanning the grid row by row, every time we encounter a land cell ('1'), it indicates the possible start of a new island. However, a single island may span multiple adjacent cells. If we simply count every '1', we would overcount islands. Hence, once a land cell is discovered, we must explore all land cells connected to it and treat them as part of the same island.

- Depth First Search (DFS) helps us achieve this exploration efficiently. Starting from a land cell, DFS recursively visits all neighboring land cells in four directions (up, down, left, right). This process continues until no connected land remains. Conceptually, DFS performs a flood-fill, spreading through the entire island before returning.

- While visiting cells during DFS, we mark them as visited by converting '1' into '0'. This is crucial because it prevents revisiting the same land cell again in future iterations. By sinking the island (turning land into water), we ensure each island is counted exactly once.

- The overall idea is therefore simple: traverse the grid, and whenever an unvisited land cell is found, increment the island count and run DFS to eliminate the entire connected landmass. Each DFS call removes one complete island from consideration, and the total number of such DFS initiations equals the number of islands.

TC & SC: O(m*n)
*/

public class noOfIslands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int m = grid.length, n = grid[0].length;
        int islands = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    dfs(grid, i, j, m, n);
                }
            }
        }
        return islands;
    }

    public void dfs(char grid[][], int r, int c, int m, int n) {
        if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] != '1')
            return;

        grid[r][c] = '0'; // mark the island as visited

        dfs(grid, r - 1, c, m, n);
        dfs(grid, r + 1, c, m, n);
        dfs(grid, r, c - 1, m, n);
        dfs(grid, r, c + 1, m, n);
    }
}
