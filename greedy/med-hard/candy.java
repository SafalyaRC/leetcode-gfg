// LC-135: https://leetcode.com/problems/candy/description/

// better:

/*

Problem intuition
- Each child must get:
1. At least 1 candy
2. More candies than an immediate neighbor if their rating is higher
# The constraint is local (only neighbors), but its effect propagates.So we resolve constraints from both sides independently. Our intuition will be to satisfy neighbor constraints in two directions

# We break the problem into two simpler subproblems: Ensure the left neighbor condition. Ensure the right neighbor condition. Then we merge both requirements safely.

Step-by-step algorithm:
- Initialize a candies[] array of size n
- Give every child 1 candy initially (minimum requirement).
- Left-to-right pass (handle left neighbor constraint)
- For each child i from left to right:
 - If ratings[i] > ratings[i-1]: Give candies[i] = candies[i-1] + 1
 - Otherwise, do nothing (1 candy is sufficient)
👉 This guarantees:Every child with a higher rating than their left neighbor has more candies. Right-to-left pass (handle right neighbor constraint)
- For each child i from right to left:
 -If ratings[i] > ratings[i+1]: They must have more candies than the right neighbor
 -Update: candies[i] = max(candies[i], candies[i+1] + 1)
👉 We take max to not break the left-pass guarantee.
Sum up all values in candies[] and return the result.

*/

import java.util.Arrays;
public class candy {
    public int candyC(int[] ratings) {
        int n = ratings.length;
        int candies[] = new int[n];
        Arrays.fill(candies, 1);

        // assign candies based on left neighbours
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1])
                candies[i] = candies[i - 1] + 1;
        }

        // now assign based on right neighbours, but we update the candy values based on
        // max of left and right neighbours
        for (int i = n - 2; i >= 0; i--) {
            // criteria check for right neighbour
            if (ratings[i] > ratings[i + 1])
                candies[i] = Math.max(candies[i], candies[i + 1] + 1); // however be careful to consider only the max
                                                                       // candy amongst neighbours
        }

        int ans = 0;
        for (int c : candies)
            ans += c;
        return ans;
    }
}

// optimal: slope-based approach

/*
The ratings array consists of: Increasing slopes, Decreasing slopes, Flat areas. Candies needed depend on the length of these slopes, not individual positions.

Algorithm: Our intuition will be to count candies based on increasing and decreasing slopes
- Traverse the array once while tracking:
- Length of current upward slope
- Length of current downward slope
- Length of last upward slope (to fix peak overlap)
- If ratings increase:
 - Increase up
 - Reset down
 - Add 1 + up candies
- If ratings decrease:
 - Increase down
 - Reset up
 - Add 1 + down candies
 - If down > lastUp, add one extra candy (peak correction)
- If ratings are equal: 
 - Reset everything
 - Add 1 candy
*/

class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        if (n == 0)
            return 0;

        int candySum = 1; // for first child
        int up = 0; // length of current increasing slope
        int down = 0; // length of current decreasing slope
        int peak = 0; // length of last increasing slope
        for (int i = 1; i < n; i++) {
            // for flat slope:
            if (ratings[i - 1] == ratings[i]) {
                up = down = peak = 0; // reset everything for equal ratings
                candySum++; // asign minimum candy (+1)
            }

            // for increasing slope:
            else if (ratings[i] > ratings[i - 1]) {
                up++; // extend uphill
                peak = up; // record peak length
                down = 0; // reset downhill
                candySum += up + 1; // child gets more candy than previous
            }

            // for decreasing slope:
            else {
                down++; // extend downhill
                up = 0; // resent uphill
                candySum += down; // asign decreasing candies

                if (down > peak)
                    candySum += 1; // peak needs extra candy if downhill longer than uphill
            }
        }
        return candySum;
    }
}
