package linkedList.insertion;

class Node{
    int data;
    Node next;

    Node(int data){
        this.data = data;
        this.next = null;
    }
}

class LinkedList{
    Node head;
    public void insertAtBegin(int data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }


    public void traverse(){
        Node curr = head;
        while(curr!=null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
}

public class InsertAtBeginOfSingleLL {


    public static void main(String args[]){
        LinkedList list = new LinkedList();
        list.insertAtBegin(1);
        list.insertAtBegin(2);
        list.insertAtBegin(3);
        list.insertAtBegin(4);
        list.insertAtBegin(5);
        System.out.print("Linked List: ");
        list.traverse();
    }

}
