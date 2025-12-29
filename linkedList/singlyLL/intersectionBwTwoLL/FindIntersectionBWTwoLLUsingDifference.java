package linkedList.intersectionBwTwoLL;

/*
 * IDEA : 
 * 1. Traverse both the linked list and find there lengths - c1, c2
 * 2. Find the absolute difference between them Math.abs(c1-c2)
 * 3. Iterate the longer list d times , where d = Math.abs(c1-c2)
 * 4. Iterate both list by one until we either found the common node or reach last in the linked list
 * 
 * TIME COMPLEXITY : O(M+N)
 * AUXILIARY SPACE : O(1)
 */



public class FindIntersectionBWTwoLLUsingDifference {

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

        int c1 = 0, c2 = 0;

        for(Node curr = head1; curr!=null; curr = curr.next){
            ++c1;
        }

        for(Node curr = head2; curr!=null ; curr = curr.next){
            ++c2;
        }

        int d = Math.abs(c1-c2);

        Node longNode = c1>c2 ? head1 : head2;
        Node shortNode = c1>c2 ? head2 : head1;

        int count=0;
        while(count != d){
            longNode = longNode.next;
            ++count;
        }

        while(longNode != null && shortNode != null){
            if(shortNode == longNode){
                return shortNode;
            }

            shortNode = shortNode.next;
            longNode = longNode.next;
        }
    
        
        return null;


    }
    
}
