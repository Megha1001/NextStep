package sorting.tripletInSortedArray;

/*
 * TIME COMPLEXITY : O(N^3)
 * AUXILIARY SPACE : O(1)
 */

public class NaiveApproach {

    public static void main(String args[]){
        int arr[] = {2,3,4,8,9,20,40};
        int x = 32;

        System.out.println("is triplet exists with given sum ? "+findTriplet(arr, arr.length, x));
    }

    public static boolean findTriplet(int arr[], int n, int x){

        for(int i=0; i<n-2; i++){
            for(int j=i+1; j<n-1; j++){
                for(int k=j+1; k<n; k++){
                    if(arr[i]+arr[j]+arr[k] == x){
                        return true;
                    }
                }
            }
        }


        return false;
    }
    
}
