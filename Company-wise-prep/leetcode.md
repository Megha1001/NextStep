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

9. LC : 2940. Find Building Where Alice and Bob Can Meet
```
class Solution {

    
    private int[] constructST(int[]heights){
        int n = heights.length;
        int segmentTree[] = new int[4 * n];
        buildTree(0, 0, n - 1, segmentTree, heights);
        return segmentTree;
    }


    private void buildTree(int i, int l, int r, int[]segmentTree, int[]heights){
        if(l == r){
            segmentTree[i] = l;
            return;
        }

        int m = l + (r - l)/2;
        buildTree(2 * i + 1, l , m, segmentTree, heights);
        buildTree(2 * i + 2, m + 1 , r, segmentTree, heights);

        segmentTree[i] = 
        heights[segmentTree[2 * i + 1]] >= heights[segmentTree[2 * i + 2]] ? 
            segmentTree[2 * i + 1] 
            : segmentTree[2 * i + 2];
    }


    private int RMIQ(int[]segmentTree, int[]heights, int start, int end){
        return querySegmentTree(start, end, 0, 0, heights.length - 1, segmentTree, heights);
    }

    private int querySegmentTree(int start, int end, int i, int l, int r, int[]segmentTree, int[]heights){
        //out of bound
        if(r < start || end < l){
            return -1;
        }

        //in bound
        if(start <= l && r <= end){
            return segmentTree[i];
        }

        //overlap
        int mid = l + (r - l)/2;
        int leftIdx = querySegmentTree(start, end, 2 * i + 1, l, mid, segmentTree, heights);
        int rightIdx = querySegmentTree(start, end, 2 * i + 2, mid + 1, r, segmentTree, heights);

        if(leftIdx == -1) return rightIdx;
        if(rightIdx == -1) return leftIdx;

        return (heights[leftIdx] >= heights[rightIdx]) ? leftIdx : rightIdx;
    }

    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length;
        int [] segmentTree = constructST(heights);
        int[]result = new int[queries.length];

        for(int q = 0; q < queries.length; q++){
            int alice = Math.min(queries[q][0], queries[q][1]);
            int bob = Math.max(queries[q][0], queries[q][1]);

            if(alice == bob || heights[bob] > heights[alice]){
                result[q] = bob;
                continue;
            }


            int l = bob + 1;
            int r = n - 1;
            int resultIdx = Integer.MAX_VALUE;

            while(l <= r){
                int m = l + (r - l)/2;
                int idx = RMIQ(segmentTree, heights, l , m);
                if(heights[idx] > Math.max(heights[alice], heights[bob])){
                    r = m - 1;
                    resultIdx = Math.min(resultIdx, idx);
                }else{
                    l = m + 1;
                }
            }

            result[q] = resultIdx == Integer.MAX_VALUE ? -1 : resultIdx;

        }

        return result;   
    }
}
```

10. Undirected Graph Cycle

- By DFS
```
class Solution {
    public boolean isCycle(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }
        
        for(int edge[] : edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        boolean[]visited = new boolean[V];
        
        for(int i = 0; i < V; i++){
            if(!visited[i]){
                if(dfs(i, adj, -1, visited)){
                    return true;
                }
            }
            
        }
        return false;
    }
    
    private boolean dfs(int node, ArrayList<ArrayList<Integer>>adj, int parent, boolean[]visited){
        visited[node] = true;
        
        for(int neighbor: adj.get(node)){
            if(!visited[neighbor]){
                if(dfs(neighbor, adj, node, visited)){
                    return true;
                }
            }else if(neighbor != parent){
                return true;
            }
        }
        
        return false;
    }
}
```

