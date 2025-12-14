package Arrays.largestEvenOddSubarray;

/*
 * TIME COMPLEXITY : Theta(N*N)
 * AUXILIARY SPACE : O(1)
 */

public class NaiveSolution {

    public static void main(String args[]){
        // int arr[] = {10,12,14,7,8}; //3
        // int arr[] = {7,10,13,14}; //4
        int arr[] = {10,12,8,4}; //1
        System.out.println("Maximum even odd subarray length is : "+findMaxEvenOddSubArr(arr, arr.length));
    }


    public static int findMaxEvenOddSubArr(int arr[], int n){

        if(n==0){
            return 0;
        }

        int res = 0;

        for(int i=0; i<n; i++){
            int currMax = 1;

            for(int j=i+1; j<n; j++){

                if((arr[j-1]%2==0 && arr[j]%2==1)
                             ||
                (arr[j-1]%2==1 && arr[j]%2==0)){
                    ++currMax;
                }else{
                    break;
                }

            }
            res = Math.max(res, currMax);

        }

        return res;
    }
    
}
