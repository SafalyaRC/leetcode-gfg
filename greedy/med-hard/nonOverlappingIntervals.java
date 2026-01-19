// LC-435: https://leetcode.com/problems/non-overlapping-intervals/description/

// this is like the reverse of N-meetings in a room problem

/*
To keep the maximum number of non-overlapping intervals, we need to remove the minimum number of overlapping ones. This is a classic greedy scenario.We sort intervals by their end times and always pick the next interval that starts after the last selected one ends. The rest need to be removed.
- Sort all intervals based on their end time in ascending order.
- Initialize a variable to store the end time of the last non-overlapping interval (e.g., set to the end of the first interval).
- Initialize a counter to track how many intervals need to be removed.
- Traverse the remaining intervals:
  - If the start of the current interval is greater than or equal to the end time of the last added interval, update the end time.
  - Else, it overlaps increment the removal counter.
- Return the counter at the end as the minimum number of intervals to remove.
*/

import java.util.Arrays;
public class nonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]); // sort intervals by their end
        int count = 0;
        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            // if there's an overlap:
            if (intervals[i][0] < prevEnd)
                count++; // we need to remove the overlapped interval
            else
                prevEnd = intervals[i][1]; // update the previous end time
        }
        return count;
    }
}
