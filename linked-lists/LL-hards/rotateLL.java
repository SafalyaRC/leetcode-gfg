// LC-61: https://leetcode.com/problems/rotate-list/description/

// brute-force:
import java.util.ArrayList;

class Solution {
    public void reverse(ArrayList<Integer> arr, int left, int right) {
        while (left <= right) {
            int temp = arr.get(left);
            arr.set(left, arr.get(right));
            arr.set(right, temp);
            left++;
            right--;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null)
            return head;
        ArrayList<Integer> arr = new ArrayList<>();
        ListNode temp = head;
        int n = 0;
        while (temp != null) {
            arr.add(temp.val);
            n++;
            temp = temp.next;
        }

        k = k % n;
        reverse(arr, 0, n - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);

        temp = head;
        for (int i = 0; i < arr.size(); i++) {
            temp.val = arr.get(i);
            temp = temp.next;
        }
        return head;
    }
}

// brute-force (TLE):
class Solution2 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null)
            return head;
        for (int i = 0; i < k; i++) {
            ListNode temp = head;
            while (temp.next.next != null) {
                temp = temp.next;
            }
            ListNode end = temp.next;
            temp.next = null;
            end.next = head;
            head = end;
        }
        return head;
    }
}

// optimal:
public class rotateLL {
    public ListNode getNewTail(ListNode temp, int k) {
        int count = 0;
        while (count != k) {
            count++;
            temp = temp.next;
        }
        return temp;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0)
            return head;
        ListNode tail = head;
        int len = 1;
        while (tail.next != null) {
            len++;
            tail = tail.next;
        }
        if (k % len == 0)
            return head;

        k = k % len;
        tail.next = head;
        ListNode newTail = getNewTail(head, len - k - 1);
        head = newTail.next;
        newTail.next = null;

        return head;
    }
}
