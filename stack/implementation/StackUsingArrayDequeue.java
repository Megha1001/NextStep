package stack.implementation;

import java.util.ArrayDeque;

public class StackUsingArrayDequeue {

    public static void main(String args[]){
        ArrayDeque<Integer> s = new ArrayDeque<>();
        s.push(10);
        s.push(20);
        s.push(30);

        System.out.println("Latest element on arraydeque is : "+ s.peek());
        s.pop();
        System.out.println("Latest element on arraydeque is : "+ s.peek());
        System.out.println("Size of arraydeque is : "+ s.size());
        System.out.println("is ArrayDeque empty : "+ s.isEmpty());
    }
    
}
