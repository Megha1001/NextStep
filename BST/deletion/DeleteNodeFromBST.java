package BST.deletion;

/*
 * IDEA : The node that needs to be deleted can be any one of follows
 *  -> Its leaf node -> delete it;
 *  -> It has one children -> make children as root of that subtree
 *  -> It has both the children -> make either predecessor or successor as root of that subtree
 * 
 * TIME COMPLEXITY : O(H)
 * AUXILIARY SPACE : O(H)
 */


public class DeleteNodeFromBST {

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
        root.right.right = new Node(18);


        int k = 10;
        System.out.println("Root of tree after deleting given element is : "+deleteNode(root, k).data);
    }

    public static Node deleteNode(Node root, int k){
        if(root == null){
            return null;
        }
        else if(root.data > k){
            root.left = deleteNode(root.left, k);
        } else if (root.data < k){
            root.right = deleteNode(root.right, k);
        }

        else { //root.data == k
            if(root.left == null){
                return root.right; //also handles when k is leaf
            }else if (root.right == null){
                return root.left;
            }else{
                //when both childs exists
                Node succ = getSuccessor(root);
                root.data = succ.data;
                root.right = deleteNode(root.right, succ.data);
            }
        }

        return root;

    }

    public static Node getSuccessor(Node root){
        Node curr = root.right; // go right then left left left ... 
        while(curr!=null && curr.left != null){
            curr = curr.left;
        }
        return curr;
    }
    
}
