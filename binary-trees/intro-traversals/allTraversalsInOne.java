import java.util.ArrayList;
import java.util.Stack;

public class allTraversalsInOne {
    class TreeNode {
        int val;
        Node left, right;

        public Node(int val){
            this.val=val;
        }
    }

    class Pair {
        Node x;
        int y;

        public Pair(Node x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public List<List<Integer>> allTraversals(TreeNode root) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> post = new ArrayList<>();

        Stack<Pair> st = new Stack<>();
        st.push(new Pair(root, 1));

        while (!st.isEmpty()) {
            Pair p = st.peek();

            if (p.y == 1) {
                st.peek().y++;
                pre.add(p.x.val);
                if (p.x.left != null)
                    st.push(new Pair(p.x.left, 1));
            }

            else if (p.y == 2) {
                st.peek().y++;
                in.add(p.x.val);
                if (p.x.right != null)
                    st.push(new Pair(p.x.right, 1));
            }

            else {
                post.add(p.x.val);
                st.pop();
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(pre);
        ans.add(in);
        ans.add(post);
        return ans;
    }
}
