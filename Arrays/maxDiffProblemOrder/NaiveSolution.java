package Arrays.maxDiffProblemOrder;

/*
 * max value of arr[j] - arr[i] such that j>i
 * 
 * Time COmplexity : Theta(N*N)
 * Auxiliary Space : O(1)
 */

public class NaiveSolution {

    public static void main(String args[]){
        int arr[] = {30,10,8,-2};
        System.out.println("Max difference is : "+findMaxDiff(arr, arr.length));
    }

    public static int findMaxDiff(int arr[], int n){

        int max = Integer.MIN_VALUE;

        for(int i=0; i<n-1; i++){
            for(int j=i+1; j<n; j++){
                int diff = arr[j]-arr[i];
                if(diff> max){
                    max = diff;
                }
            }
        }

        return max;

    }
    
}
