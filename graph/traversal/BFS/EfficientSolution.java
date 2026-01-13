package graphs.traversal.BFS;

import java.util.ArrayList;
import java.util.ArrayDeque;

/*
 * IDEA : Level order traversal but by maintaining a boolean visited array . Since in tree we dont have cycle but in graph its connected and there can be cycle
 * we dont want to visit same element twice
 */

public class EfficientSolution {
    
     public static void main(String args[]){
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(V);
        for(int i=0; i<V; i++){
            adj.add(new ArrayList<>());
        }

        addAnEdge(adj, 0,1);
        addAnEdge(adj, 0,2);
        addAnEdge(adj, 2,3);
        addAnEdge(adj, 2,4);


        int start = 0;
        bfsTraversal(adj, V, start);

    }

    public static void bfsTraversal(ArrayList<ArrayList<Integer>> adj, int v, int s){
        //visited signifies that element is entered in the queue
        boolean [] visited = new boolean[v];

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(s);
        visited[s] = true;

        while(!q.isEmpty()){
            int p = q.poll();
            System.out.print(p + " ");

            for(int i : adj.get(p)){
                if(!visited[i]){
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }
        

    }

    public static void addAnEdge(ArrayList<ArrayList<Integer>> adj, int u, int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

}
