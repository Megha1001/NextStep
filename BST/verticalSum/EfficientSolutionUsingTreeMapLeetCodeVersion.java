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


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EfficientSolutionUsingTreeMap1 {

    
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int data){
            this.val = data;
        }
    }

    public static void main(String args[]){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(15);
        root.right = new TreeNode(25);
        root.left.left = new TreeNode(35);
        root.left.right = new TreeNode(20);
        root.left.left.left = new TreeNode(40);
        root.left.right.right = new TreeNode(75);
        root.left.right.right.right = new TreeNode(80);

        List<List<Integer>> result = new ArrayList<>();
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        verticalHeight(root, map, 0);

        for(Map.Entry<Integer, List<Integer>> entry: map.entrySet()){
            result.add(entry.getValue());
        }

        for(List<Integer> i : result){
            System.out.print(i+" ");
        }
    }

   public static void verticalHeight(TreeNode root, TreeMap<Integer, List<Integer>>map, int hd){
        if(root == null){
            return;
        }

        verticalHeight(root.left, map, hd-1);
        List<Integer> previous = map.get(hd)== null ? new ArrayList<>() : map.get(hd);
        previous.add(root.val);
        map.put(hd,previous);
        verticalHeight(root.right, map, hd+1);

    }
    
}
