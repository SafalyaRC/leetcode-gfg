public class stackLL {
    public static void main(String[] args) {
        myStack obj = new myStack();
        obj.push(4);
        obj.push(3);
        obj.push(2);
        obj.push(1);

        System.out.println("Popping stack we get: " + obj.pop());
        System.out.println("Top of stack: " + obj.peek());
        obj.display();
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

class myStack {
    Node top;
    int size;

    public myStack() {
        top = null;
        size = 0;
    }

    public void push(int data) {
        Node element = new Node(data);
        element.next = top;
        top = element;

        size++;
    }

    public int pop() {
        if (top == null) {
            System.out.println("Stack underflow");
            return -1;
        }

        Node poppedElement = top;
        top = top.next;
        size--;

        return poppedElement.data;
    }

    public int peek() {
        if (top == null) {
            System.out.println("Stack is empty");
            return -1;
        }
        return top.data;
    }

    public void display() {
        Node temp = top;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }

    public int stackLength() {
        return size;
    }
}
