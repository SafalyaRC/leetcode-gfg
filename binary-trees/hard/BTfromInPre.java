// Lc-105: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

// we cannot create an UNIQUE BT using pre and post order
// however using inorder and pre/post we can indeed make an unique BT, because only knowing the root isnt important, what comes to its L and R also is
// our entire job is basically construction of appropriate subtrees
// since preoder is Root,L,R we first search for the root in preorder then locate the root in the inorder for its left and right subtrees and then recursively continue this algo

class BTfromInPre {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // store <value,index> from in-order to later identify the left and right subtree for each root inside the inorder
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        TreeNode constructedRoot = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
        return constructedRoot;
    }

    public TreeNode buildTree(int preorder[], int preStart, int preEnd, int inorder[], int inStart, int inEnd,
            Map<Integer, Integer> inMap) {
        // when no more nodes are left to process:
        if (preStart > preEnd || inStart > inEnd)
            return null;

        TreeNode root = new TreeNode(preorder[preStart]); // find the root of subtree in first element of preorder

        // locate the root and it's index for left and right subtrees in the in-order
        int inorderRoot = inMap.get(root.val);
        int leftElements = inorderRoot - inStart; // no. of elements in left subtree

        // dry-run to understand the function parameters:
        root.left = buildTree(preorder, preStart + 1, preStart + leftElements, inorder, inStart, inorderRoot - 1, inMap);
        root.right = buildTree(preorder, preStart + 1 + leftElements, preEnd, inorder, inorderRoot + 1, inEnd, inMap);

        return root;
    }
}