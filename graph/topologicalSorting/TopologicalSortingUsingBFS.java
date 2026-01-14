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
 */

import java.util.ArrayList;

public class TopologicalSortingUsingBFS {

    public static void main(String args[]){
        int V=5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(V);

        for(int i=0; i<V; i++){
            adj.add(new ArrayList<>());
        }
    }


    
}
