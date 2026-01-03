package tree.traversal.LevelOrderTraversal;

/*
 *                  [10]
               /    \
           [20]      [30]
           /   \        \
        [8]    [7]       [6]
               / \
            [9]  [15]

 O/p : 10,20,30,8,7,6,9,15

 TIME COMPLEXITY : Theta(N)
 AUXILIARY SPACE : O(N)
 */

import java.util.ArrayDeque;

public class LevelOrderTraversalVariation1 {

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
        // root.left.left = new Node(8);
        // root.left.right = new Node(7);
        // root.left.right.left = new Node(9);
        // root.left.right.right = new Node(15);
        // root.right.right = new Node(6);

        // Node root = new Node(3);
        // root.left = new Node(4);
        // root.left.right = new Node(5);
        
        Node root = new Node(8);
        root.right = new Node(6);
        root.right.left = new Node(2);
        root.right.right = new Node(4);
        root.right.right.left = new Node(3);


        System.out.println("Level order traversal of given tree is : ");
        printLevelOrderTraversal(root);
    }


    public static void printLevelOrderTraversal(Node root){

        if(root == null){
            return;
        }

        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(root);

        while(!q.isEmpty()){
            Node temp = q.poll();
            System.out.print(temp.data + " ");
            
            if(temp.left !=null){
                q.offer(temp.left);
            }

            if(temp.right != null){
                q.offer(temp.right);
            }

        }


    }
    
}
