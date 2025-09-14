// GFG: https://www.geeksforgeeks.org/problems/remove-duplicates-from-a-sorted-doubly-linked-list/1

public class removeDuplicatesDoublyLL {
    public Node removeDuplicates(Node head) {
        if (head == null)
            return null;

        Node temp = head;
        while (temp != null && temp.next != null) {
            if (temp.data == temp.next.data) {
                temp.next = temp.next.next;
                if (temp.next != null) {
                    temp.next.prev = temp;
                }
            } else {
                temp = temp.next;
            }
        }
        return head;
    }
}
