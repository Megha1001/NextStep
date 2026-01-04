package tree.widthOfTree;


/*
 * IDEA : Use Level order traversal and keep track of maximum levelsize
 */

import java.util.ArrayDeque;

public class MaximumWidthOfBinaryTree {
    
    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String argsp[]){
        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        root.left.left = new Node(40);
        root.left.left.left = new Node(80);
        root.right.left = new Node(50);
        root.right.right = new Node(60);

        System.out.println("Width of the tree is : "+calWidth(root));
    }

    public static int calWidth(Node root){
        if(root == null){
            return 0;
        }
        int res = 1;
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(root);

        while(!q.isEmpty()){
            int levelSize = q.size();
            res = Math.max(res, levelSize);

            for(int i=0; i<levelSize; i++){
                Node temp = q.poll();

                if(temp.left != null){
                    q.offer(temp.left);
                }

                if(temp.right != null){
                    q.offer(temp.right);
                }
            }
        }

        return res;
    }

}
