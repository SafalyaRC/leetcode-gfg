// LC-621: https://leetcode.com/problems/task-scheduler/description/

/*
Intuition behind the approach:
The core idea is to always execute the task that is currently the most constrained, meaning the one with the highest remaining frequency, because delaying such a task increases the risk of forced idle time later. To enforce this greedy choice, we use a max heap of task frequencies, which ensures that at every time unit we pick the task that still needs to be executed the most times. This strategy directly minimizes idle slots: if a task with high frequency is postponed, it becomes harder to place its remaining occurrences without violating the cooldown constraint. Thus, prioritizing the most frequent task at each step is the key greedy insight.

Handling cooldown correctly:
However, choosing the most frequent task alone is not enough, because tasks cannot be reused immediately due to the cooldown n. To model this, the algorithm uses a queue that tracks tasks in their cooldown period, storing both the remaining count of the task and the earliest time it can be scheduled again. Each time unit advances the global clock by one. When a task is executed, its remaining count decreases and it is placed into the cooldown queue with an availableTime = currentTime + n. At each time step, before scheduling the next task, we check whether any task’s cooldown has expired; if so, it is moved back into the heap and becomes eligible for execution again. This coordination between the max heap (for greedy selection) and the queue (for cooldown enforcement) guarantees that the CPU is never idle unless it is unavoidable, resulting in the minimum total execution time.

TC: O(n log 26) & SC: O(26), as tasks are from 'A'-'Z'
*/

import java.util.*;
class taskScheduler{
    class Pair{
        int count, availableTime;
        Pair(int count, int availableTime){
            this.count=count;
            this.availableTime=availableTime;
        }
    }
    public int leastInterval(char[] tasks, int n) {
        Map<Character,Integer> freq=new HashMap<>(); // to store frequency of each task
        for(char task:tasks) freq.put(task,freq.getOrDefault(task,0)+1);

        PriorityQueue<Integer> maxHeap=new PriorityQueue<>(Collections.reverseOrder()); // create a max heap
        for(int frequency:freq.values()) maxHeap.offer(frequency);  // add all the frequency of tasks to our heap

        Queue<Pair> q=new LinkedList<>(); // maintain a queue to accurately structure the tasks, the idle period between them as well, all in order to minimize the CPU intervals for our answer

        int time=0;  // current time stamp (which point of time we stand for a particular task) and finally calculates our min. total reqd. time

        while(!q.isEmpty() || !maxHeap.isEmpty()){
            time++;

            // schedule the most frequent tasks first in order to minimize the answer (reason to choose max heap):
            if(!maxHeap.isEmpty()){
                int taskCount=maxHeap.poll()-1;  // task gets scheduled so freq decreases by 1
                if(taskCount>0) q.offer(new Pair(taskCount,time+n));  // next will be available at the current time+ the cooldown period (n)
            }

            // when we can reschedule a task after its cooldown period:
            if(!q.isEmpty() && time==q.peek().availableTime){
                maxHeap.offer(q.poll().count); // put the task back in our max heap 
            }
        }
        return time;
    }
}