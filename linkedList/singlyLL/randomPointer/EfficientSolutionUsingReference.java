package linkedList.singlyLL.randomPointer;

/*
 * IDEA : 1. Create Cloned nodes and insert it at alternate position (for next pointer)
 * 2. Traverse the list again and cloned nodes for random references
 * 3. Seperate the linked lists
 */

public class EfficientSolutionUsingReference {
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
        Node cloned_head = clone(head); 
    
        print(cloned_head);

    }

    public static Node clone(Node head){

        //intersect nodes
        Node curr = head;
        while(curr != null){
            Node next = curr.next;
            curr.next = new Node(curr.data);
            curr.next.next = next;
            curr = next;
        }


        //add random reference to cloned nodes
        for(Node oldHead = head; oldHead!=null; oldHead = oldHead.next.next){ // here we need to traverse through original hence curr should be update by .next.next
            oldHead.next.random = oldHead.random==null ? null : oldHead.random.next;
        }

        //seperate

        Node h2 = head.next;
        Node cloned = h2;

        for(Node oldHead = head; oldHead != null ; oldHead = oldHead.next){ // we are incrementing by .next since it has been updated /seperated already
            oldHead.next = oldHead.next.next; //no need to check for oldHead.next since these are even node but not applicable for cloned one
            cloned.next = cloned.next==null ? null : cloned.next.next;

            cloned = cloned.next;
        }

        return h2;


    }

    public static void print(Node head){
        
        Node curr = head;
        while(curr != null){
            System.out.println("data : "+curr.data + "random pointer :  "+curr.random.data);
            curr = curr.next;
        }

    }
}
