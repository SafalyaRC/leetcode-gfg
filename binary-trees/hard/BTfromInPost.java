// LC-106: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

import java.util.HashMap;
import java.util.Map;

import javax.swing.tree.TreeNode;

public class BTfromInPost {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, inMap);
    }

    public TreeNode buildTree(int inorder[], int inStart, int inEnd, int postorder[], int postStart, int postEnd,
            Map<Integer, Integer> inMap) {

        if (inStart > inEnd || postStart > postEnd)
            return null;

        TreeNode root = new TreeNode(postorder[postEnd]);

        int inorderRoot = inMap.get(root.val);
        int leftElements = inorderRoot - inStart;

        root.left = buildTree(inorder, inStart, inorderRoot - 1, postorder, postStart, postStart + leftElements - 1, inMap);
        root.right = buildTree(inorder, inorderRoot + 1, inEnd, postorder, postStart + leftElements, postEnd - 1, inMap);

        return root;
    }
}
