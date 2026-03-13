## MICROSOFT — ALL QUESTIONS ORGANIZED BY PATTERN

---

### PATTERN 1: TWO POINTERS

**When to use:** Sorted arrays, pair/triplet problems, in-place operations, palindromes

| # | LC# | Problem | Diff | Freq | Logic |
|---|---|---|---|---|---|
| 1 | 1 | Two Sum | Easy | Very High | Use HashMap and compliment approach
| 2 | 167 | Two Sum II (Sorted Input) | Medium | Medium | Maintain two pointer -> i and j [if nums[i]+nums[j]= target return , if nums[i][j] > target then --j else ++i;]
| 3 | 15 | 3Sum | Medium | High | Sort + Fix i + Two-pointer 2Sum
| 4 | 16 | 3Sum Closest | Medium | Medium | Sort + Fix i + Two pointers + Track minimum [target - sum]
| 5 | 11 | Container With Most Water | Medium | High | Two pointers from both ends + Compute area using min height × width + Move the smaller height pointer inward.
| 6 | 42 | Trapping Rain Water | Hard | High | Precompute leftMax & rightMax. Water at i = min(leftMax[i], rightMax[i]) - height[i]. Sum for all i
| 7 | 283 | Move Zeroes | Easy | Very High | Maintain slow pointer at next non-zero position. Fast pointer scans array. If nums[fast] != 0 → swap(nums[slow], nums[fast]) and slow++
| 8 | 26 | Remove Duplicates from Sorted Array | Easy | Medium | Slow pointer marks next unique position. Fast pointer scans. If nums[fast] != previous unique → nums[slow++] = nums[fast]
| 9 | 75 | Sort Colors (Dutch National Flag) | Medium | High | Maintain 3 regions using low, mid, high. If 0 → swap(low++, mid++). If 1 → mid++. If 2 → swap(mid, high--)
| 10 | 88 | Merge Sorted Array | Easy | Very High | If array1 has buffer at end → Use 3 pointers from back. Compare nums1[m-1] & nums2[n-1]. Place larger at nums1[last]. Decrement pointer of chosen element + last--. Continue until one exhausts. Copy remaining nums2 if any.
| 11 | 125 | Valid Palindrome | Easy | Medium | Two pointers from both ends. Skip non-alphanumeric characters. Compare lowercase characters. If mismatch → false. Move l++ and r-- until pointers meet.
| 12 | 680 | Valid Palindrome II | Easy | Medium | Two pointers from ends. On first mismatch → try skipping left OR right once and check palindrome.
| 13 | 977 | Squares of a Sorted Array | Easy | Medium | Two pointers at both ends. Compare absolute values of nums[l] and nums[r]. Place larger square at end of result array. Move that pointer and decrement result index.
| 14 | 986 | Interval List Intersections | Medium | Medium |Two pointers i and j. Find overlap using: start = max(startA, startB) end   = min(endA, endB) If start <= end → add intersection. Move pointer of interval with smaller end.
| 15 | 344 | Reverse String | Easy | Medium | Two pointers from both ends. Swap characters at l and r, then move l++ and r-- until they meet.


**Key template:**

```java
int left = 0, right = arr.length - 1;
while (left < right) {
    if (condition) left++;
    else right--;
}
```

---

### PATTERN 2: SLIDING WINDOW

**When to use:** Subarray/substring with constraint, max/min of window, fixed/variable size window

