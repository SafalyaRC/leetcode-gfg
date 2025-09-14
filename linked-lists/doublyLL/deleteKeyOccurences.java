// GFG: https://www.geeksforgeeks.org/problems/delete-all-occurrences-of-a-given-key-in-a-doubly-linked-list/1

public class deleteKeyOccurences {
    static Node deleteAllOccurOfX(Node head, int x) {
        // Write your code here
        while (head != null && head.data == x)
            head = head.next;

        Node temp = head;
        while (temp != null) {
            if (temp.data == x) {
                if (temp.prev != null)
                    temp.prev.next = temp.next;
                if (temp.next != null)
                    temp.next.prev = temp.prev;
            }
            temp = temp.next;
        }
        return head;
    }
}

// alternatively can use a dummy node solution.