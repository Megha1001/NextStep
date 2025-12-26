package linkedList.doublyLL.insertion;

/*
 * TIME COMPLEXITY : O(1)
 * AUXILIARY SPACE : O(1)
 */

public class InsertAtBeginDLL {

    static class Node {
        int data;
        Node next;
        Node prev;

        Node(int data){
            this.data = data;
        }
    }


    public static void main(String args[]){
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.prev = head;
        head.next.next = new Node(30);
        head.next.next.prev = head.next;

        head = insertAtBegin(head, 5);
        traverse(head);

    }

    public static Node insertAtBegin(Node head, int x){
        Node newNode = new Node(x);
        if(head == null){
            return newNode;
        }

        newNode.next = head;
        head.prev = newNode;
        return newNode;

    }

    public static void traverse(Node head){
        if(head == null){
            return;
        }
        Node curr = head;
        while(curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
    
}
