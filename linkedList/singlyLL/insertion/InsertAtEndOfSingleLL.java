package linkedList.singlyLL.insertion;

class NodeInsertAtEnd{
    int data;
    NodeInsertAtEnd next;

    NodeInsertAtEnd(int data){
        this.data = data;
        this.next = null;
    }
}

class LinkedListInsertAtEnd{
    NodeInsertAtEnd head;


    public void insertAtEnd(int x){
        NodeInsertAtEnd newNode = new NodeInsertAtEnd(x);

        if(head == null){
            head = newNode;
            return;
        }

        NodeInsertAtEnd curr = head;
        while(curr.next != null){
            curr = curr.next;
        }

        curr.next = newNode;

    }

    public void traverse(){
        NodeInsertAtEnd curr = head;
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
