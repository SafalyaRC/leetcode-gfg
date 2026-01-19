// GFG: https://www.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1

// pure greedy approach (TLE):

/*
Our intuition is to prioritize high-profit jobs and schedule them as late as possible before their deadlines.
Why this works: Each job takes 1 unit time, Deadlines define the latest slot a job can occupy, If we place a job late, we keep earlier slots free for other jobs.

Algorithm steps: 
- Pair each job with its deadline and profit.
- Sort all jobs by profit in descending order.
- Create a boolean[] array to represent time slots (initially empty).
- For each job (in sorted order):
 - Try to place it in the latest available slot ≤ its deadline
 - If a slot is found: Schedule the job, Increase job count and Add its profit.
- Return the job count and total profit.

Analogy (Quick intuition): Think of deadlines as expiry dates. High-profit jobs should be done first. Doing a job as late as possible avoids blocking others
*/

import java.util.*;
public class jobSequencing {
    class Job {
        int d, p;

        public Job(int d, int p) {
            this.d = d;
            this.p = p;
        }
    }

    public ArrayList<Integer> jobSquncing(int[] deadline, int[] profit) {
        int n = deadline.length;
        Job jobs[] = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(deadline[i], profit[i]);
        }
        Arrays.sort(jobs, (a, b) -> b.p - a.p); // sort the jobs acc to profit

        // find the max deadline of jobs to get the length of job sequencing array
        int maxDeadline = 0;
        for (Job j : jobs) {
            maxDeadline = Math.max(maxDeadline, j.d);
        }

        boolean jobSequence[] = new boolean[maxDeadline + 1]; // 1-indexed sequencing array
        int count = 0, totalProfit = 0;
        for (Job job : jobs) {
            // sequence the maxprofit jobs at their deadline day not before
            for (int day = job.d; day > 0; day--) {
                // if a slot is empty, fill it else, look for the immediate previous slot
                if (!jobSequence[day]) {
                    jobSequence[day] = true;
                    count++;
                    totalProfit += job.p;
                    break;
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(count);
        ans.add(totalProfit);
        return ans;

    }
}
