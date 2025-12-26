package linkedList.singlyLL.insertion;

class NodeInsertAtBegin{
    int data;
    NodeInsertAtBegin next;

    NodeInsertAtBegin(int data){
        this.data = data;
        this.next = null;
    }
}

class LinkedListInsertAtBegin{
    NodeInsertAtBegin head;
    public void insertAtBegin(int data){
        NodeInsertAtBegin newNode = new NodeInsertAtBegin(data);
        newNode.next = head;
        head = newNode;
    }


    public void traverse(){
        NodeInsertAtBegin curr = head;
        while(curr!=null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
}

public class InsertAtBeginOfSingleLL {


    public static void main(String args[]){
        LinkedListInsertAtBegin list = new LinkedListInsertAtBegin();
        list.insertAtBegin(1);
        list.insertAtBegin(2);
        list.insertAtBegin(3);
        list.insertAtBegin(4);
        list.insertAtBegin(5);
        System.out.print("Linked List: ");
        list.traverse();
    }

}
