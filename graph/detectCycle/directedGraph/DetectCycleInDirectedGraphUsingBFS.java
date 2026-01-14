package graph.detectCycle.directedGraph;


/*
 * IDEA : To use Kah's algorithm
 * 1. Create an indegree array
 * 2. Create a queue
 * 3. Add all vertices with indegree 0;
 * --> CHANGE : count ==0
 * 4. Until q is not empty
 *  -> u = q.poll()
 *  -> for every adjacent v to u
 *      -> --indegree[v];
 *      --> if (indegree[v]==0) offer/add in queue;
 * 5. CHANGE --> return count!=V;
 */

import java.util.ArrayList;
import java.util.ArrayDeque;

public class DetectCycleInDirectedGraphUsingBFS {

    public static void main(String args[]){
        int V  = 5;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(V);

        for(int i=0; i<V; i++){
            adj.add(new ArrayList<>());
        }

        addAnEdge(adj, 0, 1);
        addAnEdge(adj, 1, 2);
        addAnEdge(adj, 2, 3);
        addAnEdge(adj, 3, 1);
        addAnEdge(adj, 4, 1);

        System.out.println("Is cycle exists in given graph ? "+isCycleExist(adj, V));

    }

    public static boolean isCycleExist(ArrayList<ArrayList<Integer>>adj, int v){
        //calculate indegree
        int indegree[] = new int[v];
        calculateIndegree(adj, indegree);

        ArrayDeque<Integer> q = new ArrayDeque<>();

        for(int i=0; i<v; i++){
            if(indegree[i]==0){
                q.offer(i);
            }
        }

        int count=0;

        while(!q.isEmpty()){
            int p = q.poll();

            for(int u : adj.get(p)){
                --indegree[u];
                if(indegree[u]==0){
                    q.offer(u);
                }
            }

            ++count;
        }

        return count != v;

    }

    public static void calculateIndegree(ArrayList<ArrayList<Integer>>adj, int [] indegree){
        for(int i=0; i<adj.size(); i++){
            for(int j=0; j<adj.get(i).size(); j++){
                ++indegree[adj.get(i).get(j)];
            }
        }
    }

    public static void addAnEdge(ArrayList<ArrayList<Integer>>adj, int u, int v){
        adj.get(u).add(v);
    }
    
}
