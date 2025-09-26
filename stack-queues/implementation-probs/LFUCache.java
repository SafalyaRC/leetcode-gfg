// LC-460: https://leetcode.com/problems/lfu-cache/description/

import java.util.Map;
import java.util.HashMap;

class Node {
    int key, val, freq;
    Node prev, next;

    Node(int key, int val, int freq) {
        this.key = key;
        this.val = val;
        this.freq = freq;
    }
}

class DLL {
    Node head, tail;
    int size;

    DLL() {
        head = new Node(0, 0, 0);
        tail = new Node(0, 0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
        size--;
    }

    Node removeFront() {
        if (size == 0)
            return null;
        Node node = head.next;
        removeNode(node);
        return node;
    }

    void addToTail(Node node) {
        Node prev = tail.prev;
        prev.next = node;
        node.prev = prev;
        node.next = tail;
        tail.prev = node;
        size++;
    }
}

public class LFUCache {
    private Map<Integer, Node> keyToNode;
    private Map<Integer, DLL> freqToList;
    private int minFreq, capacity, size;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.minFreq = Integer.MAX_VALUE;
        keyToNode = new HashMap<>();
        freqToList = new HashMap<>();
    }

    private void update(Node node) {
        int freq = node.freq;
        freqToList.get(freq).removeNode(node);

        if (freqToList.get(freq).size == 0) {
            freqToList.remove(freq);
            if (minFreq == freq)
                minFreq++;
        }

        node.freq++;
        freqToList.putIfAbsent(node.freq, new DLL());
        freqToList.get(node.freq).addToTail(node);
    }

    public int get(int key) {
        if (!keyToNode.containsKey(key))
            return -1;
        Node node = keyToNode.get(key);
        update(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0)
            return;

        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.val = value;
            update(node);
        } else {
            if (size == capacity) {
                Node node = freqToList.get(minFreq).removeFront();
                keyToNode.remove(node.key);
                size--;
            }
            Node newNode = new Node(key, value, 1);
            keyToNode.put(key, newNode);
            freqToList.putIfAbsent(1, new DLL());
            freqToList.get(1).addToTail(newNode);
            minFreq = 1;
            size++;
        }
    }
}