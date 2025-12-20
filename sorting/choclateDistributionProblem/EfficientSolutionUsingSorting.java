package sorting.choclateDistributionProblem;

import java.util.Arrays;

/*
 * TIME COMPLEXITY : O(NlogN)
 * AUXILIARY SPACE : O(logN)
 */

public class EfficientSolutionUsingSorting {

    public static void main(String args[]){
        int arr[] = {7,3,2,4,9,12,56};
        int k = 3;
        System.out.println("The minimum difference between the max and min choclate count is : "+findMinDiff(arr, arr.length, k));
    }

    public static int findMinDiff(int arr[], int n, int k){
        int res = Integer.MAX_VALUE;

        Arrays.sort(arr);

        for(int i=0; i<n-k; i++){
            res = Math.min(res, Math.abs(arr[i]-arr[i+k-1]));
        }

        return res;
    }
    
}
