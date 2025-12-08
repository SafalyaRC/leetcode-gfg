// LC-51: https://leetcode.com/problems/n-queens/description/

// brute-force:
import java.util.*;
class Solution {
    public boolean isSafe(char board[][], int row, int col, int n) {
        // we only check for left, upper-left & lower-left diagonal as for a specific value of col, we havent yet gone to the right so it's unneccsary to check for other directions concerning the right as they havent beem explored yet

        // check in the left direction:
        for (int j = col; j >= 0; j++) {
            if (board[row][j] == 'Q')
                return false;
        }

        // check upper-left diagonal:
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }

        // check lower-left diagonal:
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 'Q')
                return false;
        }

        return true; // when the placement of Q is safe as above conditions dont stand
    }

    public void backtrack(char board[][], List<List<String>> ans, int n, int col) {
        // base case:
        if (col == n) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                temp.add(new String(board[i]));
            }
            ans.add(temp);
            return;
        }

        // for a specific column, check in which row(s) of that column, we can place a Q
        for (int row = 0; row < n; row++) {
            // check from all directions if placing a queen is safe or not
            if (isSafe(board, row, col, n)) {
                board[row][col] = 'Q'; // place the Q
                backtrack(board, ans, n, col + 1); // recursively move to next column
                board[row][col] = '.'; // backtrack and revert back to previous stage
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char board[][] = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.'); // initial stage when no Qs are placed
        }
        backtrack(board, ans, n, 0);
        return ans;
    }
}


// optimal:
public class nQueens {
    // the main idea is to instead of checking for every row and diagonal, we keep three boolean arrays for left row, upper and lower diagonals respectively that we keep marking with 1s to indicate and check whether the queen we're about to place has its corresponding left row, upper and lower diagonals safe(marked=0)
    public void backtrack(char board[][], List<List<String>> ans, int leftRow[], int upperDiagonal[], int lowerDiagonal[], int n, int col) {
        if (col == n) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                temp.add(new String(board[i]));
            }
            ans.add(temp);
            return;
        }

        for (int row = 0; row < n; row++) {
            if (leftRow[row] == 0 && upperDiagonal[row + col] == 0 && lowerDiagonal[n - 1 + col - row] == 0) {
                // place the queen and mark the boolean arrays
                board[row][col] = 'Q';
                leftRow[row] = 1;
                upperDiagonal[row + col] = 1;
                lowerDiagonal[n - 1 + col - row] = 1;

                backtrack(board, ans, leftRow, upperDiagonal, lowerDiagonal, n, col + 1); // recursive call for next col

                // unplace queen and unmark the boolean arrays to revert back to previous stage
                board[row][col] = '.';
                leftRow[row] = 0;
                upperDiagonal[row + col] = 0;
                lowerDiagonal[n - 1 + col - row] = 0;
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char board[][] = new char[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(board[i], '.');
        int row[] = new int[n];
        int upperDiagonal[] = new int[2 * n - 1];
        int lowerDiagonal[] = new int[2 * n - 1];
        backtrack(board, ans, row, upperDiagonal, lowerDiagonal, n, 0); // start placing col by col
        return ans;
    }
}
