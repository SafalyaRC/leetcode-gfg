// GFG: https://www.geeksforgeeks.org/problems/minimum-platforms-1587115620/1

/* 
This is not an interval-overlap-per-interval problem. It is a timeline sweep problem. Actual problem is: At any moment in time, how many trains are present at the station simultaneously?

Our intuition will be to process train arrivals and departures in time order:
- Sort the arrival times.
- Sort the departure times.
- Use two pointers: One for arrivals, One for departures
- Traverse the timeline:
 - If a train arrives before or at the next departure → need a new platform
 - If a train departs → free a platform
- Track the maximum number of platforms needed at any moment.

Why sorting works (intuition): Sorting converts the problem into a time simulation. We only care about events in chronological order. Platforms increase on arrival, decrease on departure. We no longer care: which arrival belongs to which departure and which train it is. We only care: when does some train arrive or depart?

Note: we NEVER increment both together because each loop iteration processes exactly one event, either:
- one arrival, or
- one departure
This preserves the chronological order of events.Incrementing both would mean skipping events → incorrect simulation.
*/

import java.util.Arrays;

public class minPlatforms {
    public int minPlatform(int arr[], int dep[]) {
        int n = arr.length;

        Arrays.sort(arr);
        Arrays.sort(dep);

        // 'i'- arrival ptr, 'j'-departure ptr
        int i = 0, j = 0;
        int platforms = 0, maxPlatforms = 0;

        while (i < n) {
            if (arr[i] <= dep[j]) {
                platforms++; // new train arrives
                i++;
            } else {
                platforms--; // train departs
                j++;
            }
            maxPlatforms = Math.max(maxPlatforms, platforms);
        }
        return maxPlatforms;
    }
}
