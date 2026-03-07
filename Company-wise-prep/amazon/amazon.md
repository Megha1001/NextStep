* ⭐ Difficulty
* 🧠 Pattern / Topic
* ⏱ Expected OA solving time
* 📊 Better structure for revision

I **did not change the questions**, only added metadata to make preparation easier.

---

```markdown
# Amazon SDE Online Assessment Preparation

This repository contains a collection of **Amazon Online Assessment (OA)** problems gathered from previous interview experiences.

The questions are categorized by **difficulty, topic, and pattern** to make preparation structured and efficient.

---

# Table of Contents

1. Maximum Twin Sum of Linked List  
2. Sorting Points by Weight with Minimum Operations  
3. Array Generator Service  
4. Load Balancer Simulation  
5. Secured Password Variations  
6. Optimal Permutation for Maximum Information  
7. Maximum Characters Removable (Machine Type)  
8. Maximum Packages With Equal Total Cost  
9. Beautiful Canvas Problem  
10. Good Weight Lifting Equipment  
11. Password Variability  
12. Amazon Prime Games – Minimum Health  
13. Sum of Total Strength of Wizards  
14. Database Query Optimization (Host Throughput)  
15. Stock k-Consistency Score  
16. Circular Server Synchronization  
17. Machine Power Maximization  
18. Maximum Subarray With GCD > 1 After Changes  
19. Reverse Substring to Make String Lexicographically Smaller  
20. Maximum Characters Removed (Machine Type Variant)

---

# 1. Maximum Twin Sum of a Linked List

⭐ Difficulty: Medium  
🧠 Pattern: Linked List / Two Pointers  
⏱ Expected Time: 20 minutes  

### Problem

Given a linked list head, reading the data from the first and last node, find the max of the pairs until the linked list is empty, the solution needs to be constant space.

Example

```

1->2->1->1->8->4

```

Pairs

```

1 + 4 = 5
2 + 8 = 10
1 + 1 = 2

```

Maximum twin sum

```

10

```

Reference  
https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list

---

# 2. Sorting Points by Weight with Minimum Operations

⭐ Difficulty: Medium  
🧠 Pattern: Greedy + Sorting  
⏱ Expected Time: 25 minutes  

### Problem

Developers are working on a new sorting algorithm for points on the x-axis of the coordinate system.

There are n points.

The ith point initially has a weight of:

```

weight[i]

```

and is located at position `i`.

In a single operation the ith point can be moved to the right by a distance of:

```

dist[i]

```

Given the weight and dist, find the **minimum number of operations required to sort the points by their weights**.

Example

```

weight = [3,6,5,2]
dist   = [4,3,2,1]

```

Answer

```

5

```

---

# 3. Array Generator Service

⭐ Difficulty: Hard  
🧠 Pattern: Greedy + Heap + Simulation  
⏱ Expected Time: 35 minutes  

### Problem

The service takes the following input parameters:

```

arr
state
m

```

Where

```

state[i] = '1' → available
state[i] = '0' → blocked

```

Operations:

1. Choose any available `arr[i]`
2. Append it to sequence `S`
3. Update state:

```

Any state[i] = '0' where state[i-1] = '1' becomes '1'

```

Goal

Find the **lexicographically largest sequence S** after exactly `m` operations.

Example

```

arr = [10,5,7,6]
state = "0101"
m = 2

```

Output

```

S = [6,7]

```

---

# 4. Load Balancer Simulation

⭐ Difficulty: Medium  
🧠 Pattern: Segment Tree / Heap / Simulation  
⏱ Expected Time: 25 minutes  

### Problem

There are

```

num_servers

```

servers numbered:

```

0 ... num_servers-1

```

Requests arrive as:

```

request[i]

```

Each request must be assigned to the server with the **minimum number of requests** among servers:

```

[0 ... request[i]]

```

If multiple servers have same load choose the **smallest id**.

Return the server id assigned for each request.

---

# 5. Secured Password Variations

⭐ Difficulty: Hard  
🧠 Pattern: Dynamic Programming / Subsequences  
⏱ Expected Time: 40 minutes  

### Problem

Two passwords are generated.

Customer password:

```

s

```

System password:

```

t

```

Find how many **subsequences of s** are **lexicographically greater than t**.

Return answer modulo

```

10^9 + 7

```

---

# 6. Optimal Permutation for Maximum Information

⭐ Difficulty: Medium  
🧠 Pattern: Greedy + Sorting  
⏱ Expected Time: 20 minutes  

Information gained for permutation `p`:

```

sum(i * data[p[i]])

```

Goal

Find the **lexicographically smallest permutation** with maximum information.

Example

```

data = [2,1,2]

```

Answer

```

[2,1,3]

```

---

# 7. Maximum Characters Removable (Machine Type)

⭐ Difficulty: Easy  
🧠 Pattern: String / Greedy  
⏱ Expected Time: 10 minutes  

Machine type:

```

first character + last character

```

Example

```

