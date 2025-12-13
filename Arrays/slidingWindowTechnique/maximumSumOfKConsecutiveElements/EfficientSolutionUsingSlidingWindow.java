package Arrays.slidingWindowTechnique.maximumSumOfKConsecutiveElements;

public class EfficientSolutionUsingSlidingWindow {

    public static void main(String args[]){
        // int arr[] = {1,8,30,-5,20,7};
        int arr[] = {5,-10,6,90,3};
        // int k = 3;
        int k = 2;
        System.out.println("Maximum sum of k consecutive elements in given array is : "+findMaxSum(arr, arr.length, k));
    }


    public static int findMaxSum(int arr[], int n , int k){

        int curr=0;
        for(int i=0; i<k; i++){
            curr+= arr[i];
        }

        int res = curr;

        for(int i=k; i<n; i++){
            curr += arr[i] - arr[i-k];
            res = Math.max(res, curr);
        }

        return res;
    }
    
}
