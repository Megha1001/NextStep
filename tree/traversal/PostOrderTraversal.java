package tree.traversal;

/*
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(H), where H is height
 */

public class PostOrderTraversal {
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

        System.out.print("The postOrder traversal of given tree is : ");
        postOrderTraversal(root);
    }

    public static void postOrderTraversal(Node root){

        //LRN
        if(root!=null){
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.data + " ");
        }

    }
}