- By BFS
```
class Solution {
    public boolean isCycle(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }
        
        for(int [] edge : edges){
            int u = edge[0];
            int v = edge[1];
            
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        boolean[] visited = new boolean[V];
        
        for(int i = 0; i < V; i++){
            if(!visited[i]){
                if(bfs(i, adj, visited)){
                    return true;
                }
            }
        }
        
        return false;
        
    }
    
    private boolean bfs(int node, ArrayList<ArrayList<Integer>>adj, boolean[]visited){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{node, -1}); //node, parent
        
        while(!q.isEmpty()){
            int [] curr = q.poll();
            int currNode = curr[0];
            int parent = curr[1];
            visited[currNode] = true;
            
            for(int neighbor : adj.get(currNode)){
                if(!visited[neighbor]){
                    q.offer(new int[]{neighbor, currNode});
                }else if(parent != neighbor){
                    return true;
                }
            }
        }
        
        return false;
    }
}
```

11. Detect Cycles in 2D Grid
```
//TC : O(rows * cols)
//SC : O(rows * cols)
class Solution {
    private int rows;
    private int cols;
    private boolean[][]visited;
    private int[][]directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public boolean containsCycle(char[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        visited = new boolean[rows][cols];

        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(!visited[r][c]){
                    if(dfs(grid, r, c, - 1, -1, grid[r][c])){
                        return true;
                    }
                }
            }
        }

        return false;
    }


    private boolean dfs(char[][]grid, int r, int c, int parentR, int parentC, char ch){
        visited[r][c] = true;

        for(int dir[] : directions){
            int nr = dir[0] + r;
            int nc = dir[1] + c;

            if(nr < 0 || nc < 0 || nr >= rows || nc >= cols || grid[nr][nc]!= ch){
                continue;
            }

            if(!visited[nr][nc]){
                if(dfs(grid, nr, nc, r, c, ch)){
                    return true;
                }
            }else {
                if(nr != parentR || nc != parentC){
                    return true;
                }
            }
        }
        return false;
    }
}
```
- BFS
```
class Solution {
    private int rows;
    private int cols;
    private boolean[][]visited;
    private int[][]directions = {{0, 1},{0, -1},{1, 0},{-1, 0}};
    public boolean containsCycle(char[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        visited  = new boolean[rows][cols];

        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(!visited[r][c]){
                    if(bfs(grid, r, c, grid[r][c])){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean bfs(char[][]grid, int row, int col, char ch){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{row, col, -1, -1}); // r, c, parentR, parentC
        visited[row][col] = true;
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int parentR = curr[2];
            int parentC = curr[3];

            for(int dir[] : directions){
                int nr = r + dir[0];
                int nc = c + dir[1];

                if(nr >=0 && nc >=0  && nr < rows && nc < cols && grid[nr][nc] == ch){
                    if(!visited[nr][nc]){
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc, r, c});
                    }else {
                        if(nr != parentR || nc != parentC){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
```

12. Directed Graph Cycle
```
class Solution {
    public boolean isCyclic(int V, int[][] edges) {
        //Kahn's algo
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }
        
        for(int edge[] : edges){
            adj.get(edge[0]).add(edge[1]);
        }
        
        //calculate indegree
        int[]indegree = new int[V];
        
        for(int r = 0; r < adj.size(); r ++){
            for(int c = 0; c < adj.get(r).size(); c++){
                ++indegree[adj.get(r).get(c)];
            }
        }
        
        
        Queue<Integer> q = new ArrayDeque<>();
        
        for(int i = 0; i < V; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }
        
        int count = 0;
        while(!q.isEmpty()){
            int curr = q.poll();
            ++count;
            
            for(int neighbor : adj.get(curr)){
                --indegree[neighbor];
                if(indegree[neighbor] == 0){
                    q.offer(neighbor);
                }
            }
        }
        
        return count == V ? false : true;
    }
}
```

12. Number of Provinces - LC : 547

```
class Solution {
    public int findCircleNum(int[][] isConnected) {
        //Using DFS - TC : O(N * N), SC : O(N)

        int n = isConnected.length;
        boolean visited[] = new boolean[n];
        int provinces = 0;

        for(int i = 0; i < n; i++){
            if(!visited[i]){
                dfs(isConnected, i, visited);
                ++provinces;
            }
        }
        return provinces;
    }

    private void dfs(int[][]isConnected, int r, boolean[]visited){
        visited[r] = true;

        //find connected component
        for(int i = 0; i < isConnected.length; i++){
            if(isConnected[r][i] == 1 && !visited[i]){
                dfs(isConnected, i, visited);
            }
        }
    }
}
```

