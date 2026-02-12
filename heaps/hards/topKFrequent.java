
// heap-based approach: TC-O(n log k) & SC-O(k) for heap + O(n) for map

/*
Core intuition (heap-based): We do not need all elements sorted by frequency — only the top K. So instead of sorting everything (TreeMap / full sort), we maintain a min heap of size K.
- Count frequencies using a HashMap
- Push (element, frequency) into a min heap
- If heap size exceeds k, remove the least frequent element
- At the end, the heap contains exactly the K most frequent elements
- This ensures we only pay sorting cost for K elements, not all unique elements.

Why min heap (not max heap)?
- Max heap would store all elements → O(n log n)
- Min heap of size k → O(n log k) ✅
- When k << n, this is optimal
*/

import java.util.*;

public class topKFrequent {
    public int[] topK(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>(); // frequency map
        for (int num : nums)
            freq.put(num, freq.getOrDefault(num, 0) + 1);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> freq.get(a) - freq.get(b)); // min-heap based on frequency of numbers

        // maintain a min heap of size<=k, so that each time it stores>k elements, we delete the element with min frequency, this way, we ensure that the min heap at last stores only the top K frequent elements
        for (int num : freq.keySet()) {
            minHeap.offer(num);
            if (minHeap.size() > k)
                minHeap.poll();
        }

        int ans[] = new int[k]; // to store our ans
        int ind = 0;
        while (!minHeap.isEmpty()) {
            ans[ind++] = minHeap.poll();
        }
        return ans;
    }
}

// optimal bucket sort approach : TC-O(n) & SC-O(n)

/*
Core intuition: Frequencies are bounded. If an element appears f times, then
1 ≤ f ≤ n where n is the array length.

So instead of sorting elements by frequency, we:
- Count frequencies
- Use an array of buckets where: index = frequency, value = list of elements
with that frequency
- Traverse buckets from high → low and collect elements until k is reached
- This works because we only care about frequency ordering, not element
ordering.

Algorithm (step-by-step):
- Build a frequency map
- Create List<Integer>[] buckets of size n + 1
- Put each number into buckets[freq]
- Traverse from bucket n → 1
- Stop once k elements are collected
*/

class optimal {
    public int[] topKF(int[] nums, int k) {
        // step-1: create the frequency map
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);

        // step-2: create buckets where index=frequency, buckets[i]=list of elements with that particular frequency
        List<Integer> buckets[] = new ArrayList[nums.length + 1];
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>(); // initially create an empty list inside the bucket to store which elements have that particular frequency 
            }
            buckets[freq].add(key);
        }

        // step-3: retreive the top K frequent elements by traversing the buckets from the last (since we get highest->lowest frequency that way), only for 'k' amount of times:
        int ans[] = new int[k];
        int ind = 0;

        for (int i = buckets.length - 1; i >= 0 && ind < k; i--) {
            if (buckets[i] != null) {
                for (int num : buckets[i]) {
                    ans[ind++] = num;
                    if (ind == k)
                        break;
                }
            }
        }
        return ans;
    }
}
