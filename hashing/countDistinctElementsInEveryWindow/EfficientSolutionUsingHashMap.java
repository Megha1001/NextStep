package hashing.countDistinctElementsInEveryWindow;

/*
 * IDEA
 * 1. Create a frequency map of first k elements
 * 2. for(i=k ; i<n; i++)
 *      a. decrease the freq of a[i-k]
 *      b. if the freq of a[i-k] ==0, remove from map
 *      c. if arr[i] doesn't exist in the map add it else increase the frequency
 *      d. print the size of map
 *
 */

import java.util.HashMap;


public class EfficientSolutionUsingHashMap {

    public static void main(String args[]){
        int arr[] = {10,20,10,10,30,40};
        int k = 4;
        System.out.println("Count distinct elements in window of k : ");
        countDistinctElementsInWindow(arr, arr.length, k);
    }

    public static void countDistinctElementsInWindow(int arr[], int n, int k){

        //Create a Frequence map for first k elements
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<k; i++){
            map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
        }

        System.out.print(map.size()+ " ");


        for(int i=k; i<n; i++){
            //decrease freq of arr[i-k]
            map.put(arr[i-k], map.get(arr[i-k])-1);
            
            //check if arr[i-k] becomes 0-> if yes, remove it
            if(map.get(arr[i-k])==0){
                map.remove(arr[i-k]);
            }

            //increase or add frequency for arr[i]
            map.put(arr[i], map.getOrDefault(arr[i],0)+1);

            //print size
            System.out.print(map.size()+ " ");

        }



    }
    
}
