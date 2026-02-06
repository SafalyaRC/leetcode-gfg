class Node {
    int data;
    Node left;
    Node right;
    Node(int x) {
        data = x;
    }
}


// GFG: https://www.geeksforgeeks.org/problems/minimum-element-in-bst/1

// better (extra space): TC-O(n) & SC-O(n)
class Solution {
    int min = 0;

    public void helper(Node root) {
        if (root == null)
            return;
        min = root.data;
        helper(root.left);
    }

    public int minValue(Node root) {
        helper(root);
        return min;
    }
}

// optimal (no extra space): TC-O(log n) & SC-O(1)
public class minBST {
    public int minValue(Node root) {
        if (root == null)
            return -1;
        while (root.left != null)
            root = root.left;
        return root.data;
    }
}
