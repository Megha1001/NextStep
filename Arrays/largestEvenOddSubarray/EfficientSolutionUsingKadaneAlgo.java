package Arrays.largestEvenOddSubarray;

public class EfficientSolutionUsingKadaneAlgo {

    public static void main(String argsp[]){

        // int arr[] = {10,12,14,7,8}; //3
        // int arr[] = {7,10,13,14}; //4
        // int arr[] = {10,12,8,4}; //1
        int arr[] = {5,10,20,6,3,8}; //1
        System.out.println("Maximum even odd subarray length is : "+findMaxEvenOddSubArr(arr, arr.length));

    }

    public static int findMaxEvenOddSubArr(int arr[], int n){
        int res = 1, curr = 1;

        for(int i = 1; i<n; i++){

            if((arr[i-1]%2==0 && arr[i]%2==1)
                ||
                (arr[i-1]%2==1 && arr[i]%2==0)){
                ++curr;
                res = Math.max(res, curr);
            }else{
                curr = 1;
            }

        }

        return res;
    }
    
}
