package linkedList.circularLL.creation;

/*
 * TIME COMPLEXITY : Theta(N)
 * AUXILIARY SPACE : O(1)
 */

public class CreateCLL {

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

        Node curr = head;
        while(curr.next != head){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }

        //for last node
        System.out.print(curr.data + " ");

    }

}
