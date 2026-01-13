package graph.traversal.DFS;

import java.util.ArrayList;

public class CountComponent {

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
        int count = dfs(adj, V, source);
        System.out.println("Component in an undirected graph is : "+count);

    }

    public static int dfs(ArrayList<ArrayList<Integer>>adj, int v, int s){
        int component = 0;
        boolean [] visited = new boolean[v];
        
        for(int i=0; i<v; i++){
            if(!visited[i]){
                ++component;
                dfsTraversal(adj, i, visited);
            }
        }
        return component;
        
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
