// LC-700: https://leetcode.com/problems/search-in-a-binary-search-tree/description/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

// Best and avg case TC & SC- O(log n), worst case TC & SC- O(n)
public class search {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val)
            return root;

        if (val < root.val)
            return searchBST(root.left, val);
        else
            return searchBST(root.right, val);
    }
}