// LC-496: https://leetcode.com/problems/next-greater-element-i/description/

// brute-force:
import java.util.*;
import java.util.Stack;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 0; i < nums1.length; i++) {
            boolean added = false;

            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    // Start scanning forward
                    for (int k = j + 1; k < nums2.length; k++) {
                        if (nums2[k] > nums2[j]) {
                            arr.add(nums2[k]);
                            added = true;
                            break;
                        }
                    }
                    break; // stop after finding nums1[i] in nums2
                }
            }

            if (!added)
                arr.add(-1);
        }
        return arr.stream().mapToInt(Integer::intValue).toArray();
    }
}

// better:
class Solution2 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int ans[] = new int[nums1.length];
        Arrays.fill(ans, -1);

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
        }

        for (int i = 0; i < nums2.length; i++) {
            if (!map.containsKey(nums2[i]))
                continue;
            for (int j = i + 1; j < nums2.length; j++) {
                if (nums2[j] > nums2[i]) {
                    ans[map.get(nums2[i])] = nums2[j];
                    break;
                }
            }
        }
        return ans;
    }
}

// optimal:
public class nextGreater1 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] nextGreater = new int[10001]; // assuming max value <= 10^4
        Stack<Integer> st = new Stack<>();
        Arrays.fill(nextGreater, -1);

        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= nums2[i])
                st.pop();
            nextGreater[nums2[i]] = st.isEmpty() ? -1 : st.peek();
            st.push(nums2[i]);
        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = nextGreater[nums1[i]]; // modify nums1 array (can also use a new array int ans[])
        }
        return nums1;
    }
}

// alternate optimal:
class solution3 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int ans[] = new int[nums1.length];
        Arrays.fill(ans, -1);
        Stack<Integer> st = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++)
            map.put(nums1[i], i);

        for (int i = 0; i < nums2.length; i++) {
            int curr = nums2[i];
            while (!st.isEmpty() && curr > st.peek()) {
                ans[map.get(st.pop())] = curr;
            }
            if (map.containsKey(curr))
                st.push(curr);
        }
        return ans;
    }
}
