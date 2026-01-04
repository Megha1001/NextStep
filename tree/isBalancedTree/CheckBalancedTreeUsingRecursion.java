package tree.isBalancedTree;

/*
 * IDEA : For each node check the difference between height of lst - rst <=1
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(H), where H is height of tree
 */


public class CheckBalancedTreeUsingRecursion {

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        Node root = new Node(18);
        root.left = new Node(4);
        root.right = new Node(20);
        root.right.left = new Node(13);
        root.right.right = new Node(70);

        // Node root = new Node(18);
        // root.left = new Node(4);
        // root.left.left = new Node(20);

        System.out.println("is the given tree is balanced : "+isBalanced(root));
    }

    public static boolean isBalanced(Node root){
        
        if(root == null){
            return true;
        }

        int lh = getHeight(root.left);
        int rh = getHeight(root.right);

        return (Math.abs(lh-rh) <=1)
        && isBalanced(root.left)
        && isBalanced(root.right);

    }

    public static int getHeight(Node root){
       if(root == null){
            return 0;
       }

       return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
    
}
