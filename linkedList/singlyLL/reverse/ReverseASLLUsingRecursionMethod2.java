package linkedList.singlyLL.reverse;

public class ReverseASLLUsingRecursionMethod2 {
    
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

        System.out.println("Reverse of given LL is : ");
        head = reverse(head);
        traverse(head);
    }


    public static Node reverse(Node head){
    
        if(head==null || head.next == null){
            return head;
        }

        Node rest_head = reverse(head.next);
        Node rest_tail = head.next;
        rest_tail.next = head;
        head.next = null;
        return rest_head;

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
