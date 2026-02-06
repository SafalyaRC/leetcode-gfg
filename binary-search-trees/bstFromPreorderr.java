// LC-1008: https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/

// brute-force: for each node in the preorder, place it acc to BST property in a O(n*n) TC

// better: in a BST if we sort the nodes, we get the inorder. Hence, sort the pre-order to get in-order, next we can use the code we previously had learnt to construct a BT from its preorder and inorder

/*
optimal:  TC-O(n) & SC-O(h)

Preorder traversal order is: Root → Left Subtree → Right Subtree
So when reading the array from left to right: 
- The first element is always the root
- All subsequent values less than root belong to the left subtree
- The remaining values belong to the right subtree
- But we don’t know where left ends and right begins explicitly.

Key Insight: Instead of splitting arrays or searching, we use a valid range to decide where each value belongs.Each node in a BST must lie within a certain upper bound.
So while reading preorder- If the current value exceeds the allowed upper bound → it does not belong to this subtree. That’s how we implicitly separate left and right.

Consider preorder: [8, 5, 1, 7, 10, 12]
When constructing the left subtree of 8:
- Allowed values must be < 8
- Once we encounter 10, it violates the bound → we stop left subtree
- We never revisit elements — single pass.

Role of ub (Upper Bound) ub represents:
- The maximum value allowed for nodes in the current subtree.
- For left subtree → ub = root.val
- For right subtree → ub = parent’s ub
This propagates constraints just like BST validation problem.

Algorithm Steps: 
- Maintain a global index i (current preorder position).
- Recursive function build(preorder, ub): If i is out of bounds OR preorder[i] > ub → return null (node doesn't belong here)
- Create a node with preorder[i], increment i.
- Build left subtree: 
 - Allowed values must be < root.val
 - root.left = build(preorder, root.val)
- Build right subtree:
 - Allowed values must be < ub
 - root.right = build(preorder, ub)
- Return root.
*/

public class bstFromPreorderr {
    int i = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder(preorder, Integer.MAX_VALUE);
    }

    public TreeNode bstFromPreorder(int preorder[], int ub) {
        if (i == preorder.length || preorder[i] > ub)
            return null;

        TreeNode root = new TreeNode(preorder[i++]);
        root.left = bstFromPreorder(preorder, root.val);
        root.right = bstFromPreorder(preorder, ub);

        return root;
    }
}