| # | LC# | Problem | Diff | Freq | Logic |
|---|---|---|---|---|---|
| 1 | 3 | Longest Substring Without Repeating Characters | Medium | Very High | Sliding window with HashSet. Expand r; if duplicate appears shrink with l until unique; update max length using r - l + 1.
| 2 | 76 | Minimum Window Substring | Hard | High | Check Question | Sliding window + HashMap frequency. Expand r to include characters, track have/need. When window satisfies all chars of t, shrink l to minimize window while updating smallest substring.
| 3 | 438 | Find All Anagrams in a String | Medium | High | Fixed-size sliding window + frequency array (size 26). Count characters of p. Maintain window of length p.length() in s. Add right char, remove left char, compare frequency arrays (Arrays.equals). If equal, record start index.
| 4 | 567 | Permutation in String | Medium | Medium |Fixed-size sliding window + frequency array (size 26). Count characters of s1. Maintain window of size s1.length() in s2. Add right char and remove left char while sliding. Compare frequency arrays; if equal, return true immediately since a permutation exists.
| 5 | 209 | Minimum Size Subarray Sum | Medium | Medium | Variable-size sliding window. Expand r while adding elements to sum. When sum >= target, update minimum length r - l + 1 and shrink window by subtracting nums[l] and moving l to minimize the window.
| 6 | 239 | Sliding Window Maximum | Hard | High | [Deque and monotonically decreasing queue]Maintain a deque of indices in decreasing order of their values. Remove smaller elements from the back, remove indices from the front that fall out of the window, and the front of the deque always gives the maximum of the current window.
| 7 | 424 | Longest Repeating Character Replacement | Medium | Medium | maxf is allowed to be stale because it only makes the window shrink later,
never earlier, and therefore does not affect the maximum result.
| 8 | 560 | Subarray Sum Equals K | Medium | Very High | Two prefix sums that differ by k indicate a subarray with sum k, so we store prefix sum frequencies and count how many previous sums equal currentSum - k. 1. subArrSum[i,j] = ps[j] - ps[i] 2.  k = prefixSum[j] - prefixSum[i-1][i]

**Key template:**

```java
int left = 0;
Map<Character, Integer> window = new HashMap<>();
for (int right = 0; right < s.length(); right++) {
    // expand window: add s[right]
    while (window is invalid) {
        // shrink window: remove s[left]
        left++;
    }
    // update answer
}
```

---

### PATTERN 3: FAST & SLOW POINTERS

**When to use:** Cycle detection, finding middle, linked list cycle problems

| # | LC# | Problem | Diff | Freq | Logic |
|---|---|---|---|---|---|
| 1 | 141 | Linked List Cycle | Easy | Very High | Use Floyd's Tortoise and Hare algorithm: move slow pointer by 1 step and fast pointer by 2 steps. If a cycle exists, the fast pointer will eventually meet the slow pointer inside the loop; if fast reaches null, no cycle exists.
| 2 | 142 | Linked List Cycle II | Medium | High | First detect cycle using fast & slow pointers. If they meet, reset slow to head and move both pointers one step at a time. The node where they meet again is the start of the cycle because distance(head → cycleStart) = distance(meetingPoint → cycleStart).
| 3 | 876 | Middle of the Linked List | Easy | Medium | Use slow and fast pointers. slow moves 1 step, fast moves 2 steps. When fast reaches the end, slow is at the middle.
| 4 | 234 | Palindrome Linked List | Easy | Medium | Use fast & slow pointers to find the middle of the list. Reverse the second half of the linked list and compare it with the first half node by node. If all values match, the list is a palindrome.
| 5 | 287 | Find the Duplicate Number | Medium | Medium | Treat the array as a linked list where index → next pointer (nums[i]). Because one number repeats, two indices point to the same node, creating a cycle. Use Floyd’s cycle detection: move slow by 1 step and fast by 2 steps to find the meeting point, then reset another pointer to start and move both one step to find the duplicate (cycle start).
| 6 | 202 | Happy Number | Easy | Medium | Repeatedly replace number with sum of squares of digits. If it reaches 1 → happy. If numbers repeat → cycle → not happy. Detect cycle using HashSet or Floyd’s slow/fast pointers.
| 7 | 143 | Reorder List | Medium | High | Split the list into two halves using slow/fast pointers. Reverse the second half, then merge nodes alternately from the first and reversed second half.

**Key template:**

```java
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
```

---

### PATTERN 4: IN-PLACE LINKED LIST REVERSAL

**When to use:** Reverse full/partial list, k-group reversal, reorder

| # | LC# | Problem | Diff | Freq | Logic |
|---|---|---|---|---|---|
| 1 | 206 | Reverse Linked List | Easy | Very High |
| 2 | 92 | Reverse Linked List II | Medium | Medium | Use a dummy node to simplify edge cases. Move a pointer to the node before position left. Reverse the sublist from left to right using standard linked list reversal. Reconnect the reversed sublist by linking (left-1) to the new head and the original left node to (right+1).
| 3 | 25 | Reverse Nodes in K-Group | Hard | High |
| 4 | 143 | Reorder List | Medium | High |
| 5 | 24 | Swap Nodes in Pairs | Medium | Medium |
| 6 | 61 | Rotate List | Medium | Low |
| 7 | 328 | Odd Even Linked List | Medium | Medium |
| 8 | 86 | Partition List | Medium | Medium |
| 9 | 148 | Sort List | Medium | Medium |

