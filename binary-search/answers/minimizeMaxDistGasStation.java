// link: https://www.naukri.com/code360/problems/minimise-max-distance_7541449?leftPanelTabValue=PROBLEM

class Solution {
    public static double MinimiseMaxDistance(int[] arr, int K) {
        int n = arr.length;
        double low = 0, high = 0;
        for (int i = 0; i < n - 1; i++) {
            high = Math.max(high, (double) arr[i + 1] - arr[i]);
        }
        // double diff=1e-6;
        while (high - low > 1e-6) {
            double mid = (low + high) / (2.0);
            if (gasStationCount(arr, mid) <= K)
                high = mid;
            else
                low = mid;
        }
        return high;
    }

    public static int gasStationCount(int arr[], double maxDistance) {
        int stationCount = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int stationBetween = (int) ((arr[i + 1] - arr[i]) / maxDistance);
            if ((arr[i + 1] - arr[i]) == (stationBetween * maxDistance)) {
                stationBetween--;
            }
            stationCount += stationBetween;
        }
        return stationCount;
    }
}