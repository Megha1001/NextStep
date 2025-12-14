package Arrays.maxSumSubArray;

/*
 * 
 * IDEA :  The idea is to maintain a variable max_ending that stores the maximum sum of contiguous subarray ending at current index and a variable res stores the
 * maximum sum of contigous subarray found so far. Everytime there is a positive sum value in max_ending compare it with res and update the res if its greater than res.
 * 
 * TIME COMPLEXITY : Theat(N)
 * Auxiliary Space : O(1)
 */

public class EfficientSolution {

    public static void main(String args[]){
        int arr[] = {1, -2, 3, -1, 2};

        System.out.println("The maximum sum of subarray in given array is : "+findMaxSumSubArray(arr, arr.length));
    }

    public static int findMaxSumSubArray(int arr[], int n){

        int res = arr[0];
        int max_ending = arr[0];

        for(int i=0; i<n; i++){
            max_ending = Math.max(max_ending + arr[i], arr[i]);
            res = Math.max(res, max_ending);
        }

        return res;

    }

    
}
