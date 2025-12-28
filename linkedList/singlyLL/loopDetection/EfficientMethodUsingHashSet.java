package linkedList.singlyLL.loopDetection;

/*
 * IDEA : Traverse linked list and store elements in Hashset. if you encounter an element already there in hashSet then there is a loop
 */

import java.util.HashSet;

public class EfficientMethodUsingHashSet {
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
        HashSet<Node> h = new HashSet<>();
        if(head == null){
            return false;
        }
        Node curr = head;
        while(curr != null){

            if(h.contains(curr)){
                return true;
            }

            h.add(curr);

        }

        return false;
    }

}
