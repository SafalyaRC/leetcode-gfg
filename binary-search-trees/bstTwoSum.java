
// brute-force: perform inorder traversal of BST and perform the two sum algo in the sorted inorder list

/* 
optimal: TC & SC- O(n) 

intuition: The goal is to determine if there exist two nodes in a BST whose values sum to a target k. A simple way to achieve this is to use the Two Sum approach: while traversing the tree, we store values we've seen in a HashSet and check if k - current node value exists in the set. If yes, we’ve found our pair.This problem doesn’t require BST-specific properties (like in-order traversal), so a simple DFS with HashSet works best.
*/

import java.util.*;
public class bstTwoSum {
    Set<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null)
            return false; // base case

        // if complement found, return true
        if (set.contains(k - root.val))
            return true;

        // else, add current node's value to set
        set.add(root.val);

        // recursively check on left and right subtrees
        return findTarget(root.left, k) || findTarget(root.right, k);
    }
}
