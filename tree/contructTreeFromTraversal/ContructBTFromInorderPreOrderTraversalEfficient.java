package tree.contructTreeFromTraversal;
/*
 * IDEA : Use PreOrder traversal
 *  -> for every node of Preorder array make node
 *  -> search that node in inorder array -> inIndex
 *  -> left side of inIndex will be left subtree
 *  -> right side of inIndex will be right subtree
 * 
 * TIME COMPLEXITY : O(N)
 * For reducing time complexity to O(N)
 *  -> Make A HashMap<val, index> for inorder and pass to function and do lookup
 * 
 */

import java.util.HashMap;

public class ContructBTFromInorderPreOrderTraversalEfficient {

    public static int preIndex = 0; //need to maintain throughout recursion

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data  = data;
        }
    }

    public static void main(String args[]){
        int in[] = {20,10,40,30,50};
        int pre[] = {10,20,30,40,50};

        //pre-processing for inorder
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<in.length; i++){
            map.put(in[i], i);
        }

        Node root = buildTree(map, pre, 0, in.length-1);
        preOrderTraversal(root);
    }

    public static Node buildTree(HashMap<Integer, Integer>map, int [] pre, int is, int ie){
        
        if(is > ie){
            return null;
        }

        
        Node root = new Node(pre[preIndex++]);

        //search in inorder
        int inIndex= map.get(root.data);

        //call for left 
        root.left = buildTree(map, pre, is, inIndex-1);
        
        //call for right
        root.right = buildTree(map, pre, inIndex+1, ie);

        return root;

    }

    public static void preOrderTraversal(Node root){
        //NLR

        if(root == null){
            return;
        }

        System.out.print(root.data + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);

    }
    
}
