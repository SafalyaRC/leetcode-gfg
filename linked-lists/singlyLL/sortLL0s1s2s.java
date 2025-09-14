// GFG: https://www.geeksforgeeks.org/problems/given-a-linked-list-of-0s-1s-and-2s-sort-it/1

// brute-force:
import java.util.ArrayList;
import org.w3c.dom.Node;

class Solution1 {
    public Node segregate(Node head) {
        // code here
        Node temp = head;
        ArrayList<Integer> arr = new ArrayList<>();
        int count1 = 0, count2 = 0, count0 = 0;

        while (temp != null) {
            if (temp.data == 0)
                count0++;
            else if (temp.data == 1)
                count1++;
            else
                count2++;
            temp = temp.next;
        }

        for (int i = 0; i < count0; i++) {
            arr.add(0);
        }
        for (int i = count0; i < count0 + count1; i++) {
            arr.add(1);
        }
        for (int i = count0 + count1; i < count0 + count1 + count2; i++) {
            arr.add(2);
        }

        temp = head;
        int i = 0;
        while (temp != null) {
            temp.data = arr.get(i);
            i++;
            temp = temp.next;
        }
        return head;
    }
}

// better:
class Solution2 {
    public Node segregate(Node head) {
        // code here
        Node temp = head;
        int count1 = 0, count2 = 0, count0 = 0;

        while (temp != null) {
            if (temp.data == 0)
                count0++;
            else if (temp.data == 1)
                count1++;
            else
                count2++;
            temp = temp.next;
        }

        temp = head;
        for (int i = 0; i < count0; i++) {
            temp.data = 0;
            temp = temp.next;
        }
        for (int i = count0; i < count0 + count1; i++) {
            temp.data = 1;
            temp = temp.next;
        }
        for (int i = count0 + count1; i < count0 + count1 + count2; i++) {
            temp.data = 2;
            temp = temp.next;
        }
        return head;
    }
}

// optimal:
public class sortLL0s1s2s {
    public Node segregate(Node head) {
        // code here
        Node zeroDummy = new Node(-1);
        Node oneDummy = new Node(-1);
        Node twoDummy = new Node(-1);

        Node zeroPtr = zeroDummy;
        Node onePtr = oneDummy;
        Node twoPtr = twoDummy;
        Node temp = head;

        while (temp != null) {
            if (temp.data == 0) {
                zeroPtr.next = temp;
                zeroPtr = zeroPtr.next;
            } else if (temp.data == 1) {
                onePtr.next = temp;
                onePtr = onePtr.next;
            } else {
                twoPtr.next = temp;
                twoPtr = twoPtr.next;
            }
            temp = temp.next;
        }
        if (oneDummy.next != null) {
            zeroPtr.next = oneDummy.next;
        } else
            zeroPtr.next = twoDummy.next;

        onePtr.next = twoDummy.next;
        twoPtr.next = null;
        return zeroDummy.next;
    }
}
