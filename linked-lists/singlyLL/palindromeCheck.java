// LC-234: https://leetcode.com/problems/palindrome-linked-list/description/

// brute-force:
import java.util.ArrayList;
import java.util.Collections;

class palindromeCheck {
    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> arr = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            arr.add(temp.val);
            temp = temp.next;
        }
        ArrayList<Integer> arrCopy = new ArrayList<>(arr);
        Collections.reverse(arr);
        return arr.equals(arrCopy);
    }
}

// optimal:
class Solution {
    public ListNode reverseLL(ListNode head) {
        ListNode temp = head;
        ListNode prev = null;

        while (temp != null) {
            ListNode front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;
        }
        return prev;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHead = reverseLL(slow);
        ListNode firstHead = head;

        while (secondHead != null) {
            if (firstHead.val != secondHead.val)
                return false;
            firstHead = firstHead.next;
            secondHead = secondHead.next;
        }
        return true;
    }
}