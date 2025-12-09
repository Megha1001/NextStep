package Arrays.removeDuplicatesFromArray;

public class OneTraversalSolution {

    public static void main(String args[]){
        int arr [] = {10,20,30};
        int n = arr.length;
        int newLength = findSizeOfArray(arr,n);
        System.out.println("Size of array after removing duplicates is : "+newLength);
        printArray(arr, newLength);
    }


    public static int findSizeOfArray(int arr[], int n){
        
        int i = 0, j=1, curr=1;

        while(i < n-1){

            if(arr[i] != arr[j]){
                arr[curr] = arr[j];
                ++curr;
            }

            ++i;
            ++j;

        }

        return curr;
    }

    public static void printArray(int arr[], int n){
        for(int i=0; i<n; i++){
            System.out.print(arr[i] + " ");
        }
    }
    
}
