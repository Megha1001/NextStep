package linkedList.singlyLL.reverse;

/*
 * TIME COMPLEXITY : Theta(N)
 * AUXILIARY SPACE : O(1)
 */

public class EfficientApproachUsingThreeRef {
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

        if(head == null || head.next == null){
            return head;
        }

        Node prev = null;
        Node curr = head;

        while(curr != null){
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
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
