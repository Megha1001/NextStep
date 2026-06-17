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


4. Find K Pairs with Smallest Sums : https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/
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

5. Merge K Sorted Linked Lists
```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if(n == 0){
            return null;
        }

        return partitionAndMerge(0, n - 1, lists);
    }

    private ListNode partitionAndMerge(int s, int e, ListNode[]lists){
        if(s == e){
            return lists[s];
        }

        int m = s + (e - s)/2;

        ListNode l1 = partitionAndMerge(s, m, lists);
        ListNode l2 = partitionAndMerge(m + 1, e, lists);

        return mergeTwoList(l1, l2);
    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                dummy.next = l1;
                l1 = l1.next;
            }else{
                dummy.next = l2;
                l2 = l2.next;
            }
            dummy = dummy.next;
        }
        dummy.next = l1 != null ? l1 : l2;

        return head.next;
    }
}
```

6. Range Sum Query - Mutable
```
class NumArray {
    private int [] segTree;
    private int numlen;

    public NumArray(int[] nums) {
        int n = nums.length;
        numlen = n;
        segTree = new int[4 * n];
        buildTree(0, 0, numlen - 1, nums);
    }

    //O(N)
    private void buildTree(int i, int l, int r, int[] nums){
        if(l == r){
            segTree[i] = nums[l];
            return;
        }

        int mid = l + (r - l)/2;
        buildTree(2 * i + 1, l, mid, nums);
        buildTree(2 * i + 2, mid + 1, r, nums);

        segTree[i] = segTree[2 * i + 1] + segTree[2 * i + 2];
    }
    
    //O(logN)
    public void update(int index, int val) {
        updateTree(index, val, 0, 0, numlen - 1);
    }

    private void updateTree(int index, int val, int i, int l, int r){
        if(l == r){
            segTree[i] = val;
            return;
        }

        int mid = l + (r - l)/2;

        if(index <= mid){
            //go to left
            updateTree(index, val, 2 * i + 1, l, mid);
        }else{
            updateTree(index, val, 2 * i + 2, mid + 1, r);
        }

        segTree[i] = segTree[2 * i + 1] + segTree[2 * i + 2];
    }
    
    //O(logN)
    public int sumRange(int left, int right) {
        return query(0, 0, numlen - 1, left, right);
    }

    private int query(int i, int l, int r, int start, int end){
        //1. out of scope
        if(l > end || start > r){
            return 0;
        }

        //2. in bounds equal
        else if(start <= l &&  r <= end){
            return segTree[i];
        }

        else{ //overlapping
            int mid = l + (r - l)/2;
            return query(2 * i + 1, l, mid, start, end) + query(2 * i + 2, mid + 1, r, start, end);
        }
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
```

7. Sum of query II : https://www.geeksforgeeks.org/problems/sum-of-query-ii5310/1

```
// TC: O(n + q log n)
//SC : O(N)

class Solution {
    List<Integer> querySum(int n, int arr[], int q, int queries[]) {
        //Using segment Tree
        
        int [] segmentTree = new int[4 * n];
        
        buildTree(0, 0, n - 1, segmentTree, arr);
        
        List<Integer> result = new ArrayList<>();
        
        for(int i = 0; i < 2 * q; i += 2){
            int start = queries[i] - 1; // 1 based indexing;
            int end = queries[i + 1] - 1;
            
            result.add(queryTree(0, 0, n - 1, start, end, segmentTree));
        }
        return result;
    }
    
    private void buildTree(int i , int l, int r, int[]segTree, int[]arr){
        if(l == r){
            segTree[i] = arr[l];
            return;
        }
        int mid = l + (r - l)/2;
        buildTree(2 * i + 1, l, mid , segTree, arr);
        buildTree(2 * i + 2, mid + 1, r, segTree, arr);
        segTree[i] = segTree[2 * i + 1] + segTree[2 * i + 2];
    }
    
    private int queryTree(int i, int l, int r, int start, int end, int[] segTree){
        if(l > end || r < start){
            return 0;
        }
        
        else if(l >= start && r <= end){
            return segTree[i];
        }
        
        else{
            //overlapping
            int mid = l + (r - l)/2;
            return queryTree(2 * i + 1, l, mid, start, end, segTree) + queryTree(2 * i + 2, mid + 1, r, start, end, segTree);
        }
    }
}
```

8. Range Minimum Query : https://www.geeksforgeeks.org/problems/range-minimum-query/1
```
/* The functions which
builds the segment tree */
class GfG {
    static int st[];

    public static int[] constructST(int arr[], int n) {
        st = new int[4 * n];
        buildSegTree(0, 0, n-1, arr);
        return st;
    }
    
    private static void buildSegTree(int i, int l, int r, int[]arr){
        if(l == r){
            st[i] = arr[l];
            return;
        }
        int mid = l + (r - l)/2;
        buildSegTree(2 * i + 1, l, mid, arr);
        buildSegTree(2 * i + 2, mid + 1, r, arr);
        
        st[i] = Math.min(st[2 * i + 1], st[2 * i + 2]);
    }

    /* The functions returns the
      min element in the range
      from l and r */
    public static int RMQ(int st[], int n, int l, int r) {
        return query(0, 0, n - 1, l, r, st);
    }
    
    private static int query(int i , int l, int r, int start, int end, int[]st){
        if(l > end || r < start){
            return Integer.MAX_VALUE;
        }
        
        else if(start <= l && r <= end){
            return st[i];
        }
        
        int mid = l + (r - l)/2;
        return Math.min(query(2 * i + 1, l, mid, start, end, st), query(2 * i + 2, mid + 1, r, start, end, st));
    }
}
```
