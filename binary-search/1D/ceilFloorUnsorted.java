// GFG: https://www.geeksforgeeks.org/problems/ceil-the-floor2802/1

// User function Template for Java

class Solution {
    public int[] getFloorAndCeil(int x, int[] arr) {
        // code here
        int floor = -1, ceil = -1;

        for (int num : arr) {
            if (num <= x && num > floor)
                floor = num;
            if (num >= x && (ceil == -1 || num < ceil))
                ceil = num;
        }
        return new int[] { floor, ceil };
    }
}
