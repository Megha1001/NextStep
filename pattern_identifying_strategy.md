## Core DSA Patterns & How to Identify Them

### 1. Sliding Window
**Trigger:** Contiguous subarray/substring, fixed or variable size window, "maximum/minimum of k-size subarray"
- Fixed window: size is given
- Variable window: expand right, shrink left until condition met
- Examples: Max sum subarray of size k, longest substring without repeating chars

### 2. Two Pointers
**Trigger:** Sorted array, pair/triplet sum, removing duplicates, palindrome check
- One pointer from each end, or both from the start (slow/fast)
- Examples: Two Sum (sorted), 3Sum, container with most water, trapping rain water

### 3. Fast & Slow Pointers (Floyd's)
**Trigger:** Cycle detection, finding middle of linked list, happy number
- One pointer moves 1 step, other moves 2 steps
- Examples: Linked list cycle, find duplicate number, palindrome linked list

### 4. Merge Intervals
**Trigger:** Overlapping intervals, scheduling, "merge", "insert interval"
- Sort by start time, then merge overlapping
- Examples: Merge intervals, insert interval, meeting rooms

### 5. Cyclic Sort
**Trigger:** Array contains numbers in range `[1, n]` or `[0, n]`, find missing/duplicate
- Place each number at its correct index
- Examples: Find missing number, find all duplicates, first missing positive

### 6. In-place Linked List Reversal
**Trigger:** Reverse a linked list or part of it
- Use `prev`, `curr`, `next` pointers
- Examples: Reverse linked list, reverse between positions m and n, reverse in k-groups

### 7. BFS (Breadth-First Search)
**Trigger:** Level-order traversal, shortest path in unweighted graph, "minimum steps"
- Use a queue, process level by level
- Examples: Binary tree level order, rotting oranges, word ladder

### 8. DFS (Depth-First Search)
**Trigger:** Tree/graph traversal, path finding, "all paths", connected components
- Use recursion or explicit stack
- Examples: Number of islands, path sum, clone graph

### 9. Two Heaps
**Trigger:** Find median from a stream, balance two halves
- Max-heap for lower half, min-heap for upper half
- Examples: Find median from data stream, sliding window median

### 10. Binary Search
**Trigger:** Sorted array, "search", "minimum/maximum that satisfies condition", monotonic function
- Classic, or binary search on the answer space
- Examples: Search in rotated array, find peak, koko eating bananas, capacity to ship packages

### 11. Subsets / Backtracking
**Trigger:** "All combinations", "all permutations", "all subsets", "generate all"
- Build decision tree, include/exclude at each step
- Examples: Subsets, permutations, combination sum, N-queens, sudoku solver

### 12. Modified Binary Search
**Trigger:** Sorted but rotated, matrix sorted row/column-wise, finding boundaries
- Adapt standard binary search with extra conditions
- Examples: Search in rotated sorted array, find first/last position, search 2D matrix

### 13. Top-K Elements
**Trigger:** "K largest", "K most frequent", "K closest"
- Use a min-heap of size K (or max-heap depending on direction)
- Examples: Kth largest element, top K frequent elements, K closest points

### 14. K-Way Merge
**Trigger:** Merge K sorted lists/arrays, smallest range covering elements from K lists
- Use a min-heap to always pick the smallest among K candidates
- Examples: Merge K sorted lists, smallest range, Kth smallest in sorted matrix

### 15. Dynamic Programming
**Trigger:** "Count ways", "minimum/maximum cost", overlapping subproblems, optimal substructure

| Sub-pattern | Trigger |
|---|---|
| 0/1 Knapsack | Pick or skip items, weight/value constraint |
| Unbounded Knapsack | Unlimited picks, coin change type |
| LCS/LIS | Two sequences, longest common/increasing |
| Grid DP | Paths in matrix, min cost path |
| Interval DP | Burst balloons, matrix chain multiplication |
| Bitmask DP | Small n (<=20), assign items to groups |
| State Machine DP | Buy/sell stock with cooldown/fee |

### 16. Monotonic Stack / Queue
**Trigger:** "Next greater/smaller element", "largest rectangle in histogram"
- Maintain stack in increasing/decreasing order
- Examples: Daily temperatures, largest rectangle, sliding window maximum

### 17. Union-Find (Disjoint Set)
**Trigger:** "Connected components", "are they in the same group", dynamic graph connectivity
- Examples: Number of connected components, redundant connection, accounts merge

### 18. Topological Sort
**Trigger:** Dependency ordering, "course schedule", DAG ordering
- Kahn's (BFS with in-degree) or DFS-based
- Examples: Course schedule, alien dictionary, task scheduler

### 19. Trie
**Trigger:** Prefix matching, autocomplete, word search in dictionary
- Examples: Implement trie, word search II, longest word in dictionary

### 20. Greedy
**Trigger:** Local optimal leads to global optimal, interval scheduling, "minimum number of"
- Examples: Jump game, task scheduler, minimum platforms, activity selection

---

## Quick Decision Flowchart

