package sorting.searchInSortedRotatedArray;

/*
 * TIME COMPLEXITY : O(logN)
 * AUXILIARY SPACE : O(1)
 */

public class EfficientSolutionUsingBS {

    public static void main(String args[]){
        // int arr[] = {100,200,500,1000,2000,10,20};
        // int x = 900;
        // int arr[] = {100,500,10,20,30,40,50};
        // int x = 40;
        // int x = 500;
        int arr[] = {100,200,300,400,20,30,40};
        int x = 50;
        System.out.println("The index of element in given array is : "+findElementIdx(arr, 0, arr.length-1, x));
    }


    public static int findElementIdx(int arr[], int low, int high, int x){

        while(low <= high){
            int m = low + (high-low)/2;

            if(arr[m] == x){
                return m;
            }

            else if ( arr[m] >= arr[low]){ // left half sorted

                if(x < arr[m] && x >= arr[low]){
                    high = m-1;
                }else {
                    low = m+1;
                }

            }

            else if (arr[m] <= arr[high]){ //right half sorted
            
                if(x > arr[m] && x <= arr[high]){
                    low = m+1;
                }else{
                    high = m-1;
                }
            }

        }

        return -1;
    }
    
}
