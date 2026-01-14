package graph.detectCycle;


import java.util.ArrayList;

public class DetectCycleInDirectedGraph {

    public static void main(String args[]){
        int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i=0; i<V; i++){
            adj.add(new ArrayList<>());
        }

        // addAnEdge(adj, 0,1);
        // addAnEdge(adj, 2,1);
        // addAnEdge(adj, 2,3);
        // addAnEdge(adj, 3,4);
        // addAnEdge(adj, 4,5);
        // addAnEdge(adj, 5,3);

        addAnEdge(adj, 0,1);
        addAnEdge(adj, 2,1);
        addAnEdge(adj, 2,3);
        addAnEdge(adj, 3,4);
        addAnEdge(adj, 4,5);
        // addAnEdge(adj, 5,3);

        System.out.println("is Cycle exists in the given directed graph ? : "+detectCycle(adj, V));
    }

    public static boolean detectCycle(ArrayList<ArrayList<Integer>>adj, int v){
        boolean []visited = new boolean[v];
        boolean []recCallStack = new boolean[v];

        for(int i=0; i<v; i++){
            if(!visited[i]){
                if(isCycleExist(adj, i, visited, recCallStack)){
                    return true;
                }
            }
        }

        return false;

    }

    public static boolean isCycleExist(ArrayList<ArrayList<Integer>>adj, int s, boolean[] visited, boolean [] recCallStack){
        visited[s] = true;
        recCallStack[s] = true;

        for(int u : adj.get(s)){
            if(!visited[u] && isCycleExist(adj, u, visited, recCallStack)){
                return true;
            }else if (recCallStack[u] == true){
                return true;
            }
        }
        recCallStack[s]=false; //IMP!!!
        return false;
    }

    public static void addAnEdge(ArrayList<ArrayList<Integer>>adj, int u, int v){
        adj.get(u).add(v);
    }
    
}
