package sorting.kthSmallestElement;

/*
 * IDEA : Use lomuto partition
 */

public class FindKthSmallestElementUsingPartitionFunction {

    public static void main(String args[]){
        int arr[] = {10,4,5,8,11,6,26};
        int k =5;
        System.out.println("Kth smallest element in given array is : "+findKthSmallest(arr, arr.length, k));
    }

    public static int findKthSmallest(int arr[], int n, int k){
        int l=0; 
        int h= n-1;

        while(l<=h){ //why = its not sorting its searching hence we need to check element at = position as well
            int p = partition(arr, l, h);
            
            if(p==k-1){
                return arr[p];
            }

            else if (p>k-1){
                h = p-1;
            }
            else {
                l = p+1;
            }

        }

        return -1;
    }

    public static int partition(int arr[], int l, int h){
        int pivot = arr[h];
        int i=l-1;

        for(int j=l; j<= h-1; j++){
            if(arr[j] < pivot){
                ++i;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i+1];
        arr[i+1] = arr[h];
        arr[h] = temp;

        return i+1;
    }
    
}
