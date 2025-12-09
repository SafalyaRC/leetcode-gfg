// LC-144: https://leetcode.com/problems/binary-tree-preorder-traversal/description/

// recursive:
class preOrder {
    public void backtrack(TreeNode root, List<Integer> ans) {
        if (root == null)
            return;

        ans.add(root.val);
        backtrack(root.left, ans);
        backtrack(root.right, ans);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        backtrack(root, ans);
        return ans;
    }
}

// iterative:
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();

        if (root == null)
            return ans; // when tree is empty

        st.push(root);
        while (!st.isEmpty()) {
            root = st.pop();
            ans.add(root.val);

            if (root.right != null)
                st.push(root.right);
            if (root.left != null)
                st.push(root.left);
        }
        return ans;
    }
}