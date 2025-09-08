// GFG: https://www.geeksforgeeks.org/problems/aggressive-cows/1

// brute force:
class Solution {
    public int aggressiveCows(int[] stalls, int k) {
        // code here
        Arrays.sort(stalls);
        int n = stalls.length;
        int range = stalls[n - 1] - stalls[0];

        for (int i = 1; i <= range; i++) {
            if (cowsPlacedOrNot(stalls, i, k) == false)
                return i - 1;
        }
        return range;
    }

    public boolean cowsPlacedOrNot(int stalls[], int minDist, int cows) {
        int count = 1, cowPtr = stalls[0];
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - cowPtr >= minDist) {
                count++;
                cowPtr = stalls[i];
            }
            if (count == cows)
                return true;
        }
        return false;
    }
}

// optimal:
class Solution {
    public int aggressiveCows(int[] stalls, int k) {
        // code here
        Arrays.sort(stalls);
        int low = 1, high = stalls[stalls.length - 1] - stalls[0];
        int ans = -1;

        while (low <= high) {
            int mid = low + ((high - low) / 2);
            if (cowsPlacedOrNot(stalls, mid, k) == true) {
                ans = mid;
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return ans;
    }

    public boolean cowsPlacedOrNot(int stalls[], int minDist, int cows) {
        int count = 1, cowPtr = stalls[0];
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - cowPtr >= minDist) {
                count++;
                cowPtr = stalls[i];
            }
            if (count == cows)
                return true;
        }
        return false;
    }
}