package dynamic_Programming.longestCommonSubSequence;

import java.util.Arrays;
/*
 * TIME COMPLEXITY : Theta(M*N)
 * Every element of memo array is compute once -> Number of elements : Theta(M*N)
 *  and we are doing constant work for each call
 * 
 * For Naive solution : without memoization : TC : O(2^N)
 */

public class FindLengthOfLCSUsingMemoization {

    public static int memo[][];

    public static void main(String args[]){
        String s1 = "ABX";
        String s2 = "ABC";

        //The memo array is of size (m + 1) × (n + 1) so that we can represent empty prefixes of the strings and safely handle the base cases.
        memo = new int[s1.length()+1][s2.length()+1];

        for(int i=0; i<s1.length()+1; i++){
            Arrays.fill(memo[i], -1);
        }

        System.out.println("The length of LCS is : "+findLengthOfLCS(s1, s2, s1.length()-1, s2.length()-1));
    }

    public static int findLengthOfLCS(String s1, String s2, int m, int n){

        if(memo[m][n] != -1){
            return memo[m][n];
        }

        if(m==0 || n==0){
            memo[m][n] = 0;
        }
        else {
            if(s1.charAt(m-1) == s2.charAt(n-1)){
                memo[m][n] = 1 + findLengthOfLCS(s1, s2, m-1, n-1);
            }
            else {
                memo[m][n] = Math.max(findLengthOfLCS(s1, s2, m-1, n), findLengthOfLCS(s1, s2, m, n-1));
            }
        }

        return memo[m][n];


    }
    
}
