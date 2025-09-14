// LC-148: https://leetcode.com/problems/sort-list/

// brute-force:
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public ListNode sortList(ListNode head) {
        ArrayList<Integer> arr = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            arr.add(temp.val);
        }
        Collections.sort(arr);

        temp = head;
        int i = 0;
        while (temp != head) {
            temp.val = arr.get(i);
            temp = temp.next;
        }
        return head;
    }
}

// optimal:
public class sortLL {
    public ListNode middleLL(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode mergeSortedLists(ListNode head1, ListNode head2) {
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
        } else {
            temp.next = t2;
        }
        return dummyNode.next;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode middle = middleLL(head);

        ListNode left = head;
        ListNode right = middle.next;
        middle.next = null;

        left = sortList(left);
        right = sortList(right);

        return mergeSortedLists(left, right);
    }
}
