package Arrays.maxSumSubArray;

public class NaiveSolution {
    
    public static void main(String args[]){
        int arr[] = {1, -2, 3, -1, 2};

        System.out.println("The maximum sum of subarray in given array is : "+findMaxSumSubArray(arr, arr.length));
    }

    public static int findMaxSumSubArray(int arr[], int n){

        int res = 0;

        for(int i=0; i<n; i++){

            int curr = 0;
            for(int j=i; j<n; j++){
                curr += arr[j];
                res = Math.max(res, curr);
            }

        }


        return res;
    }

}
