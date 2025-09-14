// Lc-141: https://leetcode.com/problems/linked-list-cycle/description/

// brute-force:
import java.util.HashMap;
class Solution {
    public boolean hasCycle(ListNode head) {
        HashMap<ListNode, ListNode> visitedMap = new HashMap<>();
        ListNode temp = head;

        while (temp != null) {
            if (visitedMap.containsKey(temp))
                return true;
            visitedMap.put(temp, temp.next);
            temp = temp.next;
        }
        return false;
    }
}

// optimal (tortoise-hare):
public class detectCycle {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }
        return false;
    }
}