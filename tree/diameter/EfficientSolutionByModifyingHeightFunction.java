package tree.diameter;

/*
 * IDEA : Since While computing height we find height of LST and height of RST so keep track of maximum
 * The function will return height but set the diameter
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(N)
 */

public class EfficientSolutionByModifyingHeightFunction {
    
    public static int diameter = 0;

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
        root.right = new Node(60);

        root.left.left = new Node(30);
        root.left.right = new Node(80);
        root.left.left.left = new Node(40);
        root.left.left.right = new Node(50);
        root.left.left.left.left = new Node(60);
        root.left.right.right = new Node(90);
        root.left.right.right.right = new Node(18);

        // Node root = new Node(10);
        // root.left = new Node(20);
        // root.right = new Node(30);
        // root.right.left = new Node(40);
        // root.right.left.left = new Node(60);
        // root.right.right = new Node(50);
        findDiameter(root);
        System.out.println("Diameter of tree is : "+diameter);
    }

    public static int findDiameter(Node root){

        if(root==null){
            return 0;
        }

        int height_lst = findDiameter(root.left);
        int height_rst = findDiameter(root.right);
        
        diameter = Math.max(diameter, height_lst+height_rst+1);
        return 1 + Math.max(height_lst, height_rst);

    }
}
