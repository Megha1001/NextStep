package Arrays.maximumCircularSubArray;

/*
 * IDEA :
 * 1. Find the maximum subarray sum in normal array
 * 2. Find the minimum subarray sum in normal array
 * 3. res = Math.max(step1, totalsum - step2)
 */

public class EfficientSolution {

    public static void main(String args[]){
        int arr[] = {5, -2, 3, 4};
        System.out.println("Maximum circular subarray sum is : "+findMaxCircularSubArraySum(arr, arr.length));
    }
    
}
