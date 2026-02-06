class Node {
    int data;
    Node left;
    Node right;
    Node(int x) {
        data = x;
    }
}

// GFG: https://www.geeksforgeeks.org/problems/floor-in-bst/1

// TC-O(h) & SC-O(1)

public class floor {
    public static int fl(Node root, int x) {
        int floor = -1;
        while (root != null) {
            if (root.data == x) {
                floor = root.data;
                return floor;
            } else if (x < root.data)
                root = root.left;
            else {
                floor = root.data;
                root = root.right;
            }
        }
        return floor;
    }
}
