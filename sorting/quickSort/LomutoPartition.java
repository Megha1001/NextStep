package sorting.quickSort;

/*
 * TIME COMPLEXITY : Theta(N)
 * AuXILIARY SPACE : O(1sss)
 */

public class LomutoPartition {

    public static void main(String args[]){
        int arr[] = {10,80,30,90,40,50,70};
        int pivot = 6;
        System.out.println("Index of the pivot after paritioning using lomuto partition algorithm is : "+ partition(arr, 0, arr.length-1, pivot));

        //print
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }

    }


    public static int partition(int arr[], int l, int h, int p){

        int i = l-1;
        int pivotValue = arr[p]; 

        for(int j=l; j<=h; j++){
            if(arr[j] < pivotValue){
                ++i;
                //swap
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }

        //swap arr[i+1] and arr[p]
        int temp = arr[i+1];
        arr[i+1] = arr[p];
        arr[p] = temp;
        return (i+1);

    } 
    
}
