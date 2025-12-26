package linkedList.traversing;

/*
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(N), for recursion call stack
 */

public class TraversingOfSingleLLUsingRecursion {

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
            this.next = null;
        }

        public static void traverseRecursively(Node head){
            if(head==null){
                return ;
            }

            System.out.print(head.data + " ");
            traverseRecursively(head.next);
        }
        
    }

    public static void main(String args[]){
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);

        System.out.println("Element of LL are : ");
        Node.traverseRecursively(head);
    }
    
}
