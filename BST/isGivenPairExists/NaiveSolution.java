package BST.isGivenPairExists;

/*
 * DO inorder traversal and use two pointer approach to find sum
 */

public class NaiveSolution {

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
        root.right = new Node (16);
        root.right.left = new Node(16);
        root.right.right = new Node(40);

        int sum  = 21;

        System.out.println("is the given sum exists in tree ? "+isPairSum(root, sum));
    }
    
}
