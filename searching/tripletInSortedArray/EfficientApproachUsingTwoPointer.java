package searching.tripletInSortedArray;

/*
 * IDEA : if X+Y+Z = K
 *  => X+Y = K-Z
 *  for every element call findPair(arr, K-Z)
 * 
 * TIME COMPLEXITY : O(N*N)
 * AUXILIARY SPACE : O(1)
 */

public class EfficientApproachUsingTwoPointer {

    public static void main(String args[]){
        int arr[] = {2,3,4,8,9,20,40};
        int x = 32;

        System.out.println("is triplet exists with given sum ? "+findTriplet(arr, arr.length, x));
    }


    public static boolean findTriplet(int arr[], int n , int k){
        for(int i=0; i<n-2; i++){
            if(findPair(arr,i++, k-arr[i])){
                return true;
            }
        }

        return false;
    }

    public static boolean findPair(int arr[], int start, int k){

        int i=start, j=arr.length-1; // START IS IMP !! HERE as you can't repeat i for which this function is being called

        while(i < j){
            int sum = arr[i] + arr[j];
            if(sum == k){
                return true;
            }

            if(sum > k){
                --j;
            }else {
                ++i;
            }
        }

        return false;

    }
    
}
