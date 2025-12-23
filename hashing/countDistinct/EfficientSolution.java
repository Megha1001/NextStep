package hashing.countDistinct;

import java.util.HashSet;
import java.util.Arrays;

/*
 * TIME COMPLEXITY : Theta(N)
 * AUXIliARY SPACE : O(1)
 */

public class EfficientSolution {

    public static void main(String args[]){
        Integer arr[] = {10,20,10,20,30};
        System.out.println("Number of distinct elements in given array is : "+findDistinct(arr, arr.length));

        //another way
        HashSet<Integer> h = new HashSet<>(Arrays.asList(arr));
        System.out.println("Number of distinct elements in given array is : "+h.size());
    }

    public static int findDistinct(Integer arr[], int n){
        HashSet<Integer> h = new HashSet<>();

        for(int i=0; i<n; i++){
            h.add(arr[i]);
        }

        return h.size();
    }
    
}
