package hashing.subArrayWithGivenValue.prefixSumWithValueZero;

import java.util.HashSet;

/*
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(N)
 */

public class EfficientUsingHashSet {

    public static void main(String args[]){
        int arr[] = {-3,4,-3,-1,1};
        int sum = 0;

        System.out.println("is subarray exists with given sum : "+isSubArrayExists(arr, arr.length, sum));
    }


    public static boolean isSubArrayExists(int arr[], int n, int sum){
        HashSet<Integer> h = new HashSet<>();

        int prefix_sum = 0;

        for(int x : arr){
            prefix_sum += x;

            if(h.contains(prefix_sum)){
                return true;
            }

            if(prefix_sum == sum){
                return true; // import cases where whole array gives the required sum  [-3,2,1] or even [-3,2,1,4]
            }

            h.add(prefix_sum);

        }

        return false;

    }
    
}
