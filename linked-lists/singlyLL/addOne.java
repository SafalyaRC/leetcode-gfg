
// brute-force:

import org.w3c.dom.Node;

class Solution {
    public Node addOne(Node head) {
        Node temp = head;
        StringBuilder num = new StringBuilder();

        while (temp != null) {
            num.append(temp.data);
            temp = temp.next;
        }

        String value = String.valueOf(Long.parseLong(num.toString()) + 1);

        temp = head;
        int i = 0;
        while (temp != null && i < value.length()) {
            temp.data = value.charAt(i) - '0';
            temp = temp.next;
            i++;
        }

        // If number of digits increased (e.g., 999 -> 1000)
        while (i < value.length()) {
            Node newNode = new Node(value.charAt(i) - '0');
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
            i++;
        }

        return head;
    }
}

// optimal:
public class addOne {
    public Node reverseLL(Node head) {
        Node temp = head;
        Node prev = null;

        while (temp != null) {
            Node front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;
        }
        return prev;
    }

    public Node addOne(Node head) {
        // code here.
        Node reverseHead = reverseLL(head);
        Node temp = reverseHead;

        int carry = 1;
        Node prev = null;

        while (temp != null) {
            int sum = temp.data + carry;
            temp.data = sum % 10;
            carry = sum / 10;
            prev = temp;
            temp = temp.next;
        }

        if (carry > 0)
            prev.next = new Node(carry);
        head = reverseLL(reverseHead);

        return head;
    }
}
