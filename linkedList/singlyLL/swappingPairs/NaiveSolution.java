package linkedList.singlyLL.swappingPairs;

/*
 * IDEA :
 * Run a loop while we have one node ahead;
 * 1. Swap the data of curr and curr.next
 * 2. make curr = curr.next.next;
 */

public class NaiveSolution {
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
        // head.next.next.next.next.next = new Node(60);

        System.out.println("LL after swapping pairs is : ");
        swappingPair(head);
        traverse(head);
    }

    public static void swappingPair(Node head){
       
        Node curr = head;
        while(curr!=null && curr.next != null){//making sure we have one node ahead
            int temp = curr.data;
            curr.data = curr.next.data;
            curr.next.data = temp;
            
            curr = curr.next.next;
        }

    }

    public static void traverse(Node head){
        if(head == null){
            return;
        }

        Node curr=head;
        while(curr!=null){
            System.out.print(curr.data + " ");
            curr= curr.next;
        }
    }
}
