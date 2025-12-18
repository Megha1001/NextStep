package sorting.insertionSort;

public class InsertionSortBookVersion {

    public static void main(String args[]){
        int arr[] = {2,3,1,8,0};
        System.out.println("Array after sorting using insertion sort is : ");
        sortUsingInsertionSort(arr, arr.length);
    }

    public static void sortUsingInsertionSort(int arr[], int n){

        for(int i=1; i<n; i++){
            int key = arr[i];
            int j = i-1;
            
            while(j>=0 && arr[j] > key){
                arr[j+1] = arr[j];
                --j;
            }

            arr[j+1] = key;
        }

        //print
        for(int i=0 ;i<n; i++){
            System.out.print(arr[i]+" ");
        }

    }
    
}
