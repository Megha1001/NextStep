package Arrays.slidingWindowTechnique.maximumSumOfKConsecutiveElements;

public class NaiveSolution {

    public static void main(String args[]){
        // int arr[] = {1,8,30,-5,20,7};
        int arr[] = {5,-10,6,90,3};
        // int k = 3;
        int k = 2;
        System.out.println("Maximum sum of k consecutive elements in given array is : "+findMaxSum(arr, arr.length, k));
    }

    public static int findMaxSum(int arr[], int n, int k){
        int res = Integer.MIN_VALUE;

        for(int i=0; i<n-k; i++){
            int currSum=0;
            for(int j=0; j<k; j++){
                currSum += arr[i+j];
            }
            res = Math.max(res, currSum);

        }

        return res;
    }
    
}
