package linkedList.deletion;

public class DeleteHeadNodeInLL {

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }
    }

    static class LinkedList{
        static Node head;
        static int size;

        public static void insertAtEnd(int data){
            Node newNode = new Node(data);
            ++size;
            if(head==null){
                head = newNode;
                return;
            }

            Node curr = head;
            while(curr.next != null){
                curr = curr.next;
            }

            curr.next = newNode;
        }

        public static void traverse(){
            Node curr = head;

            while(curr != null){
                System.out.print(curr.data + " ");
                curr = curr.next;
            }

        }

        public static void deletePos(int pos){
            if(head == null || pos > size){
                return ;
            }

            //delete head
            if(pos == 1){
                head = head.next;
                return;
            }

            //traverse to pos-1
            Node curr = head;
            for(int i=1; i < pos-1; i++){
                curr = curr.next;
            }

            //reached at pos-1
            curr.next = curr.next.next;

        }

    }

    public static void main(String args[]){
        LinkedList.insertAtEnd(10);
        LinkedList.insertAtEnd(20);
        LinkedList.insertAtEnd(30);
        LinkedList.insertAtEnd(40);
        System.out.println("Given linked list is : ");
        LinkedList.traverse();
        //delete head
        LinkedList.deletePos(1);
        System.out.println("Linked list after deleting head is : ");
        LinkedList.traverse();

        LinkedList.deletePos(2);
        System.out.println("Linked list after deleting 2nd position element is : ");
        LinkedList.traverse();
    }
    
}
