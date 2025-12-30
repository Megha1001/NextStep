package stack.design;
/*
 * insert at back
 */

class MyStackClass{

    int cap;
    int top; //index of top element
    int arr[];

    MyStackClass(int cap){
        this.cap = cap;
        this.top = -1;
        this.arr = new int[cap];
    }

    public void push(int x){
        if(top == cap-1){
            System.out.println("Overflow");
            return;
        }

        ++top;
        arr[top] = x;
    }

    public int pop(){
        if(top == -1){
            System.out.println("Underflow");
            return Integer.MAX_VALUE;
        }

        int element = arr[top];
        --top;
        return element;
    }

    public int peek(){
        if(top == -1){
            System.out.println("Underflow");
            return Integer.MAX_VALUE;
        }

        return arr[top];
    }

    public int size(){
        return top+1;
    }

    public boolean isEmpty(){
        return top == -1;
    }



}

public class DesignStackUsingArrayEfficient {

    public static void main(String args[]){
        MyStackClass s = new MyStackClass(10);
        s.push(5);
        s.push(15);
        s.push(25);
        System.out.println(s.peek());
        System.out.println(s.pop());
        s.push(35);
        System.out.println(s.size());
        System.out.println(s.isEmpty());
    }



    
    
}
