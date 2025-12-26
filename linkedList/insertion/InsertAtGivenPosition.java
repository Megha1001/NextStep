package linkedList.insertion;

public class InsertAtGivenPosition {

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

        public static void insertAtEnd(int x){
            Node newNode = new Node(x);
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

        public static void insertAtGivenPos(int pos, int x){
            Node newNode = new Node(x);

            if(pos > (size+1)){
                return ; //invalid
            }

            if(head == null && pos == 1){
                //first and only node
                head = newNode;
                return;
            }

            //insert at head
            if(pos == 1){
                newNode.next = head;
                head = newNode;
                return;
            }

            //reference to pos-1
            int count = 1; // treating 1st node as 1st count
            Node curr = head;

            while(count != pos-1){
                curr = curr.next;
                ++count;
            }

            //order is imp!!
            newNode.next = curr.next;
            curr.next = newNode;
            ++size;
        }

        public static void traverse(){

            Node curr = head;
            while(curr != null){
                System.out.print(curr.data + " ");
                curr = curr.next;
            }
        }


    }

    public static void main(String args[]){
        LinkedList.insertAtEnd(10);
        LinkedList.insertAtEnd(30);
        LinkedList.insertAtEnd(50);
        LinkedList.insertAtEnd(70);
        System.out.println("Given linked list");
        LinkedList.traverse();
        
        LinkedList.insertAtGivenPos(1, 20);

        System.out.println("Modified linked list");
        LinkedList.traverse();

        System.out.println("size of Linked list is : "+ LinkedList.size);
    }
    
    
}
