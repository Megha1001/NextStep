package tree.isBalancedTree;

/*
 * IDEA : Our function will return 1 thing
 *  -1 is tree is not balanced otherwise return height
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(H) where H is height
 */

public class EfficientSolution {
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

        System.out.println("is the given tree is balanced : "+(isBalanced(root) == -1 ? false : true));
    }

    public static int isBalanced(Node root){
        if(root == null){
            return 0; //height
        }

        int lh = isBalanced(root.left);
        if(lh==-1){
            return -1;
        }

        int rh = isBalanced(root.right);
        if(rh==-1){
            return -1;
        }

        if(Math.abs(lh-rh)>1){
            return -1;
        }else{
            return Math.max(lh,rh) + 1;
        }
    }
}
