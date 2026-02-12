// LC-295: https://leetcode.com/problems/find-median-from-data-stream/description/

/*
Why the naive approach fails: In a data stream, numbers arrive incrementally, and the median is queried multiple times. Re-sorting the entire dataset on every findMedian() call is inefficient because sorting costs O(n log n) each time. As the number of operations grows, this quickly becomes a performance bottleneck and leads to TLE. What we need instead is a way to maintain order dynamically without reprocessing the whole dataset.

Core insight: The key observation is that the median divides the data into two halves: a smaller half and a larger half. If we can maintain these two halves separately in a balanced manner, the median will always be available at their boundary. This eliminates the need for full sorting while still preserving enough structure to answer median queries efficiently.

Data structure choice: To support this efficiently, we use two heaps. A max heap stores the smaller half of the numbers, ensuring quick access to the largest value in that half. A min heap stores the larger half, allowing quick access to the smallest value in that half. This setup ensures that all elements in the left heap are less than or equal to those in the right heap.

Maintaining balance: Every time a new number arrives, it is first inserted into the max heap. To maintain ordering, the largest element from the max heap is moved to the min heap. Then, if the min heap becomes larger, we move its smallest element back to the max heap. This guarantees that the size difference between the heaps is never more than one, keeping the structure balanced at all times.

Retrieving the median: With these invariants in place, finding the median becomes trivial. If both heaps have equal size, the median is the average of the two top elements. If one heap has one extra element, the median is simply the top of the max heap. This allows findMedian() to run in constant time.

Final efficiency: This approach reduces insertion time to O(log n) and median retrieval to O(1), making it optimal for streaming data. It is scalable, avoids unnecessary sorting, and is the standard solution expected in interviews and competitive programming.
*/

import java.util.*;
public class medianDataStream {
    // SC: O(n), with n elements approximately stored evenly across both heaps
    PriorityQueue<Integer> maxLeftHeap; // maxHeap for left sorted half elements
    PriorityQueue<Integer> minRightHeap; // minHeap for right sorted half elements
    public medianDataStream() {
        maxLeftHeap=new PriorityQueue<>(Collections.reverseOrder());
        minRightHeap=new PriorityQueue<>();
    }
    
    // TC: O(log n)
    public void addNum(int num) {
        // step-1: add elements to the max heap
        maxLeftHeap.offer(num);

        // step-2: balance orderings
        minRightHeap.offer(maxLeftHeap.poll());

        // step-3: balance the heaps when no. of right>left elements (so that all elements in left <= right) by adding the min element from the right half min heap to the left half
        if(minRightHeap.size()>maxLeftHeap.size()){
            maxLeftHeap.offer(minRightHeap.poll());
        }
    }
    
    // TC: O(1)
    public double findMedian() {
        // when equal elements in both heaps (i.e. even size observations):
        if(maxLeftHeap.size()==minRightHeap.size()){
            return (maxLeftHeap.peek()+minRightHeap.peek())/2.0;
        }
        return maxLeftHeap.peek(); // for odd obs, return the max element of left half
    }
}
