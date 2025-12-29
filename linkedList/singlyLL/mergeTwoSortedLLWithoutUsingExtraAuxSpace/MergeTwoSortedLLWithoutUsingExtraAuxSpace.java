package linkedList.singlyLL.mergeTwoSortedLLWithoutUsingExtraAuxSpace;

public class MergeTwoSortedLLWithoutUsingExtraAuxSpace {

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        Node h1 = new Node(10);
        h1.next = new Node(20);
        h1.next.next = new Node(30);
        h1.next.next.next = new Node(40);


        Node h2 = new Node(5);
        h2.next = new Node(15);
        h2.next.next = new Node(25);


        Node head = mergedLL(h1, h2);
        traverse(head);
    }


    public static Node mergedLL(Node h1, Node h2){

        Node t1 = h1;
        Node t2 = h2;

        Node head = null;

        // to fix head
        if(t1.data <= t2.data){
            head = t1;
            t1 = t1.next;
        }else {
            head = t2;
            t2 = t2.next;
        }

        Node prev = head;


        while(t1!=null && t2!=null){

            if(t1.data <=t2.data){
                prev.next = t1;
                prev = t1;
                t1 = t1.next;
            }
            else{
                prev.next = t2;
                prev = t2;
                t2= t2.next;
            }
        }

        if(t1!=null){
            prev.next = t1;
        }

        if(t2!=null){
            prev.next = t2;
        }


        return head;

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
