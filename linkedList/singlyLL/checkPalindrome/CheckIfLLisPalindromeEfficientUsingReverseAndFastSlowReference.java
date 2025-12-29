package linkedList.singlyLL.checkPalindrome;

/*
 * IDEA
 * 1. Find middle of LL using slow and fast reference
 * 2. Reverse other half of LL (head = slow.next)
 * 3. Compare first half elements with second half
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(1)
 */

public class CheckIfLLisPalindromeEfficientUsingReverseAndFastSlowReference {
    static class Node{
        char data;
        Node next;

        Node(char data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        // Node head = new Node('R');
        // head.next = new Node('A');
        // head.next.next = new Node('D');
        // head.next.next.next = new Node('A');
        // head.next.next.next.next = new Node('R');

        // Node head = new Node('R');
        // head.next = new Node('A');
        // head.next.next = new Node('D');
        // head.next.next.next = new Node('A');
        // head.next.next.next.next = new Node('D');


        Node head = new Node('R');
        head.next = new Node('A');
        head.next.next = new Node('A');
        head.next.next.next= new Node('R');

        System.out.println("is the given LL is palindrome ? "+isPalindrome(head));
    }

    public static boolean isPalindrome(Node head){
        Node slow = head;
        Node fast = head;

        while(fast.next != null && fast.next.next !=null){ //modified condition
            slow = slow.next;
            fast = fast.next.next;
        }

        Node rev = reverse(slow.next);
        Node curr = head;

        while(rev != null){
            if(curr.data != rev.data){
                return false;
            }
            curr = curr.next;
            rev = rev.next;
        }

        return true;

    }

    public static Node reverse(Node head){
        if(head == null || head.next == null){
            return head;
        }

        Node curr = head;
        Node prev = null;
        while(curr != null){
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
