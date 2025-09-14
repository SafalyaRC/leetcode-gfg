// LC-2095: https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/

// brute-force: count no. of nodes=n and delete the node at n/2 th position 

public class deleteMiddleOfLL {
    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null)
            return null;
        ListNode slow = head;
        ListNode fast = head;

        ListNode prevSlow = null;

        while (fast != null && fast.next != null) {
            prevSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prevSlow.next = slow.next;
        slow = null;

        return head;
    }
}
