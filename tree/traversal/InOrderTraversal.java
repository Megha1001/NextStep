package tree.traversal;

/*
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(N)
 */

public class InOrderTraversal {

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
        root.right.left = new Node(4);
        root.right.right = new Node(5);

        System.out.print("The inOrder traversal of given tree is : ");
        inOrderTraversal(root);
    }

    public static void inOrderTraversal(Node root){ //LNR
    
        if(root != null){
            inOrderTraversal(root.left);
            System.out.print(root.data + " "); 
            inOrderTraversal(root.right);
        } 

    }
    
}
