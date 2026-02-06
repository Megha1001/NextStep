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
            for(int j = n-1; j>i ; j--){

                if(arr[j] < arr[i]){
                    lds[i] = Math.max(lis[i], 1 + lis[j]);
                }

            }
        }

        for(int i = 0; i<n; i++){
            System.out.print(lis[i] + ", ");
            System.out.println();
            System.out.print(lds[i] + ", ");
        }


        return -1;

    }
    
}
