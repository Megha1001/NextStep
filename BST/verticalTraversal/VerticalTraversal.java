package BST.verticalTraversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;

public class VerticalTraversal {

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }

    static class Pair{
        Node node;
        int hd;

        Pair(Node node, int hd){
            this.node = node;
            this.hd = hd;
        }
    }

    public static void main(String args[]){
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.left = new Node(8);
        root.right.right.left.left = new Node(9);

        System.out.println("Vertical traversal of given tree is : ");
        List<List<Integer>> result = verticalTraversal(root);
        for(List<Integer> list : result){
            for(int listValues : list){
                System.out.print(listValues + " ");
            }
            System.out.println();
        }

    }

    public static List<List<Integer>> verticalTraversal(Node root){
        List<List<Integer>> result = new ArrayList<>();
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();

        ArrayDeque<Pair>q  =  new ArrayDeque<>();
        q.offer(new Pair(root, 0));

        while(!q.isEmpty()){
            Pair p = q.poll();

            map.putIfAbsent(p.hd, new ArrayList<>());
            map.get(p.hd).add(p.node.data);

            if(p.node.left != null){
                q.offer(new Pair(p.node.left, p.hd-1));
            }

            if(p.node.right != null){
                q.offer(new Pair(p.node.right, p.hd+1));
            }

        }

        for(List<Integer> val : map.values()){
            result.add(val);
        }

        return result;
        
    }
    
}
