package BST.isGivenPairExists;

/*
 * DO inorder traversal and use two pointer approach to find sum
 * 
 * TIME COMPLEXITY : Theta(N)
 * AUXILIARY SPACE : Theta(N)
 */

import java.util.ArrayList;

public class NaiveSolution {

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

        System.out.println("is the given sum exists in tree ? "+isPairSum(root, sum));
    }

    public static boolean isPairSum(Node root, int sum){

        ArrayList<Integer> list = new ArrayList<>();
        inOrderTraversal(root, list);

        for(int i :list){
            System.out.print(i + " ");
        }
        return isSumExists(list, sum);
    }

    public static void inOrderTraversal(Node root, ArrayList<Integer> list){
        if(root != null){
            //LNR
            inOrderTraversal(root.left, list);
            list.add(root.data);
            inOrderTraversal(root.right, list);
        }
    }

    public static boolean isSumExists(ArrayList<Integer> list, int sum){
        int i=0;
        int j=list.size()-1;

        while(i < j){
            int x = list.get(i) + list.get(j);
            if( x== sum){
                return true;
            }
            else if(x > sum){
                --j;
            }
            else{
                ++i;
            }
        }

        return false;
    }
    
}
