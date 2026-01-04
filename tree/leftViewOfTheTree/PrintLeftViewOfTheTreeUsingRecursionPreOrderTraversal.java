package tree.leftViewOfTheTree;

/*
 * IDEA : Use prerorder traversal to print left view. Maintain two variable level = 1(initialize), maxlevel=0, 
 * for every level in preorder traversal we reach the left most element first and since level > maxLevel for every recursive call we print that first then do maxLevel=level and then
 * recursively call for next level with level+1 value
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(H), where H is height
 */


public class PrintLeftViewOfTheTreeUsingRecursionPreOrderTraversal {

    public static int maxLevel = 0;

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
        root.right = new Node(30);
        root.left.left = new Node(40);
        root.left.right = new Node(50);
        root.right.right = new Node(60);

        // Node root = new Node(30);
        // root.right = new Node(50);
        // root.right.left = new Node(60);
        // root.right.left.right = new Node(10);

        System.out.println("Left view of the tree is : ");
        printLeftView(root, 1);
    }

    public static void printLeftView(Node root, int level){
        if(root==null){
            return;
        }

        //to print only left most element on every level
        if(level > maxLevel){
            System.out.print(root.data + " ");
            maxLevel = level; //so it wont print other elements on same level
        }

        printLeftView(root.left, level+1);
        printLeftView(root.right, level+1);
        
        
    }
    
}
