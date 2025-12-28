package linkedList.singlyLL.loopDetection;

public class FloydLoopDetectionMethod {
    
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
        head.next.next = head;

        System.out.println("is the given LL contains a loop ? "+isLoopFound(head));
    }

    public static boolean isLoopFound(Node head){

        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow==fast){
                return true;
            }
        }

        return false;

    }
    
}