```
Is it about a sorted/ordered structure?
  ├── Yes → Binary Search / Two Pointers
  └── No ↓

Is it about a subarray or substring?
  ├── Yes → Sliding Window
  └── No ↓

Is it about combinations/permutations/subsets?
  ├── Yes → Backtracking
  └── No ↓

Is it about a tree or graph?
  ├── Tree → DFS / BFS
  ├── Graph with weights → Dijkstra / Bellman-Ford
  ├── Graph ordering → Topological Sort
  └── Graph connectivity → Union-Find / BFS

Is it "count ways" or "min/max cost"?
  ├── Yes → Dynamic Programming
  └── No ↓

Is it about "K largest/smallest/frequent"?
  ├── Yes → Heap / Top-K
  └── No ↓

Is it about "next greater/smaller"?
  ├── Yes → Monotonic Stack
  └── No → Re-read the problem :)
```

---

## Memory Trick: "S-T-F-M-C-R-B-D-H-B-S-M-T-K-K-D-M-U-T-T-G"

A simpler mnemonic — group them in **5 buckets**:

| Bucket | Patterns |
|---|---|
| **Array/String** | Sliding Window, Two Pointers, Cyclic Sort, Binary Search, Monotonic Stack |
| **Linked List** | Fast/Slow Pointers, In-place Reversal, K-Way Merge |
| **Tree/Graph** | BFS, DFS, Topological Sort, Union-Find, Trie |
| **Heap** | Two Heaps, Top-K Elements |
| **DP/Backtracking** | Subsets/Backtracking, all DP variants, Greedy, Merge Intervals |

---
Here's a comprehensive company-wise breakdown of DSA focus areas based on well-known interview trends:

---

## Tier 1: FAANG + Big Tech

### Google
| Focus Area | Weight | Common Topics |
|---|---|---|
| **Dynamic Programming** | Very High | Grid DP, interval DP, state machine DP |
| **Graph Algorithms** | Very High | BFS/DFS, topological sort, shortest path, union-find |
| **Binary Search** | High | Search on answer space, modified binary search |
| **Sliding Window / Two Pointers** | High | Variable-size window problems |
| **Greedy** | Medium | Interval scheduling, optimization |
| **Trie / String** | Medium | Prefix problems, autocomplete |
| **Math / Geometry** | Medium | Number theory, computational geometry |

**Style:** Open-ended, often novel problems. Emphasis on optimal time/space. Multiple follow-ups to the same problem. Expect 2 problems in 45 min.

---

### Amazon
| Focus Area | Weight | Common Topics |
|---|---|---|
| **BFS/DFS** | Very High | Number of islands, rotting oranges, flood fill |
| **Heap / Priority Queue** | Very High | Top-K, merge K sorted, task scheduler |
| **Sliding Window** | High | Substring problems, max/min subarray |
| **Two Pointers** | High | Pair sum, container with most water |
| **Union-Find** | Medium | Connected components |
| **DP (basic)** | Medium | 0/1 knapsack, LIS, word break |
| **Trees** | High | BST validation, LCA, serialize/deserialize |

**Style:** Heavy on Leadership Principles (LP) + coding. Problems tend to be well-known patterns. BFS/graph problems are Amazon favorites. Expect system design heavily at SDE-2+.

---

### Meta (Facebook)
| Focus Area | Weight | Common Topics |
|---|---|---|
| **Binary Search** | Very High | Search in rotated array, find peak |
| **Two Pointers** | Very High | 3Sum, move zeroes, sort colors |
| **BFS** | Very High | Shortest path, word ladder, level order |
| **Backtracking** | High | Subsets, permutations, combination sum |
| **Sliding Window** | High | Minimum window substring |
| **Intervals** | High | Merge intervals, meeting rooms |
| **Trees** | High | Diameter, vertical order, right side view |
| **DP** | Medium | Decode ways, word break, coin change |

**Style:** Prefers well-known LeetCode problems. Speed matters — 2 problems in 35 min coding round. Clean, bug-free code expected. Heavy on arrays and strings.

---

### Apple
| Focus Area | Weight | Common Topics |
|---|---|---|
| **Arrays & Strings** | Very High | Two pointers, sliding window |
| **Linked Lists** | High | Reversal, merge, cycle detection |
| **Trees** | High | Traversals, BST operations |
| **Sorting & Searching** | High | Binary search variants |
| **DP** | Medium | Basic DP (climbing stairs level) |
| **System Design** | High (senior) | iOS/macOS architecture |

**Style:** Emphasis on clean code quality, OOP design, and understanding of fundamentals. Less "trick" problems, more straightforward.

---

### Microsoft
| Focus Area | Weight | Common Topics |
|---|---|---|
| **Arrays & Strings** | Very High | Anagrams, palindromes, matrix problems |
| **Linked Lists** | Very High | Reversal, merge, intersection, LRU cache |
| **Trees & BST** | Very High | Traversals, LCA, balanced tree checks |
| **DP** | High | Grid DP, subsequence problems |
| **Graphs** | High | BFS/DFS, connected components |
| **Stack & Queue** | High | Valid parentheses, monotonic stack |
| **Sorting** | Medium | Merge sort, quick sort |

