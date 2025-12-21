package matrix.transpose;

/*
 * TIME COMPLEXITY : O(n+m)
 */

public class NaiveSolution {

    public static void main(String args[]){
        int arr[][] = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,16}
        };

        System.out.println("Transpose of given matrix is : ");
        transpose(arr, arr.length, arr[0].length);
    }

    public static void transpose(int arr[][], int r, int c){
        for(int i=0; i<r; i++){
            for(int j=i+1; j<c; j++){
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }

        //print
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
