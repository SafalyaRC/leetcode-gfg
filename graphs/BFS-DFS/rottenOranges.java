// LC-994: https://leetcode.com/problems/rotting-oranges/description/

/*
Intuition: The grid can be modeled as a graph where each cell is a node and edges exist between adjacent cells (up, down, left, right). Rotting spreads simultaneously from all rotten oranges every minute. This “simultaneous spread over time” is a textbook multi-source BFS scenario.
Instead of starting from one source, we start BFS from all initially rotten oranges at once.
Each BFS level represents one minute of time passing. During a minute, all currently rotten oranges infect their fresh neighbors. If, after the spread stops, at least one fresh orange still exists, it means it was unreachable → return -1.

However, we don't use DFS because if we do it will go as deep as possible to rot the oranges per unit time, which will never give us our required "MINIMUM" minutes. Hence, we use DFS since the rot in the 4 directions occur level-by-level.

Algorithm: 
1. Initialization
- Traverse the grid.
- Push coordinates of all rotten oranges (2) into a queue.
- Count the number of fresh oranges (1).
2. Edge case: If there are no fresh oranges, return 0.
3. Multi-source BFS
- While the queue is not empty: Process all elements currently in the queue (this is one BFS level = one minute).
- For each cell: Check its 4-directional neighbors.
- If a neighbor is fresh:
  - Mark it rotten.
  - Decrease fresh count.
  - Add it to the queue.
- If at least one orange rotted in this level, increment minutes.
4. Final check
- If fresh == 0, return minutes.
- Otherwise, return -1.

TC & SC: O(m*n)
*/

import java.util.*;

public class rottenOranges {
    static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<Pair> q = new LinkedList<>();
        int fresh = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2)
                    q.offer(new Pair(i, j));
                else if (grid[i][j] == 1)
                    fresh++;
            }
        }

        if (fresh == 0)
            return 0;

        int minutes = 0;
        int directions[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        while (!q.isEmpty()) {
            int size = q.size();
            boolean rotten = false;

            for (int i = 0; i < size; i++) {
                Pair curr = q.poll();

                for (int dir[] : directions) {
                    int row = curr.r + dir[0];
                    int col = curr.c + dir[1];

                    if (row >= 0 && col >= 0 && row < m && col < n && grid[row][col] == 1) {
                        rotten = true;
                        grid[row][col] = 2;
                        fresh--;
                        q.offer(new Pair(row, col));
                    }
                }
            }
            if (rotten)
                minutes++;
        }
        return (fresh == 0) ? minutes : -1;
    }
}
