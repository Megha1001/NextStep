package linkedList.segregateEvenAndOdd;

public class EfficientSolution {

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        // Node head = new Node(1);
        // head.next = new Node(2);
        // head.next.next = new Node(3);
        // head.next.next.next = new Node(4);
        // head.next.next.next.next = new Node(5);
        // head.next.next.next.next.next = new Node(6);
        Node head = new Node(1);
        head.next = new Node(3);
        head.next.next = new Node(5);

        System.out.println("LL after segregating even and odd is : ");
        
        head = segregateEvenAndOdd(head);
        traverse(head);
    }

    public static Node segregateEvenAndOdd(Node head){
        if(head == null || head.next == null){
            return head;
        }

        Node os = null;
        Node oe = null;

        Node es = null;
        Node ee = null;

        Node curr = head;
        while(curr != null){

            if(curr.data %2 == 0){
                if(es == null){
                    es = curr;
                    ee = es;
                }else{
                    ee.next = curr;
                    ee = curr;
                }
            }else {
                if(os==null){
                    os = curr;
                    oe = os;
                }else{
                    oe.next = curr;
                    oe = curr;
                }
            }

            curr = curr.next;
        }

        //if all odd or even
        if(os==null || es == null){
            return head;
        }

        ee.next = os;
        oe.next = null;

        return es;        
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
