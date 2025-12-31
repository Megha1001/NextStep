package stack.stackWithGetMin;

/*
 * IDEA : Store Pair<value, minVal_till_now>
 */


import java.util.ArrayDeque;
 
public class EfficientMethodUsingPairClass {

    static class Pair<K, V>{
        private K value;
        private V minValue;

        Pair(K value, V minValue){
            this.value = value;
            this.minValue = minValue;
        }

        K getValue(){
            return value;
        }

        V getMinValue(){
            return minValue;
        }
    }

    static class MinStack{
        ArrayDeque<Pair<Integer, Integer>> s;

        MinStack(){
            s = new ArrayDeque<>();
        }

        void push(int x){
            if(s.isEmpty()){
                s.push(new Pair(x,x));
            }else {
                int minValue = Math.min(x, s.peek().getMinValue());
                s.push(new Pair(x, minValue));
            }
        }

        int pop(){
            return s.pop().getValue();
        }

        int getMin(){
            return s.peek().getMinValue();
        }

        int peek(){
            return s.peek().getValue();
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
