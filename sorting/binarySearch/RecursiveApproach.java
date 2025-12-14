package sorting.binarySearch;
/*
 * TIME COMPLEXITY : O(logN)
 * Auxiliary Space : O(logN)
 */

public class RecursiveApproach {

    public static void main(String args[]){
        int arr[] = {10,20,30,40,50,60};
        // int arr[] = {5,10,15,25,35};
        int k = 20;

        System.out.println("The index of element is : "+findElement(arr, 0, arr.length-1,k));
    }

    public static int findElement(int arr[], int l, int h, int x){

        if(l > h){
            return -1;
        }

        int mid = l + (h-l)/2;

        if(arr[mid] == x){
            return mid;
        }
        else if (arr[mid] > x){
            //left
            return findElement(arr, l, mid-1, x);
        }
        else {
            //right
            return findElement(arr, mid+1, h, x);
        }

    }
    
}
