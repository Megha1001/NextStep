package Arrays.subArrayWithGivenSum;

/*
 * TIME COMPLEXITY : O(N*N);
 */

public class NaiveSolution {

    public static void main(String args[]){
        // int arr[] = {1,4,20,3,10,5};
        int arr[] = {1,4,0,0,3,10,5};
        int sum = 7;
        System.out.println("is subarray exists with given sum : "+isSubArrayExista(arr, arr.length, sum));
    }

    public static String isSubArrayExista(int arr[], int n, int sum){

        for(int i=0; i<n; i++){
            int currSum = 0;
            for(int j=i; j<n; j++){
                currSum += arr[j];
                if(currSum==sum){
                    return "Yes";
                }
                else if(currSum > sum){
                    break;
                }
            }
        }

        return "No";
    }
    
}
