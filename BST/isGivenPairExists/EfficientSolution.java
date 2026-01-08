package BST.isGivenPairExists;

/* Use HashSet to store the keys and search for sum-present

TIME COMPLEXITY : Theat(N)
AUXILIARY SPACE : O(H), where H is height
 */

import java.util.HashSet;

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
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node (20);
        root.right.left = new Node(16);
        root.right.right = new Node(40);

        int sum  = 21;

        HashSet<Integer> s = new HashSet<>();

        System.out.println("is the given sum exists in tree ? "+isPairSum(root, sum, s));
    }

    public static boolean isPairSum(Node root, int sum, HashSet<Integer>s){
        if(root == null){
            return false;
        }

        //Left
        if(isPairSum(root.left, sum, s)){
            return true;
        }

        //Node
        if(s.contains(sum - root.data)){
            return true;
        }else{
            s.add(root.data);
        }

        //Right
        return isPairSum(root.right, sum, s);

    
    }
    
}
