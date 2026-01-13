package graph.traversal.DFS;

import java.util.ArrayList;

public class NaiveSolution {

    public static void main(String args[]){
        // int V = 7;
        int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(V);

        for(int i=0; i<V; i++){
            adj.add(new ArrayList<>());
        }

        // addAnEdge(adj, 0,1);
        // addAnEdge(adj, 0,4);
        // addAnEdge(adj, 1,2);
        // addAnEdge(adj, 2,3);

        // addAnEdge(adj, 4,5);
        // addAnEdge(adj, 4,5);
        // addAnEdge(adj, 5,6);

        addAnEdge(adj, 0,1);
        addAnEdge(adj, 0,2);
        addAnEdge(adj, 2,3);
        addAnEdge(adj, 1,3);
        addAnEdge(adj, 1,4);
        addAnEdge(adj, 4,5);

        int source = 0;
        boolean visited[] = new boolean[V];
        dfs(adj, source, visited);
    }

    public static void dfs(ArrayList<ArrayList<Integer>> adj, int s, boolean [] visited){
        if(adj.get(s) == null || visited[s]){
            return;
        }

        visited[s] = true;
        System.out.print(s + " ");
        
        //for every adjacency row
        int i=0;
        while(i!= adj.get(s).size()){
            dfs(adj, adj.get(s).get(i), visited);
            ++i;
        }

    }

    public static void addAnEdge(ArrayList<ArrayList<Integer>>adj, int u, int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    
}
