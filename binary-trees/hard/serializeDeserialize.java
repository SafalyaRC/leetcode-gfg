// LC-297: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

public class serializeDeserialize {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            if (curr == null)
                sb.append("null,");
            else {
                sb.append(curr.val).append(",");
                q.offer(curr.left);
                q.offer(curr.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0)
            return null;

        String values[] = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1; // to traverse the value array

        while (!q.isEmpty() && i < values.length) {
            TreeNode curr = q.poll();

            if (!values[i].equals("null")) {
                TreeNode leftNode = new TreeNode(Integer.parseInt(values[i]));
                curr.left = leftNode;
                q.offer(leftNode);
            }
            i++; // outside if condition as we increment the pointer even when we encounter null

            if (!values[i].equals("null")) {
                TreeNode rightNode = new TreeNode(Integer.parseInt(values[i]));
                curr.right = rightNode;
                q.offer(rightNode);
            }
            i++;
        }
        return root;
    }
}