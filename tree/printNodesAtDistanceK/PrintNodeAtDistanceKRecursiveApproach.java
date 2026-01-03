package tree.printNodesAtDistanceK;

/*
 * TIME COMPLEXITY : O(N), when complete/full Binary treee
 * AUXILIARY SPACE : O(H)
 */

public class PrintNodeAtDistanceKRecursiveApproach {

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
        // root.right.right = new Node(70);
        // int k = 2;

        // Node root = new Node(10);
        // root.left = new Node(20);
        // root.left.left = new Node(30);
        // int k = 1;

        Node root = new Node(10);
        root.left = new Node(6);
        root.right = new Node(8);
        root.right.right = new Node(7);
        root.right.right.left = new Node(11);
        root.right.right.right = new Node(12);
        int k = 3;
    
        System.out.print("Nodes at distance K are  ");
        printNodesAtKDistance(root, k);
    }

    public static void printNodesAtKDistance(Node root, int k){

        if(root == null){
            return;
        }

        if(k==0){
            System.out.print(root.data + " ");
            return;
        }

        printNodesAtKDistance(root.left, k-1);
        printNodesAtKDistance(root.right, k-1);

    }
    
}
