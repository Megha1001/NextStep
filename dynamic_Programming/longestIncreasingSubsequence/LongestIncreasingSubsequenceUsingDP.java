package dynamic_Programming.longestIncreasingSubsequence;

import java.util.Arrays;

public class LongestIncreasingSubsequenceUsingDP {

    public static void main(String args[]){
        int nums[] = {10,9,2,5,3,7,101,18};

        System.out.println("The length of LIS is " + lengthOfLIS(nums));

    }

    public static int lengthOfLIS(int[]nums){
        int n = nums.length;
        int [] LIS = new int[n];

        Arrays.fill(LIS, 1);

        for(int i = n - 1; i >= 0; i--){
            for(int j = i + 1; j < n; j++){
                if(nums[i] < nums[j]){
                    LIS[i] = Math.max(LIS[i], 1 + LIS[j]);
                }
            }
        }

        return Arrays.stream(LIS).max().getAsInt();
    }
    
}
