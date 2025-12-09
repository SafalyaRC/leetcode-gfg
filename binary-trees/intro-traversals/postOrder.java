// LC-145: https://leetcode.com/problems/binary-tree-postorder-traversal/description/

// recursive:
import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.TreeNode;

public class postOrder {
    public void backtrack(List<Integer> ans, TreeNode root) {
        if (root == null)
            return;

        backtrack(ans, root.left);
        backtrack(ans, root.right);
        ans.add(root.val);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        backtrack(ans, root);
        return ans;
    }
}

// iterative(2 stacks):
class Solution1 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        st1.push(root);

        while (!st1.isEmpty()) {
            root = st1.pop();
            st2.push(root);
            if (root.left != null)
                st1.push(root.left);
            if (root.right != null)
                st1.push(root.right);
        }

        while (!st2.isEmpty()) {
            ans.add(st2.pop().val);
        }
        return ans;
    }
}

// iterative(1 stack):
class Solution2 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        Stack<TreeNode> st = new Stack<>();
        TreeNode curr = root;
        TreeNode temp = null;

        while (!st.isEmpty() || curr != null) {
            if (curr != null) {
                st.push(curr);
                curr = curr.left;
            } else {
                temp = st.peek().right;
                if (temp == null) {
                    temp = st.pop();
                    ans.add(temp.val);
                    while (!st.isEmpty() && temp == st.peek().right) {
                        temp = st.pop();
                        ans.add(temp.val);
                    }
                } else
                    curr = temp;
            }
        }
        return ans;
    }
}