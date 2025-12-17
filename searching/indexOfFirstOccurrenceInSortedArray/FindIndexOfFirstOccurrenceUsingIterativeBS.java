package searching.indexOfFirstOccurrenceInSortedArray;

/*
 * Time complexity : O(logN)
 * Auxiliary Space : O(1)
 */

public class FindIndexOfFirstOccurrenceUsingIterativeBS {

    public static void main(String args[]){
        // int arr[] = {1,10,10,10,20,20,40};
        int arr[] = {15,15,15};
        int k = 15;
        System.out.println("Index of first occurrence of element k in given array is "+findIdx(arr, 0, arr.length-1, k));
    }


    public static int findIdx(int arr[], int low , int high, int k){

        while(low <= high){

            int mid = low + (high-low)/2;

            if(arr[mid]==k){
                if(mid==0 || arr[mid-1]!=arr[mid]){
                    return mid;
                }
                high = mid-1; 
            }

            else if (arr[mid] > k){
                high = mid-1;
            }else {
                low = mid+1;
            }

        }

        return -1;
    }
    
}
