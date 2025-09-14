// LC-25: https://leetcode.com/problems/reverse-nodes-in-k-group/description/

public class reverseKGroups {
    public ListNode getKthNode(ListNode temp, int k) {
        k--;
        while (temp != null && k > 0) {
            k--;
            temp = temp.next;
        }
        return temp;
    }

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

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode prevLast = null;
        ListNode temp = head;

        while (temp != null) {
            ListNode kthNode = getKthNode(temp, k);
            if (kthNode == null) {
                if (prevLast != null)
                    prevLast.next = temp;
                break;
            }

            ListNode nextNode = kthNode.next;
            kthNode.next = null;
            reverseLL(temp);

            // for first K group:
            if (temp == head) {
                head = kthNode;
            } else {
                prevLast.next = kthNode;
            }
            prevLast = temp;
            temp = nextNode;
        }
        return head;
    }
}
