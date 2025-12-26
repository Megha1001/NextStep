package linkedList.traversing;

public class TraversingOfSingleLL {

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
            this.next = null; //implicit, write for readibility
        }

    }

    public static void main(String args[]){
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);

        System.out.println("Element of linked List are : ");
        traverseLL(head);
    }

    public static void traverseLL(Node head){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }
    
}
