// GFG: https://www.geeksforgeeks.org/problems/count-subarray-with-given-xor/1

// brute-force:
class Solution {
    public long subarrayXor(int arr[], int k) {
        // code here
        int n = arr.length;
        int xor = 0;
        long count = 0;

        for (int i = 0; i < n; i++) {
            xor = 0;
            for (int j = i; j < n; j++) {
                xor ^= arr[j];
                if (xor == k)
                    count++;
            }
        }
        return count;
    }
}

// optimal:
class Solution {
    public long subarrayXor(int arr[], int k) {
        // code here
        int xor = 0;
        long count = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];

            if (xor == k)
                count++;

            if (map.containsKey(xor ^ k))
                count += map.get(xor ^ k);
            map.put(xor, map.getOrDefault(xor, 0) + 1);

        }
        return count;
    }
}