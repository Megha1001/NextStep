package linkedList.circularLL.insertion;

/*
 * TIME COMPLEXITY : O(1)
 * AUXILIARY SPACE : O(1)
 */

public class InsertionAtEndUsingSwappingTrickFor_O_1_Complexity {

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        Node head = insertAtEnd(null, 10);
        head = insertAtEnd(head, 20);
        head = insertAtEnd(head, 30);
        head = insertAtEnd(head, 40);
        head = insertAtEnd(head, 50);

        traverse(head);
    }

    public static Node insertAtEnd(Node head, int x){
        Node newNode = new Node(x);
        if(head == null){
            newNode.next = newNode;
            return newNode;
        }

        newNode.next = head.next;
        head.next = newNode;

        //swap
        int temp = newNode.data;
        newNode.data = head.data;
        head.data = temp;

        return newNode;
    }

    public static void traverse(Node head){
        if(head == null){
            return;
        }

        //handle head seperately
        System.out.print(head.data + " ");

        for(Node curr = head.next; curr != head; curr = curr.next){
            System.out.print(curr.data + " ");
        }
    }
    
}
