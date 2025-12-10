package Arrays.moveArrayByDPosition;

/*
 * Time Complexity : Theta(N)
 * Auxiliary Space : Theta(1)
 */
public class MoveArrayBy1Position {

    public static void main(String args[]){
        int arr[] ={1, 2, 3, 4, 5};
        int n = arr.length;
        System.out.println("Array after moving d position  ");
        moveByDPosition(arr, n);
        printArray(arr, n);
    }


    public static void moveByDPosition(int arr[], int n){
        int temp = arr[0];
        for(int i = 1; i<n; i++){
            arr[i-1] = arr[i];
        }

        arr[n-1] = temp;
    }

    public static void printArray(int arr[], int n){
        for(int i=0; i<n; i++){
            System.out.print(arr[i]+" ");
        }
    }

}
