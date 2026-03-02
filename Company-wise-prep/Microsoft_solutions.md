## MICROSOFT — ALL QUESTIONS ORGANIZED BY PATTERN

---

### PATTERN 1: Dynamic Programming - 1D

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

5. Longest Palindrome substring
```
class Solution {
    public String longestPalindrome(String s) {
        String res = "";
        int resLength = 0;
        int n = s.length();

        for(int i = 0; i < n; i++){
            //for odd length
            int l = i;
            int r = i;

            while(l >= 0 && r <= n-1 && s.charAt(l) == s.charAt(r)){
                if(r - l + 1 > resLength){
                    resLength = r - l +1;
                    res = s.substring(l , r + 1);
                }
                --l;
                ++r;
            }

            //for even length
            l = i;
            r = i+1;
            while(l >= 0 && r <= n-1 && s.charAt(l) == s.charAt(r)){
                if(r - l + 1 > resLength){
                    resLength = r - l +1;
                    res = s.substring(l , r + 1);
                }
                --l;
                ++r;
            }
        }

        return res;
    }
}
```

6. Palindromic Substrings

```
class Solution {
    public int countSubstrings(String s) {
        //Flavour of longest palindrome substring
        int count = 0;
        int n = s.length();

        //for odd length
        for(int i = 0; i < n; i++){
            int l = i;
            int r = i;

            while(l >= 0 && r < n && s.charAt(l) == s.charAt(r)){
                ++count;
                --l;
                ++r;
            }

            //for even length
            l = i;
            r = i+1;
            while(l >= 0 && r < n && s.charAt(l) == s.charAt(r)){
                ++count;
                --l;
                ++r;
            }
        }

        return count;
        
    }
}
```

7. Decode Ways

```
class Solution {
    public int numDecodings(String s) {
        /*
        for i+1 means first digit and rest 
        i+2 means first two digit and rest
        */
        int n = s.length();
        int memo[] = new int[n];
        Arrays.fill(memo, -1);

        return dfs(s, 0, memo); //dp[i] = indicates the number of decoding ways from i
        
    }

    private int dfs(String s, int i, int []memo){
        if(i == s.length()){
            return 1;
        }

        if(s.charAt(i) == '0'){ // from i;[dp[i] = indicates the number of decoding ways from i]
            return 0;
        }

        if(memo[i] != -1){
            return memo[i];
        }

        memo[i] = dfs(s, i+1, memo); //first and rest
        if(i+1 < s.length()){
            if(s.charAt(i)=='1' || (s.charAt(i)=='2' && s.charAt(i+1) < '7')){
                memo[i] += dfs(s, i+2, memo);//first two characters and rest;
            }
        }

        return memo[i];
    }
}
```

8. Coin Change

```
class Solution {
    public int coinChange(int[] coins, int amount) {

        int dp[] = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0; //we need 0 coint to make amount of 0. dp[i] = x; we need x coins to make amount i

        for(int i = 1; i <= amount; i++){
            for(int coin : coins){
                if(i - coin >= 0){
                    dp[i] = Math.min(dp[i], 1 + dp[i-coin]);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
        
    }
}
```

8. Maximum Product Subarray

```
class Solution {
    public int maxProduct(int[] nums) {

        //Use Kadane
        /*
        Maintain two variables
            - curr_max = maximum product ending at this index
            - curr_min = minimum product ending at this index
            why we are maintaining curr_min -> if the curr element is -ve then the whole thing will become +ve
        */

        int curr_min = 1;
        int curr_max = 1;
        int res = nums[0];
        for(int n : nums){
            int temp = n * curr_max;
            //for handling zero we are considering n to reset
            curr_max = Math.max(Math.max(temp, curr_min * n), n);
            curr_min = Math.min(Math.min(temp, curr_min * n), n);
            res = Math.max(res, curr_max);
        }

        return res;
        
    }
}
```

9. Word break

```
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        /*
        For example : "neetcode", wordDict = ["neet", "leet", "code"];
        dp[] = new boolean[s.length()+1]
        dp[8] = true
        dp[7] = invalid length(i+w.length = 7+4 <= 8(s.length)-> not) = false;
        dp[6] = false
        dp[5] = false
        dp[4] = true
        dp[3] = false
        dp[2] = false
        dp[1] = false
        dp[0] = true = dp[i + w.length() for which that matched]

        return dp[0]
        */
        int n = s.length();
        boolean dp[] = new boolean[n+1];
        //base case
        dp[n] = true;

        for(int i = n - 1; i >= 0; i--){
            for(String w : wordDict){
                if((i + w.length() <= n) && (s.substring(i, i+w.length()).equals(w))){ // this is making sure that from i to i+w.length has matching string in wordDict
                    dp[i] = dp[i + w.length()]; //for rest after i+w.length
                } //dp[i]  = s.substring(i, i+w.length()).equals(w) && dp[i+w.length()];
                if(dp[i] == true){
                    break;
                }
            }
        }

        return dp[0];

    }
}
```

10. Longest Increasing Subsequence

