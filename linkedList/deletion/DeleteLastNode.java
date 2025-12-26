package linkedList.deletion;

/*
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPCE : O(1)
 */

class Node{
    int data;
    Node next;

    Node(int data){
        this.data = data;
    }
}

public class DeleteLastNode {

    public static void main(String args[]){

        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        System.out.println("Element in LL are : ");
        traverse(head);
        System.out.println("After deleting last elment : ");
        deleteFromLast(head);
        traverse(head);
        
    }

    public static void traverse(Node head){
        if(head==null){
            return;
        }

        Node curr = head;
        while(curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

    public static void deleteFromLast(Node head){
        if(head == null || head.next == null){
            return;
        }

        Node curr = head;
        while(curr.next.next != null){
            curr = curr.next;
        }
        curr.next = null;
    }
    
}
