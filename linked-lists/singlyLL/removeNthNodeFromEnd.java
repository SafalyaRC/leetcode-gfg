// LC-19: https://leetcode.com/problems/remove-nth-node-from-end-of-list/

// brute-force:
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null)
            return null;

        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        int pos = (count - n);
        if (pos == 0) {
            head = head.next;
            return head;
        }

        count = 0;
        temp = head;
        while (temp != null) {
            count++;
            if (count == pos) {
                temp.next = temp.next.next;
            }
            temp = temp.next;
        }
        return head;
    }
}

// optimal:
public class removeNthNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        if (fast == null) {
            head = head.next;
            return head;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