**Key template:**

```java
ListNode prev = null, curr = head;
while (curr != null) {
    ListNode next = curr.next;
    curr.next = prev;
    prev = curr;
    curr = next;
}
```

---

### PATTERN 5: MERGE INTERVALS

**When to use:** Overlapping intervals, scheduling, "merge", "insert"

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 56 | Merge Intervals | Medium | Very High |
| 2 | 57 | Insert Interval | Medium | High |
| 3 | 252 | Meeting Rooms | Easy | High |
| 4 | 253 | Meeting Rooms II | Medium | High |
| 5 | 435 | Non-overlapping Intervals | Medium | Medium |
| 6 | 452 | Minimum Number of Arrows to Burst Balloons | Medium | Medium |
| 7 | 986 | Interval List Intersections | Medium | Medium |

**Key template:**

```java
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
List<int[]> merged = new ArrayList<>();
for (int[] interval : intervals) {
    if (merged.isEmpty() || merged.getLast()[1] < interval[0])
        merged.add(interval);
    else
        merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
}
```

---

### PATTERN 6: BINARY SEARCH

**When to use:** Sorted input, search space reduction, "minimum that satisfies X", monotonic function

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 704 | Binary Search | Easy | High |
| 2 | 33 | Search in Rotated Sorted Array | Medium | Very High |
| 3 | 153 | Find Minimum in Rotated Sorted Array | Medium | High |
| 4 | 74 | Search a 2D Matrix | Medium | Very High |
| 5 | 240 | Search a 2D Matrix II | Medium | High |
| 6 | 4 | Median of Two Sorted Arrays | Hard | High |
| 7 | 34 | Find First & Last Position | Medium | High |
| 8 | 162 | Find Peak Element | Medium | Medium |
| 9 | 875 | Koko Eating Bananas | Medium | Medium |
| 10 | 69 | Sqrt(x) | Easy | Medium |
| 11 | 540 | Single Element in Sorted Array | Medium | Medium |
| 12 | 981 | Time Based Key-Value Store | Medium | High |
| 13 | 35 | Search Insert Position | Easy | Medium |
| 14 | 1011 | Capacity to Ship Packages | Medium | Medium |

**Key template (search on answer):**

```java
int lo = min, hi = max;
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (feasible(mid)) hi = mid;
    else lo = mid + 1;
}
return lo;
```

---

### PATTERN 7: BFS (BREADTH-FIRST SEARCH)

**When to use:** Level-order traversal, shortest path (unweighted), "minimum steps", grid problems

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 102 | Binary Tree Level Order Traversal | Medium | Very High |
| 2 | 103 | Binary Tree Zigzag Level Order | Medium | High |
| 3 | 199 | Binary Tree Right Side View | Medium | High |
| 4 | 200 | Number of Islands | Medium | Very High |
| 5 | 994 | Rotting Oranges | Medium | Very High |
| 6 | 127 | Word Ladder | Hard | High |
| 7 | 133 | Clone Graph | Medium | High |
| 8 | 1091 | Shortest Path in Binary Matrix | Medium | High |
| 9 | 909 | Snakes and Ladders | Medium | Medium |
| 10 | 733 | Flood Fill | Easy | High |
| 11 | 116 | Populating Next Right Pointers | Medium | High |
| 12 | 117 | Populating Next Right Pointers II | Medium | Medium |
| 13 | 130 | Surrounded Regions | Medium | Medium |
| 14 | 1162 | As Far from Land as Possible | Medium | Medium |
| 15 | 417 | Pacific Atlantic Water Flow | Medium | Medium |
| 16 | 743 | Network Delay Time (BFS/Dijkstra) | Medium | High |
| 17 | 314 | Binary Tree Vertical Order | Medium | High |
| 18 | 987 | Vertical Order Traversal | Hard | High |

**Key template:**

```java
Queue<int[]> queue = new LinkedList<>();
queue.offer(start);
visited[start] = true;
while (!queue.isEmpty()) {
    int size = queue.size();
    for (int i = 0; i < size; i++) {
        int[] curr = queue.poll();
        for (int[] dir : directions) {
            // if valid & not visited -> add to queue
        }
    }
    level++;
}
```

