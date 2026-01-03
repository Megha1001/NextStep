package tree.traversal;

public class PreOrderTraversal {

    public static int idx = -1;

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }


    public static void main(String args[]){
        int preOrder[] = {1,2,4,-1,-1,-1,3,-1,5,-1,-1}; //NLR
        System.out.print("Input array is : ");

        for(int i : preOrder){
            System.out.print(i + " ");
        }

        System.out.println();
        System.out.print("Output array is : ");

        Node root  = buildTree(preOrder);
        preOrderTraversal(root);
    }

    //TIME COMPLEXITY : O(N)
    public static Node buildTree(int []preOrder){
        ++idx;

        if(preOrder[idx]==-1){
            return null;
        }


        Node root = new Node(preOrder[idx]);
        root.left = buildTree(preOrder);
        root.right = buildTree(preOrder);

        return root;
    }

    //TIME COMPLEXITY : O(N)
    public static void preOrderTraversal(Node root){

        if(root == null){
            System.out.print(-1 + " ");
            return;
        }

        //NLR
        System.out.print(root.data + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);

    }
    
}
