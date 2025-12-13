package Arrays.subArrayWithGivenSum;

/*
 * TIME COMPLEXITY : O(N)
 * AUXILIARY spACE : Theta(1)
 */

public class EfficientSolutionUsingSlidingWindow {

    public static void main(String args[]){
        // int arr[] = {1,4,20,3,10,5};
        int arr[] = {1,4,0,0,3,10,5};
        int sum = 7;
        System.out.println("is subarray exists with given sum : "+isSubArrayExista(arr, arr.length, sum));
    }


    public static boolean isSubArrayExista(int arr[], int n, int sum){
        int start = 0;
        int currSum = 0;

        for(int i=0; i<n;i++){
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
