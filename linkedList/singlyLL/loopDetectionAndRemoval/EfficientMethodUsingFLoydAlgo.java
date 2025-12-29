package linkedList.singlyLL.loopDetectionAndRemoval;

public class EfficientMethodUsingFLoydAlgo {
    

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
        head.next.next = new Node(2);
        head.next.next.next = head;

        System.out.println("LL after loop removal if present is  :  ");

        loopDetectionAndRemoval(head);
        traverse(head);
    }

    public static void loopDetectionAndRemoval(Node head){
        if(head == null){
            return;
        }

        //loop detection
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                break;
            }
        }

        //no loop
        if(slow != fast){
            return;
        }

        // after Floyd detection
        slow = head;

        // Case 1: loop starts at head
        if (slow == fast) {
            while (fast.next != slow) {
                fast = fast.next;
            }
        }
        // Case 2: loop starts elsewhere
        else {
            while (slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
        }

        // remove loop
        fast.next = null;

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