```
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int LIS[] = new int[n];
        Arrays.fill(LIS, 1);

        for(int i = n-1; i >= 0; i--){
            for(int j = i; j < n ; j++){
                if(nums[j] > nums[i]){
                    LIS[i] = Math.max(LIS[i], 1 + LIS[j]);
                }
            }
        }

        return Arrays.stream(LIS).max().getAsInt();
        
    }
}
```

11. Partition equal subset sum

Brute Force : TLE
```
class Solution {
    public boolean canPartition(int[] nums) {

        /*
         Idea : find the sum of nums
            -> if sum %2 != 0 return false
            ->  target = sum/2; (two choices at every idx select it or not select it)
                target = sum/2
                    -> select ith value -> target - nums[i], i = i+1
                    -> not select ith value -> target, i = i+1
        */
        
        int sum = 0;
        for(int num : nums){
            sum += num;
        }

        if(sum % 2 != 0){
            return false;
        }

        return dfs(nums, 0, sum/2);
    }

    private boolean dfs(int nums[], int i, int target){
        if(target == 0){
            return true;
        }
        if(i >= nums.length){
            return false;
        }

        return dfs(nums, i+1, target - nums[i]) ||  //select
                dfs(nums, i+1, target); //not select
    }
}
```

DP(top down)
```
class Solution {
    private Boolean memo[][];
    public boolean canPartition(int[] nums) {
        int n = nums.length;

        int sum = 0;
        for(int num : nums){
            sum += num;
        }

        if(sum % 2 != 0){
            return false;
        }
        /*
        i can vary from 0 to n and target can vary from 0 to sum/2
        */
        memo = new Boolean[n][sum/2 + 1];

        return dfs(nums, 0, sum/2);
        
    }

    private boolean dfs(int []nums, int i , int target){
        if(i == nums.length){
            return target == 0;
        }

        if(target < 0){
            return false;
        }

        if(memo[i][target]!= null){ // to compare this we have declared Boolean not boolean
            return memo[i][target];
        }

        memo[i][target] = dfs(nums, i+1, target-nums[i]) || dfs(nums, i+1, target);

        return memo[i][target];
    }
}
```


### PATTERN 2: Dynamic Programming - 2D

1. Unique path

```
class Solution {
    //dp[i][j] = dp[i][j+1] + dp[i+1][j]
    public int uniquePaths(int m, int n) {

        int dp[][] = new int[m+1][n+1];
        dp[m-1][n-1] = 1;
        for(int i = m-1; i >=0; i--){
            for(int j = n-1; j>=0; j--){
                //to not to override dp[m-1][n-1], use +=
                dp[i][j] += dp[i+1][j] + dp[i][j+1];
            }
        }

        return dp[0][0];
        
    }
}
```

2. Longest common subsequence

```
class Solution {
    private int memo[][];
    public int longestCommonSubsequence(String text1, String text2) {
       int m = text1.length();
       int n = text2.length();

       memo = new int[m+1][n+1];

       for(int i = 0; i <= m; i++){
            Arrays.fill(memo[i], -1);
       }

       return dfs(text1, text2, m, n);
    }

    private int dfs(String text1, String text2, int m, int n){
        if(memo[m][n] != -1){
            return memo[m][n];
        }

        if(m == 0 || n == 0){
            memo[m][n] = 0;
        }

        else{
            if(text1.charAt(m-1) == text2.charAt(n-1)){
                memo[m][n] = 1 + dfs(text1, text2, m - 1, n - 1);
            }else{
                memo[m][n] = Math.max(dfs(text1, text2, m, n - 1), dfs(text1, text2, m - 1, n));
            }
        }

        return memo[m][n];
    }
}

```
Method -2 Tabulation
```
class Solution {

    //Method-2 Tabulation
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int dp[][] = new int[m+1][n+1]; //+1 to consider 00, 10, 01

        /*i =1 means the first character. dp[i][j] indicates common in first i of text1 and j characters of text they are not indexed but when we compare them in
        if(text1.charAt(i-1) == text2.charAt(j-1)){ --> here we need to consider index which is length - 1 or pos - 1;
        */
        for(int i = 1; i <= m; i++){ 
            for(int j = 1; j <= n; j++){
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i][j-1] , dp[i-1][j]);
                }
            }
        }

        return dp[m][n];
    }
}
```


3. Coin Change - II

```

Method - 1 : Bottom up approach
//bottom up
    public int change(int amount, int []coins){
        int n = coins.length;
        // Arrays.sort(coins);
        int dp[][] = new int[n+1][amount+1];
        /*
        dp[i][a] = Number of ways to make amount a using coins from i index onwards
        dp[i][a] = dp[i+1][a] + dp[i][a-coin[i]]; //skip or dont skip. help to remove duplicates as well
        */
        
        //populate first column with value 1
        /*
        dp[i][0] = there is always 1 way to make amount 0 , i.e. dont choose anything
        */

        for(int i = 0 ; i <= n; i++){
            dp[i][0] = 1;
        }


        for(int i = n-1; i >= 0; i--){
            for(int a = 0; a <= amount; a++){ //starting from 1 as for 0 we have for loop above
                dp[i][a] = dp[i+1][a]; //always skip
                if(a >= coins[i]){
                    dp[i][a] += dp[i][a - coins[i]]; //skip or consider
                }
            }
        }

        return dp[0][amount];

    }
```