- BFS
```
class Solution {
    public int findCircleNum(int[][] isConnected) {
        //Using BFS
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for(int i = 0; i < n; i++){
            if(!visited[i]){
                bfs(isConnected, visited, i);
                ++provinces;
            }
        }

        return provinces;
        
    }

    private void bfs(int[][]isConnected, boolean[]visited, int node){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(node);
        visited[node] = true;

        while(!q.isEmpty()){
            int curr = q.poll();

            for(int i = 0; i < isConnected.length; i++){
                if(isConnected[curr][i] == 1 && !visited[i]){
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }
    }
}
```

13. Course Schedule II

```
//TC : O(N*N)
//SC : O(N)
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }


        for(int[]edge: prerequisites){
            adj.get(edge[1]).add(edge[0]);
        }
        
        int[] indegree = new int[numCourses];
        for(int i = 0 ; i < adj.size(); i++){
            for(int j = 0; j < adj.get(i).size(); j++){
                ++indegree[adj.get(i).get(j)];
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }

        int res[] = new int[numCourses];
        int idx = 0;
        while(!q.isEmpty()){
            int curr = q.poll();
            res[idx ++ ] = curr;

            for(int u : adj.get(curr)){
                --indegree[u];
                if(indegree[u] == 0){
                    q.offer(u);
                }
            }
        }

        return idx == numCourses ? res : new int[]{};
    }
}
```


14. Maximum Average Subarray I
```
class Solution {
    public double findMaxAverage(int[] nums, int k) {

        double res = Integer.MIN_VALUE;
        double sum = 0;

        //for first k element
        for(int i = 0; i < k; i++){
            sum += nums[i];
        }

        res = sum;

        //sliding window
        for(int i = k ; i < nums.length; i++){
            sum = (sum + nums[i] - nums[i-k]);
            res = Math.max(sum, res);
        }

        return res/k;
        
    }
}
```


15.  Is Graph Bipartite?
```
//TC : O(V+E)
//SC : O(V)
class Solution {
    public boolean isBipartite(int[][] graph) {
        //Idea : if cycle of odd length exists - not a Bipartite
        int n = graph.length;
        int color[] = new int[n];
        Arrays.fill(color, -1);

        for(int i = 0; i < n; i++){
            if(color[i] == - 1){
                if(!dfs(graph, i, 0, color)){
                    return false;
                }
            }
        }
        
        return true;
    }

    private boolean dfs(int[][]graph, int curr, int currColor, int[]color){
        color[curr] = currColor;

        for(int nei : graph[curr]){
            if(color[nei] == -1){
                if(!dfs(graph, nei, 1 - currColor, color)){
                    return false;
                }
            }
            else if(currColor == color[nei]){
                return false;
            }
        }

        return true;
    }
}
```

```
class Solution {
    public boolean isBipartite(int[][] graph) {

        //BFS
        int n = graph.length;
        int color[] = new int[n];
        Arrays.fill(color, -1);

        for(int i = 0; i < n; i++){
            if(color[i] == -1){
                if(!bfs(graph, i, 0, color)){
                    return false;
                }
            }
        }
        
        return true;
    }

    private boolean bfs(int[][]graph, int curr, int currColor, int[]color){
        color[curr] = currColor;
        
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(curr);

        while(!q.isEmpty()){
            int node = q.poll();

            for(int nei : graph[node]){
                if(color[nei] == -1){
                    color[nei] = 1 - color[node];
                    q.offer(nei);
                }else if(color[nei] == color[node]){
                    return false;
                }
            }
        }

        return true;
    }
}
```

