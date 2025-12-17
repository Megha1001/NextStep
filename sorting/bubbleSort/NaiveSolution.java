package sorting.bubbleSort;

/*
 * After every pass the maximum element present in the array reaches to final position.
 * 
 * TOTAL PASS : N-1
 * TIME COMPLEXITY : O(N*N)
 * AUXILIARY SPACE : O(1)
 */

public class NaiveSolution {

    public static void main(String args[]){
        int arr[] = {2,3,4,5,1};
        System.out.println("Array after sorting using bubble sort is  : ");
        sortUsingBubbleSort(arr, arr.length);
    }
    

    public static void sortUsingBubbleSort(int arr[], int n){

        for(int i=0; i<n-1; i++){
            for(int j=0; j<n-1-i; j++){
                if(arr[j] > arr[j+1]){
                    //swap
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }

        }

        //print
        for(int i=0; i<n; i++){
            System.out.print(arr[i] +" ");
        }

    }

}
