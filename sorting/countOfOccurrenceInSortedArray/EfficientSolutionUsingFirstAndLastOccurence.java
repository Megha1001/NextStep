package sorting.countOfOccurrenceInSortedArray;

/*
 * TIME COMPLEXITY : O(logN)
 * Auxiliary Space : O(1)
 */

public class EfficientSolutionUsingFirstAndLastOccurence{

    public static void main(String args[]){
        int arr[] = {10,20,20,20,30,30};
        int k = 20;

        System.out.println("Total occurrence of k in given array is : "+findTotalOccurrence(arr, 0, arr.length-1, k));
    }

    public static int findTotalOccurrence(int arr[], int l, int h, int k){
        int firstOccurrence = findFirstOccurrence(arr, l, h, k);
        if(firstOccurrence==-1){
            return -1;
        }
        int lastOccurrence = findLastOccurrence(arr, l, h, k, arr.length);

        return lastOccurrence - firstOccurrence +1;
    }

    public static int findFirstOccurrence(int arr[], int l, int h, int k){

        while(l <= h){
            int m = l + (h-l)/2;

            if(arr[m]==k){

                if(m==0 || arr[m] != arr[m-1]){
                    return m;
                }
                h = m-1;
            }

            else if(arr[m] > k){
                h = m-1;
            }else {
                l = m+1;
            }
        }

        return -1;
    }
    

    public static int findLastOccurrence(int arr[], int l, int h, int k, int n){

        while(l <= h){
            int m = l + (h-l)/2;

            if(arr[m] == k){
                if(m==n-1 || arr[m]!= arr[m+1]){
                    return m;
                }
                l = m+1;
            }

            else if(arr[m] > k){
                h = m-1;
            }else {
                l = m+1;
            }

        }

        return -1;

    }

}
