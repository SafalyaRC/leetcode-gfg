// LC-56: https://leetcode.com/problems/merge-intervals/

class Solution {
    public int[][] merge(int[][] intervals) {
        ArrayList<int[]> ans = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        for (int i = 0; i < intervals.length; i++) {
            if (ans.isEmpty() || intervals[i][0] > ans.get(ans.size() - 1)[1])
                ans.add(intervals[i]);

            else
                ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], intervals[i][1]);
        }

        return ans.toArray(new int[0][]);
    }
}