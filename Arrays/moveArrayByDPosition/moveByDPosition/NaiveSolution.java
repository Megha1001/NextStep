package Arrays.moveArrayByDPosition.moveByDPosition;


/*
 * Time COmplexity : Theta(N*D)
 * AUXILIARY Space : Theta(1)
 */

public class NaiveSolution {


    public static void main(String args[]){
        int arr[] = {1,2,3,4,5};
        int n = arr.length;
        int d=2;
        System.out.println("Array after moving  by d position");
        movingArrayByDPosition(arr, n, d);
        printArray(arr, n);
    }

    public static void movingArrayByDPosition(int arr[], int n, int d){

        for(int i=0; i<d; i++){
            int temp = arr[0];
            for(int j=1; j<n; j++){
                arr[j-1] = arr[j];
            }
            arr[n-1] = temp;
        }
    }

    public static void printArray(int arr[], int n){
        for(int i=0; i<n; i++){
            System.out.print(arr[i]+ " ");
        }
    }
    
}
