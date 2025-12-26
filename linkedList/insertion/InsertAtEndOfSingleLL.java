package linkedList.insertion;

class LinkedListInsertAtEnd{
    Node head;


    public void insertAtEnd(int x){
        Node newNode = new Node(x);

        if(head == null){
            head = newNode;
            return;
        }

        Node curr = head;
        while(curr.next != null){
            curr = curr.next;
        }

        curr.next = newNode;

    }

    public void traverse(){
        Node curr = head;
        while(curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

}

public class InsertAtEndOfSingleLL {
    
    public static void main(String args[]){
        LinkedListInsertAtEnd ll = new LinkedListInsertAtEnd();
        ll.insertAtEnd(10);
        ll.insertAtEnd(20);
        ll.insertAtEnd(30);
        ll.insertAtEnd(40);
        ll.traverse();
    }

}