16. Detect cycle in undirected graph : https://www.geeksforgeeks.org/problems/detect-cycle-using-dsu/1
```
//TC : O(V+E)
//SC : O(V)
class Solution {
    static class DSU{
        int parent[];
        int rank[];
        
        public DSU(int n){
            parent = new int[n];
            rank = new int[n];
            
            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
        }
        
        private int find(int x){
            if(parent[x] == x){
                return x;
            }
            
            return parent[x] = find(parent[x]);
        }
        
        private boolean union(int x, int y){
            int px = find(x);
            int py = find(y);
            
            if(px == py){
                return false;
            }
            
            if(rank[px] > rank[py]){
                parent[py] = px;
            }else if(rank[py] > rank[px]){
                parent[px] = py;
            }else{
                parent[px] = py;
                ++rank[py];
            }
            
            return true;
        }
    }
    public boolean detectCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        //DSU
        
        DSU dsu = new DSU(V);
        
        
        for(int i = 0; i < adj.size(); i++){
            for(int neighbor : adj.get(i)){//to process only u -> v not v -> u
                if(i < neighbor){
                    if(!dsu.union(i, neighbor)){
                        return true;
                    }
                }
            }
        }
    
        
        return false;
    }
}
```

17.  Satisfiability of Equality Equations

```
class Solution {
    static class DSU{
        private int[] parent;
        private int[] rank;

        DSU(int n){
            parent = new int[n];
            rank = new int[n];

            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
        }

        private int find(int i){
            if(parent[i] == i){
                return i;
            }

            return parent[i] = find(parent[i]); // path compression
        }

        private void union(int x, int y){
            int px = find(x);
            int py = find(y);

            if(px == py){
                return;
            }

            if(rank[px] > rank[py]){
                parent[py] = px;
            }else if(rank[px] < rank[py]){
                parent[px] = py;
            }else{
                parent[px] = py;
                ++rank[py];
            }
        }
    }
    public boolean equationsPossible(String[] equations) {

        DSU dsu = new DSU(26);

        for(String eq : equations){
            if(eq.charAt(1) == '='){
                int x = eq.charAt(0) - 'a';
                int y = eq.charAt(3) - 'a';
                dsu.union(x, y);                
            }
        }

        for(String eq : equations){
            if(eq.charAt(1) == '!'){
                int x = eq.charAt(0) - 'a';
                int y = eq.charAt(3) - 'a';
                if(dsu.find(x) == dsu.find(y)){
                    return false;
                }
            }
        }

        return true;
        
    }
}
```

18. Number of Operations to Make Network Connected
```
class Solution {

    static class DSU{
        private int parent[];
        private int rank[];

        DSU(int n){
            parent = new int[n];
            rank = new int[n];

            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
        }

        private int find(int i){
            if(parent[i] == i){
                return i;
            }

            return parent[i] = find(parent[i]);
        }

        private void union(int x, int y){
            int px = find(x);
            int py = find(y);

            if(px == py){
                return;
            }

            if(rank[px] > rank[py]){
                parent[py] = px;
            }else if(rank[py] > rank[px]){
                parent[px] = py;
            }else{
                parent[py] = px;
                ++rank[px];
            }
        }
    }

    public int makeConnected(int n, int[][] connections) {

        if(connections.length < n-1){
            return -1;
        }
        
        //DSU

        DSU dsu = new DSU(n);
        int components = n;
        int extraEdge = 0;

        for(int edge[] : connections){
            int u = edge[0];
            int v = edge[1];

            if(dsu.find(u) == dsu.find(v)){
                ++extraEdge;
            }else{
                dsu.union(u, v);
                --components;
            }
        
        }
        int neededEdges = components - 1;
        return extraEdge >= neededEdges ? neededEdges : -1;
        
    }
}
```

19. Count Unreachable Pairs of Nodes in an Undirected Graph
```
//TC: O(n + m)
//SC: O(n + m)
class Solution {
    public long countPairs(int n, int[][] edges) {
        //DFS
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }

        for(int edge[] : edges){
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        long result = 0;
        long seen = 0;

        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                long size = dfs(i, adj, visited);
                result += seen * size;
                seen += size;        
            }
        }
        
        return result;
    }

    private long dfs(int i, ArrayList<ArrayList<Integer>>adj, boolean[]visited){
        visited[i] = true;
        int size = 1;

        for(int nei : adj.get(i)){
            if(!visited[nei]){
                size += dfs(nei, adj, visited);
            }
        }

        return size;
    }
}
```

