import java.util.*;

public class BTrepresentation {
    class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    Node root;

    public void insert() {
        System.out.println("Enter the root node: ");
        Scanner sc = new Scanner(System.in);
        int val = sc.nextInt();
        root = new Node(val);
        insert(root);
    }

    public void insert(Node node) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Do you want to enter the left of " + node.val);
        boolean left = sc.nextBoolean();
        if (left) {
            System.out.println("Enter value to the left of: " + node.val);
            int val = sc.nextInt();
            node.left = new Node(val);
            insert(node.left);
        }

        System.out.println("Do you want to enter the right of " + node.val);
        boolean right = sc.nextBoolean();
        if (right) {
            System.out.println("Enter the value of the right of " + node.val);
            int val = sc.nextInt();
            node.right = new Node(val);
            insert(node.right);
        }
    }

    public void display() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        display(root, "");
    }

    public void display(Node node, String indent) {
        if (node == null)
            return;

        System.out.println(indent + node.val);
        display(node.left, indent + "   ");
        display(node.right, indent + "   ");
    }

    public static void main(String[] args) {
        BTrepresentation tree = new BTrepresentation();

        System.out.println("=== Binary Tree Construction ===");
        tree.insert();

        System.out.println("\n=== Tree Structure ===");
        tree.display();
    }
}