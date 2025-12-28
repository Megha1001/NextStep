package linkedList.singlyLL.loopDetection;

/*
 * IDEA : After traversing a node , point it to dummy node.
 * and go to next 
 * Repeat this steps until you reached the end or find a  node that is already pointing to dummy (loop)
 */

public class NaiveMethod {

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

        if(head == null){
            return false;
        }

        Node dummy = new Node(-1);
        Node curr = head;
        while(curr != null){
            if(curr.next == null){
                return false;
            }
            if(curr == dummy){
                return true;
            }

            Node next = curr.next;
            curr.next = dummy;
            curr = next;
        }

        return false;
    }
    
}
