// GFG: https://www.geeksforgeeks.org/problems/check-if-there-exists-a-subsequence-with-sum-k/1

// trick to print/check for only one answer is using two base cases: first base case, prints true if satisfied condition, second print false if not

public class checkSubseqExists {
    public static boolean helper(int arr[], int sum, int i, int k) {
        if (sum == k)
            return true;
        if (i == arr.length)
            return false;

        if (helper(arr, sum, i + 1, k))
            return true;
        if (helper(arr, sum + arr[i], i + 1, k))
            return true;
        return false;
    }

    public static boolean checkSubsequenceSum(int N, int[] arr, int K) {
        return helper(arr, 0, 0, K);
    }
}