import java.util.*;

class Solution {
    public int[] dijkstra(int V, ArrayList<ArrayList<int[]>> adj, int source) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        dist[source] = 0;
        pq.offer(new int[]{0, source});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currDist = curr[0];
            int node = curr[1];

            if (currDist > dist[node]) {
                continue;
            }

            for (int[] edge : adj.get(node)) {
                int neighbor = edge[0];
                int weight = edge[1];

                if (currDist + weight < dist[neighbor]) {
                    dist[neighbor] = currDist + weight;
                    pq.offer(new int[]{dist[neighbor], neighbor});
                }
            }
        }

        return dist;
    }

}


20. Dijkstra Algorithm
```
class Solution {
    public int[] dijkstra(int V, int[][] edges, int src) {
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        
        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }
        
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adj.get(u).add(new int[]{v, wt});
            adj.get(v).add(new int[]{u, wt});
        }
        
        int[] dis = new int[V];
        Arrays.fill(dis, Integer.MAX_VALUE);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        dis[src] = 0;
        pq.offer(new int[]{0, src}); //distance, node;
        
        while(!pq.isEmpty()){
            int curr[] = pq.poll();
            int currDist = curr[0];
            int node = curr[1];
            
            if(currDist > dis[node]){
                continue;
            }
            
            for(int[] nei : adj.get(node)){
                int nextNode = nei[0];
                int weight = nei[1];
                if(currDist + weight < dis[nextNode]){
                    dis[nextNode] = currDist + weight;
                    pq.offer(new int[]{dis[nextNode], nextNode});
                }
            }
        }
        
        return dis;
        
        
    }
}
```


using TreeSet
```
class Solution {
    public int[] dijkstra(int V, int[][] edges, int src) {
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        
        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }
        
        
        for(int edge[] : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            
            adj.get(u).add(new int[]{v, wt});
            adj.get(v).add(new int[]{u, wt});
        }
        
        int res[] = new int[V];
        Arrays.fill(res, Integer.MAX_VALUE);
        TreeSet<int[]>set = new TreeSet<>((a,b)->{
            if(a[0] != b[0]){
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        
        res[src] = 0;
        set.add(new int[]{0, src}); //distance, node;
        
        while(!set.isEmpty()){
            int curr[] = set.pollFirst();
            int currDist = curr[0];
            int node = curr[1];
            
            for(int nei[] : adj.get(node)){
                int nextNode = nei[0];
                int dist = nei[1];
                
                if(currDist + dist < res[nextNode]){
                    if(res[nextNode] != Integer.MAX_VALUE){
                        set.remove(new int[]{res[nextNode], nextNode});
                    }
                    res[nextNode] = currDist + dist;
                    set.add(new int[]{res[nextNode], nextNode});
                }
                
            }
        }
        return res;
    }
}
```

21. Shortest Path from 1 to n in a Graph
```
class Solution {
    public List<Integer> shortestPath(int n, int m, int edges[][]) {
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        
        for(int i = 0; i <=n; i++){
            adj.add(new ArrayList<>());
        }
        
        for(int[]edge : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            
            adj.get(u).add(new int[]{v, wt});
            adj.get(v).add(new int[]{u, wt});
        }
        
        int dist[] = new int[n + 1];
        int parent[] = new int[n + 1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for(int i = 1; i <=n; i++){
            parent[i] = i;
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        dist[1] = 0;
        pq.add(new int[]{0, 1}); //dist, node;
        
        while(!pq.isEmpty()){
            int curr[] = pq.poll();
            int currDist = curr[0];
            int currNode = curr[1];
            
            if(currDist > dist[currNode]){
                continue;
            }
            
            for(int [] edge : adj.get(currNode)){
                int neighbor = edge[0];
                int weight = edge[1];
                
                if(currDist + weight < dist[neighbor]){
                    dist[neighbor] = currDist + weight;
                    parent[neighbor] = currNode;
                    pq.offer(new int[]{dist[neighbor], neighbor});
                }
            }
        }
        
        if(dist[n] == Integer.MAX_VALUE){
            return new ArrayList<>(Arrays.asList(-1));
        }
        
        ArrayList<Integer> path = new ArrayList<>();
        int node = n;
        
        while(parent[node] != node){
            path.add(node);
            node = parent[node];
        }
        
        path.add(1);
        Collections.reverse(path);
        path.add(0, dist[n]);
        
        return path;
    }
}
```