Method - 2 : Top down approach

```
public int changeTopDown(int amount, int[] coins) {
        Arrays.sort(coins);
        int memo[][] = new int[coins.length + 1][amount + 1];

        for(int[]row : memo){
            Arrays.fill(row, -1);
        }
        return dfs(0, amount, coins, memo);
    }

    private int dfs(int i, int a, int coins[], int[][]memo){
        if(a == 0){
            return 1;
        }

        if(i >= coins.length){
            return 0;
        }

        if(memo[i][a]!=-1){
            return memo[i][a];
        }

        int res = 0;
        if(a >= coins[i]){
            res = dfs(i+1, a, coins, memo) + dfs(i, a - coins[i], coins, memo);
        }
        memo[i][a] = res;
        return res;
    }
```

4. Target Sum

```
class Solution {
    private int dp[][];
    private int totalSum;
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        for(int num : nums){
            totalSum += num;
        }

        dp = new int[n][2*totalSum+1]; //possible value of n : 0 to n, sum -totalSum to +totalSum

        for(int i = 0; i < n; i++){
            for(int j = 0; j < 2*totalSum+1; j++){
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        return backTrack(0, 0, nums, target);
    }

    private int backTrack(int i, int currSum, int[] nums, int target){
        if(i==nums.length){
            return currSum == target ? 1 :0;
        }

        if(dp[i][currSum + totalSum] != Integer.MIN_VALUE){ // -totalSum to +totalSum --> offesting from zero : 0 to 2*totalSum
            return dp[i][currSum + totalSum];
        }

        dp[i][currSum + totalSum] = backTrack(i+1, currSum + nums[i], nums, target) +
                                    backTrack(i+1, currSum - nums[i], nums, target);

        return dp[i][currSum + totalSum];
    }
}   
```

5. Interleaving strings

```
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        /*
        Merge thorem solution wont as when we are consider s1 even when the character matches in both s1 and s2
        => greedy wont work

        DP Problem
        dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1)) || (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1))
        */
        int m = s1.length();
        int n = s2.length();

        if(m+n != s3.length()){
            return false;
        }

        boolean dp [][] = new boolean[m+1][n+1]; //dp[i][j] tell us wheather first i characters of s1 and first j characters of s2 can form first i+j characters of s3
        dp[0][0] = true;
        
        //for first row
        for(int i = 1; i <= m; i++){
            dp[i][0] = dp[i-1][0] && s1.charAt(i-1)== s3.charAt(i-1);
        }

        //for first column
        for(int j = 1; j <= n; j++){
            dp[0][j] = dp[0][j-1] && s2.charAt(j-1) == s3.charAt(j-1);
        }

        //for rest
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                char c = s3.charAt(i+j-1);

                dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == c)
                            ||
                           (dp[i][j-1] && s2.charAt(j-1) == c);
            }
        }

        return dp[m][n];

    }
}
```

6. Edit Distance

```
class Solution {
    public int minDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        int dp[][] = new int[m+1][n+1];

        //for first column
        for(int i = 0 ; i <= m; i++){
            dp[i][0] = i;
        }

        //for first row
        for(int i = 0; i <= n; i++){
            dp[0][i] = i;
        }

        for(int i = 1 ; i <= m; i++){
            for(int j = 1; j<= n; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + Math.min(
                        Math.min(
                            dp[i][j-1], //insert
                            dp[i-1][j] //delete
                        ),
                        dp[i-1][j-1] //replace
                    );
                }
            }
        }
        

        return dp[m][n];
        
    }
}
```

### PATTERN 1: TWO POINTERS

1. Two Sum

```
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]), i};
            }

            map.put(nums[i], i);
        }

        return new int[]{-1,-1};
    }
}
```

2. Two Integer Sum II
```
class Solution {
    public int[] twoSum(int[] numbers, int target) {

        //Two Pointer
        int i = 0;
        int j = numbers.length - 1;

        while(i < j){
            int sum = numbers[i] + numbers[j];
            if(target == sum){
                return new int[]{i+1, j+1};
            }else if(target > sum){
                ++i;
            }else {
                --j;
            }
        }
        
        return new int[]{-1,-1};
    }
}
```

3. 3 Sum
```
class Solution {
    private List<List<Integer>> res;
    public List<List<Integer>> threeSum(int[] nums) {

        //to avoid duplicate triplets
        //sort , fix i then apply twoSum

        res = new ArrayList<>();
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++){
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }

            int left = i+1;
            int right = nums.length - 1;

            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];

                if(sum == 0){
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    //to avoid duplicates
                    while(left < right && nums[left] == nums[left + 1]) ++left;
                    while(left < right && nums[right] == nums[right - 1]) --right;

                    ++left;
                    --right;
                } else if(sum < 0){
                    ++left;
                }else {
                    --right;
                }
            }
        }

        return res;
        
    }
}
```