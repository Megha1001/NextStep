package linkedList.singlyLL.reverseGroupOfKNodes;

public class ReverseGroupOfKNodeRecursive {

    static class Node{
        int data;
        Node next;
        Node(int x){
            data=x;
            next=null;
        }
    }
    
    public static void main(String args[]) 
    { 
        Node head=new Node(10);
    	head.next=new Node(20);
    	head.next.next=new Node(30);
    	head.next.next.next=new Node(40);
    	head.next.next.next.next=new Node(50);
    	head.next.next.next.next.next=new Node(60);
    	head.next.next.next.next.next.next=new Node(70);
    	printlist(head);
    	head=reverseK(head,3);
        System.out.println();
    	printlist(head);
    
    }

    public static void printlist(Node head){
        if(head==null){
            return;
        }

        Node curr = head;
        while(curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

    public static Node reverseK(Node head, int k){
        Node curr = head;
        int count = 0;
        Node prev = null;
        Node next = null;
        while(curr != null && count < k){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            ++count;
        }

        if(next != null){
            Node new_head = reverseK(next, k);
            head.next = new_head;
        }

        return prev;
    }
   


}
