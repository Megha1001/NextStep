package linkedList.circularLL.deletion;

public class DeleteKthNodeCLL {

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
        head.next.next.next = head;

        traverse(head);

        int k = 2;
        // head = deleteNodeAtKthPosition(head, 1);
        // head = deleteNodeAtKthPosition(head, 3);
        head = deleteNodeAtKthPosition(head, k);
        System.out.println();
        traverse(head);

    }

    public static Node deleteNodeAtKthPosition(Node head, int k){
        if(head == null){
            return null;
        }

        //k=1, delete head
        if(k==1){
            head.data = head.next.data;
            head.next = head.next.next;
            return head;
        }

        Node curr = head;
        // traverse till k-1
        int count = 1;

        while(count != k-1){
            curr = curr.next;
            ++count;
        }

        curr.next = curr.next.next;
        return head;

    }

    public static void traverse(Node head){
        if(head == null){
            return;
        }

        Node curr = head;
        do{
            System.out.print(curr.data + " ");
            curr = curr.next;
        }while(curr != head);
    }
    
}
