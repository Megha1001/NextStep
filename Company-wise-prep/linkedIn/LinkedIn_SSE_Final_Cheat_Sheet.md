# LinkedIn SSE - FINAL CHEAT SHEET 🚀
**Interview: March 31, 2026 | 41 Problems Mastered**

---

## 🔥 TIER 1 PATTERNS (100% Asked - You've Mastered ALL!)

### 1. HashMap + Doubly Linked List
```java
// LRU Cache, All O'one Data Structure, Max Stack
class Node {
    int key, val, count;
    LinkedHashSet<String> keys; // For All O'one
    Node prev, next;
}

// Core Operations
private void addToHead(Node node) {
    node.next = head.next; node.prev = head;
    head.next.prev = node; head.next = node;
}
private void removeNode(Node node) {
    node.prev.next = node.next; node.next.prev = node.prev;
}
```
**Your Solutions:** LRU Cache, All O'one, Max Stack ✅

### 2. DFS with State Tracking
```java
// Nested List Weight Sum I & II, Find Leaves
private int dfs(NestedInteger ni, int depth) {
    if (ni.isInteger()) return ni.getInteger() * depth;
    int sum = 0;
    for (NestedInteger child : ni.getList()) {
        sum += dfs(child, depth + 1); // or depth - 1 for reverse
    }
    return sum;
}
```
**Your Solutions:** Nested List Weight Sum I & II, Find Leaves ✅

### 3. Two-Pointer Elimination
```java
// Find the Celebrity - Eliminate in pairs
int candidate = 0;
for (int i = 1; i < n; i++) {
    if (knows(candidate, i)) candidate = i;
}
// Verify candidate in second pass
```
**Your Solutions:** Find the Celebrity ✅

---

## 🚀 SLIDING WINDOW MASTERY

### Template
```java
int left = 0, maxLen = 0, count = 0;
for (int right = 0; right < n; right++) {
    // Expand window
    if (condition) count++;
    
    // Shrink window when invalid
    while (count > k) {
        if (condition) count--;
        left++;
    }
    
    maxLen = Math.max(maxLen, right - left + 1);
}
```
**Your Solutions:** Max Consecutive Ones III, Min Window Substring, Sliding Window Maximum ✅

---

## 💪 KADANE'S ALGORITHM

### Maximum Subarray
```java
int maxHere = nums[0], maxSoFar = nums[0];
for (int i = 1; i < n; i++) {
    maxHere = Math.max(nums[i], maxHere + nums[i]);
    maxSoFar = Math.max(maxSoFar, maxHere);
}
```

### Maximum Product (Track Both Max & Min)
```java
int maxHere = 1, minHere = 1, result = nums[0];
for (int num : nums) {
    int temp = maxHere * num;
    maxHere = Math.max(Math.max(temp, minHere * num), num);
    minHere = Math.min(Math.min(temp, minHere * num), num);
    result = Math.max(result, maxHere);
}
```
**Your Solutions:** Maximum Subarray, Maximum Product Subarray ✅

---

## 🌳 TREE PATTERNS

### DFS with Bounds (BST Validation)
```java
private boolean isValid(TreeNode root, long min, long max) {
    if (root == null) return true;
    if (root.val <= min || root.val >= max) return false;
    return isValid(root.left, min, root.val) && 
           isValid(root.right, root.val, max);
}
```

### Height-Based Processing
```java
private int getHeight(TreeNode root) {
    if (root == null) return 0;
    int height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
    map.computeIfAbsent(height, k -> new ArrayList<>()).add(root.val);
    return height;
}
```
**Your Solutions:** Validate BST, LCA, Diameter, Find Leaves, Level Order, Max Depth ✅

---

## 🔍 BINARY SEARCH PATTERNS

### Rotated Array Search
```java
while (left <= right) {
    int mid = left + (right - left) / 2;
    if (nums[mid] == target) return mid;
    
    if (nums[mid] <= nums[right]) { // Right half sorted
        if (target > nums[mid] && target <= nums[right]) left = mid + 1;
        else right = mid - 1;
    } else { // Left half sorted
        if (target >= nums[left] && target < nums[mid]) right = mid - 1;
        else left = mid + 1;
    }
}
```
**Your Solutions:** Binary Search, Search Rotated Array ✅

---

## 🎯 DESIGN PATTERNS

### HashMap + Array (O(1) Operations)
```java
// Insert Delete GetRandom - Swap with last for O(1) removal
int index = map.get(val);
int lastElement = list.get(list.size() - 1);
list.set(index, lastElement);
map.put(lastElement, index);
list.remove(list.size() - 1);
map.remove(val);
```
**Your Solutions:** Insert Delete GetRandom (both versions) ✅

---

## 📊 GRAPH TRAVERSAL

