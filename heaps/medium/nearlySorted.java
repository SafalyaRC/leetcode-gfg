// GFG: https://www.geeksforgeeks.org/problems/nearly-sorted-1587115620/1

/*

Problem Intuition (What “nearly sorted” means): A k-sorted array means every element is at most k positions away from where it should be in the fully sorted array.

So:
- The smallest element must be within the first k+1 elements
- The 2nd smallest must be within the next k+1, and so on
That’s the key insight.

Why a Min Heap works: 
- If an element can only move k steps: The correct next element must be among the next k+1 elements
- So instead of sorting everything, we: Keep a window of size k+1
- Always extract the minimum from that window. This is exactly what a min heap is good at.

High-Level Algorithm:
- Create a min heap
- Traverse the array, Push current element into the heap
- If heap size exceeds k, pop the smallest and place it into the array
- After traversal, empty the heap into the array

TC: O(nlogk) & SC: O(k)

*/

import java.util.PriorityQueue;
class nearlySorted {
    public void nearly(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int ind = 0;

        for (int i = 0; i < arr.length; i++) {
            minHeap.offer(arr[i]);
            
            //Heap size k+1 means we have all possible candidates. The smallest among them must be the next correct element. Hence, place it in the array and move forward:
            if (minHeap.size() > k) {
                arr[ind++] = minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) {
            arr[ind++] = minHeap.poll();
        }
    }
}