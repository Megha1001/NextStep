package hashing.LongestCommonSpanWithSameSumInBinaryArrays;

/*
 * IDEA : 
 * 1. Compute a difference array
 *  a1 = {0,1,0,0,0,0}
 *  a2 = {1,0,1,0,0,1}
 * diff = {-1,1,-1,0,0,-1}
 *            ---------
 * 2. Return the length of longest subarray with sum = 0;
 *   - We get 0 when values are same in both
 *   - we get 1 when a1[i] = 1 and a2[i] = 0;
 *   - we get -1 when a2[i] = 0 and a2[i] = 1;
 * 
 * 2nd and third will balance each other in case of common subarray. Hence, we need to find subarray with sum = 0
 */

import java.util.HashMap;

public class EfficientSolutionUsingHashMap {
    
    public static void main(String args[]){
        int a1[] = {0,1,0,0,0,0};
        int a2[] = {1,0,1,0,0,1};

        System.out.println("The length of longest common subarray with same sum and same start/end idx is : "+findLongestCommonSubArray(a1, a2, a1.length));
    }

    public static int findLongestCommonSubArray(int a1[], int a2[], int n){

        //compute difference array
        int temp [] = new int[n];
        for(int i=0; i<n; i++){
            temp[i] = a1[i] - a2[i];
        }

        return findLongestSubArrayWithSumZero(temp, n, 0);

    }

    public static int findLongestSubArrayWithSumZero(int arr[], int n, int sum){
        int res = 0;

        int prefix_sum = 0;
        int idx = -1;
        HashMap<Integer, Integer> h = new HashMap<>();
        
    
        for(int i=0; i<n; i++){
            prefix_sum += arr[i];


            if(prefix_sum == sum){
                res = Math.max(res, i+1);
            }

            if(h.containsKey(prefix_sum - sum)){
                ++idx;
                res = Math.max(res, idx - h.get(prefix_sum - sum));
            }else {
                h.put(prefix_sum, ++idx);
            }


           
        }

        return res;
    }
    
}
