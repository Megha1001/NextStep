package stack.implementTwoStacksInAnArray;

/*
 * Maintain two references (top1 = -1, top2 = cap)
 */


class TwoStacks{

    int arr[];
    int cap;
    int top1;
    int top2;


    TwoStacks(int cap){
        this.cap = cap;
        this.arr = new int[cap];
        top1 = -1;
        top2 = cap;
    }


    boolean push1(int x){
        if(top1+1 > top2){
            System.out.println("Overflow");
            return false;
        }

        ++top1;
        arr[top1] = x;
        return true;
    }

    boolean push2(int x){
        if(top2 - 1 < top1){
            System.out.println("Overflow");
            return false;
        }

        --top2;
        arr[top2] = x;
        return true;
    }

    int pop1(){
        if(top1 == -1){
            System.out.println("Underflow");
            return Integer.MAX_VALUE;
        }

        int val = arr[top1];
        --top1;
        return val;
    }

    int pop2(){
        if(top2==cap){
            System.out.println("Underflow");
            return Integer.MAX_VALUE;
        }
        int val = arr[top2];
        ++top2;
        return val;
    }

    int size1(){
        return top1+1;
    }

    int size2(){
        return cap - top2;
    }

    boolean isEmpty1(){
        return top1 == -1;
    }

    boolean isEmpty2(){
        return top2 == cap;
    }

}


public class EfficientMethod {

    public static void main(String args[]){
        TwoStacks ts=new TwoStacks(5); 
        ts.push1(5); 
        ts.push2(10); 
        ts.push2(15); 
        ts.push1(11); 
        ts.push2(7); 
        System.out.println("Popped element from stack1 is: " + ts.pop1()); 
        ts.push2(40); 
        System.out.println("Popped element from stack2 is: " + ts.pop2());  
	  
    }

}
