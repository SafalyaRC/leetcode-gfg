// LC-88: https://leetcode.com/problems/merge-sorted-array/description/

// brute force:
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            temp.add(nums1[i]);
        }
        for (int i = 0; i < n; i++)
            temp.add(nums2[i]);

        Collections.sort(temp);
        for (int i = 0; i < m + n; i++) {
            nums1[i] = temp.get(i);
        }
    }
}

// slightly optimized brute force:
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int zeroPtr = m;
        for (int i = 0; i < n; i++) {
            nums1[zeroPtr] = nums2[i];
            zeroPtr++;
        }
        Arrays.sort(nums1);
    }
}

// optimal:
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                k--;
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
                k--;
            }
        }
    }
}