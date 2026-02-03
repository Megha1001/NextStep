package dynamic_Programming.coinChange;

public class CoinChangeDPSolution {

    public static void main(String args[]){
        int coins [] = {1,2,3};
        int sum = 4;
        System.out.println("Count number of ways : "+countWays(coins, coins.length, sum));
    }

    public static int countWays(int[]coins, int n, int s){
        //2d array since 2 params are changing
        int dp[][] = new int[n+1][s+1];

        //first column would be 1 -- row indicating sum and column coins -> for first column we need sum=0 that is possilb with every combination of coins
        for(int i = 0; i <= n; i++){
            dp[i][0] = 1;
        }

        //for first row, count = 0 and sum > 1 --> then no possibilty so 0
        for(int j = 1; j <= s; j++){
            dp[0][j] = 0; //bydefault in java its zero but explicitly for understanding
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= s ; j++){
                dp[i][j] = dp[i-1][j];//not considering -> decrementing num of coins by 1 and keeping sum as is

                //to prevent -ve index
                if(coins[i-1] <= j){
                    dp[i][j] += dp[i][j-coins[i-1]]; //consider --> number of coins same as we have infinity supply but sum will decrease
                }
            }
        }

        return dp[n][s];
    }
    
}
