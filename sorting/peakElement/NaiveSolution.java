package sorting.peakElement;

/*
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(1)
 */

public class NaiveSolution {

    public static void main(String args[]){
        int arr[] = {6,7,8,20,12};
        System.out.println("The peak element in given array is : "+findPeak(arr, arr.length));
    }


    public static int findPeak(int arr[], int n){

        if(n==1){
            return arr[0];
        }

        if(arr[0] >= arr[1]){
            return arr[0];
        }

        if(arr[n-1] >= arr[n-2]){
            return arr[n-1];
        }

        for(int i=1; i<n-1; i++){
            if(arr[i] >= arr[i-1] && arr[i] >= arr[i+1]){
                return arr[i];
            }
        }

        return -1;

    }
    
}
