package linkedList.doublyLL.deletion;

public class DeleteHeadNodeDLL {

    static class Node{
        int data;
        Node prev;
        Node next;

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

        traverse(head);
        head = deleteHead(head);
        System.out.println();
        traverse(head);
    }

    public static Node deleteHead(Node head){
        if(head==null || head.next == null){
            return head;
        }

        head = head.next;
        head.prev = null;
        return head;

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
