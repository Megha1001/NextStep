package linkedList.circularLL.insertion;

/*
 * TIME COMPLEXITY : O(1) --> BY Inserting after head and swap later with head
 * AUXILIARY SPACE : O(1)
 */

public class InsertionAtBeginCLLUsingSwappingTrickForO_1_Complexity {


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


        //insert after head
        newNode.next = head.next;
        head.next = newNode;

        //swap
        int temp = head.data;
        head.data = newNode.data;
        newNode.data = temp;

        return head;


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
