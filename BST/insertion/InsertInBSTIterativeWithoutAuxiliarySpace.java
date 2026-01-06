package BST.insertion;

/*
 * TIME COMPLEXITY : O(H)
 * AUXILIARY SPACE : O(1)
 */

public class InsertInBSTIterativeWithoutAuxiliarySpace {

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
        root.left = new Node(5);
        root.right = new Node(15);
        root.right.left = new Node(12);
        root.right.right = new Node(18);

        int x = 19;
        System.out.println("Root of BST after inserting given value is : "+insert(root, x).data);
    }

    public static Node insert(Node root, int x){

        Node newNode = new Node(x);

        if(root == null){
            return newNode;
        }

        Node curr = root;
        Node pos = root;
        
        while(curr!=null){
            pos = curr;
            if(curr.data > x){
                curr = curr.left;
            }else{
                curr = curr.right;
            }
        }

        curr = pos;
        if(curr.data > x){
            curr.left = newNode;
        }else {
            curr.right = newNode;
        }

        return root;
    }
    
}
