// LC-99: https://leetcode.com/problems/recover-binary-search-tree/description/

/* TC & SC- O(N)

The cases where swapped elements exists in a sorted array (inorder of a bst is sorted):
 - the swapped elements are non adjacent
 - the swapped elements are adjacent

Steps: when we perform inorder without any extra space, we notice violations when the current element is smaller than previous element, in that case, mark the violating element which we later swap along with the previous element. if we get a second violation, that means the elements were not adjacent, hence swap the violating elements, however if we dont get a second violation, in that case we just swap the violating element and it's marked previous element as they were adjacent indeed.
*/

public class recoverBST {
    TreeNode first, prev, second;

    public void recoverTree(TreeNode root) {
        inorder(root);
        // swap the violations:
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    public void inorder(TreeNode root) {
        if (root == null)
            return;

        inorder(root.left);

        // detect swapped nodes
        if (prev != null && prev.val > root.val) {
            if (first == null)
                first = prev;
            second = root;
        }
        prev = root;

        inorder(root.right);
    }
}
