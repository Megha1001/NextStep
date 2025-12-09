package Arrays.movingZerosToEnd;


/*
 * Time complexity : Theta(N)
 * Auxiliary Space : Theta(1)
 * 
 * One traversal
 */
public class EfficientSolution2 {


    public static void main(String args[]){
        int arr[] = {8,5,0,10,0,20};
        int n = arr.length;
        System.out.println("Array after moving zeros to end is : ");
        moveZerosToEnd(arr, n);
        printArray(arr, n);
    }


    public static void moveZerosToEnd(int arr[], int n){
        int count=0;
        for(int i = 0; i<n; i++){
            if(arr[i]!=0){
                //swap
                int temp = arr[i];
                arr[i] = arr[count];
                arr[count] = temp;

                ++count;
            }
        }
    }


    public static void printArray(int arr[], int n){
        for(int i=0; i< n; i++){
            System.out.print(arr[i]+" ");
        }

    }

    
}
