package sorting.minimumDifferenceInAnArray;

/*
 * TIME COMPLEXITY : O(N*N)
 * AUXILIARY SPACE : O(1)
 */

public class NaiveSolution {

    public static void main(String args[]){
        int arr[] = {5,3,8};
        System.out.println("Minimum difference b/w given array elements is : "+findMinDiff(arr, arr.length));
    }


    public static int findMinDiff(int arr[], int n){
        int res = Integer.MAX_VALUE;

        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                res = Math.min(res, Math.abs(arr[i]-arr[j]));
            }
        }
        
        return res;
    }
    
}
