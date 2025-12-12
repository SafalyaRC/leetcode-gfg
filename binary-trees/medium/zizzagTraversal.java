// LC-103: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/

import java.util.*;

public class zizzagTraversal {
    // modified version of level-order traversal, where we use a boolean variable to determine whether to traverse L->R or R->L, depending on the value of the boolean variable and flipping it accordingly
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean leftToRight = true;

        while (!q.isEmpty()) {
            int levelNodes = q.size();
            Integer level[] = new Integer[levelNodes];

            for (int i = 0; i < levelNodes; i++) {
                TreeNode node = q.poll();

                // determine which index to add in the temporary level array based on the flag's
                // boolean value
                int index = leftToRight ? i : levelNodes - i - 1;
                level[index] = node.val;

                // add the left child
                if (node.left != null)
                    q.add(node.left);
                // add the right child
                if (node.right != null)
                    q.add(node.right);
            }

            leftToRight = !leftToRight;
            ans.add(Arrays.asList(level));
        }
        return ans;
    }
}
