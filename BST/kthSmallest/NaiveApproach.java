package BST.kthSmallest;

/*
 * IDEA : Construct BST then find Kth smallest using inorder traversal
 */

public class NaiveApproach {

    public static int count = 0;

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        int keys[] = { 20, 8, 22, 4, 12, 10, 14 };

        Node root = null;

        for(int i : keys){
            root = insert(root, i); //return BST
        }

        int k = 4;
        System.out.println("Kth smallest element is  : ");
        findKthSmallest(root, k);
     
    }
    

    public static Node insert(Node root, int x){
        if(root == null){
            return new Node(x);
        }
        else if(root.data > x){
            //left
            root.left = insert(root.left, x);
        }else {
            //right
            root.right = insert(root.right, x);
        }

        return root;
    }

    //inorder traversal
    public static void findKthSmallest(Node root, int k){ //root of BST
        if(root != null){
            findKthSmallest(root.left, k);
            ++count;
            if(count == k){
                System.out.print(root.data + " ");
                return;
            }
            findKthSmallest(root.right, k);
        }
    }

}
