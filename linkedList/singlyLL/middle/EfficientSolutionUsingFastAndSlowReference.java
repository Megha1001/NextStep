package linkedList.singlyLL.middle;

/*
 * IDEA : Use slow and fast references
 * 1. Move slow by 1 and fast by two reference until fast reaches end
 *  Odd : fast != null
 *  Even : fast.next != null
 * 
 * When fast reached end, slow will be in middle
 * 
 * TIME COMPLEXITY : O(N) --> But one traversal
 * AUXILIARY SPACE : O(1);
 */

public class EfficientSolutionUsingFastAndSlowReference {
    
    static class Node {
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        Node head = new Node(10);
        head.next = new Node(5);
        head.next.next = new Node(20);
        head.next.next.next = new Node(15);
        head.next.next.next.next = new Node(25);


        // Node head = new Node(10);
        // head.next = new Node(5);
        // head.next.next = new Node(20);
        // head.next.next.next = new Node(15);
        // head.next.next.next.next = new Node(25);
        // head.next.next.next.next.next = new Node(30);

        // Node head = new Node(10);

        // Node head = null;

        // Node head = new Node(10);
        // head.next = new Node(20);

        traverse(head);
        Node middleNode = findMiddleElement(head);
        System.out.println("The middle element in given LL is : "+(middleNode !=null ? middleNode.data : ""));
    }

    public static Node findMiddleElement(Node head){

        if(head == null){
            return null;
        }

        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
        

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
