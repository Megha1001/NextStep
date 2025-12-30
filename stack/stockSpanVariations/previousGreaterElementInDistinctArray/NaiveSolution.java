package stack.stockSpanVariations.previousGreaterElementInDistinctArray;

/*
 * 
 * TIME COMPLEXITY : O(N*N)
 * AUXILIARY SPACE : O(1)
 */


public class NaiveSolution { //elements are distinct

    public static void main(String args[]){
        int arr[] = {15, 10,18,12,4,6,2,8};
        System.out.println("Previous greater element for all the elements in array are : ");
        findPreviousGreater(arr, arr.length);
    }

    public static void findPreviousGreater(int arr[], int n){

        System.out.print(-1 + " "); //for first element

        for(int i=1; i<n; i++){
            int previousGreater = -1;
            for(int j=i-1; j>=0; j--){
                if(arr[j] > arr[i]){
                    previousGreater = arr[j];
                    break;
                }
            }
            System.out.print(previousGreater + " ");
        }

    }
    
}
