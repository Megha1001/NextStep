package BST.insertion;

/*
 * TIME COMPLEXITY : O(H)
 * AUXILIARY SPACE : O(H)
 * 
 */


public class InsertInBSTRecursive {

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
        root.left = new Node(5);
        root.right = new Node(15);
        root.right.left = new Node(12);
        root.right.right = new Node(18);

        int x = 19;
        System.out.println("Root of BST after inserting given value is : "+insert(root, x).data);
    }

    public static Node insert(Node root, int x){
        if(root == null){
            return new Node(x);
        }
        else if (root.data > x){
            root.left = insert(root.left, x);
        }
        else{
            root.right = insert(root.right, x);
        }

        return root;
    }
    
}
