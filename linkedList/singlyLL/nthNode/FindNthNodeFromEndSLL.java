package linkedList.singlyLL.nthNode;

/*
 * IDEA : Find length-N+1 from start
 * 
 * TIME COMPLEXITY : O(N + Pos) = O(N) , two traversal
 * AUXILIARY SPACE : O(1)
 */

public class FindNthNodeFromEndSLL {

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

        //find length
        int len = findLengthLL(head);

        int k = len - n + 1;

        if(k <= 0){
            return null;
        }

        int count = 1;

        Node curr = head;
        while(count != k){
            ++count;
            curr = curr.next;
        }

        return curr;

    }

    public static int findLengthLL(Node head){
        Node curr = head;
        int len = 0;
        while(curr != null){
            ++len;
            curr = curr.next;
        }

        return len;
    }
    
}
