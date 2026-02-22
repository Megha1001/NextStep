## MICROSOFT — ALL QUESTIONS ORGANIZED BY PATTERN

---

### PATTERN 1: Dynamic Programming

1. Climbing Stairs

```
class Solution {
    public int climbStairs(int n) {
        /*
        IDEA : ways(n) = ways(n-1) + ways(n-2); To reach to n we can come from n-1 or n-2
        */

        //1. Recursion
        // return useRecursion(n);

        //use Top down approach - Memoization - Recurise
        // int memo[] = new int[n+1];
        // Arrays.fill(memo, -1);
        // return useTopDownApproach(n, memo);

        //use Bottom Up approach - Tabulation - Iterative
        // return useBottomUpApproach(n);

        //bottom up optimized
        return bottomUpOptimized(n);
    }

    private int bottomUpOptimized(int n){
        if(n <= 1){
            return 1;
        }

        int prev2 = 1; //dp[0]
        int prev1 = 1; //dp[1]

        for(int i = 2; i <= n; i++){
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    private int useBottomUpApproach(int n){
        if(n<=1){
            return 1;
        }
        int dp[] = new int[n+1];
        dp[0] = dp[1] = 1; //dp[i] represent how many distinct way to climb to ith stair
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    private int useTopDownApproach(int n, int[] memo){
        if(n <= 1){
            return 1;
        }
        if(memo[n] != -1){
            return memo[n];
        }

        memo[n] = useTopDownApproach(n-1, memo) + useTopDownApproach(n-2, memo);
        return memo[n];
    }

    //TLE
    private int useRecursion(int n){
        if(n==0 || n==1){
            return 1; // to reach n=0(where we are at intitial do nothing so 1 way)
        }

        return useRecursion(n-1) + useRecursion(n-2);
    }


}
```

2. Min Cost Climbing stairs

```
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        /*
        IDEA : To find the minimum cost at i = cost[i] + Math.min(cost[i+1], cost[i+2])
        */
        int n = cost.length;
        int dp[] = new int[n+1];
        dp[n] = 0;//explicitly
        dp[n-1] = cost[n-1];

        for(int i = n-2; i >= 0; i--){
            dp[i] = cost[i] + Math.min(dp[i+1], dp[i+2]);
        }
        

        return Math.min(dp[0], dp[1]);
    }
}
```