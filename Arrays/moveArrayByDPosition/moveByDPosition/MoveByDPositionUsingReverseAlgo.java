package Arrays.moveArrayByDPosition.moveByDPosition;

/*
 * Idea : 
 * 1. Reverse elements from 0->d-1
 * 2. Reverse elements from d->n-1
 * 3. Reverse elements from 0->n-1
 * 
 * 
 * Time Complexity : Theta(N)
 * Auxiliary Space : O(1)
 * 2 traversal
 */


public class MoveByDPositionUsingReverseAlgo {


    public static void main(String args[]){
        int arr[] = {1,2,3,4,5};
        int n = arr.length;
        int d=2;
        System.out.println("Array after moving  by d position");
        movingArrayByDPosition(arr, n, d);
        printArray(arr, n);
    }

    public static void movingArrayByDPosition(int arr[], int n, int d){

        reverse(arr, 0, d-1);
        reverse(arr, d, n-1);
        reverse(arr, 0, n-1);

    }


    public static void reverse(int arr[], int low, int high){

        while(low < high){
            //swap
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            ++low;
            --high;
        }

    }

    public static void printArray(int arr[], int n){
        for(int i=0; i<n; i++){
            System.out.print(arr[i]+ " ");
        }
    }
    
}
