package BST.checkForBST;

/*
 * IDEA : For every node will pass a range
 *  -> root -> -Infinite to +Infinte
 *  -> root.left -> -Infinite to root.data -1
 *  -> root.right -> root.data + 1 to +Infinite
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(H), where H is height
 */

public class EfficientSolutionOne {

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


        //If your node values are int your BST should bound to long
        long LOWER = Long.MIN_VALUE;
        long UPPER = Long.MAX_VALUE;

        System.out.println("is given tree is BST ? : "+isBST(root, LOWER, UPPER));
    }

    public static boolean isBST(Node root, long lower, long upper){
        if(root == null){
            return true;
        }

        if(root.data < lower || root.data > upper){
            return false;
        }

        return isBST(root.left, lower, root.data) && isBST(root.right, root.data, upper);

    }
    
}
