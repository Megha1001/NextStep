package tree.leastCommonAncestor;

import java.util.ArrayList;

public class FindLeastCommonAncestorUsingPath {
    

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

        System.out.println("The least common ancestor of the given nodes is : "+findLCA(root, n1, n2));
    }

    public static int findLCA(Node root, int n1, int n2){

        if(root == null){
            return -1;
        }

        ArrayList<Integer> path1 = new ArrayList<>();
        ArrayList<Integer> path2 = new ArrayList<>();

        if(!findPath(root, path1, n1) || !findPath(root, path2, n2)){
            return -1;
        }

        for(int i=0; i<path1.size()-1 && i<path2.size()-1; i++){
            if(path1.get(i+1)!=path2.get(i+1)){
                return path1.get(i);
            }
        }

        return -1;


    }

    public static boolean findPath(Node root, ArrayList<Integer> path, int n){

        if(root == null){
            return false;
        }

        path.add(root.data);
        if(root.data == n){
            return true;
        }

        if(findPath(root.left, path, n) || findPath(root.right, path, n)){
            return true;
        }

        // path.remove((Integer)(root.data)); //need to parse in Integer otherwise it will return NPE since it will try to remove that index
        path.remove(path.size()-1);//remove last element

        return false;
    }



}
