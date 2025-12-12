package Arrays.maxConsecutiveOneInBinaryArray;

/*
 * Time complexity : O(N*N)
 */

public class NaiveSolution {

    public static void main(String args[]){
        // int arr[] = {0,1,1,0,1,0};
        // int arr[] = {1,1,1,1};
        // int arr[] = {0,0,0};
        int arr[] = {1,0,1,1,1,1,0,1,1};
        int n = arr.length;
        System.out.println("Max consecutive 1's in given binary array is : "+findMaxOne(arr, n));
    }

    public static int findMaxOne(int arr[], int n){

        int max = 0;

        for(int i=0; i<n; i++){
            int currMax = 0;
            if(i>0 && arr[i-1]==1){
                continue; //already been taken care
            }

            for(int j=i; j<n; j++){
                
                if(arr[j]==1){
                    ++currMax;
                }else { //should be zero
                    max = Math.max(max, currMax);
                    currMax=0;
                }
            }
            max = Math.max(max, currMax);

        }

        return max;

    }
    
}
