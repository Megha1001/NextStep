package stack.stackWithGetMin;

/*
 * IDEA : Maintain two stack , 
 *  - one for storing values
 *  - other for storing min values
 */

import java.util.ArrayDeque;

public class NaiveSolution {
    
    static class MinStack{

        ArrayDeque<Integer> mainStack;
        ArrayDeque<Integer> auxiliaryStack;

        MinStack(){
            mainStack = new ArrayDeque<>();
            auxiliaryStack = new ArrayDeque<>();
        }


        void push(int x){
            if(mainStack.isEmpty() || auxiliaryStack.peek() >= x){
                auxiliaryStack.push(x);
            }

            mainStack.push(x);
        }

        int pop(){
            if(auxiliaryStack.peek() == mainStack.peek()){
                auxiliaryStack.pop();
            }

            return mainStack.pop();
        }

        int getMin(){
            return auxiliaryStack.peek();
        }

        int getTop(){
            return mainStack.peek();
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
