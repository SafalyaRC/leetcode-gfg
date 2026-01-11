// LC-2385: https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/description/

// we use BFS as burning is a kind of level wise movement, hence use not using DFS
// again convert the tree to an undirected graph to traverse upwards as well
// since the value of start node is given in integer and not its address, we find the start node in the markParent() itself

import java.util.*;
import javax.swing.tree.TreeNode;

public class infectBTtime {

    public TreeNode markParents(TreeNode root, Map<TreeNode, TreeNode> parentMap, int start) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        TreeNode startNode = null;

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            if (curr.val == start)
                startNode = curr; // to find the location of start node

            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                q.offer(curr.left);
            }
            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                q.offer(curr.right);
            }
        }
        return startNode;
    }

    public int amountOfTime(TreeNode root, int start) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        TreeNode startNode = markParents(root, parentMap, start);

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(startNode);
        Set<TreeNode> visited = new HashSet<>();
        visited.add(startNode);

        int minute = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            boolean infected = false; // so that we only consider a minute to be added if only even atleast one node
                                      // is infected

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();

                // explore left:
                if (curr.left != null && !visited.contains(curr.left)) {
                    infected = true;
                    visited.add(curr.left);
                    q.offer(curr.left);
                }

                // explore right:
                if (curr.right != null && !visited.contains(curr.right)) {
                    infected = true;
                    visited.add(curr.right);
                    q.offer(curr.right);
                }

                // explore upwards towards parent:
                if (parentMap.containsKey(curr) && !visited.contains(parentMap.get(curr))) {
                    infected = true;
                    visited.add(parentMap.get(curr));
                    q.offer(parentMap.get(curr));
                }
            }

            if (infected)
                minute++;
        }

        return minute;
    }
}