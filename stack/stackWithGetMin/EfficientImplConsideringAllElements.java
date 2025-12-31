package stack.stackWithGetMin;


/*
 * Idea : refer stack/stackWithGetMin/EfficientImplConsideringPositiveElements.java with some conditinal changes current class has been implemented
 */

import java.util.ArrayDeque;

public class EfficientImplConsideringAllElements {

    static class MinStack{
        ArrayDeque<Integer> s;
        int min;

        MinStack(){
            s = new ArrayDeque<>();
            min = Integer.MAX_VALUE;
        }

        void push(int x){
            if(s.isEmpty()){
                s.push(x);
                min = x;
            }else if (x <= min){
                s.push(2*x-min) ; //earlier x-min
                min = x;
            }else {
                s.push(x);
            }
        }

        int pop(){
            int t = s.pop();
            if(t <= min){ //earlier t<=0
                int res = min;
                min = 2*min - t; // earlier min-t;
                return res;
            }

            return t;
        }


        int peek(){
            if(s.peek() <= min){ //earlier s.peek <= 0
                return min;
            }
            return s.peek();
        }

        int getMin(){
            return min;
        }
    }

    public static void main(String args[]){
        MinStack s= new MinStack();;
        s.push(5);
        s.push(10);
        s.push(20);
        s.push(2);
        s.push(6);
        s.push(4);
        s.pop();
        s.pop();
        s.push(2);
        s.pop();
        s.push(1);
        s.pop();
        s.pop();
     
        System.out.print(" Minimum Element from Stack: " + s.getMin());
    }
    
    
}
