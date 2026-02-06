/*
Intuition: A BST iterator should return elements in ascending order, i.e., inorder traversal. Instead of flattening the tree (O(n) space), we simulate inorder traversal lazily using a stack. We simulate inorder traversal using a stack that stores the path to the next smallest node, achieving O(1) amortized next() and O(h) space.
*/

public class bstIterator {
    Stack<TreeNode> st;

    public BSTIterator(TreeNode root) {
        st=new Stack<>();
        pushAll(root);
    }

    public void pushAll(TreeNode root) {
        while (root != null) {
            st.push(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode poppedNode = st.pop();
        pushAll(poppedNode.right);
        return poppedNode.val;
    }

    public boolean hasNext() {
        return !st.isEmpty();
    }
}