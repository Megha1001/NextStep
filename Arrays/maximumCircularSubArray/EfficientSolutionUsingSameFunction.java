package Arrays.maximumCircularSubArray;

public class EfficientSolutionUsingSameFunction {

    public static void main(String args[]){
        int arr[] = {5, -2, 3, 4};
        // int arr[] = {-5,-2,-8}; //corner case
        System.out.println("Maximum circular subarray sum is : "+findMaxCircularSubArraySum(arr, arr.length));
    }


    public static int findMaxCircularSubArraySum(int arr[], int n){
        int res = 0;

        int maxSubarraySumOfNormalArray = findMaxSubArraySum(arr, n);

        if(maxSubarraySumOfNormalArray < 0){
            return maxSubarraySumOfNormalArray;
        }

        int sum = invertArrayAndReturnSum(arr, n);

        int minimumSumOfNormalSubArray = findMaxSubArraySum(arr, n); //Maximum sum in inverted array given min sum in original array

        res = Math.max (maxSubarraySumOfNormalArray, sum + minimumSumOfNormalSubArray); // we have to + with sum as we have inverted array


        return res;
    }


    public static int findMaxSubArraySum(int arr[],int n){
        int res = arr[0];
        int max_ending = arr[0];

        for(int i=1; i<n; i++){
            max_ending = Math.max(max_ending+ arr[i], arr[i]);
            res = Math.max(res, max_ending);
        }

        return res;

    }

    public static int invertArrayAndReturnSum(int arr[], int n){
        int sum = 0;
        
        for(int i=0; i<n; i++){
            sum += arr[i];
            arr[i] = -arr[i];
        }

        return sum;
    }

    
}
