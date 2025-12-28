package linkedList.singlyLL.reverse;

public class ReverseASLLUsingRecursionMethod3 {
    
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
        head = reverse(head, null);
        traverse(head);
    }


    public static Node reverse(Node curr, Node prev){
    
        if(curr == null){
            return prev;
        }

        Node next = curr.next;
        curr.next = prev;

        return reverse(next, curr);

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
