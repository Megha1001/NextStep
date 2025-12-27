package linkedList.circularLL.traversal;


/*
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(1)
 */

public class TraverseCLLUsingForLoop {

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }
    }
    

    public static void main(String args[]){
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        head.next.next.next.next = head;


        traverse(head);
    }


    public static void traverse(Node head){
        if(head == null){
            return;
        }

        //print head
        System.out.print(head.data + " ");

        for(Node curr = head.next; curr!=head; curr = curr.next){
            System.out.print(curr.data + " ");
        }

    }
    
}
