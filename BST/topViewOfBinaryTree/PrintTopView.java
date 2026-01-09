package BST.topViewOfBinaryTree;

import java.util.ArrayDeque;
import java.util.TreeMap;


public class PrintTopView {

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
        root.right = new Node(3);
        root.left.right = new Node(4);
        root.left.right.right = new Node(5);

        System.out.println("Top view of binary tree : ");
        printTopView(root);
    }

    public static void printTopView(Node root){
        if(root == null){
            return;
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(root, 0));

        while(!q.isEmpty()){
            Pair p = q.poll();

            //add first time only
            if(map.get(p.hd)==null){
                map.put(p.hd, p.node.data);
            }

            if(p.node.left != null){
                q.offer(new Pair(p.node.left, p.hd-1));
            }

            if(p.node.right!=null){
                q.offer(new Pair(p.node.right, p.hd+1));
            }
        }

        for(int val : map.values()){
            System.out.print(val + " ");
        }
    }
    
    
}
