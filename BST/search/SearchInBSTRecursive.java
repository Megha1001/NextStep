package BST.search;

public class SearchInBSTRecursive {

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

        int k = 10;
        System.out.println("is the given element present in BST ? "+isGivenNodePresent(root, k));
    }

    public static boolean isGivenNodePresent(TreeNode root, int k){
        if(root == null){
            return false;
        }

        if(root.key == k){
            return true;
        }

        if(root.key > k){
            //left
            return isGivenNodePresent(root.left, k);
        }else{
            //right
            return isGivenNodePresent(root.right, k);
        }
    }
    
}
