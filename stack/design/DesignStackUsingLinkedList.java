package stack.design;

/*
 * To make push and pop operation O(1) , will be inserting at head in LL;
 */


class Node{
    int data;
    Node next;

    Node(int data){
        this.data = data;
    }
}

class MyStackUsingLL{

    Node head; // points to head
    int size; // to make size operation constant will increase it on push and decrease it on pop

    MyStackUsingLL(){
        this.head = null;
        this.size = 0;
    }


    void push(int x){
        Node newNode = new Node(x);

        ++size;
        if(head == null){
            head = newNode;
            return;
        }

        newNode.next = head;
        head = newNode;
    }

    int pop(){
        if(head == null){
            System.out.println("Underflow");
            return Integer.MAX_VALUE;
        }

        int val = head.data;
        head = head.next;
        --size;
        return val;
    }

    int peek(){
        if(head == null){
            System.out.println("Underflow");
            return Integer.MAX_VALUE;
        }

        return head.data; //no deletion
    }


    int size(){
        return size;
    }

    boolean isEmpty(){
        return size==0; //or head==null;
    }

}

public class DesignStackUsingLinkedList {

   public static void main(String args[]){
        MyStackUsingLL s = new MyStackUsingLL();
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
