package tree.diameter;

/*
 * Diameter : Node + height of LST + height of RST
 * TIME COMPLEXITY : O(N*N)
 * AUXILIARY SPACE : O(H), where H is height
 */

public class FindDiameterUsingRecursion {

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
        // root.right = new Node(60);

        // root.left.left = new Node(30);
        // root.left.right = new Node(80);
        // root.left.left.left = new Node(40);
        // root.left.left.right = new Node(50);
        // root.left.left.left.left = new Node(60);
        // root.left.right.right = new Node(90);
        // root.left.right.right.right = new Node(18);

        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        root.right.left = new Node(40);
        root.right.left.left = new Node(60);
        root.right.right = new Node(50);


        System.out.println("Diameter of tree is : "+findDiameter(root));
    }

    public static int findDiameter(Node root){
        if(root == null){
            return 0;
        }

        return Math.max(Math.max(
            (1+ height(root.left) + height(root.right)),
            findDiameter(root.left)
        ),
        findDiameter(root.right));
    }

    public static int height(Node root){
        if(root == null){
            return 0;
        }

        return 1 + Math.max(height(root.left), height(root.right));
    }
    
}
