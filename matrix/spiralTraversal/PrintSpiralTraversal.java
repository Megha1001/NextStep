package matrix.spiralTraversal;


/*
 * why we need to check if(top<=bottom){ and if(left<=right){
 * Why if (top <= bottom) is needed

This check is before printing the bottom row.

Problem without the check
Imagine a matrix with only one row left:

1 2 3


At this point:
top == bottom
You already printed this row when traversing the top row
Then you do top++, so now top > bottom
If you don’t check if (top <= bottom):
You’ll try to print the same row again in reverse
Or worse, access invalid indices

------------------------------------
Why if (left <= right) is needed

This check is before printing the left column.

Problem without the check
Consider a single column matrix:

1
2
3


At some point:
left == right
The column was already printed when traversing the right column
After right--, left > right
Without the check:
You’ll print the same column again
Or access invalid indexes
What the check ensures
if (left <= right) {
    // safe to print left column
}
 */

public class PrintSpiralTraversal {

    public static void main(String args[]){
        int arr[][] = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,16}
        };

        System.out.println("Spiral traversal of given array is  :");
        printSpiralTraversal(arr);
    }
    

    public static void printSpiralTraversal(int arr[][]){
        int r = arr.length;
        int c = arr[0].length;
        int top=0;
        int right = c-1;
        int bottom = r-1;
        int left = 0;

        while(top<=bottom && left <= right){

            for(int i=left ; i<=right; i++){
                System.out.print(arr[top][i]+" ");
            }

            ++top;

            for(int i=top; i<=bottom; i++){
                System.out.print(arr[i][right]+" ");
            }

            --right;

            if(top<=bottom){
                for(int i=right; i>=left; i--){
                    System.out.print(arr[bottom][i] + " ");
                }
            }
            --bottom;

            if(left<=right){
                for(int i=bottom; i>=top ; i--){
                    System.out.print(arr[i][left] + " ");
                }
            }
            ++left;

        }
    }

}
