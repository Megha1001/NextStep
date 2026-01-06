package tree.traversal.iterative;

import java.util.ArrayDeque;

public class PreOrderTraversalIterative {
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

        System.out.println("Preorder traversal of given tree by iterative way is : ");
        preOrderTraversalIterative(root);
    }

    public static void preOrderTraversalIterative(Node root){
        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node curr = stack.pop();
            System.out.print(curr.data + " ");
            if(curr.right!=null){
                stack.push(curr.right);
            }
            if(curr.left!=null){
                stack.push(curr.left);
            }
        }

    }
}
