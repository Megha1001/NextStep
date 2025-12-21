package sorting.meetingMaximumGuest;

import java.util.Arrays;


/*
 * TIME COMPLEXITY : O(NlogN) --> sorting
 */

public class EfficientSolution {


    public static void main(String args[]){
        int arr[] = {900,600,700};
        int dep[] = {1000,800,730};

        System.out.println("Maximum guest that we can meet is : "+findMaxGuestCount(arr, dep, arr.length));
    }

    public static int findMaxGuestCount(int arr[], int dep[], int n){

        Arrays.sort(arr);
        Arrays.sort(dep);

        int i=1;
        int res = 1;
        int curr= 1;
        int j=0;


        while(i<n && j<n){
            if(arr[i] <= dep[j]){
                ++curr;
                ++i;
            }else {
                --curr;
                ++j;
            }

            res = Math.max(res, curr);
        }

        return res;

    }
    
}
