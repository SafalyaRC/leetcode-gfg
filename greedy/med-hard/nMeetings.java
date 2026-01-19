// GFG: https://www.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1

/* our intuition will be to perform a greedy approach upon the meeting times:
    1. sort the meetings according the ending times
    2. greedily pick the meetings as soon as they are free so that we can maximize the no. of meetings

    analogy: 
     - If we pick a meeting that ends early, we free up the room sooner for more meetings
     - A meeting that starts early but ends late might block many other potential meetings
     - A meeting that ends early leaves more "free time" for subsequent meetings
*/

import java.util.*;

public class nMeetings {
    class Room {
        int s, e;

        public Room(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    public int maxMeetings(int start[], int end[]) {
        int n = start.length;
        Room meetings[] = new Room[n];
        for (int i = 0; i < n; i++) {
            meetings[i] = new Room(start[i], end[i]);
        }
        Arrays.sort(meetings, (a, b) -> a.e - b.e);

        int meetCount = 1; // always start with the first meeting
        int freeTime = meetings[0].e; // to keep track of when a meeting ends so that we can start the next one in the same room

        for (int i = 1; i < n; i++) {
            if (meetings[i].s > freeTime) {
                meetCount++;
                freeTime = meetings[i].e;
            }
        }

        return meetCount;
    }
}
