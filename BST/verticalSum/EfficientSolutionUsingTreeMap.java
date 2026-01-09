package BST.verticalSum;

/*
 * TREEMAP stores the values in sorted order. its self balancing BST
 * 
 * map(vale, sum of nodes)
 * val : val of node. For ex : root : 0, then left child is root-1 =-1 , right child is root+1 : 0+1 = 1
 * 
 * 1. Do in Order traversal/any traversal to populate the map
 * 2. Print the map
 */

import java.util.TreeMap;
import java.util.Map;

public class EfficientSolutionUsingTreeMap {

    
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
        root.left = new Node(15);
        root.right = new Node(25);
        root.left.left = new Node(35);
        root.left.right = new Node(20);
        root.left.left.left = new Node(40);
        root.left.right.right = new Node(75);
        root.left.right.right.right = new Node(80);

        System.out.println("Vertical Sum : ");
        TreeMap<Integer,Integer> map = new TreeMap<>();
        verticalSum(root, map, 0);
        //print
        for(Map.Entry<Integer,Integer> sum : map.entrySet()){
            System.out.print(sum.getValue() + " ");
        }
    }

    public static void verticalSum(Node root, TreeMap<Integer, Integer> map, int hd){
        if(root == null){
            return;
        }

        verticalSum(root.left, map, hd-1);
        
        int pSum = map.get(hd)==null ? 0:map.get(hd);
        map.put(hd, pSum+root.data);

        verticalSum(root.right, map, hd+1);

    }
    
}
