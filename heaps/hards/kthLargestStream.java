// LC-703: https://leetcode.com/problems/kth-largest-element-in-a-stream/description/

import java.util.PriorityQueue;
public class kthLargestStream {
    int k;
    PriorityQueue<Integer> pq;

    public kthLargestStream(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k)
                pq.poll();
        }
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k)
            pq.poll();
        return pq.peek();
    }
}
