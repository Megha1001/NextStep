package tree.countNodesInCompleteBT;

/*
 * COMPLETE BINARY TREE : A tree in which all levels are filled except last and last level are filled from left to right
 * 
 * This is naive solution not use the fact that its Complete BT.
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(H)
 */

class NaiveSolution{


    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        Node root=new Node(10);
        root.left=new Node(20);
        root.right=new Node(30);
        root.right.left=new Node(40);
        root.right.right=new Node(50);

        System.out.println("Number of nodes in complete binary tree is : "+countNodes(root));
    }

    public static int countNodes(Node root){
        if(root == null){
            return 0;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

}