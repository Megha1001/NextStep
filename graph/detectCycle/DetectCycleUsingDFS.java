package graph.detectCycle;

/*
 * IDEA : DO DFS for every node as source, and if we found the source again while traversing will say it has cycle
 */

import java.util.ArrayList;

public class DetectCycleUsingDFS {

    public static void main(String args[]){
        int V = 4;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i=0; i<V; i++){
            adj.add(new ArrayList<>());
        }

        addAnEdge(adj, 0, 1);
        addAnEdge(adj, 1, 2);
        addAnEdge(adj, 1, 3);
        addAnEdge(adj, 2, 3);

        System.out.println("is the given graph contains cycle ? "+isCyclePresent(adj, V));
    }

    public static boolean isCyclePresent(ArrayList<ArrayList<Integer>>adj, int v){

    
    }




    public static void addAnEdge(ArrayList<ArrayList<Integer>>adj, int u, int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    
}
