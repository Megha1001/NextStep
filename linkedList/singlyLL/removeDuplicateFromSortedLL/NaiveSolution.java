package linkedList.singlyLL.removeDuplicateFromSortedLL;

public class NaiveSolution {

    static class Node {
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        // Node head = new Node(10);
        // head.next = new Node(20);
        // head.next.next = new Node(20);
        // head.next.next.next = new Node(30);
        // head.next.next.next.next = new Node(40);
        // head.next.next.next.next.next = new Node(40);
        // head.next.next.next.next.next.next = new Node(40);

        Node head = new Node(10);
        head.next = new Node(15);
        head.next.next = new Node(20);
        head.next.next.next = new Node(30);
        head = removeDuplicates(head);
        traverse(head);
    }

    public static Node removeDuplicates(Node head){
        if(head==null || head.next == null){
            return head;
        }

        Node curr = head;
        while(curr!= null && curr.next != null){
            Node temp = curr.next;
            while(temp!=null && temp.data == curr.data){
                temp = temp.next;
            }
            curr.next = temp;
            curr = temp;
        }

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
