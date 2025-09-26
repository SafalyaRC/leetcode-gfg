
public class queueArrays {
    public static void main(String[] args) {
        myQueue obj = new myQueue(5);
        obj.enqueue(1);
        obj.enqueue(2);
        obj.enqueue(3);
        obj.enqueue(4);
        System.out.println("Front of queue deleted: " + obj.dequeue());
        System.out.println("New front: " + obj.peek());
    }
}

class myQueue {
    int size;
    int queue[];
    int rear;

    public myQueue(int size) {
        this.size = size;
        queue = new int[size];
        rear = -1;
    }

    public void enqueue(int data) {
        if (rear == size - 1) {
            System.out.println("Queue is full!");
            return;
        }
        queue[++rear] = data;
    }

    public int dequeue() {
        if (rear == -1) {
            System.out.println("Queue is empty!");
            return -1;
        }

        int front = queue[0];
        for (int i = 0; i < rear; i++) {
            queue[i] = queue[i + 1];
        }
        rear--;
        return front;
    }

    public int peek() {
        if (rear == -1) {
            System.out.println("Queue is empty!");
            return -1;
        }
        return queue[0];
    }
}