package tree.leftViewOfTheTree;

/*
 * IDEA : Use LevelOrder traversal to print left view
 */

import java.util.ArrayDeque;

public class PrintLeftViewOfTheTreeUsingLevelOrderTraversal {

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        // Node root = new Node(10);
        // root.left = new Node(20);
        // root.right = new Node(30);
        // root.left.left = new Node(40);
        // root.left.right = new Node(50);
        // root.right.right = new Node(60);

        Node root = new Node(30);
        root.right = new Node(50);
        root.right.left = new Node(60);
        root.right.left.right = new Node(10);

        System.out.println("Left view of the tree is : ");
        printLeftView(root);
    }

    public static void printLeftView(Node root){
        if(root==null){
            return;
        }

        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(root);

        while(!q.isEmpty()){

            int levelSize = q.size();

            for(int i=0; i<levelSize; i++){
                Node temp = q.poll();
                if(i==0){
                    System.out.print(temp.data + " ");
                }
                if(temp.left!=null){
                    q.offer(temp.left);
                }
                if(temp.right != null){
                    q.offer(temp.right);
                }
            }

        }
        
    }
    
}
