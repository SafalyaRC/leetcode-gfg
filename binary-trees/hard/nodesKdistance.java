// LC-863: https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

import java.util.*;
import javax.swing.tree.TreeNode;

public class nodesKdistance {
    // we can mark the parent pointers (for upward movement) using a BFS then use another BFS and run the loop till we reach all the node(s) at K distance

    public void markParents(TreeNode root, Map<TreeNode, TreeNode> parentMap) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr.left != null) {
                q.offer(curr.left);
                parentMap.put(curr.left, curr);
            }
            if (curr.right != null) {
                q.offer(curr.right);
                parentMap.put(curr.right, curr);
            }
        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        // <Cuurent node, Current node's parent>
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        markParents(root, parentMap);

        // now find all the nodes present at 'k' distance from the target node
        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>(); // we use a set to prevent loops or stumbling upon the same nodes
        q.offer(target); // start the traversal initially with the target node
        visited.add(target); // mark the target node visited

        int dist = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            if (dist == k)
                break;

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();

                // explore left child
                if (curr.left != null && !visited.contains(curr.left)) {
                    q.offer(curr.left);
                    visited.add(curr.left);
                }

                // explore right child
                if (curr.right != null && !visited.contains(curr.right)) {
                    q.offer(curr.right);
                    visited.add(curr.right);
                }

                // explore upwards (take help of marked parent ptrs)
                if (parentMap.containsKey(curr) && !visited.contains(parentMap.get(curr))) {
                    q.offer(parentMap.get(curr));
                    visited.add(parentMap.get(curr));
                }
            }
            dist++;
        }

        // now the nodes left in the queue are the nodes at K distances aka our soln.
        while (!q.isEmpty()) {
            ans.add(q.poll().val);
        }
        return ans;
    }
}