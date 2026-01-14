package graph.detectCycle.undirectedGraph;

/*
 * IDEA : Maintain a parent and check if already visited vertex is parent or not
 * 
 * TIME COMPLEXITY : O(V+E)
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
        boolean [] visited = new boolean[v];
        for(int i=0; i<v; i++){
            if(!visited[i]){
                if(isCycle(adj,i,-1,visited)){
                    return true;
                }
            }
        }
        return false;
    
    }

    public static boolean isCycle(ArrayList<ArrayList<Integer>>adj, int curr, int parent, boolean[]visited){

        visited[curr] = true;

        for(int neighbour : adj.get(curr)){
            if(!visited[neighbour]){
                if(isCycle(adj, neighbour, curr, visited)){
                    return true;
                }
            }else if (neighbour!= parent){
                return true;
            }
        }

        return false;

    }




    public static void addAnEdge(ArrayList<ArrayList<Integer>>adj, int u, int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    
}
