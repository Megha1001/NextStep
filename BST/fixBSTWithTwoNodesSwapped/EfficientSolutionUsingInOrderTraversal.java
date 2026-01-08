package BST.fixBSTWithTwoNodesSwapped;

/*
 * IDEA : Inorder traversal of BST is sorted
 * There will only be two possible problemtic pair
 *  -> will found two pairs (a, b), (c, d) -> in that case a and d will be wrongly placed
 *  -> will found one pair (a,b)
 * 
 * Maintain two references prev, first and second and swap first and second
 */

public class EfficientSolutionUsingInOrderTraversal {

    public static long check = Integer.MIN_VALUE;

    //for fix BST
    public static Node prev = null;
    public static Node first = null;
    public static Node second = null;

    static class Node{
        int data;
        Node left;
        Node right;

        Node (int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        Node root = new Node(20);
        root.left = new Node(60);
        root.right = new Node(80);
        root.left.left = new Node(4);
        root.left.right = new Node(10);
        root.right.left = new Node(8);
        root.right.right = new Node(100);
        // 60 and 8 are wrongly placed

        //correct
        // Node root = new Node(80);
        // root.left = new Node(70);
        // root.right = new Node(90);
        // root.left.left = new Node(60);
        // root.left.right = new Node(75);
        // root.right.left = new Node(85);
        // root.right.right = new Node(100);

        System.out.println("is given tree is BST ? : "+isBST(root));

        fixBST(root);

        //swap first and second
        int temp = first.data;
        first.data=second.data;
        second.data = temp;
        check = Integer.MIN_VALUE;

        System.out.println("is fixed tree is BST ? : "+isBST(root));

    }

    public static void fixBST(Node root){
        if(root == null){
            return;
        }

        //LNR

        fixBST(root.left);

        if(prev!=null && prev.data >= root.data){
            if(first==null){
                first = prev;
            }
            second = root;
        }

        prev = root;

        fixBST(root.right);

    }

    public static boolean isBST(Node root){

        if(root == null){
            return true;
        }

        if(!isBST(root.left)){
            return false;
        }

        if(check >= root.data){
            return false;
        }
        check = root.data;

        return isBST(root.right);

    }

    
}
