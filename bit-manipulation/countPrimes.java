// LC-204: https://leetcode.com/problems/count-primes/description/

/*
The intuition is based on the fact that prime numbers are defined by the absence of smaller divisors, while composite numbers must have at least one. Instead of checking each number individually for divisibility (which is computationally expensive), this approach proactively eliminates composites by systematically marking multiples of discovered primes. The boolean array acts as a global “elimination board”: when a number is first encountered as unmarked, it must be prime, so we increment the count and then invalidate all of its future multiples.

A crucial optimization is starting the marking process from i^2
. Any composite smaller than i^2 must already have been marked by a smaller prime factor, so beginning earlier would only repeat work. This ensures we do not redundantly process numbers and keeps the sieve efficient. Conceptually, the algorithm transforms primality testing from a per-number verification problem into a collective filtering process, where primes emerge naturally as the numbers that survive all elimination rounds.

The overflow guard (long)i * i < n is purely a safety mechanism due to fixed-width integer arithmetic in Java. As i grows, i * i may exceed the integer limit before the comparison occurs, leading to incorrect behavior. Casting to long ensures the boundary check remains mathematically valid while preserving the sieve’s correctness. Overall, this method leverages number theory properties and memory-based marking to achieve near-linear practical performance for prime counting.

Sieve runs in 𝑂(𝑛 log log 𝑛): because each composite number is marked only by its prime factors, and the harmonic sum over prime reciprocals converges to log log n.
*/

public class countPrimes {
    public int countPrimess(int n) {
        if (n <= 2)
            return 0;

        boolean isComposite[] = new boolean[n];
        int count = 0;

        for (int i = 2; i < n; i++) {
            if (!isComposite[i]) { // when it's a prime
                count++;
                if ((long) i * i < n) { // to prevent overflow
                    for (int j = i * i; j < n; j += i) { // start from i^2 as all primes < i^2 are marked by smaller primes, and increment +i because multiples of a prime number is always composite
                        isComposite[j] = true; // mark the composites
                    }
                }
            }
        }
        return count;
    }
}
