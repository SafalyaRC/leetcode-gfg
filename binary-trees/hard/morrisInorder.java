
// we use the concept of threaded BTs to perform morris traversal without any extra space O(1) as opposed to that of O(N) in normal traversal approaches

/*  the basic idea is that while inorder since we do L,Root,R : we ensure that the rightmost node of the left subtree is connected to the root of the subtree via thread

case-1: when the left subtree doesnt exist, we simply print the Root as L is null so next we go for Root then ->Right
case-2: when the left subtree exists:
  2.1: traverse as right as possible to find the rightmost node
  2.2: if at any point we encounter that no right nodes exists (is null), we found the rightmost node, hence make the thread linked to the root of the subtree and now we can traverse the left of the left subtree that we were processing till now
case-3: after traversing the 'L' of inorder, when we return to the root of subtree, we check:
  3.1: if a thread already exists, remove that thread connection, print the root for our inorder and move right
  3.2: if no thread exists, continue steps 2.1 and 2.2 to build a thread and eventually traverse leftwards in the subtree

*/

import java.util.*;

public class morrisInorder {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) { // case-1
                ans.add(curr.val);
                curr = curr.right;
            } else { // case-2
                TreeNode prev = curr.left; // save the root of the subtree to establish a thread later
                // case-2.1:
                while (prev.right != null && prev.right != curr) { // ensure we dont run into null or thread back to root
                    prev = prev.right;
                }

                if (prev.right == null) { // 2.2
                    prev.right = curr;
                    curr = curr.left;
                } else { // 3.1
                    prev.right = null;
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }
        }

        return ans;
    }
}
