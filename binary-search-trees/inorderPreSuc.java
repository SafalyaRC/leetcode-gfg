// GFG: https://www.geeksforgeeks.org/problems/predecessor-and-successor/1

// brute-force: find the inorder of the tree, then locate the predecessor and successor using BS since inorder of a BST is always sorted

// optimal: TC-O(H) & SC-O(1)

/*
🔷 Intuition: 
In a BST:
- Successor = smallest value greater than the node
- Predecessor = largest value smaller than the node
Instead of doing full inorder traversal, we simulate the search path.

Key observation: While moving from root toward target, Move left: Current node is a successor candidate, Move right: Current node is a predecessor candidate. Because: 
- Left subtree contains smaller values
- Right subtree contains larger values

🔷 Algorithm — Successor
- Start at root.
- If p.val >= root.val → successor must be on the right → move right.
- If p.val < root.val → current node can be successor → store it → move left.
- Continue until null.

🔷 Algorithm — Predecessor
- Start at root.
- If p.val <= root.val → predecessor must be on the left → move left.
- If p.val > root.val → current node can be predecessor → store it → move right.
- Continue until null.
*/

import java.util.ArrayList;
public class inorderPreSuc {
    public void findPredecessor(Node root, int key, ArrayList<Node> ans) {
        Node predecessor = null;
        while (root != null) {
            if (key > root.data) {
                predecessor = root;
                root = root.right;
            } else
                root = root.left;
        }
        ans.add(predecessor);
    }

    public void findSuccessor(Node root, int key, ArrayList<Node> ans) {
        Node successor = null;
        while (root != null) {
            if (key < root.data) {
                successor = root;
                root = root.left;
            } else
                root = root.right;
        }
        ans.add(successor);
    }

    public ArrayList<Node> findPreSuc(Node root, int key) {
        ArrayList<Node> ans = new ArrayList<>();
        findPredecessor(root, key, ans);
        findSuccessor(root, key, ans);
        return ans;
    }
}
