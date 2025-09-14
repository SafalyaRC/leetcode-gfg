class singlyLLBasics {
    public static void main(String[] args) {
        LL obj = new LL();
        obj.insertAtLast(3);
        obj.insertAtLast(4);
        obj.insertAtFirst(2);
        obj.insertAtFirst(1);
        obj.insertAtFirst(0);

        System.out.println("Linked List before insertion at specific position: ");
        obj.display();

        obj.insertAtPosition(99, 2);
        System.out.println("Linked List after insertion at 2nd position: ");
        obj.display();

        System.out.println("Element at first position deleted: " + obj.deleteAtFirst());
        obj.display();

        System.out.println("Element at last position deleted: " + obj.deleteAtLast());
        obj.display();

        obj.deleteAtPosition(1);
        obj.display();

        System.out.println("Is 2 present in LL ? : " + obj.search(2));

    }

}

class LL {
    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }

    }

    Node head;
    Node tail;
    int size;

    public LL() {
        this.size = 0;
    }

    public void insertAtFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;

        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void insertAtLast(int data) {
        if (tail == null) {
            insertAtFirst(data);
            return;
        }
        Node newNode = new Node(data);
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    public void insertAtPosition(int data, int index) {
        if (index == 0) {
            insertAtFirst(data);
            return;
        }
        if (index == size) {
            insertAtLast(data);
            return;
        }

        Node temp = head;
        for (int i = 1; i < index; i++) {
            temp = temp.next;
        }
        Node newNode = new Node(data);
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    public int deleteAtFirst() {
        int value = head.data;
        head = head.next;
        if (head == null)
            tail = null;

        size--;
        return value;
    }

    public int deleteAtLast() {
        if (head.next == null)
            tail = null;

        Node secondLast = head;
        while (secondLast.next.next != null) {
            secondLast = secondLast.next;
        }
        int value = tail.data;
        secondLast.next = null;
        tail = secondLast;
        size--;

        return value;
    }

    public void deleteAtPosition(int index) {
        if (index == 0) {
            deleteAtFirst();
            return;
        }
        if (index == size - 1) {
            deleteAtLast();
            return;
        }

        Node temp = head;
        for (int i = 1; i < index; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        size--;
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println(" Null");
    }

    public boolean search(int key) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == key)
                return true;
            temp = temp.next;
        }
        return false;
    }
}