s = "abade"
type = "ae"

```

You may remove characters from start or end while keeping the type unchanged.

Return the **maximum number of characters removable**.

---

# 8. Maximum Packages With Equal Total Cost

⭐ Difficulty: Medium  
🧠 Pattern: Greedy + Hashing  
⏱ Expected Time: 20 minutes  

Example

```

itemCost = [1,2,3,4,5]

```

Packages

```

[1,4]
[2,3]
[5]

```

Answer

```

3

```

---

# 9. Beautiful Canvas Problem

⭐ Difficulty: Medium  
🧠 Pattern: Binary Search + Prefix Sum  
⏱ Expected Time: 30 minutes  

Given

```

n rows
m columns
k square size
paint operations

```

Find the **minimum time** when a `k x k` square becomes fully black.

Example

```

n = 2
m = 3
k = 2

```

Answer

```

5

```

---

# 10. Good Weight Lifting Equipment

⭐ Difficulty: Easy  
🧠 Pattern: Greedy / Index Tracking  
⏱ Expected Time: 10 minutes  

Conditions

```

blocks[1] < blocks[i] for all i
blocks[i] < blocks[n]

```

Goal

Minimum adjacent swaps required.

Formula

```

moves =
idx_min + (n - 1 - idx_max)

* (1 if idx_max < idx_min else 0)

```

---

# 11. Password Variability

⭐ Difficulty: Medium  
🧠 Pattern: String / HashSet  
⏱ Expected Time: 20 minutes  

Definition

Variability = number of distinct strings formed by reversing exactly **one substring**.

Example

```

password = "abc"

```

Result

```

4

```

Distinct strings

```

abc
bac
acb
cba

```

---

# 12. Amazon Prime Games – Minimum Starting Health

⭐ Difficulty: Easy  
🧠 Pattern: Greedy  
⏱ Expected Time: 5 minutes  

Given

```

power[i]
armor

```

Formula

```

startingHealth =
(sum(power) - max(min(power[i], armor))) + 1

```

---

# 13. Sum of Total Strength of Wizards

⭐ Difficulty: Hard  
🧠 Pattern: Monotonic Stack + Prefix Sum  
⏱ Expected Time: 45 minutes  

Reference

https://leetcode.com/problems/sum-of-total-strength-of-wizards

---

# 14. Database Query Optimization (Host Throughput)

⭐ Difficulty: Medium  
🧠 Pattern: Greedy + Sorting  
⏱ Expected Time: 15 minutes  

Servers grouped in clusters of **3**.

Cluster throughput = **median of 3 values**.

Goal

Maximize total throughput.

Example

```

[4,6,3,5,4,5]

```

Answer

```

9

```

---

# 15. Stock k-Consistency Score

⭐ Difficulty: Medium  
🧠 Pattern: Sliding Window  
⏱ Expected Time: 20 minutes  

You can remove at most `k` elements.

Find the **longest subarray where all remaining values are equal**.

---

# 16. Circular Server Synchronization

⭐ Difficulty: Medium  
🧠 Pattern: Circular Array / Greedy  
⏱ Expected Time: 20 minutes  

Servers are arranged in a **circle**.

Goal

Minimum time required to synchronize selected servers.

---

# 17. Machine Power Maximization

⭐ Difficulty: Medium  
🧠 Pattern: Greedy / Priority Queue  
⏱ Expected Time: 25 minutes  

Machine power =

```

min(power units)

```

Machines can donate **one power unit**.

Goal

Maximize total system power.

---

# 18. Maximum Subarray With GCD > 1 After Changes

⭐ Difficulty: Hard  
🧠 Pattern: GCD + Sliding Window  
⏱ Expected Time: 40 minutes  

Given

```

nums
k changes

```

Find the **maximum length subarray** such that

```

GCD(subarray) > 1

```

---

# 19. Reverse Substring to Make String Smaller

⭐ Difficulty: Medium  
🧠 Pattern: String / Two Pointers  
⏱ Expected Time: 20 minutes  

Given

```

string s
integer k

```

Count number of ways to reverse substring of length `k` such that resulting string becomes **lexicographically smaller**.

---

# 20. Maximum Characters That Can Be Removed

⭐ Difficulty: Easy  
🧠 Pattern: String  
⏱ Expected Time: 10 minutes  

Machine type defined by

```

first character + last character

```

Remove characters from start or end while keeping type unchanged.

Return **maximum removable characters**.

---

# Amazon OA Preparation Strategy

Focus on mastering these patterns:

### Data Structures

```

Heap
HashMap
Segment Tree
Monotonic Stack
Deque

```

### Algorithms

```

Greedy
Sliding Window
Binary Search
Prefix Sum
Dynamic Programming
Graph Traversal

```

---

# Recommended Practice

Platforms

```

LeetCode
HackerRank
GeeksforGeeks

```

Target

```

Solve 200+ Medium Problems
Practice timed contests
Focus on Amazon tagged questions

```

---
```
