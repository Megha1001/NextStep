package hashing.longestConsecutiveSequence;

import java.util.Arrays;

/*
 * TIME COMPLEXITY : O(NlogN)
 * AUXILARY SPACE : O(1)
 */

public class NaiveSolution {

    public static void main(String args[]){
        // int arr[] = {7,8,5,10,9};
        // int arr[] = {1,9,3,4,2,20};
        // int arr[] = {8,20,7,30};
        // int arr[] = {20,30,40};
        int arr[] = {1, 2, 2, 3}; // IMP Input , we should ignore duplicate 2 and not reset the counter
        System.out.println("The longest consecutive sequence in given array is : "+findLongestConsequtiveSubSeqLength(arr, arr.length));
    }

    public static int findLongestConsequtiveSubSeqLength(int arr[], int n){
        int res = 1;

        //sort array
        Arrays.sort(arr);

        int count = 1;
        for(int i=1; i<n; i++){
            if(arr[i] - arr[i-1] ==1){
                ++count;
            }
            else if (arr[i]!=arr[i-1]){ // to ignore element in cases like : {2,3,3} , we want to ignore next 3
                /*
                 * | i | prev | curr | diff | count     | res | Explanation                 |
                   | - | ---- | ---- | ---- | --------- | --- | --------------------------- |
                   | 1 | 2    | 3    | 1    | 2         | 1   | consecutive                 |
                   | 2 | 3    | 3    | 0    | reset → 1 | 2   | ❌ duplicate breaks sequence |
                   | 3 | 3    | 4    | 1    | 2         | 2   | restarts                    |
                    final answer should be 3 not 2
                    2, 3, 4  → length = 3
                 */
                res = Math.max(res, count);
                count = 1;
            }
        }

        if(arr[n-1]- arr[n-2]==1){
            res = Math.max(res, count);
        }

        return res;
    }
    
}
