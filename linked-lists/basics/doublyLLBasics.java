class doublyLLBasics {
    public static void main(String[] args) {
        DLL obj = new DLL();
        obj.insertAtFirst(1);
        obj.insertAtFirst(0);
        obj.insertAtLast(2);
        obj.insertAtLast(3);
        obj.insertAtIndex(99, 1);
        obj.deleteAtFirst();
        obj.deleteAtLast();
        obj.deleteAtPosition(1);
        obj.display();

        System.out.println("Traversal in reverse order: ");
        obj.reverseDisplay();
    }
}

class DLL {
    class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
        }
    }

    Node head;
    int size;

    public DLL() {
        this.size = 0;
        this.head=null;
    }

    public void insertAtFirst(int data) {
        Node newNode = new Node(data);
        newNode.prev = null;
        newNode.next = head;
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }

    public void insertAtLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            newNode.prev = null;
            head = newNode;
            return;
        }

        Node temp = head;

        while (temp.next != null) { // this check will also be valid for singly LL when we wanna reach the last node
                                    // w/o keeping a tail node
            temp = temp.next;
        }
        newNode.next = null;
        temp.next = newNode;
        newNode.prev = temp;

        size++;

    }

    public void insertAtIndex(int data, int index) {
        if (index == 0) {
            insertAtFirst(data);
            return;
        }
        if (index == size - 1) {
            insertAtLast(data);
            return;
        }

        Node newNode = new Node(data);
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        newNode.prev = temp;
        temp.next = newNode;
        if (newNode.next != null)
            newNode.next.prev = newNode;

        size++;
    }

    public void deleteAtFirst() {
        if (head == null) {
            System.out.println("Empty list");
            return;
        }

        head = head.next;
        head.prev = null;

        size--;

    }

    public void deleteAtLast() {
        if (head == null) {
            System.out.println("Empty List");
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.prev.next = null;
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
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        if (temp.prev != null)
            temp.prev.next = temp.next;
        if (temp.next != null)
            temp.next.prev = temp.prev;

        size--;
    }

    public void display() {
        Node temp = head;
        System.out.print("Null -> ");
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println(" Null");
    }

    public void reverseDisplay() {
        Node temp = head;
        Node last = null;
        System.out.print("Null -> ");
        while (temp != null) {
            last = temp;
            temp = temp.next;
        }

        while (last != null) {
            System.out.print(last.data + " -> ");
            last = last.prev;
        }
        System.out.println(" Null");
    }
}