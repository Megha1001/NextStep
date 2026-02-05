package dynamic_Programming.maximumSumIncreasingSubSequence;

public class MaximumSumIncreasingSubsequence {
    
    public static void main(String args[]){
        int arr[] = {3,20,4,6,7,30};

        System.out.println("Maximum sum of increasing subsequence is : "+findMSIS(arr, arr.length));
    }

    public static int findMSIS(int arr[], int n){
        int msis[] = new int[n];

        for(int i = 0; i < n; i++){
            msis[i] = arr[i];
            for(int j = 0; j < i; j++){
                if(arr[j] < arr[i]){
                    msis[i] = Math.max(msis[i], arr[i]+msis[j]);
                }
            }
        }



        int res = msis[0];
        for(int i = 1; i < n; i++){
            res = Math.max(res, msis[i]);
        }
        return res;

    }
    
}
