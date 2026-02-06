// LC-230: https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/

// brute-force:   TC-O(nlogn+n) SC-O(n)
import java.util.*;

class Solution {
    public void dfs(TreeNode root, List<Integer> flatNodes) {
        if (root == null)
            return;
        flatNodes.add(root.val);
        dfs(root.left, flatNodes);
        dfs(root.right, flatNodes);
    }

    public int kthSmall(TreeNode root, int k) {
        ArrayList<Integer> flatNodes = new ArrayList<>();
        dfs(root, flatNodes);
        Collections.sort(flatNodes);
        return flatNodes.get(k - 1);
    }
}

// optimal: the inorder of a BST is always sorted, hence instead of flattening the nodes like brute force, we just find the inorder and return the kth element of inorder. TC-O(n) & SC-O(h)

// note: question for kth largest- the code will be same, except we'd need to perform a reverse inorder i.e. R,Root,L and print the kth element in it

public class kthSmallest {
    int k, ans;

    public int kthSmall(TreeNode root, int k) {
        this.k = k;
        inorder(root);
        return ans;
    }

    // perform inorder: L,Root,R
    public void inorder(TreeNode root) {
        if (root == null)
            return;

        inorder(root.left);
        if (--k == 0) {
            ans = root.val;
            return;
        }
        inorder(root.right);
    }
}
