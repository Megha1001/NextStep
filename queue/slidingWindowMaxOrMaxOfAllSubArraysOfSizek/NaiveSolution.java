package queue.slidingWindowMaxOrMaxOfAllSubArraysOfSizek;

/*
 * TIME COMPLEXITY : O(n*K)
 * AUXILIARY SPACE : O(1)
 */

public class NaiveSolution {

    public static void main(String args[]){
        int arr[] = {1,3,-1,3,5,3,6,7};
        int k = 3;
        System.out.println("Max of subarray of size K is : ");
        findAndPrintMax(arr, arr.length, k);

    }

    public static void findAndPrintMax(int arr[], int n, int k){

        for(int i=0; i<n-k+1; i++){
            int max = arr[i];
            for(int j=i+1; j<i+k; j++){
                max = Math.max(max, arr[j]);
            }
            System.out.print(max + " ");
        }

    }
    
}
