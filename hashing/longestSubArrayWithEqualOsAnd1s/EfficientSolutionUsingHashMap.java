package hashing.longestSubArrayWithEqualOsAnd1s;

/*
 * IDEA : 
 * 1.Replace every 0 with -1
 * 2. Find the longest subarray with given sum = 0
 */


import java.util.HashMap;

public class EfficientSolutionUsingHashMap {

    public static void main(String args[]){
        // int arr[] = {1,0,1,1,1,0,0};
        // int arr[] = {1,1,1,1};
        // int arr[] = {0,0,1,1,1,1,1,0};
        int arr[] = {0,0,1,0,1,1};
        for(int i=0; i<arr.length; i++){
            if(arr[i]==0){
                arr[i] = -1;
            }
        }
        System.out.println("Longest subarray with equals 0's and 1's is : "+findLongestSubArrayWith0sAnd1s(arr, arr.length, 0));
    }


    public static int findLongestSubArrayWith0sAnd1s(int arr[], int n, int sum){

        //initializations
        int prefix_sum = 0;
        int idx = -1;
        int res = 0;

        HashMap<Integer, Integer> h = new HashMap<>();

        for(int i=0; i<n; i++){
            prefix_sum += arr[i];

            if(prefix_sum == sum){
                res = Math.max(res, i+1);
            }

            if(h.containsKey(prefix_sum - sum)){
                ++idx;
                res = Math.max(res, h.get(prefix_sum - sum));
            }else{
                h.put(prefix_sum, ++idx);
            }

        }


        return res;

    }
    
}
