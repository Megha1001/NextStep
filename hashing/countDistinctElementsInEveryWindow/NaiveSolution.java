package hashing.countDistinctElementsInEveryWindow;

import java.util.HashSet;

public class NaiveSolution {
    
    public static void main(String args[]){
        int arr[] = {10,20,20,10,30,40,10};
        int k = 4;
        // int arr[] = {10,10,10,10};
        // int k = 3;
        System.out.println("Distinct elements in window of K are : ");
        printDistinct(arr, arr.length, k);
    }

    public static void printDistinct(int arr[], int n, int k){
        HashSet<Integer> h = null;
        for(int i=0; i<n-k+1; i++){
            h = new HashSet<>();
            for(int j=i; j<i+k; j++){
                h.add(arr[j]);
            }
            System.out.print(h.size()+" ");
        }
    }

}
