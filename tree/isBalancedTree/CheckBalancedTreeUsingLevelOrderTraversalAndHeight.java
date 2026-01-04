package tree.isBalancedTree;

/*
 * IDEA : For each node find height of level subtree and right subtree. If Math.abs(heigh of LST - height of RST) >0 return false;
 * For tree traversal use Level order traversal
 * 
 * TIME COMPLEXITY : O(N*N). For every node we are calling height function
 * AUXILIARY SPACE : O(W) where w is width of tree
 */

import java.util.ArrayDeque;

public class CheckBalancedTreeUsingLevelOrderTraversalAndHeight {

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        // Node root = new Node(18);
        // root.left = new Node(4);
        // root.right = new Node(20);
        // root.right.left = new Node(13);
        // root.right.right = new Node(70);

        Node root = new Node(18);
        root.left = new Node(4);
        root.left.left = new Node(20);

        System.out.println("is the given tree is balanced : "+isBalanced(root));
    }

    public static boolean isBalanced(Node root){

        if(root == null){
            return true;
        }

        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(root);

        while(!q.isEmpty()){
            Node temp = q.poll();
            int height_lst = 0;
            int height_rst = 0;

            if(temp.left!=null){
                q.offer(temp.left);
                height_lst = getHeight(temp.left);
            }

            if(temp.right!=null){
                q.offer(temp.right);
                height_rst = getHeight(temp.right);
            }

            if(Math.abs(height_lst - height_rst) > 1){
                return false;
            }

        }

        return true;
    }

    public static int getHeight(Node root){
        if(root == null){
            return 0;
        }

        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
    
}
