package tree.countNodesInCompleteBT;

public class EfficientSolution {
    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        Node root=new Node(10);
        root.left=new Node(20);
        root.right=new Node(30);
        root.right.left=new Node(40);
        root.right.right=new Node(50);

        System.out.println("Number of nodes in complete binary tree is : "+countNodes(root));
    }


    public static double countNodes(Node root){
        if(root == null){
            return 0;
        }

        int lh = 0, rh = 0;
        Node curr = root;
        while(curr!=null){
            ++lh;
            curr = curr.left;
        }

        curr = root;
        while(curr!=null){
            ++rh;
            curr = curr.right;
        }

        if(lh == rh){
            return Math.pow(2, lh) - 1;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);


    }


}
