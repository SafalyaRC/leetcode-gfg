// LC-100: https://leetcode.com/problems/same-tree/description/

public class sameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true; // if both nodes null, they identical
        if (p == null || q == null)
            return false; // if only one of them null, they not identical

        // check if current nodes have same values and recursively check for the left and right subtrees as well
        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
