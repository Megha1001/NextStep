package sorting.quickSort;

public class HoaresPartition {

    public static void main(String args[]){
        int arr[]  = {5,3,8,4,2,7,1,10};
        System.out.println("Index of pivot after partitioning using hoare's partition is : "+partition(arr, 0, arr.length-1));

        //print
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

    public static int partition(int arr[], int l, int h){
        int i= l-1;
        int j= h+1;
        int pivot = arr[l];

        while(true){
            do{
                ++i;
            }while(arr[i] < pivot);

            do{
                --j;
            }while(arr[j] > pivot);

            if(i>=j){
                return j;//pivot index
            }

            //swap 
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

    }
    
}
