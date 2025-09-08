// LC-4: https://leetcode.com/problems/median-of-two-sorted-arrays/description/

// brute-force:
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;
        ArrayList<Integer> merged = new ArrayList<>();

        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j])
                merged.add(nums1[i++]);
            else
                merged.add(nums2[j++]);
        }
        while (i < nums1.length)
            merged.add(nums1[i++]);
        while (j < nums2.length)
            merged.add(nums2[j++]);

        int mid = n / 2;
        if (n % 2 != 0)
            return merged.get(mid);
        else
            return (double) ((double) merged.get(mid) + (double) merged.get(mid - 1)) / 2.0;
    }
}

// better:
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        int n = n1 + n2;
        int i = 0, j = 0;
        int ind1 = n / 2;
        int ind2 = ind1 - 1;
        int ele1 = -1, ele2 = -1, count = 0;

        while (i < n1 && j < n2) {
            if (nums1[i] < nums2[j]) {
                if (count == ind1)
                    ele1 = nums1[i];
                if (count == ind2)
                    ele2 = nums1[i];
                count++;
                i++;
            } else {
                if (count == ind1)
                    ele1 = nums2[j];
                if (count == ind2)
                    ele2 = nums2[j];
                count++;
                j++;
            }
        }
        while (i < n1) {
            if (count == ind1)
                ele1 = nums1[i];
            if (count == ind2)
                ele2 = nums1[i];
            count++;
            i++;
        }
        while (j < n2) {
            if (count == ind1)
                ele1 = nums2[j];
            if (count == ind2)
                ele2 = nums2[j];
            count++;
            j++;
        }

        if (n % 2 == 0)
            return (double) ((double) ele1 + (double) ele2) / 2.0;
        else
            return (double) ele1;
    }
}

// optimal:
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int n = n1 + n2;

        if (n1 > n2)
            return findMedianSortedArrays(nums2, nums1);
        int low = 0, high = n1;
        int left = (n1 + n2 + 1) / 2; // find no. of elements in our left half

        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = left - mid1;
            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            if (mid1 < n1)
                r1 = nums1[mid1];
            if (mid2 < n2)
                r2 = nums2[mid2];
            if (mid1 - 1 >= 0)
                l1 = nums1[mid1 - 1];
            if (mid2 - 1 >= 0)
                l2 = nums2[mid2 - 1];

            if (l1 <= r2 && l2 <= r1) {
                if (n % 2 == 0)
                    return ((double) Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                else
                    return Math.max(l1, l2);
            } else if (l1 > r2)
                high = mid1 - 1;
            else
                low = mid1 + 1;
        }
        return 0;
    }
}