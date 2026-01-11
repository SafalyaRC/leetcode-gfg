// GFG: https://www.geeksforgeeks.org/problems/children-sum-parent/1

import org.w3c.dom.Node;

public class childrenSum {
    public boolean isSumProperty(Node root) {
        // valid when it's a null node or a leaf node
        if (root == null || (root.left == null && root.right == null))
            return true;

        // calculate the values of left and right nodes
        int left = (root.left != null) ? root.left.data : 0;
        int right = (root.right != null) ? root.right.data : 0;

        if (root.data != left + right)
            return false;

        // recursively check for left and right subtrees
        return isSumProperty(root.left) && isSumProperty(root.right);
    }
}
