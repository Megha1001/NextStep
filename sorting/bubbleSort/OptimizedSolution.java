package sorting.bubbleSort;

public class OptimizedSolution {


    public static void main(String args[]){
        int arr[] = {2,3,4,5,1};
        System.out.println("Array after sorting using bubble sort is  : ");
        sortUsingBubbleSort(arr, arr.length);
    }


    public static void sortUsingBubbleSort(int arr[], int n){

        //pass = n-1
        for(int i=0; i<n-1; i++){
            boolean swapped = false;
            //after every traversal -> max in rest array find its position
            for(int j=0; j<n-i-1; j++){
                if(arr[j] > arr[j+1]){ // > to keep the algo stable
                    //swap
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;

                    //mark the flag
                    swapped = true;
                }
            }

            if(!swapped){
                //array is already/now sorted.
                break;
            }
        }

        //print
        for(int i=0; i<n; i++){
            System.out.print(arr[i]+" ");
        }

    }
    
}
