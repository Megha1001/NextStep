package queue.stack;


import java.util.Queue;
import java.util.ArrayDeque;

public class ImplementStackUsingQueue {


    static class Stack{
        Queue<Integer> q1, q2;
        int size;

        Stack(){
            q1 = new ArrayDeque<>();
            q2 = new ArrayDeque<>();
            size = 0;
        }

        int peek(){
            if(q1.isEmpty()){
                return -1;
            }
            return q1.peek();
        }

        int pop(){
            if(q1.isEmpty()){
                return -1;
            }
            --size;
            return q1.poll();
        }

        void push(int x){
            while(!q1.isEmpty()){
                q2.offer(q1.poll());
            }
            q1.offer(x);
            while(!q2.isEmpty()){
                q1.offer(q2.poll());
            }
            ++size;
        }

        int size(){
            return size;
        }

    }
    
    public static void main(String[] args) 
	{ 
		Stack s = new Stack(); 
		s.push(10); 
        s.push(5); 
        s.push(15); 
        s.push(20);

		System.out.println("current size: " + s.size()); 
		System.out.println(s.peek()); 
		s.pop(); 
		System.out.println(s.peek()); 
		s.pop(); 
		System.out.println(s.peek()); 

		System.out.println("current size: " + s.size()); 
	} 
}
