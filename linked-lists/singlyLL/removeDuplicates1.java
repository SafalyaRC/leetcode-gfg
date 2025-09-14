// LC-83: https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode temp = head;

        while (temp != null && temp.next != null) {
            if (temp.val == temp.next.val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }
}