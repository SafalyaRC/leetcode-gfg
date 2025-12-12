// LC-110: https://leetcode.com/problems/balanced-binary-tree/

// brute-force:
import javax.swing.tree.TreeNode;
class Solution {
    public int findHeight(TreeNode root) {
        if (root == null)
            return 0;
        int l = findHeight(root.left);
        int r = findHeight(root.right);
        return 1 + Math.max(l, r);
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        int lHeight = findHeight(root.left);
        int rHeight = findHeight(root.right);

        if (Math.abs(lHeight - rHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right)) {
            return true;
        }
        return false;
    }
}

// optimal:
public class balancedBT {
    public int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        int lHeight = getHeight(root.left);
        if (lHeight == -1)
            return -1;
        int rHeight = getHeight(root.right);
        if (rHeight == -1)
            return -1;

        if (Math.abs(lHeight - rHeight) > 1)
            return -1;
        return 1 + Math.max(lHeight, rHeight);
    }

    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }
}
