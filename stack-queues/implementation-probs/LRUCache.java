// LC-146: https://leetcode.com/problems/lru-cache/

import java.util.Map;
import java.util.HashMap;

public class LRUCache {
    class Node {
        int data, key;
        Node prev;
        Node next;

        public Node(int key, int data) {
            this.key = key;
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);
    int capacity;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            deleteAtLast(node);
            insertAtFirst(node);
            return node.data;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            deleteAtLast(map.get(key));
        }
        if (map.size() == capacity) {
            deleteAtLast(tail.prev);
        }
        insertAtFirst(new Node(key, value));
    }

    public void insertAtFirst(Node node) {
        // add to the first:
        map.put(node.key, node);
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public void deleteAtLast(Node node) {
        // delete from that position:
        node.prev.next = node.next;
        node.next.prev = node.prev;
        map.remove(node.key);
    }
}