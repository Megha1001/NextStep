package Arrays.maximumCircularSubArray;

/*
 * Idea : Consider every element to be start of maximum circular subarray 
 * Starting with every element there can be N subarray 
 * 
 * TIME COMPLEXITY : Theta(N*N)
 * Auxiliary Space : O(1)
 */

public class NaiveSolution {

    public static void main(String args[]){
        int arr[] = {5, -2, 3, 4};
        System.out.println("Maximum circular subarray sum is : "+findMaxCircularSubArraySum(arr, arr.length));
    }

    public static int findMaxCircularSubArraySum(int arr[], int n){
        int res = arr[0];

        for(int i=0; i<n; i++){
            int currSum = arr[i];
            int currMax = arr[i]; // this is one out of n possible subarray

            for(int j=1; j<n; j++){ //these are n-1 subarrray
                int idx = (i+j)%n;
                currSum += arr[idx];
                currMax = Math.max(currMax, currSum);
            }

            res = Math.max(res, currMax);
        }

        return res;
    }


}
