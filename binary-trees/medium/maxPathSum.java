// LC-124: https://leetcode.com/problems/binary-tree-maximum-path-sum/

public class maxPathSum {
    // sum of max-path at a node N: max(N.val+max-left+max-right) of all nodes
    int maxSum = Integer.MIN_VALUE;

    public int maxPath(TreeNode root) {
        if (root == null)
            return 0;
        int leftSum = Math.max(0, maxPath(root.left)); // compare with 0 so that it discards the -ve sums, since we want
                                                       // overall max. answer
        int rightSum = Math.max(0, maxPath(root.right));

        maxSum = Math.max(maxSum, root.val + leftSum + rightSum); // keep updating overall max path sum
        return root.val + Math.max(leftSum, rightSum); // controls the value returned upwards to the parent
    }

    public int maxPathSum(TreeNode root) {
        maxPath(root);
        return maxSum;
    }
}
