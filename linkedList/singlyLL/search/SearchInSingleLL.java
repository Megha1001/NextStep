package linkedList.singlyLL.search;


class Node{

    int data;
    Node next;

    Node(int data){
        this.data = data;
        this.next = null;
    }

}

public class SearchInSingleLL {

    public static void main(String args[]){
        Node head = new Node(10);
        head.next = new Node(5);
        head.next.next = new Node(20);
        head.next.next.next = new Node(15);

        System.out.println("Iterative method : Element is present at idx : "+ search(head, 20));
        System.out.println("Recursive method : Element is present at idx : "+ searchRecursively(head, 20, 1));
    }
    
    /*
    * TIME COMPLEXITY : O(N)
    * AUXILIARY SPACE : O(1)
    */

    public static int search(Node head, int x){
        if(head == null){
            return -1;
        }

        Node curr = head;
        int idx = 0;
        while(curr != null){
            ++idx;
            if(curr.data == x){
                return idx;
            }
            curr = curr.next;
        }

        return -1;

    }
    
    /*
    * TIME COMPLEXITY : O(N)
    * AUXILIARY SPACE : O(N)
    */


    public static int searchRecursively(Node head, int x, int idx){
        if(head==null){
            return -1;
        }

        if(head.data == x){
            return idx;
        }

        return searchRecursively(head.next, x, idx+1);
    }
    
}
