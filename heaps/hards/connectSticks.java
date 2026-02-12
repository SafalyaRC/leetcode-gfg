// GFG: https://www.geeksforgeeks.org/problems/minimum-cost-of-ropes-1587115620/1

/*

*/

import java.util.*;
class connectSticks {
    public static int minCost(int[] arr) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.add(num);
        }

        int cost = 0;
        while (minHeap.size() > 1) {
            int sum = minHeap.poll() + minHeap.poll();
            cost += sum;
            minHeap.offer(sum);
        }
        return cost;
    }
}