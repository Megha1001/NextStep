package linkedList.doublyLL.insertion;

public class InsertAtEndDLL {

    static class Node{
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

        insertAtEnd(head, 40);
        traverse(head);
    }
    
    public static void insertAtEnd(Node head, int x){
        Node newNode  = new Node(x);
        if(head==null){
            return;
        }

        Node curr = head;
        while(curr.next != null){
            curr = curr.next;
        }

        curr.next = newNode;
        newNode.prev = curr;
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
