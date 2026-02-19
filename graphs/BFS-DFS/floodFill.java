// LC-733: https://leetcode.com/problems/flood-fill/description/

/*
Intuition: The image can be viewed as a grid-based graph where each pixel is a node connected to its 4-directional neighbors (up, down, left, right). Flood fill means recoloring only the connected region that starts from (sr, sc) and has the same initial color as the starting pixel.
The key idea is:
- First, identify the initial color at the starting cell.
- From (sr, sc), recursively move to neighboring cells only if: 
  - They are inside the grid
  - They still have the initial color
- While traversing, repaint each valid cell with the target color.
- Repainting also acts as a visited marker, preventing infinite recursion.
If the initial color is already equal to the target color, no work is needed, so we return immediately.

Algorithm: 
- Store the color of the starting pixel as initialColor.
- If initialColor == targetColor, return the image (nothing to change).
- Start a DFS from (sr, sc).
- In DFS:
  - If the current cell is out of bounds, return.
  - If the current cell is not of initialColor, return.
  - If the current cell is already colored with targetColor, return.
  - Recolor the current cell to targetColor.
  -Recursively call DFS for the four neighboring cells: Up, Down, Left, Right.
- After DFS completes, return the modified image.

We use DFS here since there is no specific condition we need to follow, however we could've used BFS as well.

TC & SC: O(m*n)
*/

public class floodFill {
    public int[][] floodFilll(int[][] image, int sr, int sc, int color) {
        int initialColor = image[sr][sc];
        if (initialColor == color)  // to not waste a recursion step
            return image;
        dfs(image, sr, sc, initialColor, color, image.length, image[0].length);
        return image;
    }

    public void dfs(int image[][], int row, int col, int initialColor, int targetColor, int m, int n) {
        if (row < 0 || col < 0 || row >= m || col >= n || image[row][col] == targetColor
                || image[row][col] != initialColor) {
            return;
        }
        image[row][col] = targetColor;
        dfs(image, row - 1, col, initialColor, targetColor, m, n);
        dfs(image, row + 1, col, initialColor, targetColor, m, n);
        dfs(image, row, col + 1, initialColor, targetColor, m, n);
        dfs(image, row, col - 1, initialColor, targetColor, m, n);
    }
}
