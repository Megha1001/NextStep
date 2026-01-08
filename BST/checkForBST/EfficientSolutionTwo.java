package BST.checkForBST;

/*
 * IDEA : InOrder Traversal of BST is sorted
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(H), where H is height
 */

public class EfficientSolutionTwo {

    public static long prev = Long.MIN_VALUE;

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data=data;
        }
    }

    public static void main(String args[]){
        // Node root = new Node(80);
        // root.left = new Node(70);
        // root.right = new Node(90);
        // root.left.left = new Node(60);
        // root.left.right = new Node(75);
        // root.right.left = new Node(85);
        // root.right.right = new Node(100);

        Node root = new Node(20);
        root.left = new Node(8);
        root.right = new Node(30);
        root.right.left = new Node(18);
        root.right.right = new Node(35);

        System.out.println("is given tree is BST ? : "+isBST(root));
    }

    public static boolean isBST(Node root){
        if(root == null){
            return true;
        }
        if(!isBST(root.left)){
            return false;
        }

        if(root.data <= prev){
            return false;
        }

        prev = root.data;

        return isBST(root.right);
    }
    
}
