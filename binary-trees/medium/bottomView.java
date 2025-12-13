// GFG: https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import org.w3c.dom.Node;

public class bottomView {
    class Pair {
        int x;
        Node node;

        public Pair(Node node, int x) {
            this.node = node;
            this.x = x;
        }
    }

    public ArrayList<Integer> bottomView(Node root) {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();
            Node curr = p.node;
            int x = p.x;

            map.put(x, curr.data);

            if (curr.left != null)
                q.offer(new Pair(curr.left, x - 1));
            if (curr.right != null)
                q.offer(new Pair(curr.right, x + 1));
        }

        for (int val : map.values()) {
            ans.add(val);
        }
        return ans;
    }
}
