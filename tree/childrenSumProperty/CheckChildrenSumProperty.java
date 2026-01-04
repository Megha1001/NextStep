package tree.childrenSumProperty;

/*
 * Children sum property is a property when the sum of value left child and right child is equal to node's value if both childs are present. Otherwise, if one child is present then that child node value
 * must be equal to root value.
 * 
 * root == null should return true
 * Single node should return true;
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(H), where H is height
 */

public class CheckChildrenSumProperty {
    
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
        root.left = new Node(8);
        root.right = new Node(2);
        root.right.left = new Node(2);

        System.out.println("is the given tree satisfy the child sum property ? "+isCSum(root));
    }

    public static boolean isCSum(Node root){
        if(root == null){
            return true;
        }

        //single node
        if(root.left == null && root.right == null){
            return true;
        }

        int sum=0;
        if(root.left != null){
            sum += root.left.data;
        }

        if(root.right != null){
            sum += root.right.data;
        }

        return (root.data == sum ) && isCSum(root.left) && isCSum(root.right);
    
    }

}
