// LC-57: https://leetcode.com/problems/insert-interval/description/

/*
Core intuition: The given intervals are already sorted and non-overlapping. So when inserting a new interval, every existing interval must fall into one of three categories relative to the new interval:
- Completely on the left (no overlap)
- Overlapping with the new interval
- Completely on the right (no overlap)
We process them in order using a single pass.

Step-by-step Algorithm
- Step 1: Insert all intervals completely before the new interval
As long as an interval ends before the new interval starts, it cannot overlap.
These intervals are already correct and can be added directly to the answer: interval.end < newInterval.start

- Step 2: Merge all overlapping intervals
If an interval starts before or at the end of the new interval, it overlaps.
Merge by: Taking the minimum start, Taking the maximum end, Continue merging until no overlap is possible.
interval.start ≤ newInterval.end
This grows the new interval to absorb all overlaps.

- Step 3: Add the merged interval
At this point, the new interval represents the final merged range.
Add it once to the answer.

- Step 4: Insert all remaining intervals
These intervals start after the merged interval ends.
They cannot overlap and can be appended directly.
*/

import java.util.*;
public class insertIntervals {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int i = 0;
        List<int[]> ans = new ArrayList<>();

        // insert the leftmost part of new interval:
        while (i < n && intervals[i][1] < newInterval[0]) {
            ans.add(intervals[i]);
            i++;
        }

        // merge overlapping intervals:
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        ans.add(newInterval); // add the merged interval

        // insert the remaining intervals that cant be merged aka the right half:
        while (i < n) {
            ans.add(intervals[i]);
            i++;
        }

        return ans.toArray(new int[ans.size()][]);
    }
}
