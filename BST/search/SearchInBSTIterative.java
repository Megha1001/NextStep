package BST.search;

/*
 * TIME COMPLEXITY : O(H)
 * AUXILIARY SPACE : O(1)
 */

public class SearchInBSTIterative {

    static class TreeNode{
        int key;
        TreeNode left;
        TreeNode right;

        TreeNode(int key){
            this.key = key;
        }
    }

    public static void main(String args[]){
        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(18);
        root.right.right = new TreeNode(80);
        root.right.left.left = new TreeNode(16);

        int k = 16;
        System.out.println("is the given element present in BST ? "+isGivenNodePresent(root, k));
    }

    public static boolean isGivenNodePresent(TreeNode root, int k){
        if(root==null){
            return false;
        }

        TreeNode curr = root;
        while(curr!=null){
            if(curr.key == k){
                return true;
            }
            else if (curr.key > k){
                //left
                curr = curr.left;
            }else{
                //right
                curr = curr.right;
            }
        }

        return false;
    }
    
}
