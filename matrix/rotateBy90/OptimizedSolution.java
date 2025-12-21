package matrix.rotateBy90;

/*
 * IDEA : 
 * 1. Trasnpose
 * 2. reverse columns
 */

public class OptimizedSolution {

    public static void main(String args[]){
        int arr[][] = {
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };

        System.out.println("Matrix after rotating 90 degree is : ");
        rotateBy90Degree(arr, arr.length, arr[0].length);
    }

    public static void rotateBy90Degree(int arr[][], int r, int c){
        findTranspose(arr, r, c);
        reverseColumnElements(arr, r, c);

        //print
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void findTranspose(int arr[][], int r, int c){
        
        for(int i=0; i<r; i++){
            for(int j=i+1; j<c; j++){
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }

    }

    public static void reverseColumnElements(int arr[][], int r, int c){
        for(int i=0; i<c; i++){
           int low = 0, high = r-1;
           while(low < high){

            int temp = arr[low][i];
            arr[low][i] = arr[high][i];
            arr[high][i] = temp;

            ++low;
            --high;
           }
        }
    }
    
}
