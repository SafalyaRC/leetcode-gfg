// GFG: https://www.geeksforgeeks.org/problems/kth-smallest-element5635/1

// TC: O(nlogk) & SC: O(k)

/*
The intuition is the exact mirror image. Instead of tracking the k largest values, we track the k smallest values using a max heap of size k. The root of the heap represents the largest among the k smallest seen so far, again serving as a boundary. While iterating through the array, if a new element is larger than this boundary, it cannot affect the k-th smallest and is ignored. If it is smaller, it deserves a place among the k smallest, so we remove the boundary and insert the new element. At the end, the heap contains exactly the k smallest elements in any order, and the root—being the largest of these—is the k-th smallest element. This approach achieves optimal efficiency by keeping the heap size limited to k and avoiding full sorting.
*/

import java.util.*;
public class kthSmallest {
    public int kthSmallestt(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // max-heap

        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }

        for (int i = k; i < n; i++) {
            if (arr[i] < pq.peek()) {
                pq.poll();
                pq.offer(arr[i]);
            }
        }
        return pq.peek();
    }
}
