package tree.height;

/*
 * Height : Longest path from root to leaf
 * 
 * TIME COMPLEXITY :O(N)
 * AUXILIARY SPACE : O(H), where H is height
 */

public class FindHeightOfTreeRecursive {

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
        root.left = new Node(8);
        root.right = new Node(30);
        root.right.left = new Node(40);
        root.right.right = new Node(50);

        System.out.println("Height of the given binary tree is : "+findHeight(root));
    }

    public static int findHeight(Node root){

        if(root == null){
            return 0;
        }

        return Math.max(findHeight(root.left), findHeight(root.right)) + 1;
    }
    
}
