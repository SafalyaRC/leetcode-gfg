class Node {
    int data;
    Node left;
    Node right;
    Node(int x) {
        data = x;
    }
}


// GFG: https://www.geeksforgeeks.org/problems/implementing-ceil-in-bst/1

/*
In a Binary Search Tree:
Left subtree → values < node.val
Right subtree → values > node.val

For Ceil(x): We want the smallest value ≥ x

Decision logic at each node:
- If node.val == x → Exact match → this is the ceil
- If node.val < x → Ceil cannot be here or on the left → move right
- If node.val > x → This can be a candidate → store it and move left to find a smaller valid one
This greedy narrowing is what makes the solution optimal.

TC-O(h) & SC-O(1)
*/

public class ceil {
    int findCeil(Node root, int x) {
        int ceil = -1;
        while (root != null) {
            if (x == root.data) {
                ceil = root.data;
                return ceil;
            } else if (x > root.data)
                root = root.right;
            else {
                ceil = root.data;
                root = root.left;
            }
        }
        return ceil;
    }
}
