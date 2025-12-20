package sorting.quickSort;

public class QuickSortUsingHoaresPartition {

    public static void main(String args[]){
        int arr[] = {3,4,2,7,0,8};
        System.out.println("Array after sorting using quick sort with lomuto partition algo is ");
        quickSort(arr, 0, arr.length-1);

        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] +" ");
        }
    }


    public static void quickSort(int arr[], int l, int h){
        if(l<h){
           int p = partition(arr, l, h);
           quickSort(arr, l, p);
           quickSort(arr, p+1, h);

        }
    }

    public static int partition(int arr[], int l, int h){
        int i= l-1;
        int j = h+1;
        int pivot = arr[l];

        do{
            ++i;
        }while(arr[i] < pivot);


        do{
            --j;
        }while(arr[j] > pivot);
        
        if(i>=j){
            return j;
        }

        //swap
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    
        return j;
    }
    
}
