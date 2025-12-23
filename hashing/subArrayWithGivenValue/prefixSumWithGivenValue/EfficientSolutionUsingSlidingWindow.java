package hashing.subArrayWithGivenValue.prefixSumWithGivenValue;

public class EfficientSolutionUsingSlidingWindow {
    

    public static void main(String args[]){
        int arr[] = {5,8,6,13,3,-1};
        int sum = 22;
        System.out.println("is the subarray exists with given sum ? "+isSubArrayExists(arr, arr.length, sum));
    }


    public static boolean isSubArrayExists(int arr[], int n, int sum){

        int start = 0;
        int currSum = 0;

        for(int i=0; i<n; i++){
            currSum += arr[i];

            while(currSum > sum){
                currSum -= arr[start];
                ++start;
            }

            if(currSum == sum){
                return true;
            }
        }

        return false;
    }
}
