// LC-257: https://leetcode.com/problems/binary-tree-paths/description/

import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.TreeNode;

public class leafPath {
    public boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public void dfs(TreeNode root, List<String> ans, StringBuilder curr) {
        int len = curr.length(); // save state for backtracking
        curr.append(root.val);

        if (isLeaf(root)) {
            ans.add(curr.toString());
        } else {
            curr.append("->");
            if (root.left != null) {
                dfs(root.left, ans, curr);
            }
            if (root.right != null) {
                dfs(root.right, ans, curr);
            }
        }
        curr.setLength(len); // backtrack
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null)
            return ans;

        dfs(root, ans, new StringBuilder());
        return ans;
    }
}