package tree.traversal.LevelOrderTraversal;


import java.util.ArrayDeque;

/*
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(W) , where w is width of binary tree
 */

public class LevelOrderTraversalVariation2Efficient {
    
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
            int levelSize = q.size(); //nodes in current level. DONT PUT in for loop keep it seperate. As queue size change

            for(int i=0; i<levelSize; i++){
                Node temp = q.poll();
                System.out.print(temp.data + " ");

                //adding nodes for next level

                if(temp.left != null){
                    q.offer(temp.left);
                }

                if(temp.right != null){
                    q.offer(temp.right);
                }
            }
            System.out.println();
        }
    }

}
