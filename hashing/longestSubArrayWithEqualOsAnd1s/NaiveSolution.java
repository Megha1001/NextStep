package hashing.longestSubArrayWithEqualOsAnd1s;

/*
 * TIME COMPLEXITY : Theta(N*N)
 * AUXILIARY SPACE : O(1)
 */

public class NaiveSolution {

    public static void main(String args[]){
        // int arr[] = {1,0,1,1,1,0,0};
        // int arr[] = {1,1,1,1};
        int arr[] = {0,0,1,1,1,1,1,0};
        System.out.println("Longest subarray with equals 0's and 1's is : "+findLongestSubArrayWith0sAnd1s(arr, arr.length));
    }
    

    public static int findLongestSubArrayWith0sAnd1s(int arr[], int n){
        
        int res = 0;

        if(n==1){
            return res;
        } 

        for(int i=0; i<n-1; i++){
            int currCount = 0;

            //consider every element as start of the subarray
            for(int j=i; j<n; j++){
                if(arr[j]==1){
                    ++currCount;
                }
                else {
                    --currCount;
                }

                if(currCount == 0){
                    res = Math.max (res, j-i+1);
                }
            }

        }

        return res;
    }

}
