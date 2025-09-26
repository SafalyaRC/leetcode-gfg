public class circularQueue {
    public static void main(String[] args) {
        myCircularQueue obj = new myCircularQueue(5);
        obj.enqueue(1);
        obj.enqueue(2);
        obj.enqueue(3);
        obj.enqueue(4);
        obj.enqueue(5);

        System.out.println("Element removed from queue: " + obj.dequeue());
        obj.enqueue(6);
        System.out.println("Element removed from queue: " + obj.dequeue());
        obj.enqueue(7);
        System.out.println("Current front of queue: " + obj.peek());
        obj.display();
    }
}

class myCircularQueue {
    int queue[];
    int size;
    int front, rear;

    public myCircularQueue(int size) {
        this.size = size;
        queue = new int[size];
        front = -1;
        rear = -1;
    }

    public void enqueue(int data) {
        if ((rear + 1) % size == front) {
            System.out.println("Queue is full!");
            return;
        }
        if (front == -1)
            front = 0;

        rear = (rear + 1) % size;
        queue[rear] = data;
    }

    public int dequeue() {
        if (rear == -1 && front == -1) {
            System.out.println("Empty queue!");
            return -1;
        }
        int val = queue[front];
        if (rear == front) {
            rear = front = -1;
        } else {
            front = (front + 1) % size;
        }
        return val;
    }

    public int peek() {
        if (rear == -1 && front == -1) {
            System.out.println("Queue is empty!");
            return -1;
        }

        return queue[front];
    }

    public void display() {
        int temp = front;
        do {
            System.out.print(queue[temp] + " -> ");
            temp = (temp + 1) % size;
        } while (temp != (rear + 1) % size);
        System.out.println("END");
    }
}