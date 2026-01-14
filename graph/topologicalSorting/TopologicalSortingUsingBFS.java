package graph.topologicalSorting;

/*
 * IDEA : 
 * Using BFS
 *  -> Find the indegrees of the the vertex -> inDegree[]
 *  -> Create a queue
 *  -> Add all the nodes with indree zero
 *  -> Until q is not empty
 *      -> u = q.poll
 *      -> for every adjacent v of u
 *          -> --inDegree[v];
 *          -> if indegree becomes 0 add into q
 * 
 * TIME COMPLEXITY : O(V+E)
 */

import java.util.ArrayList;
import java.util.ArrayDeque;

public class TopologicalSortingUsingBFS {

    public static void main(String args[]){
        int V=5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(V);

        for(int i=0; i<V; i++){
            adj.add(new ArrayList<>());
        }

        addAnEdge(adj,0,2);
        addAnEdge(adj,0,3);
        addAnEdge(adj,2,3);
        addAnEdge(adj,1,3);
        addAnEdge(adj,1,4);

        System.out.println("Topological sorting : ");
        topologicalSorting(adj, V);
    }

    public static void topologicalSorting(ArrayList<ArrayList<Integer>>adj, int v){
        int [] indegree = new int[v];
        calculateIndgree(adj, indegree);
        
        //put all vertex with indegree 0 in queue
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for(int i=0; i<v; i++){
            if(indegree[i]==0){
                q.offer(i);
            }
        }

        while(!q.isEmpty()){
            int p = q.poll();
            System.out.print(p + " ");

            for(int u : adj.get(p)){
                --indegree[u];
                if(indegree[u] == 0){
                    q.offer(u);
                }
            }
        }
    }

    public static void calculateIndgree(ArrayList<ArrayList<Integer>>adj, int [] indegree){
        //calculate indegree
        for(int i=0; i<adj.size(); i++){
            for(int j=0; j<adj.get(i).size(); j++){
                ++indegree[adj.get(i).get(j)];
            }
        }

        // for(int i: indegree){
        //     System.out.print(i + " ");
        // }
    }

    public static void addAnEdge(ArrayList<ArrayList<Integer>>adj, int u, int v){
        adj.get(u).add(v);
    }


    
}