22. Network Delay Time
```
//TC : O(E logV)
//SC : O(E + V)
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();

        for(int i = 0; i <= n; i++){
            adj.add(new ArrayList<>());
        }

        for(int[]time : times){
            int u = time[0];
            int v = time[1];
            int w = time[2];

            adj.get(u).add(new int[]{v, w});
        }
        
        int[] dis = new int[n + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        dis[k] = 0;
        pq.add(new int[]{0, k}); //distance, node;

        while(!pq.isEmpty()){
            int [] curr = pq.poll();
            int currTime = curr[0];
            int node = curr[1];

            if(currTime > dis[node]){
                continue;
            }

            for(int [] nei : adj.get(node)){
                int neigNode = nei[0];
                int neigTime = nei[1];
                if(neigTime + currTime < dis[neigNode]){
                    dis[neigNode] = neigTime + currTime;
                    pq.offer(new int[]{dis[neigNode], neigNode});
                }
            }
        }


        int result = 0;
        for(int i = 1; i <= n; i ++){
            if(dis[i] == Integer.MAX_VALUE){
                return -1;
            }
            result = Math.max(result, dis[i]);
        }

        return result;
    }
}
```

23. Shortest Path in Binary Matrix
```
//TC : O(N*N)
//SC : O(N*N)
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n - 1][n - 1] == 1){
            return -1;
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0});
        grid[0][0] = 1;

        int path = 1;
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int curr[] = q.poll();
                int r = curr[0];
                int c = curr[1];

                if(r == n - 1 && c == n - 1){
                    return path;
                }

                for(int [] dir : directions){
                    int nr = dir[0] + r;
                    int nc = dir[1] + c;

                    if(nr >= 0 && nc >= 0 && nr < n && nc < n && grid[nr][nc] == 0){
                        grid[nr][nc] = 1;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
            ++path;
        }

        return -1;
        
    }
}
```

24. Path with Minimum Effort
```
//TC : O(R * C * log(R * C));
//SC : O(R * C)
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int r = heights.length;
        int c = heights[0].length;

        int[][]effort = new int[r][c];

        for(int i = 0; i < r; i++){
            Arrays.fill(effort[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0,0,0}); //effort, row, col
        effort[0][0] = 0;

        int directions[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while(!pq.isEmpty()){
            int curr[] = pq.poll();
            int currEffort = curr[0];
            int row = curr[1];
            int col = curr[2];

            if(row == r - 1 && col == c - 1){
                return currEffort;
            }
            
            if(currEffort > effort[row][col]){
                continue;
            }

            for(int dir[] : directions){
                int nr = dir[0] + row;
                int nc = dir[1] + col;

                if(nr < 0 || nc < 0 || nr >= r || nc >= c){
                    continue;
                }

                int edgeEffort = Math.abs(heights[nr][nc] -  heights[row][col]);
                int newEffort = Math.max(edgeEffort, currEffort);

                if(newEffort < effort[nr][nc]){
                    effort[nr][nc] = newEffort;
                    pq.offer(new int[]{newEffort, nr, nc});
                }
            }
        }

        return 0;
        
    }
}
```


25. Bellman-Ford : https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1

