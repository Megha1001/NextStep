package BST.kthSmallest;

/*
 * IDEA : Maintain an extra property in node -> lCount . Will increment when we insert leftNode
 * lCount : count of left nodes for curr nodes
 *  Compare lCount + 1 with k
 *      -> if equal return curr node
 *      -> if greater, go to left with same k
 *      -> if smaller, go to right and update k with k-lCount-1
 *     
 */

public class EfficientApproachUsingAugmentedBST {

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

     
    }
    

    public static Node insert(Node root, int x){
    
    }

    public static void findKthSmallest(Node root, int k){ //root of BST
       
    }

}
