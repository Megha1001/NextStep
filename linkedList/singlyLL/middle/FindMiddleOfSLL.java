package linkedList.singlyLL.middle;

public class FindMiddleOfSLL {

    static class Node {
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        // Node head = new Node(10);
        // head.next = new Node(5);
        // head.next.next = new Node(20);
        // head.next.next.next = new Node(15);
        // head.next.next.next.next = new Node(25);


        // Node head = new Node(10);
        // head.next = new Node(5);
        // head.next.next = new Node(20);
        // head.next.next.next = new Node(15);
        // head.next.next.next.next = new Node(25);
        // head.next.next.next.next.next = new Node(30);

        // Node head = new Node(10);

        // Node head = null;

        Node head = new Node(10);
        head.next = new Node(20);

        traverse(head);
        Node middleNode = findMiddleElement(head);
        System.out.println("The middle element in given LL is : "+(middleNode !=null ? middleNode.data : ""));
    }

    public static Node findMiddleElement(Node head){
        if(head == null){
            return null;
        }

        if(head.next == null){
            return head;
        }


        //find length
        int len = 0;
        Node curr = head;
        while(curr != null){
            ++len;
            curr = curr.next;
        }

        //find middle
        curr = head;
        int count = 1;
        while(curr.next != null && count != len/2){
            curr = curr.next;
            ++count;
        }
        
        return curr.next;

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
