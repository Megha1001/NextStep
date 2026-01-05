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
            return null;
        }

    }

}
