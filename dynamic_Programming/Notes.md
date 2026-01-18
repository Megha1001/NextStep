- Its an optimization over plain recursion
- Idea is to reuse the solution of subproblems when there are overlapping subproblems
- Types
    - Memoization(top down)
    - Tabulation(bottom up)
- Applications of DP
    - Bellman Ford Algorithm
    - Floyd Warshall algorithm
    - Diff Utility(LCS (Longest common subsequence))
    - Search closed words(Edit distance)
    - Resource Allocation(0-1 knapsnack)

- Memoization
    - Dimension are decided based on input/parameter
        - if one input then 1-D array (Fibonacci)
        - If two dimensions then 2-D array (Longest common subsequence)
    - Size 
        - Determined by min and max value in the parameter
            - in Fibonacci : memo[n+1]

- Tabulation -> Leave recursion, have an array or table and build your solution bottom up manner
    - Dimension are decided based on input/parameter
    - Size 
        - Determined by min and max value in the parameter

- Substring
A substring is a contiguous (continuous) part of a string.

- Subsequence
A subsequence keeps order but does NOT need to be contiguous.