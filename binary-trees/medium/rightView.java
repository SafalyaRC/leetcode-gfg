// LC-199: https://leetcode.com/problems/binary-tree-right-side-view/

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

public class rightView {
    public void dfs(TreeNode root, int level, List<Integer> ans) {
        if (root == null)
            return;
        // ensures that we append only the first occured nodes at each level, since we traverse right first, then left, it ensures only the right most nodes are inserted
        if (level == ans.size())
            ans.add(root.val);

        dfs(root.right, level + 1, ans);
        dfs(root.left, level + 1, ans);
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, 0, ans);
        return ans;
    }
}