---

### PATTERN 8: DFS (DEPTH-FIRST SEARCH)

**When to use:** Tree traversals, path finding, connected components, "all paths", exhaustive search on graphs

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 104 | Maximum Depth of Binary Tree | Easy | High |
| 2 | 226 | Invert Binary Tree | Easy | High |
| 3 | 100 | Same Tree | Easy | Medium |
| 4 | 101 | Symmetric Tree | Easy | Medium |
| 5 | 98 | Validate Binary Search Tree | Medium | Very High |
| 6 | 236 | Lowest Common Ancestor of BT | Medium | Very High |
| 7 | 235 | LCA of a BST | Medium | High |
| 8 | 124 | Binary Tree Maximum Path Sum | Hard | High |
| 9 | 543 | Diameter of Binary Tree | Easy | High |
| 10 | 572 | Subtree of Another Tree | Easy | Medium |
| 11 | 112 | Path Sum | Easy | Medium |
| 12 | 113 | Path Sum II | Medium | Medium |
| 13 | 437 | Path Sum III | Medium | Medium |
| 14 | 129 | Sum Root to Leaf Numbers | Medium | Medium |
| 15 | 114 | Flatten BT to Linked List | Medium | Medium |
| 16 | 105 | Construct BT from Preorder & Inorder | Medium | High |
| 17 | 297 | Serialize and Deserialize BT | Hard | Very High |
| 18 | 230 | Kth Smallest Element in BST | Medium | High |
| 19 | 450 | Delete Node in a BST | Medium | Medium |
| 20 | 108 | Convert Sorted Array to BST | Easy | Medium |
| 21 | 547 | Number of Provinces | Medium | Medium |
| 22 | 323 | Number of Connected Components | Medium | Medium |
| 23 | 785 | Is Graph Bipartite? | Medium | Medium |
| 24 | 261 | Graph Valid Tree | Medium | Medium |

**Key template (tree):**

```java
int dfs(TreeNode node) {
    if (node == null) return 0;
    int left = dfs(node.left);
    int right = dfs(node.right);
    // process current node
    return result;
}
```

---

### PATTERN 9: TOPOLOGICAL SORT

**When to use:** Dependency ordering, "course schedule", DAG ordering, build order

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 207 | Course Schedule | Medium | Very High |
| 2 | 210 | Course Schedule II | Medium | High |
| 3 | 269 | Alien Dictionary | Hard | Medium |
| 4 | 310 | Minimum Height Trees | Medium | Medium |
| 5 | 1136 | Parallel Courses | Medium | Medium |

