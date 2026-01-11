// LC-222: https://leetcode.com/problems/count-complete-tree-nodes/description/

import javax.swing.tree.TreeNode;

public class countCompleteTreeNodes {
    // for any node N we can count using: {no of nodes left of N + no of nodes right of N + 1 (for N itself)}
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        int l = countNodes(root.left);
        int r = countNodes(root.right);
        return l + r + 1;
    }
}
