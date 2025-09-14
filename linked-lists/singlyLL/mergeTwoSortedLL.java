// LC-21: https://leetcode.com/problems/merge-two-sorted-lists/description/

// brute-force: use additional arraylist to sort the elements then update the combined new list

// optimal:
public class mergeTwoSortedLL {
    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode t1 = head1;
        ListNode t2 = head2;
        ListNode temp = dummyNode;

        while (t1 != null && t2 != null) {
            if (t1.val <= t2.val) {
                temp.next = t1;
                temp = temp.next;
                t1 = t1.next;
            } else {
                temp.next = t2;
                temp = temp.next;
                t2 = t2.next;
            }
        }

        if (t1 != null) {
            temp.next = t1;
        } else if (t2 != null) {
            temp.next = t2;
        }
        return dummyNode.next;
    }
}
