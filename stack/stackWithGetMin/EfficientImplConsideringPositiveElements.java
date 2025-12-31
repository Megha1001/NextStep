package stack.stackWithGetMin;

/*
 * IDEA : Considering all elements are +ve and will store -ve value when we encounter a val less than minimum
 */

import java.util.ArrayDeque;

public class EfficientImplConsideringPositiveElements {

    static class MinStack{
        ArrayDeque<Integer> s;
        int min;

        MinStack(){
            s = new ArrayDeque<>();
            min = Integer.MAX_VALUE;
        }

        void push(int x){

            if(s.isEmpty()){
                min = x;
                s.push(x);
                return;
            } else if (x <= min){
                s.push(x-min); //store negative hence x-min
                min = x;
            }else{
                s.push(x);
            }

        }

        int pop(){
            int t = s.pop();
            if(t <= 0){
                int e = min; //actual value
                min = min - t;
                return e;
            }

            return t;
        }

        int getMin(){
            return min;
        }

        int getTop(){
            if(s.peek() <=0){
                return min;
            }

            return s.peek();
        }

    }

    public static void main(String args[]){
        MinStack s=new MinStack();;
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
