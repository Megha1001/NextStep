package tree.traversal.iterative;

import java.util.ArrayDeque;

/*
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(H), where H is height
 */

public class InOrderTraversalIterative {

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
        root.left = new Node(20);
        root.right = new Node(30);

        root.left.left = new Node(40);
        root.left.right = new Node(50);

        System.out.println("Inorder traversal of given tree by iterative way is : ");
        inOrderTraversalIterative(root);
    }

    public static void inOrderTraversalIterative(Node root){
        ArrayDeque<Node> stack = new ArrayDeque<>();

        Node curr = root;
        while(curr != null || !stack.isEmpty()){
            while(curr!=null){
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            System.out.print(curr.data + " ");
            curr = curr.right;
        }


    }
    
}
