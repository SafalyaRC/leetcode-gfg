// LC-355: https://leetcode.com/problems/design-twitter/description/

/*
Overall Design Intuition:- 
The core challenge of the Twitter News Feed problem is to efficiently retrieve the 10 most recent tweets from a user’s own timeline and the timelines of users they follow. A naïve approach—flattening all tweets and sorting—does not scale. Instead, this solution models each user’s tweets as a time-ordered linked list and applies a max-heap–based k-way merge, which is the same strategy used to merge multiple sorted streams efficiently.

The system is optimized around two facts:-
1. Tweets are always consumed in reverse chronological order.
2. We only ever need the top 10 tweets, not the entire history.

Tweet Class Intuition (Linked List Representation):-
1. Each Tweet node stores:
- id: the tweet identifier,
- time: a monotonically increasing global timestamp,
- next: a pointer to the next older tweet by the same user.

2. This forms a singly linked list per user, where the head always represents the most recent tweet. This structure is ideal because:
- Posting a tweet becomes O(1) (head insertion).
- Tweets for a user are already sorted by time, eliminating the need for per-user sorting.
- The next pointer enables efficient traversal when merging timelines.

Global Timestamp Intuition:-
The globalTime counter ensures a total ordering of tweets across all users.
Instead of comparing timestamps per user, we compare a single increasing integer, which:
- Avoids collisions,
- Simplifies heap comparisons,
- Guarantees correctness when merging tweets from different users.

postTweet Intuition:-
When a user posts a tweet:
- A new Tweet node is created with the current globalTime.
- The new tweet becomes the head of that user’s tweet list.
- This design ensures: Constant-time insertion, The newest tweet is always immediately accessible, No shifting or resizing of data structures.
- This mirrors a real Twitter feed, where new tweets appear instantly at the top.

followMap and userHeads Intuition:-
- followMap: Tracks who a user follows.
- userHeads: Stores the head tweet for each user.

This separation of concerns is deliberate:
- Social relationships (followMap) are independent of tweet storage.
- Tweet access is optimized by storing only the head pointer, not entire lists.
- Together, these maps allow us to instantly identify which tweet streams are relevant for a given user’s feed.

getNewsFeed Intuition (Core Logic):-
This method is the heart of the solution.

Step 1: Why a Max Heap?
We use a max heap ordered by tweet time so that the most recent tweet across all users is always accessible in O(log F) time, where F is the number of followed users.Each heap entry represents the current newest unconsumed tweet from a particular user’s timeline.

Step 2: Initial Heap Population

We insert:
- The head tweet of the user themself,
- The head tweet of each followed user (if it exists).

This works because:
- Each head is the most recent tweet from that user,
- The heap now represents the “front” of multiple sorted lists.
- At this point, the heap size is at most F + 1.

Step 3: K-Way Merge Using the Heap

We repeatedly:
- Extract the most recent tweet from the heap,
- Add its ID to the result,
- Insert the next older tweet from the same user (via current.next) back into the heap.
This is identical to merging k sorted linked lists:
- The heap decides which list contributes next,
- The linked list pointer advances only when needed.
We stop after collecting 10 tweets, ensuring we never process unnecessary data.


Why This Is Optimal:-
- We never scan entire timelines.
- We never store more than one tweet per user in the heap at a time.
- We stop as soon as the feed is complete.
- This makes the solution both time-efficient and memory-efficient, even if users have millions of tweets.

Follow / Unfollow Intuition:-
The follow and unfollow operations are simple set updates:
- No feed recomputation is required.
- Changes take effect naturally the next time getNewsFeed is called.
This lazy evaluation approach keeps write operations cheap and predictable.
*/

import java.util.*;
class Twitter{
    // a pair class to represent a tweet
    private static class Tweet{
        int id, time;
        Tweet next;
        public Tweet(int id, int time){
            this.id=id; // tweet id 
            this.time=time; // time when the users tweet
            this.next=null; // "next" here means the next OLDER tweet in the chain
        }
    }

    private static int globalTime; // global timestamps to ensure correct total ordering of tweets across all users
    private Map<Integer,Set<Integer>> followMap; // Map for User ID->Set of users they follow
    private Map<Integer,Tweet> userHeads; // Map for User ID->List of heir tweets (can access the list of tweets just by the head of the LL) i.e. this essentially stores the newest(head) tweet for every user

    public Twitter() {
        globalTime=0;
        followMap=new HashMap<>();
        userHeads=new HashMap<>();
    }

    // TC: O(1) for head insertion in LL
    public void postTweet(int userId, int tweetId) {
       Tweet t=new Tweet(tweetId,globalTime++); // create the new tweet (new head of LL)

       // insert the new head (newest first):
       t.next=userHeads.get(userId);
       userHeads.put(userId,t);
    }
    
    // TC: O(10 log k), k->no of users given user follows: complexity due to atmost 10 heap operations, this function is just implementing "merge k sorted lists"
    public List<Integer> getNewsFeed(int userId) {
        // max heap: orders by descending time (newest at top)
        PriorityQueue<Tweet> maxHeap=new PriorityQueue<>((a,b)->b.time-a.time); // SC:O(k) atmost

        // 1. add the head tweet of the users themselves:
        if(userHeads.containsKey(userId)){
            maxHeap.offer(userHeads.get(userId));
        }

        // 2. add the head of the tweet of everyone they follow:
        Set<Integer> following=followMap.get(userId);
        if(following!=null){
            for(int followeeId:following){
                Tweet head=userHeads.get(followeeId);
                if(head!=null) maxHeap.offer(head);
            }
        }

        // 3. Extract top 10 recent tweet IDs:
        List<Integer> ans=new ArrayList<>();
        int tweetCount=0;
        while(!maxHeap.isEmpty() && tweetCount<10){
            // get the most recent tweet available by polling the max heap
            Tweet current=maxHeap.poll();
            ans.add(current.id);
            tweetCount++;

            // add the next tweet from this specific user's timeline (same logic as "merge k sorted lists")
            if(current.next!=null) maxHeap.offer(current.next);
        }
        return ans;
    }
    
    // TC: O(1), HashSet iteration
    public void follow(int followerId, int followeeId) {
        if(followerId==followeeId) return; // can't follow yourself

        followMap.putIfAbsent(followerId,new HashSet<>()); // only create sets for users that aren't yet in the map
        followMap.get(followerId).add(followeeId);
    }
    
    // TC: O(1), HashSet removal
    public void unfollow(int followerId, int followeeId) {
        if(followMap.containsKey(followerId)){
            followMap.get(followerId).remove(followeeId);
        }
    }
}