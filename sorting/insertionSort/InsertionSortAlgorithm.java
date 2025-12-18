package sorting.insertionSort;

public class InsertionSortAlgorithm {

    public static void main(String args[]){
        int arr[] = {2,3,1,8,0};
        System.out.println("Array after sorting using insertion sort is : ");
        sortUsingInsertionSort(arr, arr.length);
    }

    public static void sortUsingInsertionSort(int arr[], int n){

        if(n>1){

            for(int i=0; i<n-1; i++){
                for(int j=i+1; j>0; j--){
                    if(arr[j] > arr[j-1]){
                        continue;
                    }
                    else if(arr[j] < arr[j-1]){
                        //swap 
                        int temp = arr[j];
                        arr[j] = arr[j-1];
                        arr[j-1] = temp;
                    }
                }
            }
            
        }

        //print
        for(int i=0; i<n; i++){
            System.out.print(arr[i]+ " ");
        }

    }
    
}
