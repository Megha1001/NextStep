package hashing.longestSubArrayWithGivenSum;

import java.util.HashMap;

/*
 * TIME COMPLEXITY : Theta(N)
 * AUXILIARY ARRAY : O(N)
 */

public class FindLongestSubArrayWithGivenSumUsingHashMap {

    public static void main(String args[]){
        // int arr[] = {5,8,-4,-4,9,-2,2};
        // int sum = 0;
        // int arr[] = {3,1,0,1,8,2,3,6};
        // int sum = 5;
        int arr[] = {-1,1,-1,0,0,-1};
        int sum = 0;

        System.out.println("The longest subarray length with given sum is : "+findLongestSubArrayLengthWithGivenSum(arr, arr.length, sum));
    }

    public static int findLongestSubArrayLengthWithGivenSum(int arr[], int n, int sum){
        //initializations
        int prefix_sum=0;
        int idx = -1;
        int res = 0;

        HashMap <Integer, Integer> h  = new HashMap<>();

        for(int i=0; i<n; i++){
            prefix_sum += arr[i];


            if(prefix_sum == sum){
                res = Math.max(res, i+1);
            }

            if(h.containsKey(prefix_sum - sum)){
                ++idx;
                res = Math.max(res, idx - h.get(prefix_sum - sum));
            } else{
                h.put(prefix_sum, ++idx);
            }


            
        }


        return res;
    }
    
}
