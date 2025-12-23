package hashing.subArrayWithGivenValue.prefixSumWithValueZero;

public class EfficientUsingSlidingWindow {

    public static void main(String args[]){
        int arr[] = {-3,4,-3,-1,1};
        int sum = 0;

        System.out.println("is subarray exists with given sum : "+isSubArrayExists(arr, arr.length, sum));
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

            if(currSum==sum){
                return true;
            }

        }

        return false;

    }
    
}
