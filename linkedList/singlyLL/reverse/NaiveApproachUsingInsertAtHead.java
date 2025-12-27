package linkedList.singlyLL.reverse;

/*
 * IDEA : Traverse the linked list and insert at begin
 */

public class NaiveApproachUsingInsertAtHead {

    public static Node newNode;

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
        head.next.next.next.next = new Node(50);

        System.out.println("Reverse of given LL is : ");
        reverse(head);
        traverse(newNode);
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

    public static void reverse(Node head){

        if (head == null){
            return;
        }

        Node curr = head;
        while(curr != null){
            insertAtBegin(curr);
            curr = curr.next;
        }

    }
    
    public static void insertAtBegin(Node curr){ // we can't use curr , as it will change in original array
        Node temp = new Node(curr.data);
        if(newNode == null){
            newNode = temp;
            return;
        }

        temp.next = newNode;
        newNode = temp;
        return;

    }

}
