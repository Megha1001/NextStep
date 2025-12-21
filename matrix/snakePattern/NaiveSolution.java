package matrix.snakePattern;

/*
 * IDEA : 
 * if rowIndex
 *      - even : print from L to R
 *      - odd  : print from R to L
 * 
 * 
 * TIME COMPLEXIYT : O(n*m), where n = number of rows, c = number of columns
 * AUXILIARY SPACE : O(1)
 */

public class NaiveSolution {
    
    public static void main(String args[]){
        int arr[][] = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,16}
        };

        System.out.println("Element in snake pattern are : ");
        printSnakePattern(arr, arr.length);
    }

    public static void printSnakePattern(int arr[][], int row){
        if(row==0){
            return;
        }

        for(int i=0; i<row; i++){

            //left to right
            if(i%2==0){
                for(int j=0; j<arr[i].length; j++){
                    System.out.print(arr[i][j] + " ");
                }
            }

            //right to left
            else {
                for(int j= arr[i].length-1; j>=0; j--){
                    System.out.print(arr[i][j] + " ");
                }
            }

        }
    }

}
