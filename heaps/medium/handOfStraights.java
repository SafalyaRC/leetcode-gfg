// LC-https://leetcode.com/problems/hand-of-straights/description/

/*
Greedy + Ordering Insight:
The key observation is that every valid grouping must start from the smallest available card. If we ever start a group from a larger number while a smaller one still exists, that smaller card can never be placed later because groups must be consecutive. To enforce this ordering constraint, we maintain a min heap of all distinct card values. At each step, we look at the smallest card (i) and force a group of size groupSize starting from it:
i, i+1, ..., i+groupSize−1.
If any required card is missing, the hand cannot be rearranged into valid groups.

Frequency Management + Correctness: 
We also track frequencies using a map because cards may repeat. When we consume a card, we decrement its count. If a card’s count drops to zero, it must be removed from the heap exactly when it becomes the smallest element. If some larger card’s count becomes zero before the smallest one, it means we are trying to skip smaller values—this breaks the consecutive ordering and must return false. This invariant ensures that cards are always consumed in the correct ascending order. By repeatedly forming groups from the smallest available card and enforcing this rule, we guarantee correctness.

TC-O(nlogn) & SC-O(n)
*/

import java.util.*;
public class handOfStraights {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0)
            return false;

        Map<Integer, Integer> card_count = new HashMap<>(); // to store the frequency of cards (since there can be duplicates in there as well)
        for (int card : hand)
            card_count.put(card, card_count.getOrDefault(card, 0) + 1);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(card_count.keySet()); // min heap of cards according to our map keys

        // process until all cards used
        while (!minHeap.isEmpty()) {
            int minCard = minHeap.peek();

            for (int card = minCard; card < minCard + groupSize; card++) {
                if (!card_count.containsKey(card))
                    return false; // missing reqd consecutive card

                card_count.put(card, card_count.get(card) - 1); // card used, so lower its count

                // if frequency becomes 0, it must be the min card that we have to remove
                if (card_count.get(card) == 0) {
                    if (card != minHeap.peek()) { // violation if any other card other than the min. one goes zero (ask gpt to dry run this case with an example)
                        return false;
                    }
                    minHeap.poll();
                    card_count.remove(card);
                }
            }
        }
        return true;
    }
}
