package Arrays.maximumCircularSubArray;

/*
 * IDEA :
 * 1. Find the maximum subarray sum in normal array
 * 2. Find the minimum subarray sum in normal array
 * 3. res = Math.max(step1, totalsum - step2)
 */

public class EfficientSolution {

    public static void main(String args[]){
        // int arr[] = {5, -2, 3, 4};
        int arr[] = {-5,-2,-8}; //corner case
        System.out.println("Maximum circular subarray sum is : "+findMaxCircularSubArraySum(arr, arr.length));
    }

    public static int findMaxCircularSubArraySum(int arr[], int n){
        int res = 0;
        
        int maxSumOfNormalArray = findMaximumSum(arr,n);

        //for corner case
        if(maxSumOfNormalArray < 0){
            return maxSumOfNormalArray;
        }

        int minSumOfNormalArray = findCircularSum(arr,n);

        res = Math.max(maxSumOfNormalArray,minSumOfNormalArray);

        return res;

    }

    public static int findMaximumSum(int[]arr, int n){

        int res = arr[0];
        int max_ending = arr[0];

        for(int i=1; i< n; i++){
            max_ending = Math.max(max_ending + arr[i], arr[i]);
            res = Math.max(res, max_ending);
        }

        return res;

    }

    public static int findCircularSum(int arr[], int n){
        int res = arr[0];
        int min_ending = arr[0];
        int arraySum = arr[0];

        for(int i=1; i<n; i++){
            min_ending = Math.min(min_ending + arr[i], arr[i]);
            res = Math.min(res, min_ending);
            arraySum += arr[i];
        }
    
        return arraySum - res;
    }
    
}
