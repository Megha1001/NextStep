# 🎯 Amazon SDE2 OA - 5 Day Plan (ACTUAL Questions from LeetCode/Reddit)

## 📋 Overview

This plan is based on **20 actual Amazon OA questions** gathered from LeetCode discussions and Reddit. These are real problems that have appeared in recent Amazon OAs.

**Total Questions**: 20  
**Easy**: 4 | **Medium**: 12 | **Hard**: 4

---

## 📅 DAY 1: Core Patterns & Easy Wins (8-10 hours)

### ⏰ Morning Session (4 hours) - Easy & Medium Problems

**Goal**: Solve 6-7 problems | Build confidence with easier problems first

| # | Problem | Difficulty | Pattern | Time | Priority |
|---|---------|------------|---------|------|----------|
| 1 | Maximum Characters Removable (Machine Type) | Easy | String / Greedy | 10 min | ⭐⭐⭐ HIGH |
| 2 | Amazon Prime Games – Minimum Starting Health | Easy | Greedy | 5 min | ⭐⭐⭐ HIGH |
| 3 | Good Weight Lifting Equipment | Easy | Greedy / Index Tracking | 10 min | ⭐⭐ MEDIUM |
| 4 | Maximum Characters That Can Be Removed | Easy | String | 10 min | ⭐⭐ MEDIUM |
| 5 | Maximum Twin Sum of Linked List | Medium | Linked List / Two Pointers | 20 min | ⭐⭐⭐ HIGH |
| 6 | Database Query Optimization (Host Throughput) | Medium | Greedy + Sorting | 15 min | ⭐⭐⭐ HIGH |
| 7 | Optimal Permutation for Maximum Information | Medium | Greedy + Sorting | 20 min | ⭐⭐ MEDIUM |

**✅ Target**: Complete all 7 problems (90 min total)

**Why Start Here?**
- Easy problems build confidence
- Quick wins help momentum
- These patterns appear frequently

---

### ⏰ Afternoon Session (4 hours) - Medium Problems

**Goal**: Solve 6-7 medium problems | Master common patterns

| # | Problem | Difficulty | Pattern | Time | Priority |
|---|---------|------------|---------|------|----------|
| 8 | Stock k-Consistency Score | Medium | Sliding Window | 20 min | ⭐⭐⭐ HIGH |
| 9 | Maximum Packages With Equal Total Cost | Medium | Greedy + Hashing | 20 min | ⭐⭐⭐ HIGH |
| 10 | Password Variability | Medium | String / HashSet | 20 min | ⭐⭐ MEDIUM |
| 11 | Reverse Substring to Make String Smaller | Medium | String / Two Pointers | 20 min | ⭐⭐ MEDIUM |
| 12 | Circular Server Synchronization | Medium | Circular Array / Greedy | 20 min | ⭐⭐ MEDIUM |
| 13 | Machine Power Maximization | Medium | Greedy / Priority Queue | 25 min | ⭐⭐⭐ HIGH |
| 14 | Sorting Points by Weight with Minimum Operations | Medium | Greedy + Sorting | 25 min | ⭐⭐ MEDIUM |

**✅ Target**: Complete at least 6 problems

---

### ⏰ Evening Session (2 hours) - Review & Pattern Recognition

- [ ] Review all solutions solved today
- [ ] Identify patterns: Greedy, Sliding Window, String manipulation
- [ ] Write down key insights
- [ ] Create pattern cheat sheet

**📊 Day 1 Total**: 13-14 problems solved ✅

---

## 📅 DAY 2: Medium-Hard Problems & Advanced Patterns (8-10 hours)

### ⏰ Morning Session (4 hours) - Medium Problems Continued

**Goal**: Solve 4-5 medium problems | Focus on simulation and heap

