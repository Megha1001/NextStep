package graphs.representations;

import java.util.ArrayList;

public class AdjacencyList {

    public static void main(String args[]){
        int V = 4;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(V);

        for(int i=0; i<V; i++){
            adj.add(i, new ArrayList<Integer>());
        }

        addAnEdge(adj, 0, 1);
        addAnEdge(adj, 0, 2);
        addAnEdge(adj, 1, 2);
        addAnEdge(adj, 3, 1);

        printGraph(adj);
    }
    
    public static void addAnEdge(ArrayList<ArrayList<Integer>>adj, int u, int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public static void printGraph(ArrayList<ArrayList<Integer>>adj){
        for(int i=0; i<adj.size(); i++){
            for(int j=0; j<adj.get(i).size(); j++){
                System.out.print(adj.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

}
