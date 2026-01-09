package BST.bottomViewOfBinaryTree;

import java.util.ArrayDeque;
import java.util.TreeMap;

public class PrintBottomView {

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

    public static void main(String arg[]){
        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        root.left.left = new Node(40);
        root.left.right = new Node(50);

        System.out.println("Bottom view of tree is : ");
        printBottomView(root);
    }

    public static void printBottomView(Node root){
        if(root == null){
            return;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(root, 0));

        while(!q.isEmpty()){
            Pair p = q.poll();

            //always put
            map.put(p.hd, p.node.data);

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
