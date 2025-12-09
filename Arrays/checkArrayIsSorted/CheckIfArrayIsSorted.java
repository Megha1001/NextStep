package Arrays.checkArrayIsSorted;

/*
 * Time Complexity : O(N)
 */

public class CheckIfArrayIsSorted {


    public static void main(String args[]){
        int arr[] = {100,60,80};

        System.out.println("is the given array sorted ? : "+isSorted(arr));
    }

    public static boolean isSorted(int arr[]){

        for(int i=1; i<arr.length; i++){

            if(arr[i-1] > arr[i]){
                return false;
            }

        }
        return true;

    }
    
}
