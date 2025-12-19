package sorting.quickSort;


/*
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPCE : O(N) //for temp array
 */

public class PartitionFunction {

    public static void main(String args[]){
        int arr[] = {3,8,6,12,10,7};
        int p = 5;

        System.out.println("Array after partitioning around p is : ");
        partition(arr, arr.length, p);
    }

    public static void partition(int arr[], int n, int p){
        int key = arr[p];
        int temp [] = new int [n];
        int k = 0; //index for temp;

        //copy element <= key
        for(int i=0; i<n; i++){
            if(arr[i] <= key){ //p also gets copy here
                temp[k++] = arr[i];
            }
        }

        //copy element >key
        for(int i=0; i<n; i++){
            if(arr[i] > key){
                temp[k++] = arr[i];
            }
        }


        //print
        for(int i=0; i<n; i++){
            System.out.print(temp[i] + " " );
        }

    }
    
}
