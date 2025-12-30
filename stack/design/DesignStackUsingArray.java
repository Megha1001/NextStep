package stack.design;

/*
 * THIS IS NOT CORRECT - WHy because we are inserting at start of array that makes push and pop operation O(N) so insert at end and maintain top reference as done in DesignStackUsingArrayEfficient.java
 */


class MyStack{

    int capacity;
    int size;
    int arr[];

    MyStack(int capacity){
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.size = 0; //implicit
    }

    public void push(int x){
        if(capacity == size){
            System.out.println("Overflow");
            return;
        }

        for(int i=size-1; i>=0; i--){
            arr[i] = arr[i+1];
        }
        arr[0] = x;
        ++size;
    }

    public int pop(){
        if(size==0){
            System.out.println("Underflow");
            return -1;
        }

        int element = arr[0];
        for(int i=1; i<size; i++){
            arr[i-1] = arr[i];
        }
        --size;
        return element;
    }

    public int peek(){
        if(size==0){
            System.out.println("Underflow");
            return -1;
        }
        return arr[0];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }




}

public class DesignStackUsingArray {

    public static void main(String args[]){
        MyStack s = new MyStack(10);
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
