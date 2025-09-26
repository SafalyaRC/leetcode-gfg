
public class stackArrays {
    public static void main(String[] args) {

        Stack obj = new Stack(5);
        obj.push(4);
        obj.push(3);
        obj.push(2);
        obj.push(1);

        System.out.println("Element popped from stack: " + obj.pop());
        System.out.println("Top of stack is: " + obj.peek());
    }
}

class Stack {
    public int capacity;
    public int stack[];
    public int top;

    public Stack(int capacity) {
        this.capacity = capacity;
        stack = new int[capacity];
        top = -1;
    }

    public void push(int data) {
        if (top == capacity - 1) {
            System.out.println("Stack overflow");
            return;
        }
        stack[++top] = data;
    }

    public int pop() {
        if (top == -1) {
            System.out.println("Stack underflow");
            return -1;
        }
        return stack[top--];
    }

    public int peek() {
        if (top == -1) {
            System.out.println("Empty stack");
            return -1;
        }
        return stack[top];
    }
}
