package tree.sizeOfTree;


public class CalculateSizeByRecursive {

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
        root.left = new Node(80);
        root.right = new Node(70);
        root.left.left = new Node(30);
        root.left.right = new Node(40);

        System.out.println("The size of binary tree is : "+findSize(root));
    }


    public static int findSize(Node root){
       if(root == null){
            return 0;
       }

       return 1 
        + findSize(root.left) 
        + findSize(root.right);
    }
    
}
