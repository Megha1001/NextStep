package hashing.countDistinct;

import java.util.HashSet;

public class EfficientSolution {

    public static void main(String args[]){
        int arr[] = {10,20,10,20,30};
        System.out.println("Number of distinct elements in given array is : "+findDistinct(arr, arr.length));
    }

    public static int findDistinct(int arr[], int n){
        HashSet<Integer> h = new HashSet<>();

        for(int i=0; i<n; i++){
            h.add(arr[i]);
        }

        return h.size();
    }
    
}
