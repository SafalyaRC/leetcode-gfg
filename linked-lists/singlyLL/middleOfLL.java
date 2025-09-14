// LC-876: https://leetcode.com/problems/middle-of-the-linked-list/

// brute-force:
class middleOfLL {
    public ListNode middleNode(ListNode head) {

        if (head == null || head.next == null)
            return head;

        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        temp = head;
        int mid = (count / 2);

        for (int i = 0; i < mid; i++) {
            temp = temp.next;
        }

        return temp;
    }
}

// optimal (tortoise-hare):
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) { // maintain this order to avoid of check to avoid NullPointerException
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
