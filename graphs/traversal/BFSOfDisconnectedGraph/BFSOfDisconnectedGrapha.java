package graphs.traversal.BFSOfDisconnectedGraph;

import java.util.ArrayList;
import java.util.ArrayDeque;

/*
 * TIME COMPLEXITY : O(V+E)
 * 
 * E : We are traversing all the adjacency list which is 2E
 * V : when all the nodes are disconnected - corner case
 */

public class BFSOfDisconnectedGrapha {
    
    public static void main(String args[]){

        int V = 7;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(V);

        for(int i=0; i<V; i++){
            adj.add(new ArrayList<>());
        }

        addAnEdge(adj,0,1);
        addAnEdge(adj,0,2);
        addAnEdge(adj,1,3);
        addAnEdge(adj,2,3);

        addAnEdge(adj,4,5);
        addAnEdge(adj,4,6);
        addAnEdge(adj,5,6);

        System.out.println("BFS of given disconnected graph is : ");
        BFS(adj, V);
        
    }

    public static void addAnEdge(ArrayList<ArrayList<Integer>>adj, int u, int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public static void BFS(ArrayList<ArrayList<Integer>>adj, int v){
        boolean visited[] = new boolean[v];

        for(int i=0; i<v; i++){
            if(!visited[i]){
                BFSConnectedComp(adj, i, visited);
            }
        }
    }

    public static void BFSConnectedComp(ArrayList<ArrayList<Integer>>adj, int start, boolean [] visited){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()){
            int p = q.poll();
            System.out.print(p + " ");

            for(int i: adj.get(p)){
                if(!visited[i]){
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }
    }

}
