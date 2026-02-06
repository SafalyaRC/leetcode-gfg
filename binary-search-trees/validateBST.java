// LC-98: https://leetcode.com/problems/validate-binary-search-tree/description/

/*
Intuition: A BST cannot be validated by checking only parent–child relationships. Because a node is constrained not just by its parent, but by all of its ancestors. Instead of asking: “Is this node greater than its left child and smaller than its right child?”. Ask:“Is this node allowed to exist here in the tree?”.

Each node must lie within a valid numeric range:
- The root can take any value → (-∞, +∞)
- When you go left, the maximum allowed value becomes the parent’s value
- When you go right, the minimum allowed value becomes the parent’s value
These bounds accumulate as you go deeper.

Algorithm:
- Start with the full valid range (-∞, +∞)
- For each node: If node.val ≤ min or node.val ≥ max → return false
- Recursively validate:
 - Left subtree with range (min, node.val)
 - Right subtree with range (node.val, max)
- If all nodes satisfy their range → valid BST

# since it's impossible to validate a BST with naked eyes, we ensure that for each node, they fall between a certain range, for example in [5,1,6,N,N,4,8]: it's false because node 4 lies in the right of root node 5. hence it may have been valid if the left of 6 had a node in the range- [5<node<6], this is the intuition.

TC-O(N) SC-O(1)

*/

public class validateBST {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null)
            return true;
        if (root.val >= max || root.val <= min)
            return false;

        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
}
