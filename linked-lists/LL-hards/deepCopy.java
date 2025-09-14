// LC-138: https://leetcode.com/problems/copy-list-with-random-pointer/description/

// brute-force:
class Solution {
    public Node copyRandomList(Node head) {
        Node temp = head;
        HashMap<Node, Node> map = new HashMap<>(); // <original node, its copy node>

        while (temp != null) {
            Node newNode = new Node(temp.val);
            map.put(temp, newNode);
            temp = temp.next;
        }

        temp = head;
        while (temp != null) {
            Node copyNode = map.get(temp);
            copyNode.next = map.get(temp.next);
            copyNode.random = map.get(temp.random);
            temp = temp.next;
        }
        return map.get(head);
    }
}

// optimal:
public class deepCopy {
    public void insertCopyBetween(Node head) {
        Node temp = head;
        while (temp != null) {
            Node copyNode = new Node(temp.val);
            copyNode.next = temp.next;
            temp.next = copyNode;
            temp = copyNode.next;
        }
    }

    public void connectRandoms(Node head) {
        Node temp = head;
        while (temp != null) {
            Node copyNode = temp.next;
            if (temp.random != null) {
                copyNode.random = temp.random.next; // connect random to copy of temp's random
            } else {
                copyNode.random = null;
            }
            temp = copyNode.next;
        }
    }

    public Node getDeepCopy(Node head) {
        Node temp = head;
        Node dummyNode = new Node(-1);
        Node curr = dummyNode;

        while (temp != null) {
            curr.next = temp.next;
            curr = curr.next;

            // disconnect the original nodes with copy nodes
            temp.next = temp.next.next;
            temp = temp.next;
        }
        return dummyNode.next;
    }

    public Node copyRandomList(Node head) {
        if (head == null)
            return head;

        insertCopyBetween(head);
        connectRandoms(head);
        return getDeepCopy(head);
    }
}
