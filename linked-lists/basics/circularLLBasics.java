class circularLLBasics {
    public static void main(String[] args) {
        CLL obj = new CLL();
        obj.insertAtFirst(1);
        obj.insertAtFirst(0);
        obj.insertAtLast(2);
        obj.insertAtLast(3);
        obj.insertAtPosition(99, 2);
        obj.deleteAtFirst();
        obj.deleteAtLast();
        obj.deleteAtPosition(1);
        obj.display();
    }
}

class CLL {
    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    Node head;
    Node tail; // necessary to keep track of tail because it connects back to head
    int size = 0;

    public CLL() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public void insertAtFirst(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            tail.next = head;
        } else {
            newNode.next = head;
            head = newNode;
            tail.next = head;
        }

        size++;
    }

    public void insertAtLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            tail.next = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }

        size++;
    }

    public void insertAtPosition(int data, int index) {
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
        for (int i = 1; i < index; i++) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;

        size++;
    }

    public void deleteAtFirst() {
        if (head == null) {
            System.out.println("Empty list");
            return;
        }

        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            tail.next = head;
        }

        size--;
    }

    public void deleteAtLast() {
        if (head == null) {
            System.out.println("Empty list");
            return;
        }

        if (head == tail) {
            head = tail = null;
        } else {
            Node temp = head;
            while (temp.next != tail) {
                temp = temp.next;
            }
            temp.next = head;
            tail = temp;
        }

        size--;
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
        if (head == null) {
            System.out.println("Empty list");
            return;
        }
        if (head == tail) {
            System.out.println(head.data);
            return;
        } else {
            Node temp = head.next;
            System.out.print(head.data + " -> ");
            while (temp != head) {
                System.out.print(temp.data + " -> ");
                temp = temp.next;
            }
            System.out.println("Head");
        }
    }

    // cleaner display function:
    /* public void display() {
        if (head == null) {
            System.out.println("Empty list");
            return;
        }

        Node temp = head;
        System.out.print("HEAD -> ");
        do {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        } while (temp != head);
        System.out.println("HEAD");
    } */

}