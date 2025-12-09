package Arrays.removeDuplicatesFromArray;

public class OneTraversalSolutionUsingForLoop {

    public static void main(String args[]){
        int arr [] = {10,20,20,20,30};
        int n = arr.length;
        int newLength = findSizeOfArray(arr,n);
        System.out.println("Size of array after removing duplicates is : "+newLength);
        printArray(arr, newLength);
    }


    public static int findSizeOfArray(int arr[], int n){
        int res = 1;

        for(int i = 1; i<n; i++){
            if(arr[i] != arr[i-1]){
                arr[res] = arr[i];
                ++res;
            }
        }

        return res;
    }

    public static void printArray(int arr[], int n){
        for(int i=0; i<n; i++){
            System.out.print(arr[i] + " ");
        }
    }
    
}
