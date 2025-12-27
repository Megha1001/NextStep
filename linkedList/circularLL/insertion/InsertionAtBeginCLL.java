package linkedList.circularLL.insertion;

/*
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(1)
 */

public class InsertionAtBeginCLL {


    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        Node head = insertAtBegin(null, 10);
        head = insertAtBegin(head, 20);
        head = insertAtBegin(head, 30);
        head = insertAtBegin(head, 40);
        head = insertAtBegin(head, 50);

        traverse(head);
    }

    public static Node insertAtBegin(Node head, int x){
        Node newNode = new Node(x);

        if(head == null){
            newNode.next = newNode;
            return newNode;
        }

        Node curr = head;
        //get hold of last node
        while(curr.next != head){
            curr = curr.next;
        }

        //lastNode -> curr
        newNode.next = curr.next;
        curr.next = newNode;

        return newNode;

    }

    public static void traverse(Node head){
        if(head==null){
            return;
        }

        Node curr = head;
        do{
            System.out.print(curr.data + " ");
            curr = curr.next;
        }while(curr != head);

    }

    
}
