package sorting.quickSort;

public class QuickSortUsingLomutoPartition {

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
           quickSort(arr, l, p-1);
           quickSort(arr, p+1, h);

        }
    }

    public static int partition(int arr[], int l, int h){
        int pivot = arr[h];
        int i = l-1;
        for(int j=l; j<=h-1; j++){
            if(arr[j] < pivot){
                ++i;
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i]  = temp;
            }
        }

        int temp = arr[i+1];
        arr[i+1] = arr[h];
        arr[h] = temp;

        return i+1;
    }
    
}
