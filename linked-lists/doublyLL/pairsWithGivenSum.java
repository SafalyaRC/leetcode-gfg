// GFG: https://www.geeksforgeeks.org/problems/find-pairs-with-given-sum-in-doubly-linked-list/1

public class pairsWithGivenSum {
    public static Node findTail(Node head) {
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        return tail;
    }

    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target,
            Node head) {
        // code here
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Node left = head;
        Node right = findTail(head);

        // left!=right ensures unique nodes and right.next!=left ensures the pointers
        // dont cross
        while (left != null && right != null && left != right && right.next != left) {
            int sum = left.data + right.data;
            if (sum == target) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(left.data);
                temp.add(right.data);
                ans.add(temp);
                left = left.next;
                right = right.prev;
            } else if (sum > target) {
                right = right.prev;
            } else {
                left = left.next;
            }
        }
        return ans;
    }
}
