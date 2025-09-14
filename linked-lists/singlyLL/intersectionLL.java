// LC-160: https://leetcode.com/problems/intersection-of-two-linked-lists/description/

// brute-force:
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();

        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB))
                return headB;
            headB = headB.next;
        }
        return null;
    }
}

// better:
public class intersectionLL {
    public int differenceLengthLL(ListNode headA, ListNode headB) {
        int lenA = 0, lenB = 0;
        while (headA != null || headB != null) {
            if (headA != null) {
                lenA++;
                headA = headA.next;
            }
            if (headB != null) {
                lenB++;
                headB = headB.next;
            }
        }
        return lenA - lenB;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenDiff = differenceLengthLL(headA, headB);

        if (lenDiff > 0) {
            while (lenDiff-- != 0)
                headA = headA.next;
        } else {
            while (lenDiff++ != 0)
                headB = headB.next;
        }

        while (headA != null && headB != null) {
            if (headA == headB)
                return headA;
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
}

// optimal:
public class Solution2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode ptrA = headA;
        ListNode ptrB = headB;

        while (ptrA != ptrB) {
            if (ptrA == null) {
                ptrA = headB;
            } else
                ptrA = ptrA.next;

            if (ptrB == null) {
                ptrB = headA;
            } else
                ptrB = ptrB.next;
        }
        return ptrA;
    }
}