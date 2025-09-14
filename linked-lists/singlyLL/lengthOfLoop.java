// GFG: https://www.geeksforgeeks.org/problems/find-length-of-loop/1

// brute-force:
import java.util.HashMap;
import org.w3c.dom.Node;

class Solution {
    public int lengthOfLoop(Node head) {
        // code here
        HashMap<Node, Integer> map = new HashMap<>();
        Node temp = head;
        int i = 1;

        while (temp != null) {
            if (map.containsKey(temp)) {
                return i - map.get(temp);
            }
            map.put(temp, i);
            i++;
            temp = temp.next;
        }
        return 0;
    }
}

// optimal:
public class lengthOfLoop {
    public int lengthOfLoop(Node head) {
        // code here
        Node slow = head;
        Node fast = head;

        int count = 0;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                count = 1;
                fast = fast.next;

                while (slow != fast) {
                    fast = fast.next;
                    count++;
                }
                return count;
            }
        }
        return 0;
    }
}
