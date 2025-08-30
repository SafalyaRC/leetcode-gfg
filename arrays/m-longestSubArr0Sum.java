// GFG: https://www.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1

// brute-force:
class Solution {
    int maxLength(int arr[]) {
        // code here
        int n = arr.length;
        int sum = 0;
        int len = 0, maxLen = 0;

        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                if (sum == 0) {
                    len = j - i + 1;
                    maxLen = Math.max(maxLen, len);
                }
            }
        }
        return maxLen;
    }
}

// optimal:
class Solution {
    int maxLength(int arr[]) {
        // code here
        int sum = 0;
        int maxLen = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum == 0)
                maxLen = i + 1;

            if (map.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - map.get(sum));
            } else
                map.put(sum, i);
        }
        return maxLen;
    }
}