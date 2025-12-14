package sorting.indexOfLastOccurrenceInSortedArray;

public class FindIndexOfastOccurrenceUsingRecursiveBS {

    public static void main(String args[] ){
        int arr[] = {10,15,20,20,40,40};
        int k = 20;
        System.out.println("The last occurrence of given k in array is : "+findIdx(arr, 0, arr.length-1, k, arr.length));
    }


    public static int findIdx(int arr[], int l, int h, int k , int n){

        if(l > h){
            return -1;
        }

        int mid = l + (h-l)/2;

        if(arr[mid] == k){
            if(mid==n-1 || arr[mid] != arr[mid+1]){
                return mid;
            }else{
                return findIdx(arr, mid+1, h, k, n);
            }
        } 

        else if (arr[mid] > k){
            return findIdx(arr, l, mid-1, k, n);
        }else {
            return findIdx(arr, mid+1, h, k, n);
        }

    }
    
}
