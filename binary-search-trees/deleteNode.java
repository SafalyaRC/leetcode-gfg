// LC-450: https://leetcode.com/problems/delete-node-in-a-bst/description/

// iterative: TC-O(h) & SC-O(1)

/*
Intuition (Iterative): 
- Search the node to delete using BST property.
- Keep track of its parent.
- When found:
 -0 or 1 child → directly link parent to the child.
 -2 children: Promote the left subtree, Find the rightmost node of the left subtree, Attach the original right subtree there, Reconnect the modified subtree back to the parent.

Key idea: Attach the deleted node’s left subtree to the parent, and attach the deleted node’s right subtree to the extreme right of the left subtree. The rightmost node of the left subtree is the largest value smaller than the deleted node, so attaching the right subtree there preserves BST order.

Algorithm:
- Initialize: curr = root, parent = null
- Search for key:
 -If key < curr.val → move left
 -If key > curr.val → move right
- When curr is found:
 -If curr.left == null → replacement = curr.right
 -Else if curr.right == null → replacement = curr.left
 -Else:
 - leftSub = curr.left
 - rightSub = curr.right
- Find rightmost node in leftSub
- Attach rightSub
- replacement = leftSub
- Attach replacement to parent
- Return root
*/

public class deleteNode {
    public TreeNode delete(TreeNode root, int key) {
        TreeNode curr = root;
        TreeNode parent = null;

        // search for the key node to be deleted:
        while (curr != null && curr.val != key) {
            parent = curr;
            if (key < curr.val)
                curr = curr.left;
            else
                curr = curr.right;
        }

        if (curr == null)
            return root; // if the key is not found, return the tree as it is

        TreeNode replacement; // determine replacement subtree (subtree root that will take the place of the deleted node in its parent’s child pointer)

        // case-1: no left child
        if (curr.left == null)
            replacement = curr.right;

        // case-2: no right child
        else if (curr.right == null)
            replacement = curr.left;

        // case-3: both children exist
        else {
            TreeNode leftSubtree = curr.left;
            TreeNode rightSubtree = curr.right;

            TreeNode rightmostNode = leftSubtree; // determine the rightmost node of left subtree
            while (rightmostNode.right != null) {
                rightmostNode = rightmostNode.right; // go as right as possible
            }
            rightmostNode.right = rightSubtree;
            replacement = leftSubtree;
        }

        if (parent == null)
            return replacement;

        // attach replacement to parent
        if (parent.left == curr)
            parent.left = replacement;
        else
            parent.right = replacement;

        return root;
    }
}

// recursive: TC-O(log n) & SC-O(h) 

class Solution {

    public TreeNode delete(TreeNode root, int key) {
        if (root == null)
            return null;

        if (key < root.val) {
            root.left = delete(root.left, key);
        } else if (key > root.val) {
            root.right = delete(root.right, key);
        } else {
            // Node to delete found

            // Case 1: no left child
            if (root.left == null)
                return root.right;

            // Case 2: no right child
            if (root.right == null)
                return root.left;

            // Case 3: two children
            TreeNode leftSubtree = root.left;
            TreeNode rightSubtree = root.right;

            TreeNode rightMost = leftSubtree;
            while (rightMost.right != null) {
                rightMost = rightMost.right;
            }

            rightMost.right = rightSubtree;
            return leftSubtree;
        }

        return root;
    }
}