```
//TC : O(V*E)
//SC : O(V)
class Solution {
    public int[] bellmanFord(int V, int[][] edges, int src) {
        int dist[] = new int[V];
        Arrays.fill(dist, (int)1e8);
        
        dist[src] = 0;
        
        for(int i = 0; i < V-1; i++){
            for(int [] edge : edges){
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];
                
                if(dist[u] != (int)1e8 && dist[u] + wt < dist[v]){
                    dist[v] = dist[u] + wt;
                }
            }
        }
        
        for(int [] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            
            if(dist[u] != (int)1e8 && dist[u] + wt < dist[v]){
                return new int[]{-1};
            }
        }
        
        return dist;
        
        
    }
}
```

26. Remove Element
```
class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != val){
                nums[k] = nums[i];
                ++k;
            }
        }

        return k;
        
    }
}
```

27. Kth Distinct String in an Array
```
class Solution {
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> map = new HashMap<>();
        for(String s : arr){
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        for(String s : arr){
            if(map.get(s) == 1){
                --k;
                if(k==0){
                    return s;
                }
            }
        }

        return "";
    }
}
```

28. Floyd Warshall Algorith
```
class Solution {
    public void floydWarshall(int[][] dist) {
        int INF = 100000;
        int n = dist.length;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(dist[i][j] == -1){
                    dist[i][j] = INF;
                }
            }
        }
        
        
        for(int via = 0; via < n; via++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(dist[i][via] + dist[via][j] < dist[i][j]){
                        dist[i][j] = dist[i][via] + dist[via][j];
                    }
                }
            }
        }
        
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(dist[i][j] == INF){
                    dist[i][j] = -1;
                }
            }
        }
        
    }
}
```

GFG
```
class Solution {
    public void floydWarshall(int[][] dist) {
        int INF = (int)1e8;
        int n = dist.length;

        for (int via = 0; via < n; via++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if (dist[i][via] != INF &&
                        dist[via][j] != INF &&
                        dist[i][via] + dist[via][j] < dist[i][j]) {
                        dist[i][j] = dist[i][via] + dist[via][j];
                    }
                }
            }
        }
    }
}
```

28. Minimum Spanning Tree
```
//using Prim's algorithm

```
class Solution {
    public int spanningTree(int V, int[][] edges) {
        
        ArrayList<ArrayList<int[]>>adj = new ArrayList<>();
        
        for(int i =0; i < V; i++){
            adj.add(new ArrayList<>());
        }
        
        for(int[]edge : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            
            adj.get(u).add(new int[]{wt, v});
            adj.get(v).add(new int[]{wt, u});
        }
        
       PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
       boolean isMST [] = new boolean[V];
       minHeap.offer(new int[]{0, 0}); //wt, node
       
       int res = 0;
       
       while(!minHeap.isEmpty()){
           int curr [] = minHeap.poll();
           int wt = curr[0];
           int node = curr[1];
           
           if(isMST[node]) continue;
           isMST[node] = true;
           res += wt;
           for(int[]neig : adj.get(node)){
               int neig_wt = neig[0];
               int neig_node = neig[1];
               if(!isMST[neig_node]){
                   minHeap.offer(new int[]{neig_wt, neig_node});
               }
           }
       }
       
       return res;
        
    }
}
```


29. Count Strongly Connected

```
class Solution {
    // Function to find number of strongly connected components in the graph
    public int kosaraju(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }
        
        for(int[]edge : edges){
            int u = edge[0];
            int v = edge[1];
            
            adj.get(u).add(v);
        }
        
        //Step 1 : fill the stack in order of completion time
        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[V];
        
        for(int i = 0; i < V; i++){
            if(!visited[i]){
                dfsFill(i, adj, visited, st);
            }
        }
        
        
        //Step 2 : Create reverse graph
        List<List<Integer>> reverseAdj = new ArrayList<>();
        for(int i = 0; i < V; i++){
            reverseAdj.add(new ArrayList<>());
        }
        
        for(int u = 0; u < V; u++){
            for(int v : adj.get(u)){
                reverseAdj.get(v).add(u);
            }
        }
        
        
        Arrays.fill(visited, false);
        int sccCount = 0;
        
        while(!st.isEmpty()){
            int node = st.pop();
            if(!visited[node]){
                dfsTraversal(node, reverseAdj, visited);
                ++sccCount;
            }
        }
        
