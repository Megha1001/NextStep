package linkedList.singlyLL.insertion;

/*
 * TIME COMPLEXITY : THETA(P), where P is position of element in final LL
 * AUXILIARY SPACE : O(1)
 * 
 */

public class InsertInSortedSigleLL {

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

        head = insert(head, 25);
        traverse(head);
        System.out.println();

        head = insert(head, 5);
        traverse(head);
        System.out.println();

        head = insert(head, 50);
        traverse(head);
    }

    public static Node insert(Node head, int x){
        Node newNode = new Node(x);

        if(head == null){
            return newNode;
        }

        //insert at head
        if(head.data >= x){
            newNode.next = head;
            return newNode;
        }

        Node curr = head;

        while(curr.next != null && curr.next.data < x){
            curr = curr.next;
        }

        newNode.next = curr.next;
        curr.next = newNode;

        return head;

    }


    public static Node traverse(Node head){
        if(head == null || head.next == null){
            return head;
        }

        Node curr = head;
        while(curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        return head;
    }
    
}
