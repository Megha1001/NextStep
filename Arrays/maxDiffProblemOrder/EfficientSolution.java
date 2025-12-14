package Arrays.maxDiffProblemOrder;

/*
 * Time COmplexity : Theta(N)
 * Auxiliary Space : O(1)
 */

public class EfficientSolution {

    public static void main(String args[]){
        int arr[] = {30,10,8,-2};
        System.out.println("Max difference is : "+findMaxDiff(arr, arr.length));
    }

    public static int findMaxDiff(int arr[], int n){

        int res = arr[1] - arr[0];
        int minValue = arr[0];

        for(int i = 1; i<n; i++){
            res = Math.max (res, arr[i] - minValue);
            minValue = Math.min(minValue, arr[i]);
        }


        return res;
    }
    
}
