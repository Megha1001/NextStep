package linkedList.circularLL.deletion;

/*
 * TIME COMPLEXITY : O(1) --> WOAH
 * AUXILIARY SPACE : O(1)
 */

public class DeleteHeadUsingSwipLogic_O_1_Complexity_CLL {

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
        head.next.next.next.next = head;


        traverse(head);

        head = deleteHead(head);
        System.out.println();
        traverse(head);
        head = deleteHeadOptimized(head);
        System.out.println();
        traverse(head);
    }


    public static Node deleteHead(Node head){
        //no node or single node
        if(head == null || head.next == head){
            return null;
        }

        //swap data of head and heads' next node
        int temp = head.data;
        head.data = head.next.data;
        head.next.data = temp;

        //delete temp
        head.next = head.next.next;

        return head;

    }

    public static Node deleteHeadOptimized(Node head){
        //no node or single node
        if(head == null || head.next == head){
            return null;
        }

        head.data = head.next.data;

        //delete temp
        head.next = head.next.next;

        return head;

    }

    public static void traverse(Node head){
        if(head==null){
            return;
        }

        Node curr = head;
        do{
            System.out.print(curr.data + " ");
            curr = curr.next;
        }while(curr != head);

    }

    
}
