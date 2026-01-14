package graph.topologicalSorting;


/*
 * IDEA : Do DFS Traversal and after every vertex get visility push that in stack
 * if u-> v then u should come before v
 * 
 * Idea is to push all the adjacent first then push the curr node. so curr node will pop first. A vertex will go into the stack once all 
 * of its dependent are pushed
 */

import java.util.ArrayList;
import java.util.ArrayDeque;

public class TopologicalSortingUsingDFS {

    public static void main(String args[]){

        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(V);

        for(int i=0; i<V;i++){
            adj.add(new ArrayList<>());
        }

        addAnEdge(adj, 0, 1);
        addAnEdge(adj, 1, 3);
        addAnEdge(adj, 2, 3);
        addAnEdge(adj, 2, 4);
        addAnEdge(adj, 3, 4);

        System.out.println("Topological sorting using DFS algorithm is : ");
        dfs(adj, V);

    }

    public static void dfs(ArrayList<ArrayList<Integer>> adj, int v){
        boolean [] visited = new boolean[v];
        ArrayDeque<Integer> d = new ArrayDeque<>();
        for(int i=0; i<v; i++){
            if(!visited[i]){
                dfs(adj, visited, i, d);
            }
        }

        while(!d.isEmpty()){
            System.out.print(d.pop() + " ");
        }

    }

    public static void dfs(ArrayList<ArrayList<Integer>>adj, boolean [] visited, int s, ArrayDeque<Integer> d){
        visited[s] = true;

        for(int u : adj.get(s)){
            if(!visited[u]){
                dfs(adj, visited, u, d);
            }     
        }

        d.push(s);

    }

    public static void addAnEdge(ArrayList<ArrayList<Integer>> adj, int u, int v){
        adj.get(u).add(v);
    }
    
}
