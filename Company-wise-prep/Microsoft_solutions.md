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
        IDEA : To find the minimum cost at i = cost[i] + Math.min(min cost to reach at end from [i+1], min cost to reach at end from [i+2])
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

SPace optimized

```
public int minCostClimbingStairs(int[] cost) {

        /*
        Minimum cost from i to reach end is = cost[i] + Math.min(min cost to reach at end from i+1, min cost to reach at end from i+2)
        */
        int n = cost.length;
        int prev1 = cost[n-1];
        int prev2 = 0; //top position

        for(int i = n-2; i >= 0; i--){
            int temp = cost[i] + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = temp;
        }

        return Math.min(prev1, prev2);
        
    }
```


3. House Robber

```
class Solution {
    public int rob(int[] nums) {
        /*
        At every house at index i we can have two choices
            -> rob that house + best up to i-2
            -> not robbing it -> best upto i-1
        */

        int rob1 = 0;
        int rob2 = 0;

        for(int n : nums){
            int temp = Math.max(rob1 + n, rob2);
            rob1 = rob2;
            rob2 = temp;
        }
        return rob2;
    }
}
```

4. House Robber - ||
```
class Solution {
    public int rob(int[] nums) {
        /*
        Logic : we can't select first and last 
        max(houseRobber(nums, 0, n-2), houseRobber(nums, 1, n-1))
        */
        int n = nums.length;
        if(n == 1){
            return nums[0];
        }

        return Math.max(houseRobber1(nums, 0, n-2), houseRobber1(nums, 1, n-1));
    }

    private int houseRobber1(int[]nums, int start, int end){
        int rob1 = 0;
        int rob2 = 0;

        for(int i = start; i <= end; i++){
            int temp = Math.max(rob1+nums[i], rob2);
            rob1 = rob2;
            rob2 = temp;
        }
        return rob2;
    }
}
```