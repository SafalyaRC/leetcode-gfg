// LC-543: https://leetcode.com/problems/diameter-of-binary-tree/

// brute-force:
class Solution {
    // diameter of a tree is, for any node N: max(leftHeight+rightHeight)
    int diameter = 0; // consider a global variable

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return diameter;
    }

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int lHeight = maxDepth(root.left);
        int rHeight = maxDepth(root.right);

        diameter = Math.max(diameter, lHeight + rHeight); // keep updating the global diameter variable
        return 1 + Math.max(lHeight, rHeight); // return the max depth of the nodes
    }
}

// optimal(without global variable):
public class diameterBT {
    // maxDepth()[height,diameter]
    public int[] maxDepth(TreeNode root) {
        if (root == null)
            return new int[] { 0, 0 };

        int left[] = maxDepth(root.left);
        int right[] = maxDepth(root.right);
        int lHeight = left[0];
        int rHeight = right[0];
        int currHeight = 1 + Math.max(lHeight, rHeight);

        int maxDiameter = Math.max(Math.max(left[1], right[1]), lHeight + rHeight);
        return new int[] { currHeight, maxDiameter };
    }

    public int diameterOfBinaryTree(TreeNode root) {
        return maxDepth(root)[1];
    }
}
