package graph.traversal.DFS;

import java.util.ArrayList;

public class DisconnectedGraph {

    public static void main(String args[]){
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i=0; i<V; i++){
            adj.add(new ArrayList<>());
        }

        addAnEdge(adj, 0, 1);
        addAnEdge(adj, 0, 2);
        addAnEdge(adj, 1, 2);
     
        addAnEdge(adj, 3,4);

        int source = 0;
        dfs(adj, V, source);

    }

    public static void dfs(ArrayList<ArrayList<Integer>>adj, int v, int s){

        boolean [] visited = new boolean[v];
        
        for(int i=0; i<v; i++){
            if(!visited[i]){
                dfsTraversal(adj, i, visited);
            }
        }
        
    }

    public static void dfsTraversal(ArrayList<ArrayList<Integer>> adj, int s, boolean[] visited){
        visited[s] = true;
        System.out.print(s + " ");

        for(int u : adj.get(s)){
            if(!visited[u]){
                dfsTraversal(adj, u, visited);
            }
        }

    }

    public static void addAnEdge(ArrayList<ArrayList<Integer>>adj, int u, int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    
}
