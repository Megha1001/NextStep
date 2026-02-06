package dynamic_Programming.bitonicSubSequence;

public class MaximumLengthBitonicSubsequence {

    /*
        IDEA : 
        1. Find largest increasing subsequence considering every element as end of pivot
        2. Find largest decreasing subsequence  : basically find the largest increasing subsequence by traversing array from last
    */

    public static void main(String args[]){
        int arr[] = {1,11,2,10,4,5,2,1};
        System.out.println("The maximum length bitonic subsequence is : "+findMaxLenBitonicSubSeq(arr, arr.length));
    }

    public static int findMaxLenBitonicSubSeq(int arr[], int n){

        int lis[] = new int[n];
        int lds[] = new int[n];

        //for lis
        for(int i = 0; i < n; i++){
            lis[i] = 1;
            for(int j = 0; j < n; j++){ //considering every elment as pivot
                if(arr[j] < arr[i]){
                    lis[i] = Math.max(lis[i], 1 + lis[j]);
                }
            }
        }


        //for lds
        for(int i = n-1; i >=0; i--){
            lds[i] = 1;
            for(int j = i ; j <n ; j++){

                if(arr[j] < arr[i]){
                    lds[i] = Math.max(lds[i], 1 + lds[j]);
                }

            }
        }

        // for(int i = 0; i<n; i++){
        //     System.out.print(lis[i] + ", ");
        // }

        // System.out.println();
        // for(int i = 0; i<n; i++){
        //     System.out.print(lds[i] + ", ");
        // }


        int res = lis[0] + lds[0] - 1; // -1 since same number is included in LIS and LDS

        for(int i = 1; i < n; i++){
            res = Math.max(res, lis[i]+lds[i]-1);
        }


        return res;

    }
    
}
