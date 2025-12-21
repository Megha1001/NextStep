package matrix.BoundaryTraversal;

/*
 * 
 */

public class NaiveSolution {

    public static void main(String args[]){
        int arr[][]= {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,16}
        };

        System.out.println("Elements printed in boundary traversal are : ");
        findAndPrintBoundaryTraversal(arr, arr.length);
    }
    

    public static void findAndPrintBoundaryTraversal(int arr[][], int row){

        int col=arr[0].length;
        
        if(row==1){
            for(int i=0; i<col; i++){
                System.out.print(arr[0][i] + " ");
            }
        }else if(col==1){
            for(int i=0; i<row; i++){
                System.out.print(arr[i][0]+ " ");
            }
        }else{

            //print first row - column change
            for(int i=0; i<col; i++){
                System.out.print(arr[0][i]+ " ");
            }

            //print last column - row change
            for(int i=1; i<row; i++){
                System.out.print(arr[i][col-1]+ " ");
            }

            //print last row - column change
            for(int i=col-2; i>=0; i--){
                System.out.print(arr[row-1][i]+ " ");
            }

            //print first column - row change
            for(int i=row-2;i>=1; i--){
                System.out.print(arr[i][0]+ " ");
            }

        }
    }

}