        return sccCount;
    }
    
    private void dfsTraversal(int node, List<List<Integer>>reverseAdj, boolean [] visited){
        visited[node] = true;
        for(int u : reverseAdj.get(node)){
            if(!visited[u]){
                dfsTraversal(u, reverseAdj, visited);
            }
        }
    }
    
    private void dfsFill(int u, List<List<Integer>>adj, boolean[]visited, Stack<Integer>st){
        visited[u] = true;
        
        for(int v : adj.get(u)){
            if(!visited[v]){
                dfsFill(v, adj, visited, st);
            }
        }
        
        st.push(u);
    }
}
```

30. Euler Path and Circuit

```
class Solution {
	public int isEulerCircuit(int V, int[][] adj) {
		
		if (!isConnected(adj, V)) {
			return 0;
		}
		
		int oddCount = 0;
		for (int i = 0; i < V; i++) {
			if (adj[i].length % 2 != 0) {
				++oddCount;
			}
		}
		
		if (oddCount > 2) {
			return 0;
		}
		
		if (oddCount == 2) {
			return 1;
		}
		
		return 2;
		
	}
	
	private boolean isConnected(int[][] adj, int V) {
		boolean[] visited = new boolean[V];
		
		int nonZeroDegreeVertex = -1;
		
		for (int i = 0; i < V; i++) {
			if (adj[i].length != 0) {
				nonZeroDegreeVertex = i;
				break;
			}
		}
		
		if (nonZeroDegreeVertex == -1) {
            return true;
        }
		
		dfs(adj, nonZeroDegreeVertex, visited);
		
		for (int i = 0; i < V; i++) {
			if (!visited[i] && adj[i].length > 0) {
				return false;
			}
		}
		
		return true;
	}
	
	private void dfs(int[][] adj, int u, boolean []visited) {
		visited[u] = true;
		for (int i = 0; i < adj[u].length; i++) {
			int neighbor = adj[u][i];
			if (!visited[neighbor]) {
				dfs(adj, neighbor, visited);
			}
		}
	}
}

```

31. Valid Arrangement of Pairs
```
class Solution {
    private Map<Integer, List<Integer>> adj = new HashMap<>();
    private List<Integer> eulerPath = new ArrayList<>();
    public int[][] validArrangement(int[][] pairs) {
        //Hierholzer's Algorithm

        // Step-1 : Build adjacency list and indegree out-degree
        Map<Integer, Integer> indegree = new HashMap<>();
        Map<Integer, Integer> outdegree = new HashMap<>();

        for(int edge[] : pairs){
            int u = edge[0];
            int v = edge[1];

            adj.computeIfAbsent(u, k->new ArrayList<>()).add(v);
            outdegree.put(u, outdegree.getOrDefault(u, 0) + 1);
            indegree.put(v, indegree.getOrDefault(v, 0) + 1);
        }

        // Step 2 : find starting point
        //outdegree[x] - indegree[x] = 1;
        int startNode = pairs[0][0];
        for(int node : adj.keySet()){
            if(outdegree.getOrDefault(node, 0) - indegree.getOrDefault(node, 0) == 1){
                startNode = node;
                break;
            }
        }

        // Step 3 : perform DFS
        dfs(startNode);
        
        // Step 4 : Build result
        Collections.reverse(eulerPath);
        int[][] result = new int[eulerPath.size() -1 ][2];
        for(int i = 0; i < eulerPath.size() - 1; i++){
            result[i][0] = eulerPath.get(i);
            result[i][1] = eulerPath.get(i + 1);
        }
        return result;
    }

    private void dfs(int node){
        while(adj.containsKey(node) && !adj.get(node).isEmpty()){
            int nextNode = adj.get(node).remove(adj.get(node).size() - 1);
            dfs(nextNode);
        }
        eulerPath.add(node);
    }
}
```

32. 

/*
If you choose a random node u and find the farthest node v then this node v will always be one end of the diameter

The farthest node from one end of the diameter is the other end of diameter
*/