package linkedList.singlyLL.randomPointer;

/*
 * IDEA : 1. Create a HashMap m
 *  2. Traverse the given list and do the following
 *      m.put(curr, new Node(curr.data));
 *  3. Traverse again the given list and do the following
 *  Node clone = m.get(curr);
 *  clone.next = m.get(curr.next);
 *  clone.random = m.get(curr.random);
 * 
 * 4. Return m.get(head);
 */

import java.util.HashMap;

public class NaiveSolutionUsingHashMap {

    static class Node{
        int data;
        Node next;
        Node random;

        Node(int data){
            this.data = data;
        }
    }
    

    public static void main(String args[]){
        Node head = new Node(10); 
        head.next = new Node(5); 
        head.next.next = new Node(20); 
        head.next.next.next = new Node(15); 
        head.next.next.next.next = new Node(20); 
        
        head.random = head.next.next;
        head.next.random=head.next.next.next;
        head.next.next.random=head;
        head.next.next.next.random=head.next.next;
        head.next.next.next.next.random=head.next.next.next;
        
        System.out.print( "Original list : \n"); 
        print(head); 
      
        System.out.print( "\nCloned list : \n"); 
        Node cloned_list = clone(head); 
        print(cloned_list);

    }

    public static Node clone(Node head){
        HashMap<Node, Node> map = new HashMap<>();
        
        //populate map with step1
        for(Node curr = head; curr!=null; curr = curr.next){
            map.put(curr, new Node(curr.data)); //clone
        }

        for(Node curr= head; curr!=null; curr = curr.next){
            Node clone = map.get(curr);
            clone.next = map.get(curr.next);
            clone.random = map.get(curr.random);
        }

        return map.get(head);

    }


    public static void print(Node head){
        
        Node curr = head;
        while(curr != null){
            System.out.println("data : "+curr.data + "random pointer :  "+curr.random.data);
            curr = curr.next;
        }

    }

}
