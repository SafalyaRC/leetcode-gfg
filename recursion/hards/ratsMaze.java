// GFG: https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1

import java.util.ArrayList;

public class ratsMaze {
    public boolean canMove(int maze[][], int visited[][], int row, int col, int n) {
        return (row >= 0 && col >= 0 && row < n && col < n && visited[row][col] == 0 && maze[row][col] == 1);
    }

    public void backtrack(int maze[][], int visited[][], ArrayList<String> ans, String curr, int n, int row, int col) {
        // base case:]
        if (row == n - 1 && col == n - 1) {
            ans.add(curr);
            return;
        }

        visited[row][col] = 1; // mark the current (row,col) so that we dont traverse this again

        // since we need ans in lexicographical order, move D,L,R,U in this manner

        // check if we can move down:
        if (canMove(maze, visited, row + 1, col, n)) {
            backtrack(maze, visited, ans, curr + 'D', n, row + 1, col); // if we can move down, then we do so
        }

        // check if we can move left:
        if (canMove(maze, visited, row, col - 1, n)) {
            backtrack(maze, visited, ans, curr + 'L', n, row, col - 1);
        }

        // check if we can move right:
        if (canMove(maze, visited, row, col + 1, n)) {
            backtrack(maze, visited, ans, curr + 'R', n, row, col + 1);
        }

        // check if we can move up:
        if (canMove(maze, visited, row - 1, col, n)) {
            backtrack(maze, visited, ans, curr + 'U', n, row - 1, col);
        }

        // unmark and revert back to previous stage:
        visited[row][col] = 0;
    }

    public ArrayList<String> ratInMaze(int[][] maze) {
        int n = maze.length;
        ArrayList<String> ans = new ArrayList<>();
        int visited[][] = new int[n][n];
        if (maze[0][0] == 1) {
            backtrack(maze, visited, ans, "", n, 0, 0);
        }
        return ans;
    }
}
