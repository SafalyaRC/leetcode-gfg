// GFG: https://www.geeksforgeeks.org/problems/number-of-nges-to-the-right/1

// brute-force:
class Solution {
    public static int[] count_NGE(int arr[], int indices[]) {
        // code here
        int n = arr.length;
        int ans[] = new int[indices.length];
        int count = 0;

        for (int i = 0; i < indices.length; i++) {
            count = 0;
            for (int j = indices[i] + 1; j < n; j++) {
                if (arr[j] > arr[indices[i]]) {
                    count++;
                }
            }
            ans[i] = count;
        }
        return ans;
    }
}

public class nextGreaterToRight {
    
}
