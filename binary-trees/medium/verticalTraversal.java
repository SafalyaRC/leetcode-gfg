// LC-987: https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/

class verticalTraversal {
    class NodePos {
        int val, row, col;

        public NodePos(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
    }

    // dfs function fills up the (value,row,col) for every node in the BT
    public void dfs(List<NodePos> values, TreeNode root, int row, int col) {
        if (root == null)
            return;
        values.add(new NodePos(root.val, row, col)); // add the current triplet of (val,row,col)
        dfs(values, root.left, row + 1, col - 1); // left child will be at position (row+1,col-1)
        dfs(values, root.right, row + 1, col + 1); // right child will be at position (row+1,col+1)
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<NodePos> values = new ArrayList<>(); // arraylist to store the (val,row,col) for every tree nodes
        dfs(values, root, 0, 0); // fill up the list

        // sort according to the question
        Collections.sort(values, (a, b) -> {
            if (a.col != b.col)
                return a.col - b.col; // column-wise sort
            if (a.row != b.row)
                return a.row - b.row; // row-wise sort
            return a.val - b.val; // value-wise sort
        });

        int prevCol = Integer.MIN_VALUE; // to ensure we keep track of last col. so that we know when two nodes are at different cols.
        List<List<Integer>> ans = new ArrayList<>();
        for (NodePos n : values) { // iterate over the list
            if (n.col != prevCol) { // add a new nested inner list only when col changes
                ans.add(new ArrayList<>()); // create an empty list then later proceed to fill values in it which are already sorted in previous steps
                prevCol = n.col;
            }
            ans.get(ans.size() - 1).add(n.val); // add the value to the arraylist which is at the last(most recent) index
        }
        return ans;
    }
}