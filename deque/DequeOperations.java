package deque;

import java.util.Deque;
import java.util.ArrayDeque;

public class DequeOperations {

    public static void main(String args[]){

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerFirst(10);
        deque.offerLast(20);
        deque.offerFirst(30);
        deque.offerLast(40);

        System.out.println("Deque : "+deque);
        System.out.println("last element : "+deque.getLast() 
        + " | first element : "+ deque.getFirst()
        + " | size : "+deque.size() 
        + " | isEmpty ? : "+deque.isEmpty());

    }
    
}
