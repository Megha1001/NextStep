package dynamic_Programming.maximumCuts;

public class MaximumCuts {

    public static void main(String args[]){
       int n = 5, a = 1, b = 2, c = 3;

       System.out.println("Maximum cuts : "+findMaxCuts(n, a, b, c));
    }


    public static int findMaxCuts(int n , int a, int b, int c){
        int dp[] = new int[n+1];
        dp[0] = 0; //dp[i] indicates how many pieces we can cut from input choices of length i

        for(int i = 1; i <= n; i++){
            dp[i] = -1; //not possible

            if(i - a >= 0) dp[i] = Math.max(dp[i], dp[i-a]);
            if(i - b >= 0) dp[i] = Math.max(dp[i], dp[i-b]);
            if(i - c >= 0) dp[i] = Math.max(dp[i], dp[i-c]);
            
            if(dp[i] != -1){
                ++dp[i]; //remaining best + cut that we just made
            }
        }

        return dp[n];

    }
    
}
