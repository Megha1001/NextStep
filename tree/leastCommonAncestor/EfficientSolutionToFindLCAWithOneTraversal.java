package tree.leastCommonAncestor;

/*
 *  ASSUMPTION : Both N1 and N2 are present in the tree
 *  IDEA : We do normal recursive traversal. We have following cases for every node.
 *  -> If its same as n1 or n2 then return root
 *  -> if one of its subtree contains n1 and other contains n2 then return root
 *  -> if one of its subtree contains both n1 and n2 -> then return whatever the subtree that contains n1 and n2 return
 *  -> if none of its subtree contains n1 and n2 return null
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(H), where H is height
 */

public class EfficientSolutionToFindLCAWithOneTraversal {
    
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
        root.right.left = new Node(40);
        root.right.right = new Node(50);

        int n1= 40, n2 = 50;

        Node result = findLCA(root, n1, n2);

        System.out.println("The least common ancestor of the given nodes is : "+result==null? -1 : result.data);
    }


    public static Node findLCA(Node root, int n1, int n2){

        if(root==null){
            return null;
        }

        // -> If its same as n1 or n2 then return root
        if(root.data == n1 || root.data == n2){
            return root;
        }

        Node lca_left = findLCA(root.left, n1, n2);
        Node lca_right = findLCA(root.right, n1, n2);

        //-> if one of its subtree contains n1 and other contains n2 then return root
        if(lca_left != null && lca_right != null){
            return root;
        }

        //if one of its subtree contains both n1 and n2 -> then return whatever the subtree that contains n1 and n2 return
        if(lca_left!=null){ //lca_right == null
            return lca_left;
        }else{
            return lca_right;
        }

    }

}
