package linkedList.doublyLL.reverseDLL;

public class ReverseDLL {

    static class Node{
        int data;
        Node next;
        Node prev;

        Node(int data){
            this.data = data;
        }
    }
    
    public static void main(String args[]){
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.prev = head;
        head.next.next = new Node(30);
        head.next.next.prev = head.next;

        head = reverseDLL(head);
        traverse(head);
    }

    public static Node reverseDLL(Node head){
        if(head == null || head.next==null){
            return head;
        }

        Node curr = head;
        Node prev = null;

        while(curr != null){
            prev = curr.prev;
            curr.prev = curr.next;
            curr.next = prev;

            //curr.next now becomes curr.prev
            curr = curr.prev;
        }

        return prev.prev;


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
