// LC-206: https://leetcode.com/problems/reverse-linked-list/description/

// brute-force:
import java.util.ArrayList;
import java.util.Collections;

class reverseLL {
    public ListNode reverseList(ListNode head) {
        ArrayList<Integer> arr = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            arr.add(temp.val);
            temp = temp.next;
        }

        Collections.reverse(arr);
        temp = head;
        int i = 0;
        while (temp != null) {
            temp.val = arr.get(i);
            i++;
            temp = temp.next;
        }
        return head;
    }
}

// optimal (iterative):
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode temp = head;
        ListNode prevNode = null;
        while (temp != null) {
            ListNode nextNode = temp.next;
            temp.next = prevNode;
            prevNode = temp;
            temp = nextNode;
        }
        return prevNode; // return new head
    }
}

// optimal(recursive):
public ListNode reverseListRecursive(ListNode head) {
    if (head == null || head.next == null)
        return head;

    ListNode newHead = reverseListRecursive(head.next);
    head.next.next = head;
    head.next = null;

    return newHead;
}
