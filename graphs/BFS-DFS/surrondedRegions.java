// LC-130: https://leetcode.com/problems/surrounded-regions/description/

/*
Intuition (Detailed Explanation): The problem asks us to flip all 'O' regions that are completely surrounded by 'X'. A region is considered surrounded only if none of its cells are connected to the boundary of the board. Trying to directly identify whether a region is surrounded can become complicated. For every 'O', we would need to check whether its connected component touches the boundary. That approach is repetitive and inefficient. Instead, we apply reverse thinking (reverse engineering): Rather than trying to find surrounded regions, we identify the regions that are not surrounded and protect them first.

Core Insight
Any 'O' cell that is: on the boundary, or Connected (directly or indirectly) to a boundary 'O' can never be surrounded.

Therefore: First, protect all boundary-connected 'O' cells. Then, whatever 'O' cells remain must be fully enclosed. Flip only those remaining 'O' cells to 'X'. To protect boundary-connected regions, we use DFS and temporarily mark them as 'T'. This distinguishes safe cells from potentially surrounded ones.

After marking:
- All remaining 'O' cells are guaranteed to be surrounded.
- We flip them.
- Finally, restore 'T' back to 'O'.
This approach ensures correctness and avoids unnecessary region validation logic.


Why This Works
- Boundary-connected regions cannot be surrounded.
- DFS ensures we capture the entire connected component.
- We separate the board into:
  - Safe region (T)
 -Surrounded region (O)
- Final conversion is straightforward.
- This pattern is commonly used in grid problems involving enclosure or boundary protection.

TC & SC: O(m*n)
*/

// we use reverse engineering here, instead of thinking about capturing the surronded regions, we capture everything except the unsurronded regions
public class surrondedRegions {
    public void dfs(char board[][], int r, int c, int m, int n) {
        if (r < 0 || c < 0 || r >= m || c >= n || board[r][c] != 'O')
            return;

        board[r][c] = 'T'; // mark the cell

        // check and mark if possible for it's 4-directional neighbors
        dfs(board, r - 1, c, m, n);
        dfs(board, r + 1, c, m, n);
        dfs(board, r, c - 1, m, n);
        dfs(board, r, c + 1, m, n);
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0)
            return;
        int m = board.length, n = board[0].length;

        // step-1: capture all boundary-connected unsurronded regions (mark O->T)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && (i == 0 || i == m - 1 || j == 0 || j == n - 1)) {
                    dfs(board, i, j, m, n); // run a dfs from the boundary 'O's since its 4-way neighbor 'O's will also be unsurronded
                }
            }
        }

        // step-2: mark all the surronded regions (O->X) since the unsurronded are already marked above
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }

        // step-3: restore the marked 'T' regions back to normal (T->O)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'T')
                    board[i][j] = 'O';
            }
        }
    }
}
