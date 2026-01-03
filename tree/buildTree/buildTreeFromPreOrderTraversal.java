package tree.buildTree;

/*
 * TIME COMPLEXITY : O(N) since we are traversing N elements
 */

public class buildTreeFromPreOrderTraversal {

    public static int idx = -1; //traverse preOrder


    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        int preOrder[] = {1,2,-1,-1,3,4,-1,-1,5,-1,-1}; //NLR
        Node root = buildTree(preOrder);

        System.out.println("Root data : "+root.data+"\nLeft child : "+root.left.data + " \nRight Child : "+root.right.data);
    }


    public static Node buildTree(int [] preOrder){
        ++idx;

        if(preOrder[idx]== -1){
            return null;
        }


        Node root = new Node(preOrder[idx]);
        root.left = buildTree(preOrder);
        root.right = buildTree(preOrder);

        return root;

    }
    
}
