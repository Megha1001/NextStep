package sorting.selectionSort;

/*
 * IN PLACE
 *  Does less memory write compares to quick sort, insertion sort , merge sort etc
 * 
 * BUT CYCLE sort performs better in terms of memory
 * 
 * TIME COMPLEXITY : Theta(N*2) --> ALWAYS
 * AUXILIARY SPACE : O(1)
 */

public class SelectionSortAlgorithm {

    public static void main(String args[]){
        int arr[] = {10,5,8,20,2,18};
        System.out.println("Array after sorting using selection sort is : ");
        sortUsingSelectionSort(arr, arr.length);
    }

    public static void sortUsingSelectionSort(int arr[], int n){

        //find min elment in every traversal from remaining considerable array and swap with arr[i]
        for(int i=0; i<n; i++){
            int minIdx = i;
            for(int j=i+1; j<n; j++){
                if(arr[j] < arr[minIdx]){
                    minIdx = j;
                }
            }

            //swap
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;

        }


        //print
        for(int i=0; i<n; i++){
            System.out.print(arr[i]+" ");
        }

    }
    
}
