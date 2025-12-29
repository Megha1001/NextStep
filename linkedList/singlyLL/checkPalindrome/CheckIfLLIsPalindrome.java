package linkedList.singlyLL.checkPalindrome;

import java.util.Deque;
import java.util.ArrayDeque;

public class CheckIfLLIsPalindrome {

    static class Node{
        char data;
        Node next;

        Node(char data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        Node head = new Node('R');
        head.next = new Node('A');
        head.next.next = new Node('D');
        head.next.next.next = new Node('A');
        head.next.next.next.next = new Node('R');

        System.out.println("is the given LL is palindrome ? "+isPalindrome(head));
    }

    public static boolean isPalindrome(Node head){
        if(head == null || head.next == null){
            return true;
        }

        Deque<Character> stack = new ArrayDeque<Character>();

        for(Node curr = head; curr!=null; curr = curr.next){
            stack.push(curr.data);
        }

        for(Node curr=head; curr!=null; curr= curr.next){
            if(stack.pop() != curr.data){
                return false;
            }
        }

        return true;
    }
    
}
