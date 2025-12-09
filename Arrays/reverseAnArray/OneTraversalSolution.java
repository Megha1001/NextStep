package Arrays.reverseAnArray;



public class OneTraversalSolution {


    public static void main(String args[]){
        int arr[] = {10, 2, 1, 40};
        System.out.println("The reverse of given array is ");
        printReverse(arr, arr.length);
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]+" ");
        }
    }

    public static void printReverse(int arr[], int n){
        int i=0;
        int j=n-1;

        while(i < j){

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            ++i;
            --j;
        }
    }
    
}
