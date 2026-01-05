package tree.spiralOrZigZagTraversal;

/*
 * IDEA : Use two Stacks - S1, & S2
 *  1. Put root in S1
 *  2. While S1 != empty or s2 != empty
 *      -> while S1 != empty
 *          -> Pop top and print then push the child left then right
 *      -> while S2 != empty
 *          -> pop top and print then push the child RIGHT THEN LEFT ---> ORDER IS REVERSED
 */

import java.util.ArrayDeque;

public class SpiralWithTwoStack {

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }
    

    public static void main(String args[]){
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.left = new Node(10);

        System.out.println("Spiral Traversal of given Binary tree is : ");
        spiralTraversal(root);
    }

    public static void spiralTraversal(Node root){
        if(root == null){
            return;
        }

        ArrayDeque<Node> s1 = new ArrayDeque<>();
        ArrayDeque<Node> s2 = new ArrayDeque<>();

        s1.push(root);

        while(!s1.isEmpty() || !s2.isEmpty()){
            while(!s1.isEmpty()){
                Node s1Top = s1.pop();
                System.out.print(s1Top.data + " ");

                if(s1Top.left!=null){
                    s2.push(s1Top.left);
                }
               
                if(s1Top.right != null){
                    s2.push(s1Top.right);
                }
                
            }

            while(!s2.isEmpty()){
                Node s2Top = s2.pop();
                System.out.print(s2Top.data+ " ");
                if(s2Top.right != null){
                    s1.push(s2Top.right);
                }

                if(s2Top.left != null){
                    s1.push(s2Top.left);
                }
                
            }

        }

    }

}
