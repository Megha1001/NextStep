package queue.reverse;

import java.util.ArrayDeque;
import java.util.Queue;

public class ReversingQueueRecursiveWay {
    
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
        
        int e = q.poll();
        reverse(q);
        q.offer(e);

    }

}
