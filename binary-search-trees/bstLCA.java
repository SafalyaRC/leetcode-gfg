// LC-235: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/

/* 
the possibilities can be for nodes p and q:
 1. both p and q reside in the left
 2. both p and q reside in the right
 3. the current node is either p or q
 4. p lies in left, q in right or vice-versa 

intuition: the moment where we get the splitting factor that p/q lies in the left/right of a certain node, then that node will surely be our LCA as it's the deepest/lowest point where we get p/q from it's subtress. Imagine walking from the root toward p and q:
- As long as both targets go in the same direction, you keep moving
- The moment they go in different directions, you’ve reached their lowest common ancestor
- No need to store paths or backtrack.

note: if at a certain point, we find while traversing that p or q is encountered, we return p/q since the left/right of it has the other reqd. node
*/

public class bstLCA {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        if (p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);
        if (p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right, p, q);

        return root; // point of intersection found
    }
}
