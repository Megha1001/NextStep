package BST.ceil;

public class FindCeilIterativeSolution {

    public static Node res = null;

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
        root.right.right = new Node(30);

        int k = 14;
        Node ceil = findCeil(root, k);
        System.out.println("Ceil of given key is : "+ceil==null ? null: ceil.data);
    }

    public static Node findCeil(Node root, int k){
        if(root == null){
            return null;
        }

        Node curr = root;
        while(curr != null){
            if(curr.data == k){
                return curr;
            }else if(curr.data > k){
                res = curr;
                curr = curr.left;
            }else{
                curr = curr.right;
            }
        }

        return res;
    }
    
}
