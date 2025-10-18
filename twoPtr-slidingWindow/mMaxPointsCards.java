// LC-1423: https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/

// optimal:
public class mMaxPointsCards {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int lSum = 0, rSum = 0;

        for (int i = 0; i < k; i++) {
            lSum += cardPoints[i];
        }
        int maxSum = lSum;
        for (int i = 0; i < k; i++) {
            lSum -= cardPoints[k - i - 1];
            rSum += cardPoints[n - i - 1];
            maxSum = Math.max(maxSum, lSum + rSum);
        }
        return maxSum;
    }
}
