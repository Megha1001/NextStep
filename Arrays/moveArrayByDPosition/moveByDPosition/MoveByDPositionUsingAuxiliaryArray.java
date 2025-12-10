package Arrays.moveArrayByDPosition.moveByDPosition;

/*
 * Time Complexity : Theta(N)
 * Auxiliary Space : Theta(d)
 */

public class MoveByDPositionUsingAuxiliaryArray {


    public static void main(String args[]){
        int arr[] = {1,2,3,4,5};
        int n = arr.length;
        int d=2;
        System.out.println("Array after moving  by d position");
        movingArrayByDPosition(arr, n, d);
        printArray(arr, n);
    }

    public static void movingArrayByDPosition(int arr[], int n, int d){

        int temp [] = new int[d];

        //copy elements from arr[0] to arr[d] into temp
        for(int i=0; i<d; i++){
            temp[i] = arr[i];
        }

        //move element from d to n-1 to 0 to n-1-d
        for(int i=d; i<n; i++){
            arr[i-d] = arr[i];
        }

        //move elements from temp to arr
        for(int i=0; i<d; i++){
            arr[n-d+i]= temp[i];
            
        }

    }

    public static void printArray(int arr[], int n){
        for(int i=0; i<n; i++){
            System.out.print(arr[i]+ " ");
        }
    }
    
}
