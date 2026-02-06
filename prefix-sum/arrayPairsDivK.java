// LC-1497: https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/

/*
Goal: Can we split the array into pairs such that each pair sum is divisible by k?

🔷 Key Insight: Instead of pairing numbers directly, think in terms of remainders modulo k.

For any number: r=num%k

A pair 
(a,b) is valid if:
(a+b)modk=0

Which means:
ra+rb≡0(modk)

So:
rb=(k−ra)modk rb

🔷 Why this works
We only care about remainders, not the actual values.

Example:
k = 5
remainders = [1,4,2,3,0,0]

Valid pairings:

Remainder	Needs partner
1	           4
2	           3
0	           0

So counts must match.

🔷 Rules for remainders

Let freq[r] = number of elements with remainder r.

Case 1: r = 0

Numbers divisible by k can only pair among themselves.

freq[0] must be even

Case 2: r = k/2 (only when k even)

Example: k=6 → r=3

3 + 3 = 6 → divisible.

freq[k/2] must be even

Case 3: general r
freq[r] must equal freq[k-r]


Because each r needs a matching (k−r).

🔷 Why brute pairing fails

Pairing is global matching, not local.
Greedy picking may trap you even if solution exists.

Remainder counting ensures perfect balance.

🔷 Algorithm
- Compute frequency of remainders
- Check the 3 rules

✅ Case 1 — Remainder 0
Example
arr = [6, 12, 3, 9]
k = 3


Remainders:

Value	%3
6	    0
12	    0
3	    0
9	    0

freq[0] = 4

These numbers can only pair with other remainder 0:

(6,12), (3,9)


✔ Condition: freq[0] must be even

If odd → one element left unpaired.

✅ Case 2 — General r
Example
arr = [1, 4, 2, 3]
k = 5


Remainders:

Value	%5
1	    1
4	    4
2	    2
3	    3

Pairs:

1 ↔ 4
2 ↔ 3


freq[1] = freq[4]
freq[2] = freq[3]

✔ Condition:

freq[r]=freq[k−r]
Failing Example
arr = [1,1,4]
k = 5


freq[1]=2, freq[4]=1 → mismatch ❌

One 1 left unpaired.

✅ Case 3 — Special midpoint (k even)
Example
k = 6
arr = [3, 9, 15, 21]


Remainders:

Value	%6
3	     3
9	     3
15	     3
21	     3

Here: r=k/2=3

Pairing rule:

3 + 3 = 6


So these must pair among themselves.

freq[3] = 4 → even ✔

Failing Example
arr = [3, 9, 15]
k = 6


freq[3]=3 → odd ❌

One leftover.
*/

public class arrayPairsDivK {
    public boolean canArrange(int[] arr, int k) {
        int freq[] = new int[k];
        for (int num : arr) {
            int r = ((num % k) + k) % k; // to handle -ves as well
            freq[r]++;
        }

        if (freq[0] % 2 != 0)
            return false; // case-1
        for (int r = 1; r <= k / 2; r++) { // only loop till k/2 because after that pairs repeat
            if (r == k - r) {
                if (freq[r] % 2 != 0)
                    return false; // special case-2
            }
            if (freq[r] != freq[k - r])
                return false; // case-3
        }
        return true;
    }
}
