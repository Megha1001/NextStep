package hashing.longestSubArrayWithGivenSum;

public class NaiveSolution {


    public static void main(String args[]){
        // int arr[] = {5,8,-4,-4,9,-2,2};
        // int sum = 0;
        int arr[] = {3,1,0,1,8,2,3,6};
        int sum = 5;
        // int arr[] = {8,3,7};
        // int sum = 15;

        System.out.println("The longest subarray length with given sum is : "+findLongestSubArrayLengthWithGivenSum(arr, arr.length, sum));
    }

    public static int findLongestSubArrayLengthWithGivenSum(int arr[], int n, int sum){
        int res = 0;

        for(int i=0; i<n; i++){
            int currSum = 0;
            for(int j=i; j<n; j++){
                currSum += arr[j];
                if(currSum == sum){
                    res = Math.max(res, j-i+1);
                }
            }
        }

        return res;
    }
    
}
