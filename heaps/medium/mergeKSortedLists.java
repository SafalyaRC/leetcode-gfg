
// brute-force: empty the unsorted lists into an arraylist, sort it then create a new LL using a dummy node

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        List<Integer> nodes = new ArrayList<>();
        for (ListNode head : lists) {
            while (head != null) {
                nodes.add(head.val);
                head = head.next;
            }
        }

        if (nodes.isEmpty())
            return null;

        Collections.sort(nodes);

        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        for (int node : nodes) {
            curr.next = new ListNode(node);
            curr = curr.next;
        }
        return dummy.next;
    }
}

// optimal: TC-O(nlogk) & SC-O(k)

/*
You are merging k sorted lists.
At any moment:
- The smallest next element must be among the current heads of the k lists.
- So instead of sorting everything: Keep only the k candidates
- Always pick the smallest among them
That’s exactly what a min heap does.
*/

public class mergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val); // since ListNode does not implement Comparable, so this causes a runtime error (note: we use PQ of type ListNode not Integer, hence we must tell the heap how to compare the nodes as shown)

        // store the head of each non-empty lists into our pq
        for (ListNode head : lists) {
            if (head != null)
                minHeap.offer(head);
        }

        // create a dummy node and a current traversal pointer
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while (!minHeap.isEmpty()) {
            ListNode min = minHeap.poll(); // get the minimum node from the heap

            // start building the sorted list using the min retrieved elements
            curr.next = min;
            curr = curr.next;

            // add the next node of the min node to heap, if they exist
            if (min.next != null)
                minHeap.offer(min.next);
        }
        return dummy.next;
    }
}
