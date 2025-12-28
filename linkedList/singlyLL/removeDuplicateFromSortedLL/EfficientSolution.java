package linkedList.singlyLL.removeDuplicateFromSortedLL;

public class EfficientSolution {

    static class Node {
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        // Node head = new Node(10);
        // head.next = new Node(20);
        // head.next.next = new Node(20);
        // head.next.next.next = new Node(30);
        // head.next.next.next.next = new Node(40);
        // head.next.next.next.next.next = new Node(40);
        // head.next.next.next.next.next.next = new Node(40);

        Node head = new Node(10);
        head.next = new Node(15);
        head.next.next = new Node(20);
        head.next.next.next = new Node(30);
        removeDuplicates(head);
        traverse(head);
    }

    public static void removeDuplicates(Node head){
        if(head==null || head.next == null){
            return ;
        }

        Node curr = head;
        while(curr != null && curr.next != null){
            if(curr.data == curr.next.data){
                curr.next = curr.next.next; //change only next reference not curr since the next ref can be duplicate
            }
            else{
                curr = curr.next;
            }
        }
       
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
    
}
