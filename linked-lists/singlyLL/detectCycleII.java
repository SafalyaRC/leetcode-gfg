// LC-142: https://leetcode.com/problems/linked-list-cycle-ii/

// brute-force:
import java.util.HashMap;
class Solution {
    public ListNode detectCycle(ListNode head) {
        HashMap<ListNode,ListNode> map=new HashMap<>();
        ListNode temp=head;

        while(temp!=null){
            if(map.containsKey(temp)) return temp;
            map.put(temp,temp.next);
            temp=temp.next;
        }
        return null;
    }
}

// optimal:
public class detectCycleII {
    public ListNode detectCycle(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            
            // if loop exists, find starting point:
            if(slow==fast){
                slow=head;
                while(slow!=fast){
                    slow=slow.next;
                    fast=fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}
