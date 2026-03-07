# Amazon SDE Online Assessment Preparation Guide

[![Amazon OA](https://img.shields.io/badge/Amazon-OA-orange)](https://www.amazon.jobs/)
[![LeetCode](https://img.shields.io/badge/LeetCode-Practice-blue)](https://leetcode.com/company/amazon/)
[![Difficulty](https://img.shields.io/badge/Difficulty-Easy%20%7C%20Medium%20%7C%20Hard-brightgreen)]()

> A curated collection of **Amazon Online Assessment (OA)** problems with structured metadata for efficient preparation.

This repository contains **20 real Amazon OA problems** gathered from previous interview experiences. Each problem includes:
- ⭐ **Difficulty** rating
- 🧠 **Pattern/Topic** identification
- ⏱️ **Expected solving time**
- 📊 **Structured format** for easy revision

---

## 📋 Table of Contents

- [Overview](#overview)
- [Problem List](#problem-list)
- [Preparation Strategy](#preparation-strategy)
- [Recommended Practice](#recommended-practice)
- [Contributing](#contributing)

---

## 🎯 Overview

### What's Included

This guide contains **20 carefully selected Amazon OA problems** that have appeared in recent interviews. Each problem is annotated with:

- **Difficulty Level**: Easy, Medium, or Hard
- **Pattern/Topic**: Algorithm pattern or data structure used
- **Expected Time**: Estimated time to solve during OA
- **Problem Description**: Clear problem statement with examples
- **References**: Links to similar LeetCode problems (where applicable)

### How to Use This Guide

1. **Start with Easy/Medium problems** to build confidence
2. **Focus on patterns** rather than memorizing solutions
3. **Time yourself** - practice solving within the expected time
4. **Review solutions** - understand the approach, not just the code
5. **Group by pattern** - master one pattern before moving to the next

---

## 📚 Problem List

### Easy Problems (3)

| # | Problem | Pattern | Time | LeetCode |
|---|---------|---------|------|----------|
| 7 | Maximum Characters Removable (Machine Type) | String / Greedy | 10 min | - |
| 10 | Good Weight Lifting Equipment | Greedy / Index Tracking | 10 min | - |
| 12 | Amazon Prime Games – Minimum Starting Health | Greedy | 5 min | - |
| 20 | Maximum Characters That Can Be Removed | String | 10 min | - |

### Medium Problems (12)

| # | Problem | Pattern | Time | LeetCode |
|---|---------|---------|------|----------|
| 1 | Maximum Twin Sum of Linked List | Linked List / Two Pointers | 20 min | [2130](https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list) |
| 2 | Sorting Points by Weight with Minimum Operations | Greedy + Sorting | 25 min | - |
| 4 | Load Balancer Simulation | Segment Tree / Heap / Simulation | 25 min | - |
| 6 | Optimal Permutation for Maximum Information | Greedy + Sorting | 20 min | - |
| 8 | Maximum Packages With Equal Total Cost | Greedy + Hashing | 20 min | - |
| 9 | Beautiful Canvas Problem | Binary Search + Prefix Sum | 30 min | - |
| 11 | Password Variability | String / HashSet | 20 min | - |
| 14 | Database Query Optimization (Host Throughput) | Greedy + Sorting | 15 min | - |
| 15 | Stock k-Consistency Score | Sliding Window | 20 min | - |
| 16 | Circular Server Synchronization | Circular Array / Greedy | 20 min | - |
| 17 | Machine Power Maximization | Greedy / Priority Queue | 25 min | - |
| 19 | Reverse Substring to Make String Smaller | String / Two Pointers | 20 min | - |

### Hard Problems (5)

| # | Problem | Pattern | Time | LeetCode |
|---|---------|---------|------|----------|
| 3 | Array Generator Service | Greedy + Heap + Simulation | 35 min | - |
| 5 | Secured Password Variations | Dynamic Programming / Subsequences | 40 min | - |
| 13 | Sum of Total Strength of Wizards | Monotonic Stack + Prefix Sum | 45 min | [2281](https://leetcode.com/problems/sum-of-total-strength-of-wizards) |
| 18 | Maximum Subarray With GCD > 1 After Changes | GCD + Sliding Window | 40 min | - |

---

## 📖 Detailed Problem Descriptions

### 1. Maximum Twin Sum of Linked List

⭐ **Difficulty**: Medium  
🧠 **Pattern**: Linked List / Two Pointers  
⏱️ **Expected Time**: 20 minutes

**Problem**: Given a linked list, find the maximum sum of pairs (first node + last node, second + second-last, etc.) until the list is empty. Solution must use constant space.

**Example**:
```
Input: 1->2->1->1->8->4
Pairs:
  1 + 4 = 5
  2 + 8 = 10
  1 + 1 = 2
Output: 10 (maximum twin sum)
```

**Reference**: [LeetCode 2130](https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list)

---

### 2. Sorting Points by Weight with Minimum Operations

⭐ **Difficulty**: Medium  
🧠 **Pattern**: Greedy + Sorting  
⏱️ **Expected Time**: 25 minutes

**Problem**: There are `n` points on the x-axis. The `i-th` point has weight `weight[i]` and is initially at position `i`. In one operation, the `i-th` point can move right by distance `dist[i]`. Find the minimum number of operations to sort points by their weights.

**Example**:
```
Input:
  weight = [3, 6, 5, 2]
  dist = [4, 3, 2, 1]
Output: 5
```

---

### 3. Array Generator Service

⭐ **Difficulty**: Hard  
🧠 **Pattern**: Greedy + Heap + Simulation  
⏱️ **Expected Time**: 35 minutes

**Problem**: Given an array `arr`, state string `state` (where `state[i] = '1'` means available, `'0'` means blocked), and integer `m`:

1. Choose any available `arr[i]`
2. Append it to sequence `S`
3. Update state: Any `state[i] = '0'` where `state[i-1] = '1'` becomes `'1'`

Find the **lexicographically largest sequence S** after exactly `m` operations.

**Example**:
```
Input:
  arr = [10, 5, 7, 6]
  state = "0101"
  m = 2
Output: [6, 7]
```

---

### 4. Load Balancer Simulation

⭐ **Difficulty**: Medium  
🧠 **Pattern**: Segment Tree / Heap / Simulation  
⏱️ **Expected Time**: 25 minutes

**Problem**: There are `num_servers` servers numbered `0` to `num_servers-1`. Requests arrive as `request[i]`. Each request must be assigned to the server with the **minimum number of requests** among servers `[0 ... request[i]]`. If multiple servers have the same load, choose the **smallest id**. Return the server id assigned for each request.

---

### 5. Secured Password Variations

⭐ **Difficulty**: Hard  
🧠 **Pattern**: Dynamic Programming / Subsequences  
⏱️ **Expected Time**: 40 minutes

**Problem**: Given customer password `s` and system password `t`, find how many **subsequences of s** are **lexicographically greater than t**. Return answer modulo `10^9 + 7`.

---

### 6. Optimal Permutation for Maximum Information

⭐ **Difficulty**: Medium  
🧠 **Pattern**: Greedy + Sorting  
⏱️ **Expected Time**: 20 minutes

**Problem**: Information gained for permutation `p` is `sum(i * data[p[i]])`. Find the **lexicographically smallest permutation** with maximum information.

**Example**:
```
Input: data = [2, 1, 2]
Output: [2, 1, 3]
```

---

### 7. Maximum Characters Removable (Machine Type)

⭐ **Difficulty**: Easy  
🧠 **Pattern**: String / Greedy  
⏱️ **Expected Time**: 10 minutes

**Problem**: Machine type is defined as `first character + last character`. You may remove characters from start or end while keeping the type unchanged. Return the **maximum number of characters removable**.

**Example**:
```
Input: s = "abade", type = "ae"
Output: Maximum removable characters
```

---

### 8. Maximum Packages With Equal Total Cost

⭐ **Difficulty**: Medium  
🧠 **Pattern**: Greedy + Hashing  
⏱️ **Expected Time**: 20 minutes

**Problem**: Given `itemCost` array, group items into packages such that all packages have equal total cost. Find the maximum number of packages.

**Example**:
```
Input: itemCost = [1, 2, 3, 4, 5]
Packages: [1,4], [2,3], [5]
Output: 3
```

---

### 9. Beautiful Canvas Problem

⭐ **Difficulty**: Medium  
🧠 **Pattern**: Binary Search + Prefix Sum  
⏱️ **Expected Time**: 30 minutes

**Problem**: Given `n` rows, `m` columns, square size `k`, and paint operations, find the **minimum time** when a `k x k` square becomes fully black.

**Example**:
```
Input: n = 2, m = 3, k = 2
Output: 5
```

---

### 10. Good Weight Lifting Equipment

⭐ **Difficulty**: Easy  
🧠 **Pattern**: Greedy / Index Tracking  
⏱️ **Expected Time**: 10 minutes

**Problem**: Given blocks array where `blocks[1] < blocks[i]` for all `i` and `blocks[i] < blocks[n]`, find minimum adjacent swaps required.

**Formula**:
```
moves = idx_min + (n - 1 - idx_max) - (1 if idx_max < idx_min else 0)
```

---

### 11. Password Variability

⭐ **Difficulty**: Medium  
🧠 **Pattern**: String / HashSet  
⏱️ **Expected Time**: 20 minutes

**Problem**: Variability = number of distinct strings formed by reversing exactly **one substring**. Find the variability of a password.

**Example**:
```
Input: password = "abc"
Distinct strings: "abc", "bac", "acb", "cba"
Output: 4
```

---

### 12. Amazon Prime Games – Minimum Starting Health

⭐ **Difficulty**: Easy  
🧠 **Pattern**: Greedy  
⏱️ **Expected Time**: 5 minutes

**Problem**: Given `power[i]` array and `armor`, find minimum starting health.

**Formula**:
```
startingHealth = (sum(power) - max(min(power[i], armor))) + 1
```

---

### 13. Sum of Total Strength of Wizards

⭐ **Difficulty**: Hard  
🧠 **Pattern**: Monotonic Stack + Prefix Sum  
⏱️ **Expected Time**: 45 minutes

**Problem**: Calculate the sum of total strength of wizards using monotonic stack and prefix sum techniques.

**Reference**: [LeetCode 2281](https://leetcode.com/problems/sum-of-total-strength-of-wizards)

---

### 14. Database Query Optimization (Host Throughput)

⭐ **Difficulty**: Medium  
🧠 **Pattern**: Greedy + Sorting  
⏱️ **Expected Time**: 15 minutes

**Problem**: Servers are grouped in clusters of **3**. Cluster throughput = **median of 3 values**. Maximize total throughput.

**Example**:
```
Input: [4, 6, 3, 5, 4, 5]
Output: 9
```

---

### 15. Stock k-Consistency Score

⭐ **Difficulty**: Medium  
🧠 **Pattern**: Sliding Window  
⏱️ **Expected Time**: 20 minutes

**Problem**: You can remove at most `k` elements. Find the **longest subarray where all remaining values are equal**.

---

### 16. Circular Server Synchronization

⭐ **Difficulty**: Medium  
🧠 **Pattern**: Circular Array / Greedy  
⏱️ **Expected Time**: 20 minutes

**Problem**: Servers are arranged in a **circle**. Find minimum time required to synchronize selected servers.

---

### 17. Machine Power Maximization

⭐ **Difficulty**: Medium  
🧠 **Pattern**: Greedy / Priority Queue  
⏱️ **Expected Time**: 25 minutes

**Problem**: Machine power = `min(power units)`. Machines can donate **one power unit**. Maximize total system power.

---

### 18. Maximum Subarray With GCD > 1 After Changes

⭐ **Difficulty**: Hard  
🧠 **Pattern**: GCD + Sliding Window  
⏱️ **Expected Time**: 40 minutes

**Problem**: Given `nums` array and `k` changes, find the **maximum length subarray** such that `GCD(subarray) > 1`.

---

### 19. Reverse Substring to Make String Smaller

⭐ **Difficulty**: Medium  
🧠 **Pattern**: String / Two Pointers  
⏱️ **Expected Time**: 20 minutes

**Problem**: Given string `s` and integer `k`, count number of ways to reverse substring of length `k` such that resulting string becomes **lexicographically smaller**.

---

### 20. Maximum Characters That Can Be Removed

⭐ **Difficulty**: Easy  
🧠 **Pattern**: String  
⏱️ **Expected Time**: 10 minutes

**Problem**: Machine type defined by `first character + last character`. Remove characters from start or end while keeping type unchanged. Return **maximum removable characters**.

---

## 🎯 Preparation Strategy

### Focus on Mastering These Patterns

#### Data Structures
- ✅ **Heap** (Priority Queue) - Top K problems, scheduling
- ✅ **HashMap** - Frequency counting, lookups
- ✅ **Segment Tree** - Range queries
- ✅ **Monotonic Stack** - Next greater/smaller element
- ✅ **Deque** - Sliding window maximum/minimum

#### Algorithms
- ✅ **Greedy** - Optimization problems
- ✅ **Sliding Window** - Subarray/substring problems
- ✅ **Binary Search** - Search in sorted arrays, optimization
- ✅ **Prefix Sum** - Range sum queries
- ✅ **Dynamic Programming** - Optimization, counting
- ✅ **Graph Traversal** - BFS/DFS for trees and graphs

### Study Plan

1. **Week 1**: Focus on Easy and Medium problems
   - Master basic patterns: Greedy, Sliding Window, String manipulation
   - Solve 3-4 problems daily

2. **Week 2**: Tackle Hard problems
   - Deep dive into DP, Monotonic Stack, Advanced Greedy
   - Solve 2-3 problems daily

3. **Week 3**: Timed practice
   - Solve problems within expected time limits
   - Focus on time management
   - Review solutions and optimize

4. **Week 4**: Mock OA practice
   - Simulate real OA conditions
   - Solve 2-3 problems in 90 minutes
   - Review and identify weak areas

---

## 💻 Recommended Practice

### Platforms

- 🟢 **[LeetCode](https://leetcode.com/company/amazon/)** - Amazon tagged problems
- 🟢 **[HackerRank](https://www.hackerrank.com/)** - OA-style problems
- 🟢 **[GeeksforGeeks](https://www.geeksforgeeks.org/)** - Algorithm explanations

### Practice Targets

- ✅ **200+ Medium Problems** - Build strong foundation
- ✅ **50+ Hard Problems** - Prepare for challenging OAs
- ✅ **Timed Contests** - Improve speed and accuracy
- ✅ **Amazon Tagged Questions** - Focus on company-specific patterns

### Tips for Success

1. **Understand Patterns**: Don't memorize solutions, understand the pattern
2. **Time Management**: Practice solving within time limits
3. **Edge Cases**: Always test with edge cases (empty arrays, single elements)
4. **Code Quality**: Write clean, readable code with comments
5. **Review Solutions**: After solving, review optimal solutions
6. **Group by Pattern**: Master one pattern before moving to the next

---

## 📊 Problem Statistics

| Difficulty | Count | Percentage |
|------------|-------|------------|
| Easy | 4 | 20% |
| Medium | 12 | 60% |
| Hard | 5 | 20% |

**Total Problems**: 20  
**Average Solving Time**: ~22 minutes  
**Most Common Pattern**: Greedy (8 problems)

---

## 🤝 Contributing

Found an error or want to add more problems? Contributions are welcome!

1. Fork the repository
2. Add your problem with proper metadata
3. Submit a pull request

---

## 📝 Notes

- ⚠️ **I did not change the questions**, only added metadata to make preparation easier
- 📚 Problems are based on real Amazon OA experiences
- 🔗 LeetCode references are provided where applicable
- ⏱️ Time estimates are based on typical OA performance

---

## 📄 License

This collection is for educational purposes. Problems are from public interview experiences and LeetCode.

---

## 🙏 Acknowledgments

- Problems collected from various interview experiences
- LeetCode for providing platform and references
- Amazon interview candidates who shared their experiences

---

**Good luck with your Amazon OA! 🚀**

*Remember: Focus on understanding patterns, not memorizing solutions. Practice consistently and manage your time effectively.*
