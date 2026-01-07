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

    static class Node{ //Augmented Node
        int data;
        Node left;
        Node right;
        int lCount; // new property

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        int keys[] = { 20, 8, 22, 4, 12, 10, 14 };

        Node root = null;
        for(int i : keys){
            root = insert(root, i);
        }

        int k = 4;
        Node kthSmallest = findKthSmallest(root, k);
        System.out.println("The smallest kth element is "+kthSmallest==null?null:kthSmallest.data);
     
    }
    

    public static Node insert(Node root, int x){
        if(root == null){
            return new Node(x);
        }
        else if (root.data > x){
            //left
            root.left = insert(root.left, x);
            root.lCount++; //Indicates that in left of root we are inserting
        }else {
            //right
            root.right = insert(root.right, x); //no need to have lCount changes as we are inserting in right
        }

        return root;
    }

    public static Node findKthSmallest(Node root, int k){ //root of BST
        if(root == null){
            return null;
        }

        if(root.lCount+1 == k){
            return root;
        }
        else if(root.lCount + 1 > k){
            //left
            return findKthSmallest(root.left, k); //no change in k
        }

        //for right change k
        return findKthSmallest(root.right, k-root.lCount-1);// why -1 since lCount indicates number of nodes in left not include that node. we are neglecting that too.

    }

}
