package linkedList.intersectionBwTwoLL;

/*
 * IDEA : Traverse one of the LL and then put it into HashSet
 * Traverse another linkedList to see which is the intersection node
 */

public class FindIntersectionBWTwoLLUsingHashSet {

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data=data;
        }
    }

    public static void main(String args[]){
         /* 
    		Creation of two linked lists 
    	
    		1st 3->6->9->15->30 
    		2nd 10->15->30 
    	
    		15 is the intersection point 
    	*/
        Node newNode; 
    	Node head1 = new Node(10); 
    	Node head2 = new Node(3); 
    	newNode = new Node(6); 
    	head2.next = newNode; 
    	newNode = new Node(9); 
    	head2.next.next = newNode; 
    	newNode = new Node(15); 
    	head1.next = newNode; 
    	head2.next.next.next = newNode; 
    	newNode = new Node(30); 
    	head1.next.next = newNode; 
        head1.next.next.next = null;

        Node intersectNode = findIntersectionElement(head1, head2);

        System.out.println("The intersection element in given LLs is : "+ intersectNode== null ? " " : intersectNode.data);
    }

    public static Node findIntersectionElement(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }



    }
    
}
