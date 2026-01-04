package tree.maximumInTree;

/*
 * TIME COMPLEXITY : Theta(N)
 * AUXILIARY SPCE  : O(H), where H is height
 */

public class FindMaxUsingRecursion {

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        // Node root = new Node(10);
        // root.left = new Node(30);
        // root.right = new Node(40);
        // root.left.left = new Node(80);
        // root.left.left.right = new Node(70);
        // root.right.left = new Node(60);
        // root.right.right = new Node(20);
        
        Node root = new Node(30);
        root.left = new Node(20);
        root.left.left = new Node(40);

        System.out.println("Maximum in tree is : "+findMax(root));
    }

    public static int findMax(Node root){
        if(root==null){
            return Integer.MIN_VALUE;
        }

        return Math.max(root.data,Math.max(findMax(root.left),findMax(root.right)));
    }
    
}
