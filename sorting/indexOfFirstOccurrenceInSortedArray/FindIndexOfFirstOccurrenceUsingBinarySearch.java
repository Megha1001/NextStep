package sorting.indexOfFirstOccurrenceInSortedArray;

/*
 * TIME COMPLEXITY : O(logN)
 * AUXILIARY SPACE : O(logN)
 */

public class FindIndexOfFirstOccurrenceUsingBinarySearch {

    public static void main(String args[]){
        // int arr[] = {1,10,10,10,20,20,40};
        int arr[] = {15,15,15};
        int k = 15;
        System.out.println("Index of first occurrence of element k in given array is "+findIdx(arr, 0, arr.length-1, k));
    }
    
    public static int findIdx(int arr[], int l, int h, int k){
        if(l > h){
            return -1;
        }

        int mid = l + (h-l)/2;
        if(arr[mid] == k){
            //first occurrence check left
            if(mid > 0 && arr[mid] == arr[mid-1]){
                return findIdx (arr, l, mid-1, k);
            }
            return mid;
        }

        else if (arr[mid] > k){
            return findIdx(arr, l, mid-1, k);
        }else{
            return findIdx(arr, mid+1, h, k);
        }
    }


}