**Style:** Balanced across data structures. Loves linked list and tree problems. Code quality matters. Expect 1-2 problems per round with follow-ups.

---

### Netflix
| Focus Area | Weight | Common Topics |
|---|---|---|
| **System Design** | Very High | Distributed systems, streaming architecture |
| **Graphs** | High | Shortest path, recommendation graphs |
| **DP** | High | Optimization problems |
| **Concurrency** | High | Multithreading, async patterns |

**Style:** Very senior-heavy hiring. More focus on system design and architecture than pure DSA.

---

## Tier 2: High-Growth Tech

### Uber
| Focus Area | Weight |
|---|---|
| **Graph Algorithms** | Very High (maps/routing) |
| **DP** | High |
| **Binary Search** | High |
| **Heap / Priority Queue** | High |
| **Sliding Window** | Medium |
| **Design (LLD + HLD)** | Very High |

---

### Airbnb
| Focus Area | Weight |
|---|---|
| **Backtracking** | Very High (famous for it) |
| **DFS/BFS** | High |
| **String Parsing** | High |
| **DP** | Medium |
| **Calendar/Intervals** | High |

---

### LinkedIn
| Focus Area | Weight |
|---|---|
| **Graphs** | Very High (social graph) |
| **DP** | High |
| **Binary Search** | High |
| **Design Patterns** | High |
| **Tries** | Medium |

---

### Twitter / X
| Focus Area | Weight |
|---|---|
| **Hash Maps** | Very High |
| **Heaps / Top-K** | High |
| **Streaming Algorithms** | High |
| **Graphs** | Medium |
| **System Design** | Very High |

---

### Stripe
| Focus Area | Weight |
|---|---|
| **String Parsing** | Very High |
| **Simulation** | Very High |
| **Hash Maps** | High |
| **Practical Coding** | Very High |
| **DP** | Low |

**Style:** Very practical, real-world problems. Less algorithmic tricks, more clean implementation.

---

## Tier 3: Finance / Trading

### Goldman Sachs / JP Morgan
| Focus Area | Weight |
|---|---|
| **Arrays & Strings** | Very High |
| **Sorting & Searching** | High |
| **DP (basic-medium)** | High |
| **Trees** | Medium |
| **Math / Probability** | High |
| **OOP / Design** | High |

---

### Jane Street / Citadel / Two Sigma
| Focus Area | Weight |
|---|---|
| **Math / Probability** | Very High |
| **DP (hard)** | Very High |
| **Graph Algorithms** | High |
| **Greedy** | High |
| **Game Theory** | Medium |
| **Bit Manipulation** | Medium |

**Style:** Extremely hard. Competitive programming level. Novel problems.

---

## Company-to-Pattern Quick Reference

| Pattern | Top Companies That Ask It |
|---|---|
| **Sliding Window** | Meta, Amazon, Google, Microsoft |
| **Two Pointers** | Meta, Amazon, Apple, Microsoft |
| **Binary Search** | Google, Meta, Uber, LinkedIn |
| **BFS/DFS** | Amazon, Google, Meta, Uber |
| **Backtracking** | Airbnb, Google, Meta |
| **DP** | Google, Uber, Jane Street, Citadel |
| **Trees** | Microsoft, Amazon, Meta, Apple |
| **Graphs** | Google, Uber, LinkedIn, Amazon |
| **Heap / Top-K** | Amazon, Twitter, Uber |
| **Linked Lists** | Microsoft, Apple, Amazon |
| **Trie** | Google, LinkedIn |
| **Union-Find** | Google, Amazon |
| **Monotonic Stack** | Google, Amazon, Microsoft |
| **Intervals** | Meta, Airbnb, Amazon |
| **Greedy** | Google, Citadel |
| **Bit Manipulation** | Jane Street, Google |

---

## Recommended Prep Strategy by Company

| Company | LeetCode Difficulty Split | Total Problems |
|---|---|---|
| **Google** | 10% Easy, 50% Medium, 40% Hard | 200+ |
| **Meta** | 20% Easy, 60% Medium, 20% Hard | 150 |
| **Amazon** | 20% Easy, 60% Medium, 20% Hard | 150 |
| **Microsoft** | 30% Easy, 50% Medium, 20% Hard | 120 |
| **Apple** | 30% Easy, 50% Medium, 20% Hard | 100 |
| **Stripe** | Practical problems, less LC style | 50 + projects |
| **Trading Firms** | 10% Easy, 30% Medium, 60% Hard | 200+ |

---

## Pro Tips

1. **LeetCode company tags** — Filter by company and sort by frequency. The top 50 for each company cover ~80% of questions.
2. **Neetcode.io** — Has a curated 150 list organized by pattern, great for structured prep.
3. **Blind 75 / Grind 75** — If short on time, these cover the most bang-for-buck problems.
4. **For Meta/Amazon** — Speed > novelty. Practice solving mediums in 15-20 min.
5. **For Google** — Depth > speed. Practice hard problems with follow-up variations.
6. **For trading firms** — Do Codeforces/AtCoder alongside LeetCode.

