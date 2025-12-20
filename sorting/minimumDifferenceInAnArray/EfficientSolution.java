package sorting.minimumDifferenceInAnArray;

import java.util.Arrays;

/*
 * TIME COMPLEXITY : O(NlogN)
 * AUXILIARY SPACE : O(N)
 */

public class EfficientSolution {

    public static void main(String args[]){
        int arr[] = {5,3,8};
        System.out.println("Minimum difference b/w given array elements is : "+findMinDiff(arr, arr.length));
    }


    public static int findMinDiff(int arr[], int n){
        int res = Integer.MAX_VALUE;
      
        Arrays.sort(arr);

        for(int i=0; i<n-1; i++){
            res = Math.min(res, Math.abs(arr[i]-arr[i+1]));
        }

        return res;

    }
    
}
