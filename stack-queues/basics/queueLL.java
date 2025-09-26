public class queueLL {
    public static void main(String[] args) {
        myQueue q = new myQueue();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);

        q.dequeue();

        q.display();
    }
}

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class myQueue {
    Node front;
    Node rear;
    int size;

    public myQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = rear.next;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Empty queue!");
            return -1;
        }
        int val = front.data;
        front = front.next;
        if (front == null)
            rear = null;
        size--;

        return val;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Empty queue!");
            return -1;
        }
        return front.data;
    }

    public void display() {
        Node temp = front;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }
}