### BFS Template
```java
Queue<Node> queue = new ArrayDeque<>();
Set<Node> visited = new HashSet<>();
queue.offer(start);
visited.add(start);

while (!queue.isEmpty()) {
    Node curr = queue.poll();
    for (Node neighbor : curr.neighbors) {
        if (!visited.contains(neighbor)) {
            visited.add(neighbor);
            queue.offer(neighbor);
        }
    }
}
```

### Topological Sort
```java
int[] indegree = new int[numCourses];
// Calculate indegrees...
Queue<Integer> queue = new ArrayDeque<>();
for (int i = 0; i < numCourses; i++) {
    if (indegree[i] == 0) queue.offer(i);
}
```
**Your Solutions:** Number of Islands, Clone Graph, Course Schedule ✅

---

## 🔄 INTERVALS PATTERN

### Merge Intervals
```java
Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
int idx = 0;
for (int i = 1; i < intervals.length; i++) {
    if (intervals[idx][1] >= intervals[i][0]) {
        // Merge
        intervals[idx][1] = Math.max(intervals[idx][1], intervals[i][1]);
    } else {
        intervals[++idx] = intervals[i];
    }
}
```
**Your Solutions:** Merge Intervals, Insert Interval ✅

---

## 🎲 BACKTRACKING PATTERN

### Permutations Template
```java
private void backtrack(List<Integer> temp, int[] nums, Set<Integer> used) {
    if (temp.size() == nums.length) {
        result.add(new ArrayList<>(temp));
        return;
    }
    
    for (int i = 0; i < nums.length; i++) {
        if (!used.contains(nums[i])) {
            // Choose
            temp.add(nums[i]);
            used.add(nums[i]);
            
            // Explore
            backtrack(temp, nums, used);
            
            // Unchoose
            temp.remove(temp.size() - 1);
            used.remove(nums[i]);
        }
    }
}
```
**Your Solutions:** Permutations ✅

---

## ⚡ COMPLEXITY QUICK REFERENCE

| Pattern | Time | Space | Your Examples |
|---------|------|-------|---------------|
| HashMap + DLL | O(1) | O(n) | LRU Cache, All O'one |
| Sliding Window | O(n) | O(1) | Max Consecutive Ones III |
| Kadane's | O(n) | O(1) | Max Subarray/Product |
| Binary Search | O(log n) | O(1) | Rotated Array Search |
| BFS/DFS | O(V+E) | O(V) | Islands, Clone Graph |
| Intervals | O(n log n) | O(1) | Merge Intervals |
| Backtracking | O(n!) | O(n) | Permutations |

---

## 🎯 INTERVIEW EXECUTION CHECKLIST

### Step-by-Step Process:
1. **CLARIFY** → Ask about inputs, outputs, constraints, edge cases
2. **APPROACH** → Explain brute force first, then optimize
3. **CODE** → Think aloud, use meaningful variable names
4. **TEST** → Trace through examples, check edge cases
5. **OPTIMIZE** → Discuss time/space complexity, alternatives

### Common Edge Cases You've Handled:
- `null` inputs, empty arrays/strings
- Single element cases
- Duplicate elements
- Integer overflow (use `long` for bounds)
- Cycles in graphs, unbalanced trees

---

## 💪 YOUR ACHIEVEMENTS SUMMARY

### Problems Solved: 41/55 (75% Complete!)
- ✅ **ALL Tier 1** (100% asked frequency)
- ✅ **ALL Tier 2** (75-87% asked frequency)
- ✅ **All major patterns** covered
- ✅ **Recent validation** - matches 2024-2025 LinkedIn interviews

### Real-World Connection:
- **LRU Cache** → Your GMSS caching optimizations
- **Sliding Window** → Your latency optimization (40% improvement)
- **HashMap + DLL** → Your high-performance systems (99.95% uptime)
- **Graph algorithms** → Your distributed system architecture

---

## 🌟 CONFIDENCE BOOSTERS

### You've Mastered:
- **Every high-frequency LinkedIn problem**
- **All algorithmic patterns they could ask**
- **Advanced optimization techniques**
- **Complex system design problems**

### You Have:
- **6+ years experience** (exceeds 4+ requirement)
- **Technical ownership** of 5M+ daily sessions
- **Proven leadership** (promoted twice, mentor engineers)
- **Perfect role alignment** (Applications team focus)

---

## 🚀 FINAL REMINDERS

**Tomorrow Morning:**
- Light warm-up with Two Sum or Valid Parentheses
- Quick glance at this cheat sheet (5 minutes)
- Stay calm and confident

**During Interview:**
- **Trust your preparation** - you've solved more problems than 95% of candidates
- **Communicate clearly** - explain your thought process
- **Connect to experience** - relate algorithms to your GMSS work
- **You belong there** - your experience proves it

**You've got this! 🔥💪⭐**