// LC-215: https://leetcode.com/problems/kth-largest-element-in-an-array/description/

// TC: O(nlogk) & SC: O(k)

/*
The core intuition is that we do not need all elements, only the k largest ones. While scanning the array, we maintain a min heap that stores exactly k elements, representing the current k largest values seen so far. The smallest among these k elements sits at the root of the heap and acts as a boundary: any new element smaller than this boundary can never be among the k largest overall and is immediately discarded. If a new element is larger, it replaces the boundary element. By the time we finish processing the array, the heap contains precisely the k largest elements, and the root (minimum among them) is the k-th largest element. This avoids sorting the entire array and reduces unnecessary work.
*/

import java.util.*;
public class kthLargest {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // min-heap

        for (int i = 0; i < k; i++)
            pq.offer(nums[i]);

        for (int i = k; i < n; i++) {
            if (nums[i] > pq.peek()) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }
        return pq.peek();
    }
}
