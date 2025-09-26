import java.util.Stack;
public class queueUsingStack {
    public static void main(String[] args) {
        queueStack q=new queueStack();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        System.out.println("Data from dequeueing: "+q.dequeue());
        System.out.println("Front of queue is: "+q.peek());
    }
}

class queueStack{
    Stack<Integer> stack1=new Stack<>();
    Stack<Integer> stack2=new Stack<>();

    public void enqueue(int data){
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        stack1.push(data);
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
    }

    public int dequeue(){
        if(stack1.isEmpty()){
            System.out.println("Empty stack!");
            return -1;
        }
        return stack1.pop();
    }

    public int peek(){
        if(stack1.isEmpty()){
            System.out.println("Empty stack!");
            return -1;
        }
        return stack1.peek();
    }
}