| # | Problem | Difficulty | Pattern | Time | Priority |
|---|---------|------------|---------|------|----------|
| 15 | Load Balancer Simulation | Medium | Segment Tree / Heap / Simulation | 25 min | ⭐⭐⭐ HIGH |
| 16 | Beautiful Canvas Problem | Medium | Binary Search + Prefix Sum | 30 min | ⭐⭐⭐ HIGH |
| 17 | Maximum Twin Sum of Linked List (Review) | Medium | Linked List / Two Pointers | 15 min | Review |
| 18 | Stock k-Consistency Score (Review) | Medium | Sliding Window | 15 min | Review |

**✅ Target**: Complete 2-3 new problems + review 2 from Day 1

---

### ⏰ Afternoon Session (4 hours) - Hard Problems

**Goal**: Solve 2-3 hard problems | Master advanced patterns

| # | Problem | Difficulty | Pattern | Time | Priority |
|---|---------|------------|---------|------|----------|
| 19 | Array Generator Service | Hard | Greedy + Heap + Simulation | 35 min | ⭐⭐⭐ HIGH |
| 20 | Secured Password Variations | Hard | Dynamic Programming / Subsequences | 40 min | ⭐⭐⭐ HIGH |
| 21 | Maximum Subarray With GCD > 1 After Changes | Hard | GCD + Sliding Window | 40 min | ⭐⭐ MEDIUM |
| 22 | Sum of Total Strength of Wizards | Hard | Monotonic Stack + Prefix Sum | 45 min | ⭐⭐ MEDIUM |

**✅ Target**: Complete at least 2 hard problems

**Note**: Hard problems are challenging. If stuck > 30 min, review solution and understand approach.

---

### ⏰ Evening Session (2 hours) - Mock Practice

- [ ] Solve 2 random Medium problems from this list in 90 minutes
- [ ] Simulate OA conditions
- [ ] Review time management
- [ ] Identify weak areas

**📊 Day 2 Total**: 6-8 problems solved ✅

---

## 📅 DAY 3: Work Simulation & System Design (6-8 hours)

### ⏰ Morning Session (3 hours) - System Design Concepts

**Goal**: Understand scalability patterns for Work Simulation

**Study Topics** (Check off as you study):

- [ ] **Caching Strategies**
  - Redis vs Memcached
  - When to use caching
  - Cache invalidation strategies

- [ ] **Database Choices**
  - SQL vs NoSQL
  - When to use each
  - Sharding and replication

- [ ] **Load Balancing**
  - Round-robin
  - Consistent hashing
  - Health checks
  - **Related to**: Load Balancer Simulation problem

- [ ] **Message Queues**
  - Kafka vs RabbitMQ vs SQS
  - When to use async processing
  - Message ordering

- [ ] **AWS Services** (Basics)
  - S3 (Object storage)
  - DynamoDB (NoSQL database)
  - EC2 (Compute)
  - Lambda (Serverless)

- [ ] **Scalability Patterns**
  - Horizontal vs Vertical scaling
  - Microservices architecture
  - CAP theorem

**Resources**:
- YouTube: System Design Primer
- Blog: High Scalability
- AWS: Architecture Center

---

### ⏰ Afternoon Session (3 hours) - Work Simulation Scenarios

**Goal**: Practice rating technical decisions

**Practice Scenarios** (Based on actual OA patterns):

