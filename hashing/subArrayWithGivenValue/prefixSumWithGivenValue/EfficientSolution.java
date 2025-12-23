package hashing.subArrayWithGivenValue.prefixSumWithGivenValue;


/*
 *  IDEA
 *      [a0,a1,a2,a3,........, ai-1, ai, ai+1, ai+2, ..........................]
 *       <--------prefix_sum-1---------><--------sum------------>
 *       <--------prefix_sum-2---------------------------------->
 * 
 * Search for prefix_sum - sum
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(N)
 */


import java.util.HashSet;

public class EfficientSolution {

    public static void main(String args[]){
        int arr[] = {5,8,6,13,3,-1};
        int sum = 22;
        System.out.println("is the subarray exists with given sum ? "+isSubArrayExists(arr, arr.length, sum));
    }
    

    public static boolean isSubArrayExists(int arr[], int n, int sum){
        HashSet<Integer> set = new HashSet<>();

        int prefix_sum = 0;

        for(int i=0; i<n; i++){
            prefix_sum += arr[i];

            if(set.contains(prefix_sum - sum)){
                return true;
            }

            if(prefix_sum == sum){
                return false;
            }

            set.add(prefix_sum);
        }

        return false;

    }

}
