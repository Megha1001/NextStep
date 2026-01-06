package BST.insertion;


/*
 * IDEA : Search the position until gets null and store the curr in stack.
 * If we found the element return root. If not if we get the null find the peek from stack and insert in its left or right by comparing value
 * 
 * */

import java.util.ArrayDeque;

public class InsertInBSTUsingStackIterative {

    static class Node{
        int data;
        Node left;
        Node right;
    
        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.right.left = new Node(12);
        root.right.right = new Node(18);

        int x = 19;
        System.out.println("Root of BST after inserting given value is : "+insert(root, x).data);
    }

    public static Node insert(Node root, int x){

        Node newNode = new Node(x);

        if(root == null){
            return newNode;
        }

        ArrayDeque<Node> stack = new ArrayDeque<>();
        Node curr = root;
        
        while(curr!=null){
            stack.push(curr);
            if(curr.data > x){
                curr = curr.left;
            }else{
                curr = curr.right;
            }
        }

        curr = stack.peek();
        if(curr.data > x){
            curr.left = newNode;
        }else {
            curr.right = newNode;
        }

        return root;
    }
    
}
