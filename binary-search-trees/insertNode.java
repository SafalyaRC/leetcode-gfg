// LC-701: https://leetcode.com/problems/insert-into-a-binary-search-tree/description/

/*
TC-O(h) SC-O(1)

Intuition: A Binary Search Tree (BST) enforces this invariant:
- Left subtree → values less than the node
- Right subtree → values greater than the node

To insert a value:
- Start from the root
- At every node, decide left or right based on comparison
- Keep moving down until you hit a null child
- Insert the new node there

Key insight: Insertion in a BST always happens at a leaf position, and the structure above that point remains unchanged.
*/

class insertNode {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        TreeNode curr = root;

        while (true) {
            if (val < curr.val) {
                if (curr.left != null)
                    curr = curr.left;
                else {
                    curr.left = new TreeNode(val);
                    break;
                }
            }

            else {
                if (curr.right != null)
                    curr = curr.right;
                else {
                    curr.right = new TreeNode(val);
                    break;
                }
            }
        }

        return root;
    }
}