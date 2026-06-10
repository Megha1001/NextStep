1. Fractional knapsack : https://www.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1
```
class Solution {
    class Item{
        int val;
        int weight;
        
        public Item(int val, int weight){
            this.val = val;
            this.weight = weight;
        }
    }
    public double fractionalKnapsack(int[] val, int[] wt, int capacity) {
        int n = val.length;
        List<Item> list = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            list.add(new Item(val[i], wt[i]));
        }
        
        //sort
        Collections.sort(list, (a,b)->{
           double r1 = (1.0 * a.val)/a.weight;
           double r2 = (1.0 * b.val)/b.weight;
           return Double.compare(r2, r1); //decending order
        });
        
        double res = 0.0;
        
        for(Item item : list){
            if(item.weight <= capacity){
                res += item.val;
                capacity -= item.weight;
            }else{
                res += (1.0 * item.val/item.weight ) * capacity;
                break;
            }
        }
        
        return res;
        
    }
}
```

2. o/1 Knapsack Problem : https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1
```
class Solution {
    private int n;
    private int dp[][];
    public int knapsack(int W, int val[], int wt[]) {
        n = val.length;
        dp = new int[n][W+1];
        
        for(int row[] : dp){
            Arrays.fill(row, -1);
        }
        
        
        return solve(0, W, val, wt);
    }
    
    private int solve(int i, int W, int []val, int[]wt){
        if(i >= n || W == 0){
            return 0;
        }
        
        if(dp[i][W] != -1){
            return dp[i][W];
        }
        
        int take = 0;
        
        if(wt[i] <= W){
            take = val[i] + solve(i + 1, W - wt[i], val, wt);
        }
        
        int skip = solve(i + 1, W, val, wt);
        
        return dp[i][W] = Math.max(take, skip);
    }
}
```

3. Subset sum problem : https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1
```
//TC : O(n* sum)
//SC : O(n* sum)
class Solution {
    private static Boolean[][] memo;
    static Boolean isSubsetSum(int arr[], int sum) {
        
        //memoization
        int n = arr.length;
        memo = new Boolean[n][sum + 1];
        return solve(n - 1, sum, arr);
    }
    
    private static boolean solve(int i, int target, int []arr){
        if(target == 0){
            return true;
        }
        
        if(i < 0){
            return false;
        }
        
        if(memo[i][target] != null){
            return memo[i][target];
        }
        
        boolean take = false;
        if(arr[i] <= target){
            take = solve(i - 1, target - arr[i], arr);
        }
        
        boolean skip = solve(i - 1, target, arr);
        
        return memo[i][target] = take || skip;
    }
}
```


```
class Solution {

    static Boolean isSubsetSum(int arr[], int sum) {
        // bottom up
        int n = arr.length;
        boolean dp [][] = new boolean[n + 1][sum + 1];
        
        for(int i = 0; i <= n; i++){
            dp[i][0] = true;
        }
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= sum; j++){
                boolean skip = dp[i-1][j];
                
                boolean take = false;
                if(arr[i-1] <= j){
                    take = dp[i-1][j - arr[i-1]];
                }
                
                dp[i][j] = take || skip;
            }
        }
        
        return dp[n][sum];
        
    }
}
```


3. Find K Pairs with Smallest Sums : https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/
```
//TC : O(kLogK)
//SC : O(K)
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //Using min Heap

        List<List<Integer>> res = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;

        if(m == 0 || n == 0 || k == 0){
            return res;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        ); //min Heap

        Set<String> visited = new HashSet<>(); //SC : O(k)
        pq.offer(new int[]{nums1[0] + nums2[0], 0, 0});
        visited.add("0#0");

        while(k -- > 0 && !pq.isEmpty()){ // TC : K times and for each k we're popping one element and inserting two -> log(size) = log(K) = Total : O(kLogK)
            int curr[] = pq.poll();

            int i = curr[1];
            int j = curr[2];

            res.add(Arrays.asList(nums1[i], nums2[j]));

            if(j + 1 < n){
                String key = i + "#" + (j + 1);
                if(!visited.contains(key)){
                    pq.offer(new int[]{nums1[i] + nums2[j + 1], i , j + 1});
                    visited.add(key);
                }
            }

            if(i + 1 < m){
                String key = (i + 1) + "#" + j;
                if(!visited.contains(key)){
                    pq.offer(new int[]{nums1[i + 1] + nums2[j], i + 1, j});
                    visited.add(key);
                }
            }
        }

        return res;
        
    }
}
```