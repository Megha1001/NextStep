package searching.peakElement;

/*
 * TIME COMPLEXITY : O(logN)
 * AUXILIARY SPACE : O(1)
 * 
 * IDEA : If arr[m] <= arr[m-1] --> there will always be a peak element at left side
 *      or  
 * arr[m] <=arr[m+1] -> there will always be a peak element on right side
 */


public class EfficientSolutionUsingBinarySearch {

    public static void main(String args[]){
        int arr [] = {5,20,40,30,20,50,60};
        System.out.println("The Peak element in given array is : "+findPeak(arr, 0, arr.length-1));
    }

    public static int findPeak(int arr[], int low, int high){

        while(low <= high){

            int m = low + (high - low)/2;

            if((m==0 || arr[m] >= arr[m-1]) && (arr[m] >= arr[m+1] || m==arr.length-1)){
                return m;
            }

            if (m > 0 && arr[m-1] >= arr[m]){
                high = m-1; //go to left
            }
            else {
                low = m+1; //go to right
            }

        }


        return -1;
    }
    
}
