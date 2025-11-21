// LC-79: https://leetcode.com/problems/word-search/

public class wordSearch {
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;

        // iterate linearly to reach the (row,col) of our first letter match with String word
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (searchNext(board, word, i, j, m, n, 0))
                        return true;
                }
            }
        }
        return false;
    }

    public boolean searchNext(char board[][], String word, int row, int col, int m, int n, int index) {
        // base case-1:
        if (index == word.length()) {
            return true;
        }

        // base case-2:
        if (row < 0 || col < 0 || row == m || col == n || board[row][col] != word.charAt(index)
                || board[row][col] == '!') {
            return false;
        }

        char ch = board[row][col];
        board[row][col] = '!'; // we mark so that we dont revisit the same cell again

        // check the adjacent cells for a potential word match
        boolean top = searchNext(board, word, row - 1, col, m, n, index + 1);
        boolean bottom = searchNext(board, word, row + 1, col, m, n, index + 1);
        boolean left = searchNext(board, word, row, col - 1, m, n, index + 1);
        boolean right = searchNext(board, word, row, col + 1, m, n, index + 1);

        board[row][col] = ch; // undo the marking and revert back to previous stage

        return (top || bottom || left || right);
    }
}
