package sorting.binarySearch;

public class IterativeApproach {

    public static void main(String args[]){
        int arr[] = {10,20,30,40,50,60};
        int k = 20;

        System.out.println("The index of element is : "+findElement(arr, 0, arr.length-1,k));
    }

    public static int findElement(int arr[], int low, int high, int k){

        while(low < high){
            int mid = low + (high-low)/2;
            
            if(arr[mid]==k){
                return mid;
            }

            if(arr[mid] > k){
                --high;
            }else{
                ++low;
            }
        }

        return -1;

    }
    
}
