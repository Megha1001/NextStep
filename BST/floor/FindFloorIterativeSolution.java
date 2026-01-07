package BST.floor;

/*
 * TIME COMPLEXITY : O(H)
 * AUXILIARY SPACE : O(H)
 */
public class FindFloorIterativeSolution {


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

        Node res = findFloor(root, k);
        System.out.println("Floor of given value is : "+res==null? null : res.data);
    }

    public static Node findFloor(Node root, int k){
        Node res = null;
        while(root != null){
            if(root.data == k){
                return root;
            }else if (root.data > k){
                root = root.left;
            }else{
                res = root;
                root = root.right;
            }
        }
        
        return res;
    }

    
}
