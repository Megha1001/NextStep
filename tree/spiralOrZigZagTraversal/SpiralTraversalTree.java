package tree.spiralOrZigZagTraversal;

import java.util.ArrayDeque;

public class SpiralTraversalTree {

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.left = new Node(10);

        System.out.println("Spiral Traversal of given Binary tree is : ");
        spiralTraversal(root);
    }
    
    public static void spiralTraversal(Node root){
        if(root == null){
            return;
        }

        ArrayDeque<Node> q = new ArrayDeque<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        boolean reverse = false;

        q.offer(root);

        while(!q.isEmpty()){
            int levelSize = q.size();

            for(int i=0; i<levelSize; i++){
                Node curr = q.poll();

                if(reverse){
                    stack.push(curr.data);
                }else{
                    System.out.print(curr.data + " ");
                }

                if(curr.left != null){
                    q.offer(curr.left);
                }

                if(curr.right != null){
                    q.offer(curr.right);
                }

            }

            if(reverse){
                while(!stack.isEmpty()){
                    System.out.print(stack.poll() + " ");
                }
            }

            reverse = !reverse;
            System.out.println();

        }
    }


}