**Key template (Kahn's BFS):**

```java
int[] indegree = new int[n];
// build indegree array
Queue<Integer> queue = new LinkedList<>();
for (int i = 0; i < n; i++)
    if (indegree[i] == 0) queue.offer(i);
while (!queue.isEmpty()) {
    int node = queue.poll();
    order.add(node);
    for (int neighbor : graph.get(node)) {
        if (--indegree[neighbor] == 0)
            queue.offer(neighbor);
    }
}
```

---

### PATTERN 10: UNION-FIND

**When to use:** "Connected components", grouping, dynamic connectivity, cycle detection in undirected graph

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 200 | Number of Islands (alt approach) | Medium | Very High |
| 2 | 323 | Number of Connected Components | Medium | Medium |
| 3 | 684 | Redundant Connection | Medium | Medium |
| 4 | 721 | Accounts Merge | Medium | Medium |
| 5 | 990 | Satisfiability of Equality Equations | Medium | Medium |
| 6 | 1584 | Min Cost to Connect All Points | Medium | Medium |
| 7 | 547 | Number of Provinces | Medium | Medium |

**Key template:**

```java
int[] parent, rank;
int find(int x) {
    if (parent[x] != x) parent[x] = find(parent[x]);
    return parent[x];
}
void union(int x, int y) {
    int px = find(x), py = find(y);
    if (px == py) return;
    if (rank[px] < rank[py]) parent[px] = py;
    else if (rank[px] > rank[py]) parent[py] = px;
    else { parent[py] = px; rank[px]++; }
}
```

---

### PATTERN 11: MONOTONIC STACK

**When to use:** "Next greater/smaller element", histogram problems, temperature problems

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 739 | Daily Temperatures | Medium | High |
| 2 | 84 | Largest Rectangle in Histogram | Hard | High |
| 3 | 496 | Next Greater Element I | Easy | Medium |
| 4 | 503 | Next Greater Element II | Medium | Medium |
| 5 | 42 | Trapping Rain Water (stack approach) | Hard | High |
| 6 | 316 | Remove Duplicate Letters | Medium | Medium |
| 7 | 402 | Remove K Digits | Medium | Medium |
| 8 | 856 | Score of Parentheses | Medium | Medium |
| 9 | 901 | Online Stock Span | Medium | Medium |

**Key template:**

```java
Stack<Integer> stack = new Stack<>();
for (int i = 0; i < n; i++) {
    while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
        int idx = stack.pop();
        result[idx] = arr[i]; // next greater
    }
    stack.push(i);
}
```

---

### PATTERN 12: STACK (PARENTHESES / EXPRESSION)

**When to use:** Matching brackets, evaluate expressions, nested structures, decode strings

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 20 | Valid Parentheses | Easy | Very High |
| 2 | 155 | Min Stack | Medium | Very High |
| 3 | 150 | Evaluate Reverse Polish Notation | Medium | High |
| 4 | 394 | Decode String | Medium | Very High |
| 5 | 227 | Basic Calculator II | Medium | High |
| 6 | 1249 | Min Remove to Make Valid Parentheses | Medium | High |
| 7 | 232 | Implement Queue using Stacks | Easy | High |
| 8 | 225 | Implement Stack using Queues | Easy | Medium |
| 9 | 71 | Simplify Path | Medium | Medium |
| 10 | 895 | Maximum Frequency Stack | Hard | Medium |

---

### PATTERN 13: BACKTRACKING

**When to use:** "All combinations/permutations/subsets", "generate all", constraint satisfaction

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 78 | Subsets | Medium | High |
| 2 | 90 | Subsets II | Medium | Medium |
| 3 | 46 | Permutations | Medium | High |
| 4 | 47 | Permutations II | Medium | Medium |
| 5 | 39 | Combination Sum | Medium | High |
| 6 | 77 | Combinations | Medium | Medium |
| 7 | 79 | Word Search | Medium | Very High |
| 8 | 212 | Word Search II (Trie + Backtrack) | Hard | High |
| 9 | 17 | Letter Combinations of Phone Number | Medium | Very High |
| 10 | 22 | Generate Parentheses | Medium | High |
| 11 | 131 | Palindrome Partitioning | Medium | Medium |
| 12 | 93 | Restore IP Addresses | Medium | Medium |
| 13 | 51 | N-Queens | Hard | Medium |
| 14 | 37 | Sudoku Solver | Hard | Low |

**Key template:**

```java
void backtrack(List<List<Integer>> result, List<Integer> path, int start) {
    result.add(new ArrayList<>(path));
    for (int i = start; i < nums.length; i++) {
        path.add(nums[i]);
        backtrack(result, path, i + 1);
        path.remove(path.size() - 1);
    }
}
```

---

### PATTERN 14: TOP-K ELEMENTS (HEAP)

**When to use:** "K largest/smallest/frequent", streaming data, priority-based

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 215 | Kth Largest Element in Array | Medium | High |
| 2 | 347 | Top K Frequent Elements | Medium | High |
| 3 | 692 | Top K Frequent Words | Medium | Medium |
| 4 | 973 | K Closest Points to Origin | Medium | Medium |
| 5 | 378 | Kth Smallest in Sorted Matrix | Medium | Medium |
| 6 | 230 | Kth Smallest Element in BST | Medium | High |
| 7 | 1046 | Last Stone Weight | Easy | Medium |
| 8 | 621 | Task Scheduler | Medium | High |
| 9 | 767 | Reorganize String | Medium | Medium |

**Key template:**

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
for (int num : nums) {
    minHeap.offer(num);
    if (minHeap.size() > k) minHeap.poll();
}
return minHeap.peek(); // kth largest
```

---

### PATTERN 15: TWO HEAPS

**When to use:** Find median in stream, balance two halves

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 295 | Find Median from Data Stream | Hard | Very High |
| 2 | 480 | Sliding Window Median | Hard | Medium |

**Key template:**

```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // lower half
PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // upper half

void addNum(int num) {
    maxHeap.offer(num);
    minHeap.offer(maxHeap.poll());
    if (minHeap.size() > maxHeap.size())
        maxHeap.offer(minHeap.poll());
}
```

---

### PATTERN 16: K-WAY MERGE

**When to use:** Merge K sorted structures, smallest range from K lists

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 23 | Merge K Sorted Lists | Hard | High |
| 2 | 21 | Merge Two Sorted Lists | Easy | Very High |
| 3 | 88 | Merge Sorted Array | Easy | Very High |
| 4 | 378 | Kth Smallest in Sorted Matrix | Medium | Medium |
| 5 | 4 | Median of Two Sorted Arrays | Hard | High |

---

### PATTERN 17: DYNAMIC PROGRAMMING — 1D

**When to use:** "Count ways", "min/max cost", linear sequence with choices

| #  | LC#  | Problem                        | Diff   | Freq      | Sub-pattern           | Logic          |
|----|------|--------------------------------|--------|-----------|---------------------|----------------|
| 1  | 70   | Climbing Stairs                | Easy   | Very High | 1D DP / Fibonacci    |  ways(n) = ways(n-1) + ways(n-2); To reach to n we can come from n-1 or n-2              |
| 2  | 198  | House Robber                   | Medium | High      | Linear choice        |  max_rob[i] = Math.max(max_rob(i-1), cost[i] + max(i-2))              |
| 3  | 213  | House Robber II                | Medium | Medium    | Circular             |  Math.max(houseRobber1(0, n-2), houseRobber1(1, n-1))              |
| 4  | 322  | Coin Change                    | Medium | Very High | Unbounded Knapsack   |  dp[i] = Math.max(dp[i]  , 1 + dp[i - coin])    for 1 to amount and for every coin        |
| 5  | 518  | Coin Change 2                  | Medium | Medium    | Unbounded Knapsack   |   dp[i][a] = dp[i+1][a] + dp[i][a-coin[i]]             |
| 6  | 139  | Word Break                     | Medium | Very High | String DP            |  dp[i] = dp[i + w.length()]              |
| 7  | 91   | Decode Ways                    | Medium | Very High | String DP            | Ways = (ways if I take 1 digit)+ (ways if I take 2 digits, if valid)               |
| 8  | 300  | Longest Increasing Subsequence | Medium | High      | LIS                  |   LIS[i] = Math.max(LIS[i], 1 + LIS[j]) where i = n-1 to 0 and j=i to < n return Max(LIS)             |
| 9  | 152  | Maximum Product Subarray       | Medium | High      | Kadane variant       | Maintain two variable curr_max, and curr_min               |
| 10 | 53   | Maximum Subarray               | Medium | Very High | Kadane's             |                |
| 11 | 55   | Jump Game                      | Medium | Medium    | Greedy/DP            |                |
| 12 | 45   | Jump Game II                   | Medium | Medium    | Greedy/DP            |                |
| 13 | 279  | Perfect Squares                | Medium | Medium    | BFS/DP               |                |
| 14 | 377  | Combination Sum IV             | Medium | Medium    | Unbounded Knapsack   |                |
| 15 | 416  | Partition Equal Subset Sum     | Medium | Medium    | 0/1 Knapsack         |   dfs(nums, sum/2 - nums[i]) || dfs(nums, sum/2)             |
| 16 | 494  | Target Sum                     | Medium | Medium    | 0/1 Knapsack         |   dp[0-num][0-2*totalSum+1] = backtrack(i+1, currSum + nums[i], nums, target) + backtrack(i+1, currSum - nums[i], nums, target)             |

**Key template (bottom-up):**

```java
int[] dp = new int[n + 1];
dp[0] = base_case;
for (int i = 1; i <= n; i++) {
    dp[i] = recurrence(dp[i-1], dp[i-2], ...);
}
return dp[n];
```

---

### PATTERN 18: DYNAMIC PROGRAMMING — 2D

**When to use:** Grid paths, two-string comparison, matrix optimization

| #  | LC#  | Problem                        | Diff   | Freq      | Sub-pattern           | Logic          |
|----|------|--------------------------------|--------|-----------|---------------------|----------------|
| 1 | 62 | Unique Paths | Medium | High | Grid DP | dp[i][j] = dp[i+1][j] + dp[i][j+1]|
| 2 | 64 | Minimum Path Sum | Medium | High | Grid DP |
| 3 | 72 | Edit Distance | Medium | High | LCS variant | if(s1.charAt(i-1) == s2.charAt(j-1) then dp[i][j] = dp[i-1][j-1] else dp[i][j] = Max(dp[i][j-1], dp[i-1][j], dp[i-1][j-1]))
| 4 | 1143 | Longest Common Subsequence | Medium | High | LCS | if(text1.charAt(m-1) == text2.charAt(n-1)){ memo[m][n] = 1 + dfs(text1, text2, m - 1, n - 1); }else{memo[m][n] = Math.max(dfs(text1, text2, m, n - 1), dfs(text1, text2, m - 1, n));}|
| 5 | 5 | Longest Palindromic Substring | Medium | High | Expand / DP |
| 6 | 10 | Regular Expression Matching | Hard | High | String matching DP |
| 7 | 97 | Interleaving String | Medium | Medium | String DP | dp[i][j] = (dp[i-1][j] && s1.charAt(i-1)==s3.charAt(i+j-1)) || (dp[i][j-1] && s2.charAt(j-1)==s3.charAt(i+j-1))
| 8 | 120 | Triangle | Medium | Medium | Grid DP |
| 9 | 312 | Burst Balloons | Hard | Low | Interval DP |
| 10 | 647 | Palindromic Substrings | Medium | Medium | Expand / DP |

**Key template:**

```java
int[][] dp = new int[m + 1][n + 1];
for (int i = 1; i <= m; i++) {
    for (int j = 1; j <= n; j++) {
        if (s1[i-1] == s2[j-1])
            dp[i][j] = dp[i-1][j-1] + 1;
        else
            dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
    }
}
```

---

### PATTERN 19: GREEDY

**When to use:** Local optimal leads to global optimal, scheduling, "minimum/maximum of"

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 55 | Jump Game | Medium | Medium |
| 2 | 45 | Jump Game II | Medium | Medium |
| 3 | 134 | Gas Station | Medium | Medium |
| 4 | 763 | Partition Labels | Medium | Medium |
| 5 | 435 | Non-overlapping Intervals | Medium | Medium |
| 6 | 452 | Min Arrows to Burst Balloons | Medium | Medium |
| 7 | 621 | Task Scheduler | Medium | High |
| 8 | 402 | Remove K Digits | Medium | Medium |
| 9 | 334 | Increasing Triplet Subsequence | Medium | Medium |

---

### PATTERN 20: TRIE (PREFIX TREE)

**When to use:** Prefix search, autocomplete, word dictionary, word search in matrix

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 208 | Implement Trie | Medium | High |
| 2 | 212 | Word Search II | Hard | High |
| 3 | 211 | Design Add and Search Words | Medium | Medium |
| 4 | 1268 | Search Suggestions System | Medium | High |
| 5 | 648 | Replace Words | Medium | Medium |

**Key template:**

```java
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
}
void insert(String word) {
    TrieNode node = root;
    for (char c : word.toCharArray()) {
        if (node.children[c - 'a'] == null)
            node.children[c - 'a'] = new TrieNode();
        node = node.children[c - 'a'];
    }dynami
    node.isEnd = true;
}
```

---

### PATTERN 21: DESIGN (DATA STRUCTURE FROM SCRATCH)

**When to use:** "Implement X", "Design X", combine multiple data structures

| # | LC# | Problem | Diff | Freq | Key Idea |
|---|---|---|---|---|---|
| 1 | 146 | LRU Cache | Medium | Very High | HashMap + Doubly Linked List |
| 2 | 155 | Min Stack | Medium | Very High | Two stacks |
| 3 | 380 | Insert Delete GetRandom O(1) | Medium | High | HashMap + ArrayList |
| 4 | 348 | Design Tic-Tac-Toe | Medium | High | Row/col/diag counters |
| 5 | 362 | Design Hit Counter | Medium | Medium | Queue / circular buffer |
| 6 | 355 | Design Twitter | Medium | Medium | Heap + HashMap |
| 7 | 706 | Design HashMap | Easy | Medium | Array of buckets |
| 8 | 232 | Implement Queue using Stacks | Easy | High | Two stacks |
| 9 | 460 | LFU Cache | Hard | Medium | Two HashMaps + DLL |
| 10 | 173 | BST Iterator | Medium | Medium | Stack-based inorder |

---

### PATTERN 22: PREFIX SUM

**When to use:** Subarray sum queries, range sum, "sum equals K"

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 560 | Subarray Sum Equals K | Medium | Very High |
| 2 | 238 | Product of Array Except Self | Medium | Very High |
| 3 | 303 | Range Sum Query - Immutable | Easy | Medium |
| 4 | 304 | Range Sum Query 2D - Immutable | Medium | Medium |
| 5 | 437 | Path Sum III | Medium | Medium |
| 6 | 525 | Contiguous Array | Medium | Medium |

**Key template:**

```java
Map<Integer, Integer> prefixCount = new HashMap<>();
prefixCount.put(0, 1);
int sum = 0, count = 0;
for (int num : nums) {
    sum += num;
    count += prefixCount.getOrDefault(sum - k, 0);
    prefixCount.merge(sum, 1, Integer::sum);
}
```

---

### PATTERN 23: MATRIX TRAVERSAL

**When to use:** Spiral, rotate, set zeroes, search in matrix

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 54 | Spiral Matrix | Medium | High |
| 2 | 48 | Rotate Image | Medium | High |
| 3 | 73 | Set Matrix Zeroes | Medium | High |
| 4 | 74 | Search a 2D Matrix | Medium | Very High |
| 5 | 240 | Search a 2D Matrix II | Medium | High |
| 6 | 36 | Valid Sudoku | Medium | Medium |

---

### PATTERN 24: BIT MANIPULATION

**When to use:** Single number, power of two, XOR tricks, counting bits

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 136 | Single Number | Easy | High |
| 2 | 137 | Single Number II | Medium | Medium |
| 3 | 260 | Single Number III | Medium | Medium |
| 4 | 191 | Number of 1 Bits | Easy | Medium |
| 5 | 231 | Power of Two | Easy | Medium |
| 6 | 371 | Sum of Two Integers | Medium | Medium |
| 7 | 268 | Missing Number | Easy | Medium |
| 8 | 338 | Counting Bits | Easy | Medium |

---

### PATTERN 25: MATH / STRING PARSING

**When to use:** Number manipulation, roman numerals, string-to-number, English words

| # | LC# | Problem | Diff | Freq |
|---|---|---|---|---|
| 1 | 8 | String to Integer (atoi) | Medium | Very High |
| 2 | 273 | Integer to English Words | Hard | High |
| 3 | 7 | Reverse Integer | Medium | High |
| 4 | 9 | Palindrome Number | Easy | High |
| 5 | 13 | Roman to Integer | Easy | Medium |
| 6 | 50 | Pow(x, n) | Medium | High |
| 7 | 43 | Multiply Strings | Medium | Medium |
| 8 | 66 | Plus One | Easy | Medium |
| 9 | 14 | Longest Common Prefix | Easy | High |
| 10 | 68 | Text Justification | Hard | Medium |

---

## PATTERN PRIORITY FOR MICROSOFT

Here's the order you should study patterns in, ranked by Microsoft frequency:

| Rank | Pattern | Microsoft Frequency |
|---|---|---|
| 1 | In-place Linked List Reversal | Very High |
| 2 | DFS (Trees) | Very High |
| 3 | BFS (Level Order / Grid) | Very High |
| 4 | Two Pointers | Very High |
| 5 | Sliding Window | Very High |
| 6 | Stack (Parentheses/Expression) | Very High |
| 7 | Design (LRU Cache, etc.) | Very High |
| 8 | Binary Search | High |
| 9 | DP — 1D | High |
| 10 | Backtracking | High |
| 11 | Merge Intervals | High |
| 12 | Top-K / Heap | High |
| 13 | Prefix Sum | High |
| 14 | DP — 2D | Medium-High |
| 15 | Topological Sort | Medium |
| 16 | Monotonic Stack | Medium |
| 17 | Trie | Medium |
| 18 | Union-Find | Medium |
| 19 | Greedy | Medium |
| 20 | K-Way Merge | Medium |
| 21 | Two Heaps | Medium |
| 22 | Bit Manipulation | Low-Medium |

---


First 
Two Pointers
Sliding Window
Stack
Linked List Reversal
DFS/BFS Trees
Binary Search
Heap
DP 1D
Backtracking
DP 2D