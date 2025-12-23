package hashing.LongestCommonSpanWithSameSumInBinaryArrays;
/*
 * Problem : find the longest common subarray b/w two array with same sum and same starting and ending index
 * 
 * For Example : 
 * a1 : {0,1,0,0,0,0}
 *         -------
 * a2 : {1,0,1,0,0,1}
 *         -------
 * 
 * The underlined is common. Hence, ans = 4
 * 
 * 
 * TIME COMPLEXITY : Theta(N*N)
 * AUXILIARY SPACE : O(1)
 * 
 */

public class NaiveSolution {

    public static void main(String args[]){
        int a1[] = {0,1,0,0,0,0};
        int a2[] = {1,0,1,0,0,1};

        System.out.println("The length of longest common subarray with same sum and same start/end idx is : "+findLongestCommonSubArray(a1, a2, a1.length));
    }    

    public static int findLongestCommonSubArray(int a1[], int a2[], int n){

        int res = 0;

        for(int i=0; i<n; i++){
            int sum1=0, sum2=0;
            for(int j=i; j<n; j++){
                sum1 += a1[j];
                sum2 += a2[j];

                if(sum1 == sum2){
                    res = Math.max(res, j-i+1);
                }
            }
        }

        return res;

    }
}
