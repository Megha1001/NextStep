package queue.reverse;

import java.util.Queue;
import java.util.ArrayDeque;

public class ReversingAQueueUsingStackIterativeSolution {

    public static void main(String args[]){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(10);
        q.offer(3);
        q.offer(11);
        q.offer(4);
        q.offer(0);

        reverse(q);
        System.out.println("Reversed : "+q);

    }

    public static void reverse(Queue<Integer> q){
        if(q.isEmpty()){
            return;
        }
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        while(!q.isEmpty()){
            stack.push(q.poll());
        }

        while(!stack.isEmpty()){
            q.offer(stack.pop());
        }

    }
    
}
