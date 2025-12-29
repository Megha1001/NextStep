package linkedList.singlyLL.swappingPairs;

/*
 * IDEA : Apply reverse K nodes in LL solution for k=2;
 * 
 */

public class SwapPairsInLL {

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
        // head.next.next.next.next.next = new Node(60);

        System.out.println("LL after swapping pairs is : ");
        head = swappingPair(head, 2);
        traverse(head);
    }

    public static Node swappingPair(Node head, int k){
        if(head == null || head.next == null){
            return head;
        }

        Node curr = head;
        int count = 0;

        Node next = null;
        Node prev = null;

        while(curr != null && count < k){
            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
            ++count;
        }

        if(next != null){
            Node new_head = swappingPair(next, k);
            head.next = new_head;
        }

        return prev;

    }

    public static void traverse(Node head){
        if(head == null){
            return;
        }

        Node curr=head;
        while(curr!=null){
            System.out.print(curr.data + " ");
            curr= curr.next;
        }
    }

    
}
