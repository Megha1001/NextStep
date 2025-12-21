package matrix.searchInRowWiseAndColumnWiseSortedMatrix;

/*
 * IDEA : Either start with rightmost top element or with bottommost left element
 * 
 * TIME COMPLEXITY : O(N+M)
 * AUXILIARY SPACE : O(1)
 */

public class EfficientSolution {

    public static void main(String args[]){
        int arr[][] = {
            {10,20,30,40},
            {15,25,35,45},
            {27,29,37,48},
            {32,33,39,50}
        };

        int x = 29;
        System.out.println("is the given element present in array : "+findElement(arr, x));
    }

    public static String findElement(int arr[][], int x){
        int r = arr.length;
        int c = arr[0].length;

        int i=0;
        int j= arr[0].length-1;

        while(i<r && j>=0){
            int curr = arr[i][j];

            if(curr == x){
                return "Yes";
            }

            else if(curr > x){
                //go left
                --j;
            }else {
                //go down
                ++i;
            }

        }

        return "No";
    }
    
}
