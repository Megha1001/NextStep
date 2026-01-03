package deque.firstCircularTour;

import java.util.ArrayDeque;

/*
 * IDEA : 
 * 1. Keep adding items to the end of the queue while curr_petrol >=0;
 * 2. Keep removing items from the front while curr_petrol <0
 */

public class EfficientSolutionWithAuxiliarySpace {


    public static void main(String args[]){
        int petrol[] = {4,8,7,4};
        int dist[] = {6,5,3,5};
        // int petrol[] = {4,8};
        // int dist[] = {5,6};

        // int petrol[] = {8,9,4};
        // int dist[] = {5,10,12};

        System.out.println("The possible petrol pump from where we start is : "+findPetrolPump(petrol, dist, petrol.length));
    }


    public static int findPetrolPump(int petrol[], int dist[], int n){

        ArrayDeque<Integer> d = new ArrayDeque<>();
        int curr_petrol = 0;
        int i=0;

        while(d.size() < n && i < 2*n){ //Why 2n , if last is the starting point we need to traverse in circular = n-1 + n = 2n-1. It should run till 2n
            int idx = i%n; //for ciruclar

            //Adding items
            d.offerLast(i);
            curr_petrol = curr_petrol + (petrol[idx] - dist[idx]);

            //remove if curr_petrol <0
            while(d.size() >0 && curr_petrol < 0){
                d.pollFirst();
            }

            ++i;

        }

        return d.size()==n ? d.peekFirst()+1 : -1;
    }
    
}
