// GFG: https://www.geeksforgeeks.org/problems/shortest-job-first/1

/*
Algorithm steps:
- Sort the jobs in ascending order based on their durations. The jobs array will now contain the job durations arranged from shortest to longest.
- Initialise variables waitTime to 0 (waiting time for that particular job) and totalTime (total waiting time for all jobs).
- Iterate through each job in the sorted array. For each job, its waiting time is the sum of total time taken by all previous jobs. Update the total time taken by adding the duration of the current job to the total waiting time.
- After iterating through each job in the array, the average waiting is total waiting time divided by the number of jobs ie. length of the jobs array.
- After iterating through each job in the array, the average waiting is total waiting time divided by the number of jobs ie. length of the jobs array.
*/

import java.util.Arrays;
public class SJF {
    static int solve(int bt[]) {
        int n = bt.length;
        Arrays.sort(bt);
        int waitTime = 0, totalTime = 0;
        for (int i = 0; i < n - 1; i++) {
            waitTime += bt[i];
            totalTime += waitTime;
        }
        return totalTime / n;
    }
}
