// GFG: https://www.geeksforgeeks.org/problems/flattening-a-linked-list/1

// brute-force:
class Solution {
    public Node flatten(Node root) {
        // code here
        List<Integer> arr = new ArrayList<>();
        Node temp = root;

        while (temp != null) {
            Node inside = temp;
            while (inside != null) {
                arr.add(inside.data);
                inside = inside.bottom;
            }
            temp = temp.next;
        }

        Collections.sort(arr);
        Node dummyNode = new Node(-1);
        Node t = dummyNode;
        for (int val : arr) {
            Node newNode = new Node(val);
            t.bottom = newNode;
            t = t.bottom;
        }
        return dummyNode.bottom;
    }
}

// optimal:
class flattenLL {
    public Node mergeTwoListsVertically(Node head1, Node head2) {
        Node dummyNode = new Node(-1);
        Node temp = dummyNode;

        Node t1 = head1, t2 = head2;

        while (t1 != null && t2 != null) {
            if (t1.data <= t2.data) {
                temp.bottom = t1;
                temp = temp.bottom;
                t1 = t1.bottom;
            } else {
                temp.bottom = t2;
                temp = temp.bottom;
                t2 = t2.bottom;
            }
            temp.next = null;
        }
        if (t1 != null)
            temp.bottom = t1;
        else if (t2 != null)
            temp.bottom = t2;

        return dummyNode.bottom;
    }

    public Node flatten(Node head) {
        // code here
        if (head == null || head.next == null)
            return head;

        Node mergedHead = flatten(head.next);
        return mergeTwoListsVertically(head, mergedHead);
    }
}