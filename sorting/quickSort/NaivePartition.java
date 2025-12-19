package sorting.quickSort;

public class NaivePartition {


    public static void main(String args[]){
        int arr[] = {3,8,6,12,10,7};
        int p = 5;

        System.out.println("Array after partitioning around p is : ");
        partition(arr, 0, arr.length-1, p);
    }

    public static void partition(int arr[], int l, int h, int p){

        int temp [] = new int[h-l+1];
        int idx = 0; //for temp array

        //copy element <=arr[p]
        for(int i=l; i<=h; i++){
            if(arr[i] <= arr[p] && i!=p){
                temp[idx++] = arr[i];
            }
        }

        //copy partition element
        temp[idx++] = arr[p];

        //copy element > arr[p]
        for(int i=l; i<=h; i++){
            if(arr[i] > arr[p]){
                temp[idx++] = arr[i];
            }
        }


        //copy and print to original array
        for(int i=l ;i<=h; i++){
            arr[i] = temp[i-l];
            System.out.print(arr[i]+" ");
        }


    }
    
}