1. **Load Balancer Design** (Related to Problem #4)
   - Scenario: Design load balancer for millions of requests
   - Options: Round-robin, Consistent hashing, Least connections
   - **Think**: Scale, latency, distribution
   - **Answer**: Rate based on customer impact and scalability

2. **Database Selection** (Related to Problem #14)
   - Scenario: Choose database for query optimization
   - Options: SQL, NoSQL, In-memory cache
   - **Think**: Query patterns, consistency, scale

3. **System Design Decisions**
   - Scenario: Microservices vs Monolith
   - Scenario: Synchronous vs Asynchronous processing
   - **Think**: Trade-offs, scale, complexity

**Answer Strategy**:
- ✅ Always think: **Customer impact first**
- ✅ Consider: **Scalability and long-term impact**
- ✅ Prefer: **Simple, scalable solutions**
- ✅ Show: **Ownership mindset**

---

### ⏰ Evening Session (2 hours) - Leadership Principles

**Goal**: Master Amazon's 16 Leadership Principles

**Study All 16 Principles**:

1. [ ] **Customer Obsession** - Customer needs first
2. [ ] **Ownership** - Take responsibility, think long-term
3. [ ] **Bias for Action** - Act quickly, iterate
4. [ ] **Are Right, A Lot** - Data-driven decisions
5. [ ] **Invent and Simplify** - Simple solutions
6. [ ] **Learn and Be Curious** - Continuous learning
7. [ ] **Hire and Develop the Best** - Team development
8. [ ] **Insist on Highest Standards** - Quality focus
9. [ ] **Think Big** - Long-term vision
10. [ ] **Earn Trust** - Honest communication
11. [ ] **Dive Deep** - Understand deeply
12. [ ] **Have Backbone; Disagree and Commit** - Stand ground, commit
13. [ ] **Frugality** - Cost-conscious
14. [ ] **Strive to be Earth's Best Employer** - Team culture
15. [ ] **Success and Scale Bring Responsibility** - Broader impact
16. [ ] **Deliver Results** - Execution focus

**Practice**: Think of 1-2 examples from your experience for each principle

**📊 Day 3 Total**: System design knowledge + Leadership Principles ✅

---

## 📅 DAY 4: Mock OA #1 & Targeted Practice (8-10 hours)

### ⏰ Morning Session (2 hours) - Work Style Survey Prep

**Goal**: Understand what Amazon looks for

**Survey 1: Software Engineering Approach**
- Questions about: Code quality, testing, debugging, architecture
- **Answer as**: Someone who values quality, scalability, customer impact

**Survey 2: General Work Approach**
- Questions about: Problem-solving, teamwork, priorities
- **Answer as**: Customer-focused, ownership mindset, bias for action

**Sample Questions**:
- "When I encounter a bug, I..."
  - ✅ Answer: Investigate immediately, prioritize customer impact
- "When choosing between solutions, I..."
  - ✅ Answer: Consider scalability, simplicity, customer needs
- "When facing a difficult problem, I..."
  - ✅ Answer: Take action, iterate, learn quickly

---

### ⏰ Midday Session (4 hours) - Full Mock OA #1

**Goal**: Complete full OA simulation using ACTUAL Amazon problems

**Setup**:
- [ ] Quiet environment
- [ ] Timer ready
- [ ] No distractions
- [ ] Water/snacks ready

**Mock OA Structure**:

#### **1. Coding Challenge (90 minutes)** ⏱️ TIMED

**Problem 1**: Medium difficulty (40 min)
- **Suggested**: Maximum Twin Sum of Linked List (#1)
- **OR**: Stock k-Consistency Score (#15)
- **OR**: Load Balancer Simulation (#4)

**Problem 2**: Medium-Hard difficulty (45 min)
- **Suggested**: Array Generator Service (#3) - Hard
- **OR**: Beautiful Canvas Problem (#9) - Medium
- **OR**: Machine Power Maximization (#17) - Medium

**Buffer**: 5 min for review/testing

#### **2. Work Simulation (15 minutes)** ⏱️ UNTIMED
- Practice rating 3-4 technical decision scenarios
- Think aloud about trade-offs
- Rate options based on customer impact and scalability

#### **3. Work Style Surveys (10 minutes)** ⏱️ UNTIMED
- Answer as you would in real OA
- Think customer-first
- Show ownership and bias for action

**After Mock OA**:
- [ ] Review what went well
- [ ] Identify areas for improvement
- [ ] Note time management issues
- [ ] List weak patterns/topics

---

### ⏰ Afternoon Session (2 hours) - Targeted Practice

**Focus on Weak Areas**:

- [ ] If struggled with coding: Solve 2-3 more problems from weak area
- [ ] If struggled with work simulation: Review more system design scenarios
- [ ] If struggled with surveys: Review Leadership Principles again

**Common Weak Areas to Address**:
- [ ] Greedy problems → Review greedy patterns
- [ ] DP problems → Review DP patterns (Problem #5)
- [ ] Sliding Window → Practice more (Problem #15)
- [ ] System design → Review scalability patterns

---

### ⏰ Evening Session (2 hours) - Review & Refine

- [ ] Review all solved problems (quick recap)
- [ ] Create cheat sheet of patterns/formulas
- [ ] Review system design decision frameworks
- [ ] Update pattern notes

**📊 Day 4 Total**: Mock OA completed + targeted practice ✅

---

## 📅 DAY 5: Final Prep & Confidence Building (6-8 hours)

### ⏰ Morning Session (3 hours) - Final Mock OA #2

**Goal**: Second full practice run with different problems

**Mock OA Structure**:

#### **1. Coding Challenge (90 minutes)** ⏱️ TIMED

**Problem 1**: Easy-Medium (30 min)
- **Suggested**: Maximum Packages With Equal Total Cost (#8)
- **OR**: Database Query Optimization (#14)

**Problem 2**: Medium-Hard (45 min)
- **Suggested**: Secured Password Variations (#5) - Hard
- **OR**: Maximum Subarray With GCD > 1 (#18) - Hard
- **OR**: Beautiful Canvas Problem (#9) - Medium

**Buffer**: 15 min for review/testing

#### **2. Work Simulation (15 minutes)** ⏱️ UNTIMED
- Practice 2-3 new scenarios
- Focus on clear reasoning
- Rate options confidently

#### **3. Work Style Surveys (10 minutes)** ⏱️ UNTIMED
- Answer confidently
- Show alignment with Amazon values

**After Mock OA**:
- [ ] Compare with Mock OA #1
- [ ] Note improvements
- [ ] Final weak areas to review

---

### ⏰ Midday Session (2 hours) - Quick Review

**Last-Minute Checklist**:

- [ ] Review top 10 problems from actual OA list (quick read)
  1. Maximum Characters Removable (#7) - Easy
  2. Amazon Prime Games (#12) - Easy
  3. Maximum Twin Sum (#1) - Medium
  4. Database Query Optimization (#14) - Medium
  5. Stock k-Consistency Score (#15) - Medium
  6. Maximum Packages (#8) - Medium
  7. Load Balancer Simulation (#4) - Medium
  8. Machine Power Maximization (#17) - Medium
  9. Array Generator Service (#3) - Hard
  10. Beautiful Canvas (#9) - Medium

- [ ] Review system design decision patterns
- [ ] Review Leadership Principles (quick refresher)
- [ ] Review your cheat sheet

**Key Patterns to Remember**:
- Greedy → Optimization problems
- Sliding Window → Subarray problems
- Heap/Priority Queue → Top-k, scheduling
- DP → Counting, optimization
- String manipulation → Two pointers, hash map

---

### ⏰ Afternoon Session (2 hours) - Mental Preparation

**Before OA Day**:

- [ ] Set up quiet test environment
- [ ] Test internet connection
- [ ] Prepare water/snacks
- [ ] Review OA logistics (7-day window, submission process)
- [ ] Get good sleep tonight (8 hours)

**Mindset**:
- ✅ You've prepared well with actual OA questions
- ✅ Trust your problem-solving process
- ✅ Read problems carefully
- ✅ Manage time wisely
- ✅ Stay calm and focused

---

### ⏰ Evening - Rest & Relax

- [ ] Light review only (30 min max)
- [ ] Get good sleep (8 hours)
- [ ] Don't cram - rest is important
- [ ] Trust your preparation

**📊 Day 5 Total**: Second mock OA + final review ✅

---

## 📊 5-Day Summary

### **Total Problems Solved**: 20+ problems
- Day 1: 13-14 problems (Easy + Medium)
- Day 2: 6-8 problems (Medium + Hard)
- Day 3: 0 problems (concepts)
- Day 4: 2-5 problems (mock + practice)
- Day 5: 2 problems (mock)

### **Total Mock OAs**: 2 complete practice runs

### **Key Achievements**:
- ✅ Solved all 20 actual Amazon OA problems
- ✅ Mastered patterns: Greedy, Sliding Window, Heap, DP, String manipulation
- ✅ Understand system design concepts
- ✅ Prepared for Work Simulation scenarios
- ✅ Studied all Leadership Principles
- ✅ Completed 2 full mock OAs with actual problems

---

## 🎯 Problem Priority Ranking

### **Must Solve (High Priority)** ⭐⭐⭐
1. Maximum Characters Removable (#7) - Easy, 10 min
2. Amazon Prime Games (#12) - Easy, 5 min
3. Maximum Twin Sum (#1) - Medium, 20 min
4. Database Query Optimization (#14) - Medium, 15 min
5. Stock k-Consistency Score (#15) - Medium, 20 min
6. Maximum Packages (#8) - Medium, 20 min
7. Load Balancer Simulation (#4) - Medium, 25 min
8. Machine Power Maximization (#17) - Medium, 25 min
9. Array Generator Service (#3) - Hard, 35 min
10. Beautiful Canvas (#9) - Medium, 30 min

### **Should Solve (Medium Priority)** ⭐⭐
11. Good Weight Lifting Equipment (#10) - Easy, 10 min
12. Maximum Characters That Can Be Removed (#20) - Easy, 10 min
13. Optimal Permutation (#6) - Medium, 20 min
14. Password Variability (#11) - Medium, 20 min
15. Reverse Substring (#19) - Medium, 20 min
16. Circular Server Synchronization (#16) - Medium, 20 min
17. Sorting Points by Weight (#2) - Medium, 25 min

### **Nice to Solve (Lower Priority)** ⭐
18. Secured Password Variations (#5) - Hard, 40 min
19. Maximum Subarray With GCD (#18) - Hard, 40 min
20. Sum of Total Strength (#13) - Hard, 45 min

---

## ✅ Final Checklist (Day Before OA)

### **Technical**:
- [ ] Solved all 20 actual Amazon OA problems
- [ ] Completed 2 mock OAs with actual problems
- [ ] Reviewed system design concepts
- [ ] Studied Leadership Principles
- [ ] Created cheat sheet

### **Logistics**:
- [ ] Quiet test environment ready
- [ ] Internet connection tested
- [ ] Browser updated (Chrome/Firefox)
- [ ] Water and snacks ready
- [ ] Phone on silent
- [ ] All applications closed except browser

### **Mental**:
- [ ] Confident in problem-solving approach
- [ ] Understand time management strategy
- [ ] Ready for all sections
- [ ] Good sleep planned

---

## 💡 Pro Tips

1. **Time Management**: Don't spend more than allocated time on one problem
2. **Code Quality**: Write clean, readable code with comments
3. **Edge Cases**: Always test with edge cases
4. **System Design**: Think scalability, cost, simplicity
5. **Work Styles**: Answer as if you already work at Amazon
6. **Practice**: Do full mock OAs under timed conditions

---

## 🚨 Common Mistakes to Avoid

1. ❌ Spending too long on one problem
2. ❌ Not reading problem carefully
3. ❌ Jumping to code without planning
4. ❌ Ignoring edge cases
5. ❌ Over-engineering solutions
6. ❌ Not managing time
7. ❌ Forgetting work simulation prep
8. ❌ Cramming on Day 5

---

**You've got this! You're practicing with actual Amazon OA questions. Stay focused, manage your time, and trust your preparation. Good luck! 🚀**

*Remember: These are real questions that have appeared in Amazon OAs. Master these patterns and you'll be well-prepared!*
