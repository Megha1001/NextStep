package BST.verticalSum;

/*
 * TREEMAP stores the values in sorted order. its self balancing BST
 * 
 * Input
    root =
    [1,2,3,4,6,5,7]

    Expected
    [[4],[2],[1,5,6],[3],[7]]
    
    Note order of 1,5,6 
 * 
 * 1. DO LEVEL ORDER TRAVERSAL
 * 2. Print the map
 */

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ArrayDeque;

public class EfficientSolutionUsingTreeMapLeetCodeVersion {

    
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int data){
            this.val = data;
        }
    }

    static class Pair{
        TreeNode node;
        int hd;

        Pair(TreeNode node, int hd){
            this.node = node;
            this.hd = hd;
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

         List<List<Integer>> result = verticalHeight(root);
       
         for(List<Integer> list : result){
            System.out.print(list + " ");
         }
    }

    public static List<List<Integer>> verticalHeight(TreeNode root){

        List<List<Integer>> result =  new ArrayList<>();
    
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
    
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(root, 0));

        while(!q.isEmpty()){
            Pair curr = q.poll();

            map.putIfAbsent(curr.hd, new ArrayList<>());
            map.get(curr.hd).add(curr.node.val);

            if(curr.node.left != null){
                q.offer(new Pair(curr.node.left, curr.hd-1));
            }

            if(curr.node.right != null){
                q.offer(new Pair(curr.node.right, curr.hd+1));
            }

        }



        for(List<Integer> val : map.values()){
            result.add(val);
        }

        return result;
    }
    
}
