// LC-82: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/

public class removeDuplicates2 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode prev = dummyNode;
        ListNode temp = head;

        while (temp != null && temp.next != null) {
            if (temp.val == temp.next.val) {
                while (temp.next != null && temp.val == temp.next.val) {
                    temp = temp.next;
                }
                prev.next = temp.next;
            } else {
                prev = prev.next;
            }
            temp = temp.next;
        }
        return dummyNode.next;
    }
}
