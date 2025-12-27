package linkedList.singlyLL.nthNode;

/*
 * Approach : Two Pointer Approach
 *  -> Maintain two references -> first and second
 *  -> Move first reference by n position
 *  -> Point second reference to head
 *  -> Now move both reference by one position until first reference becomes null
 *  -> Second will be at position N from last, return second.data;
 * 
 * TIME COMPLEXITY : O(N) , one traversal
 * AUXILIARY SPACE : O(1)
 */

public class FindNthNodeUsingTwoPointerEfficientSLL {

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        // Node head = new Node(10);
        // head.next = new Node(20);
        // head.next.next = new Node(30);
        // head.next.next.next = new Node(40);
        // head.next.next.next.next = new Node(50);

        // int n = 2;

        // Node head = new Node(10);
        // head.next = new Node(20);
        // head.next.next = new Node(30);

        // int n = 3;

        // Node head = new Node(10);
        // head.next = new Node(20);

        // int n = 3;

        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);

        int n = 1;

        Node nthNodeFromLast = findNthNodeFromLast(head, n);
        System.out.println("The node's data present at N from last is : "+ (nthNodeFromLast==null ? "" : nthNodeFromLast.data));
    }

    public static Node findNthNodeFromLast(Node head, int n){

        if(head == null){
            return null;
        }

        Node first = head;
        //move n position
        for(int i=0; i<n; i++){
            if(first == null){
                return null;
            }
            first = first.next;
        }

        Node second = head;
        while(first != null){
            first = first.next;
            second = second.next;
        }

        return second;

    }
    
}
