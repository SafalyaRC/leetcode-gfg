// LC-114: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/

// brute-force:
import java.util.*;
class Solution {
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        List<TreeNode> preList = new ArrayList<>();
        preorder(root, preList);

        for (int i = 0; i < preList.size() - 1; i++) {
            preList.get(i).left = null;
            preList.get(i).right = preList.get(i + 1);
        }
    }

    public void preorder(TreeNode root, List<TreeNode> preList) {
        if (root == null)
            return;
        preList.add(root);
        preorder(root.left, preList);
        preorder(root.right, preList);
    }
}

// optimal:
public class flattenBTtoLL {
    // we can convert the BT to LL using a morris kind of traversal for each root of a subtree, we connect the rightmost node of the left subtree
    // to the immediate right child of the root along with connecting the root's right to it's left (understand this visually in dry-run)
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            // if left subtree exists, find it's rightmost node
            if (curr.left != null) {
                TreeNode prev = curr.left;
                while (prev.right != null)
                    prev = prev.right;

                prev.right = curr.right; // connect rightmost node to the immediate right child of root
                curr.right = curr.left; // connect root's right to root's left to flatten it
                curr.left = null; // as the question states, left ptr must be null
            }
            curr = curr.right; // process the next node
        }
    }
}
