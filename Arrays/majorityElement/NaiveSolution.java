package Arrays.majorityElement;

/*
 * Majority Element : Its an element that appears more than n/2 times.
 * 
 * Time Complexity : O(N*N)
 * Auxiliary Space : Theta(1)
 */

public class NaiveSolution {

    public static void main(String args[]){
        int arr[] = {8,3,4,8,8};
        System.out.println("The index of majority of element appears in given array is : "+getIndexOfMajorityElement(arr, arr.length));
    }

    public static int getIndexOfMajorityElement(int arr[], int n){
        int res = -1;

        for(int i=0; i<n; i++){
            int currCount = 1;
            for(int j=i+1; j<n; j++){
                if(arr[j]==arr[i]){
                    ++currCount;
                }
            }
            if(currCount > n/2){
                return i;
            }
        }

        return res;
    }
    
}
