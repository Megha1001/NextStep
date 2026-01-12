package graphs.traversal.BFS;

import java.util.ArrayList;
import java.util.HashSet;

public class NaiveSolution {

    public static void main(String args[]){
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(V);
        for(int i=0; i<V; i++){
            adj.add(new ArrayList<>());
        }

        addAnEdge(adj, 0,1);
        addAnEdge(adj, 0,2);
        addAnEdge(adj, 2,3);
        addAnEdge(adj, 2,4);

        // printGraph(adj);

        int start = 0;
        bfsTraversal(adj, start);

    }

    public static void bfsTraversal(ArrayList<ArrayList<Integer>> adj, int start){
        HashSet<Integer> visited = new HashSet<>();
        //always print 1st
        System.out.print(start + " ");
        visited.add(start);
        for(int i=0; i<adj.get(0).size(); i++){
            int curr = adj.get(0).get(i);
            System.out.print(curr + " ");
            visited.add(curr);
        }

        for(int i=1; i<adj.size();i++){
            for(int j=0; j<adj.get(i).size(); j++){
                int curr = adj.get(i).get(j);
                if(visited.contains(curr)){
                    continue;
                }
                System.out.print(curr + " ");
                visited.add(curr);
            }
        }

    }

    public static void addAnEdge(ArrayList<ArrayList<Integer>> adj, int u, int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public static void printGraph(ArrayList<ArrayList<Integer>> adj){
        for(int i=0; i<adj.size(); i++){
            for(int j=0; j<adj.get(i).size(); j++){
                System.out.print(adj.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
    
}
