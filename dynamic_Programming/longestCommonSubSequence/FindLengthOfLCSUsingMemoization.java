package dynamic_Programming.longestCommonSubSequence;

public class FindLengthOfLCSUsingMemoization {

    public static void main(String args[]){
        String s1 = "ABX";
        String s2 = "ABC";

        System.out.println("The length of LCS is : "+findLengthOfLCS(s1, s2, s1.length(), s2.length()));
    }
    
}
