package graph.shortestPathInAnUnWeightedGraph;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Arrays;

/*
 * TIME COMPLEXITY : O(V+E), same as breadth first Search traversal
 */

public class FindShortedPathInAnUnWeightedGraphUsingBFS {

    public static void main(String args[]){
        int V = 4;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i=0; i<V; i++){
            adj.add(new ArrayList<>());
        }

        addAnEdge(adj, 0,1);
        addAnEdge(adj, 0,2);
        addAnEdge(adj, 1,2);
        addAnEdge(adj, 1,3);
        addAnEdge(adj, 2,3);

        int source = 0;
        shortestPath(adj, V, source);
    }

    public static void shortestPath(ArrayList<ArrayList<Integer>>adj, int v, int s){
        //intialize dist array
        int [] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean [] visited = new boolean[v];
        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.offer(s);
        visited[s] = true;
        dist[s] = 0;

        while(!q.isEmpty()){
            int u = q.poll();

            for(int i : adj.get(u)){
                if(!visited[i]){
                    visited[i] = true;
                    dist[i] = dist[u] + 1;
                    q.offer(i);
                }
            }
        }

        //print dist
        for(int i=0; i<v; i++){
            System.out.print(dist[i]+ " ");
        }

    }

    public static void addAnEdge(ArrayList<ArrayList<Integer>>adj, int u, int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    
}
