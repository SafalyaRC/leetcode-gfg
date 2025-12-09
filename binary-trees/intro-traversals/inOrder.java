// LC-94: https://leetcode.com/problems/binary-tree-inorder-traversal/description/

// recursive:
import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.TreeNode;

public class inOrder {
    public void backtrack(List<Integer> ans, TreeNode root) {
        if (root == null)
            return;
        backtrack(ans, root.left);
        ans.add(root.val);
        backtrack(ans, root.right);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        backtrack(ans, root);
        return ans;
    }
}

// iterative:
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        TreeNode node = root;

        while (true) {
            if (node != null) {
                st.push(node);
                node = node.left;
            } else {
                if (st.isEmpty())
                    break;
                node = st.pop();
                ans.add(node.val);
                node = node.right;
            }
        }
        return ans;
    }
}
