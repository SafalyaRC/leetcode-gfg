import java.util.Queue;
import java.util.LinkedList;
public class stackUsingQueues {
    public static void main(String[] args) {
        stackQueue stack=new stackQueue();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println("Popped data: "+stack.pop());
        System.out.println("New top: "+stack.peek());
    }
}

class stackQueue{
    Queue<Integer> queue1=new LinkedList<>();
    Queue<Integer> queue2=new LinkedList<>();

    public void push(int data){
        while(!queue1.isEmpty()){
            queue2.offer(queue1.poll());  // offer is appending and poll is deleting
        }
        queue1.offer(data);
        while(!queue2.isEmpty()){
            queue1.offer(queue2.poll());
        }
    }

    public int pop(){
        if(queue1.isEmpty()){
            System.out.println("Queue is empty!");
            return -1;
        }

        return queue1.poll();
    }

    public int peek(){
        if(queue1.isEmpty()){
            System.out.println("Queue is empty!");
            return -1;
        }
        return queue1.peek();
    }
}
