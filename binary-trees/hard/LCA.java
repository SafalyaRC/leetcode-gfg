// LC-236: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

// brute-force:

// we can brute force it by finding the paths from root->p and root->q and taking the last common element in their paths
class Solution {
    // use a boolean function so that after founding the correct path from root to target, recursion stops
    public boolean dfs(TreeNode root, List<TreeNode> path, TreeNode target) {
        if (root == null)
            return false;
        path.add(root);

        if (root == target)
            return true; // when target found
        // recursively check for left and right subtrees for the path to target
        if (dfs(root.left, path, target) || dfs(root.right, path, target))
            return true;

        path.remove(path.size() - 1); // backtrack when target not found in the current path
        return false;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return root;
        List<TreeNode> pPath = new ArrayList<>();
        List<TreeNode> qPath = new ArrayList<>();

        dfs(root, pPath, p);
        dfs(root, qPath, q);

        int i = 0;
        while (i < pPath.size() && i < qPath.size() && pPath.get(i) == qPath.get(i)) {
            i++;
        }
        return pPath.get(i - 1);
    }
}

// optimal:

// for the left and right subtrees, ensure that whenever we get a node that is either equal to p or q, that node returns it, now if at a particular node we get the required nodes p and q from its subtrees, we have got our answer for the LCA and return the node, where we got our answer at
// if a subtree is returning null, another is returning a node (either p or q), we take the node into our consideration and not the null
public class LCA {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root==p || root==q) return root; // return null if the node is null, however if we got the reqd. nodes- p or q, return them

        // recursively check for left and right subtrees
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);

        // if either of the subtrees are null and the other one has p/q, return p/q 
        if(left==null) return right;
        else if(right==null) return left;
        
        // if both subtrees are not null, we have got out LCA, return it
        else return root;
    }
}
