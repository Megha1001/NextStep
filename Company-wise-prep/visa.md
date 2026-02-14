1. Problem 1: Sum of Uppercase and Lowercase Characters

You are given a string consisting of alphabets and other characters.
The task was to calculate the sum of ASCII values of all lowercase and uppercase letters separately.

```

import java.util.*;

class Main {
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();
        
        int[] res = computeAscii(input);
        
    }
    
    public static int[] computeAscii(String input){
        int lowerCaseSum = 0;
        int upperCaseSum = 0;
        
        for(char ch : input.toCharArray()){
            if(Character.isLowerCase(ch)){
                lowerCaseSum += ch;
            }else if(Character.isUpperCase(ch)){
                upperCaseSum += ch;
            }
        }
        
        return new int[]{lowerCaseSum, upperCaseSum};
        
    }

}
```

2. Easy — Problem 2: The Bird and the Trees
This one was more of a simulation-based problem.

You are given an array of integers, where: Each index represents a tree, and the value at that index represents the number of branches on that tree.

A bird starts from a given position, where the value is always 0.
It first moves right, collecting all branches from the first non-zero tree it encounters, and then returns to the starting position.
Next, it moves left, following the same rule.

The bird alternates between right and left directions, continuing this process until it collects at least 100 branches in total.
You need to determine how many trips it takes for the bird to reach this target.

This problem tested array traversal, directional iteration, and simulation logic.


```

import java.util.*;

class Main {
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int [] trees = new int[n];
        
        for(int i = 0; i < n; i++){
            trees[i] = sc.nextInt();
        }
        
        int startIdx = sc.nextInt();
        
        sc.close();
        
        int trips = countTrips(trees, startIdx);
       
        System.out.println("Trips : "+trips);
    }
    
    public static int countTrips(int [] trees, int startIdx){
        int totalBranches = 0;
        int trips = 0;
        boolean moveRight = true;
        int n = trees.length;
        
        while(totalBranches < 100){
            boolean collected = false;
            if(moveRight){
                for(int i = startIdx + 1; i < n; i++){
                    if(trees[i] > 0){
                        totalBranches += trees[i];
                        trees[i] = 0;
                        ++trips;
                        collected = true;
                        break;
                    }
                }
            }
            else{
                for(int i = startIdx - 1; i >= 0 ; i--){
                    if(trees[i] > 0){
                        totalBranches += trees[i];
                        trees[i] = 0;
                        collected = true;
                        ++trips;
                        break;
                    }
                }
            }
            
            if(!collected){
                return -1; // no branch with value > 0;
            }
            
            moveRight = !moveRight;
        }
        
        
        return trips;
    }

}
```

3.Path Sum III: https://leetcode.com/problems/path-sum-iii/

```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(0L,1);
        return pathSum2(root, 0, targetSum, map);
    }

    private int pathSum2(TreeNode node, long runningSum, int targetSum, HashMap<Long, Integer>map){

        if(node == null){
            return 0;
        }

        runningSum += node.val;
        int count = map.getOrDefault(runningSum - targetSum, 0);
        map.put(runningSum, map.getOrDefault(runningSum, 0) + 1);

        count += pathSum2(node.left, runningSum, targetSum, map)
                + pathSum2(node.right, runningSum, targetSum, map);

        //remove-> since considering path
        map.put(runningSum, map.get(runningSum) - 1);

        return count;

    }
}
```

4. Climbing Stairs: https://leetcode.com/problems/climbing-stairs/
```
class Solution {
    public int climbStairs(int n) {
        //memoization
        int memo[] = new int [n+1];
        return dfs(n-1, memo) + dfs(n-2, memo);
    }

    private int dfs(int n, int []memo){
        if(n == 0){
            return 1;
        }

        if(n < 0){
            return 0;
        }
        
        if(memo[n] != 0){
            return memo[n];
        }

        memo[n] = dfs(n-1, memo) + dfs(n-2, memo);
        return memo[n];
    }
}
```

5. Validate IP Address : https://leetcode.com/problems/validate-ip-address/description/

```
class Solution {
    public String validIPAddress(String queryIP) {
        if(queryIP == null || queryIP.length() == 0){
            return "Neither";
        }
        
        if(queryIP.contains(".")){
            return isValidIPV4(queryIP) ? "IPv4" : "Neither";
        }else if(queryIP.contains(":")){
            return isValidIPV6(queryIP) ? "IPv6" : "Neither";
        }

        return "Neither";

    }

    private boolean isValidIPV4(String input){
        String [] parts = input.split("\\.", -1); //-1 to keep trailing space and \\. to split on . as bydefault . means anything
        if(parts.length != 4){
            return false;
        }

        for(String part : parts){
            if(part.length() == 0 || part.length() > 3){
                return false;
            }

            if(part.length() > 1 && part.charAt(0) == '0'){
                return false;
            }

            for(char ch : part.toCharArray()){
                if(!Character.isDigit(ch)){
                    return false;
                }
            }

            int val;
            try{
                val = Integer.parseInt(part);
            }catch(NumberFormatException nfe){
                return false;
            }

            if(val < 0 || val > 255){
                return false;
            }

        }

        return true;

    }

    private boolean isValidIPV6(String input){
        String parts[] = input.split(":", -1);

        if(parts.length != 8){
            return false;
        }

        for(String part : parts){
            if(part.length() == 0 || part.length() > 4){
                return false;
            }

            for(char ch : part.toCharArray()){
                boolean isDigit = Character.isDigit(ch);
                boolean isLowerHex = ch >= 'a' && ch <= 'f';
                boolean isUpperHex = ch >= 'A' && ch <= 'F';

                if(!(isDigit || isLowerHex || isUpperHex)){
                    return false;
                }
            }

        }

        return true;
    }
}
```

6. Sort Integers by The Number of 1 Bits: https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits/
```
class Solution {
    public int[] sortByBits(int[] arr) {
        Arrays.sort(arr);

        List<List<Integer>> list = new ArrayList<>();

        for(int i = 0; i <= 500; i++){
            list.add(new ArrayList<>());
        }

        int n = arr.length;

        for(int i = 0; i < n; i++){
            int idx = countBits(arr[i]);
            list.get(idx).add(arr[i]);
        }

        int res [] = new int[n];
        int idx = 0;
        for(List<Integer> l1 : list){
            for(int element : l1){
                res[idx ++] = element;
            }
        }

        return res;
        
    }
    
    //Brain kernghans's algo
    private int countBits(int input){
        int count = 0;
        while(input != 0){
            count++;
            input = input &(input-1);
        }
        return count;
    }
}
```

Optimized
```
public int[] sortByBits(int[] arr) {
        int n = arr.length;
        Integer temp[] = new Integer[n];

        //convert int[] -> Integer[]
        for(int i = 0; i < n; i++){
            temp[i] = arr[i];
        }

        //custom sort
        Arrays.sort(temp, (a,b)-> {
            int bitsA = Integer.bitCount(a);
            int bitsB = Integer.bitCount(b);

            if(bitsA == bitsB){
                return a - b; //same then return natural
            }

            return bitsA - bitsB;
        });


        //copy temp to arr
        for(int i = 0; i < n; i++){
            arr[i] = temp[i];
        }


        return arr;
}
```

7. Ransom Note : https://leetcode.com/problems/ransom-note/description/
```
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote.length() > magazine.length()){
            return false;
        }

        int [] list = new int[26];

        for(char ch : magazine.toCharArray()){
            ++list[ch - 'a'];
        }

        for(char ch : ransomNote.toCharArray()){
            if(list[ch - 'a'] > 0){
                --list[ch - 'a'];
            }else {
                return false;
            }
        }

        return true;
    }
}
```

8. Search a 2D Matrix : https://leetcode.com/problems/search-a-2d-matrix/description/

```
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        int i = 0;
        int j = COLS - 1;

        while(i < ROWS && j >= 0){
            int element = matrix[i][j];
            if(element == target){
                return true;
            }else if (element > target){
                --j;
            }else {
                ++i;
            }
        }

        return false;
        
    }
}
```

8. Minimum Falling Path Sum  : https://leetcode.com/problems/minimum-falling-path-sum/description/
```
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        /*
        dp[r][c] = matrix[r][c] +
                min(
                    dp[r-1][c],
                    dp[r-1][c-1],
                    dp[r-1][c+1]
                )
        */

        int n = matrix.length;
        int prev[] = new int[n];

        //first row
        for(int c = 0; c < n; c++){
            prev[c] = matrix[0][c];
        }


        for(int r = 1; r < n; r++){
            int curr[] = new int[n];
            for(int c = 0; c < n; c++){
                int minAbove = prev[c];

                if(c > 0){
                    minAbove = Math.min(minAbove, prev[c - 1]);
                }

                if(c < n - 1){
                    minAbove = Math.min(minAbove, prev[c + 1]);
                }

                curr[c] = matrix[r][c] + minAbove;
            }
            prev = curr;
        }

        int res = Integer.MAX_VALUE;
        for(int val : prev){
            res = Math.min(res, val);
        }

        return res;

        
    }
}
```

9. Merge Two Sorted Lists : https://leetcode.com/problems/merge-two-sorted-lists/description/

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyNode  = new ListNode(-1);
        ListNode tail = dummyNode;

        while(list1!=null && list2!=null){
            if(list1.val <= list2.val){
                tail.next = list1;
                list1 = list1.next;
            }
            else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next; //always;
        }

        tail.next = list1!=null ? list1 : list2;

        return dummyNode.next;
        
    }
}
```

10. Two Sum: https://leetcode.com/problems/two-sum/

```
class Solution {
    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            int diff = target - nums[i];
            if(map.containsKey(diff)){
                return new int[]{map.get(diff), i};
            }

            map.put(nums[i], i);
        }

        return new int[]{};
        
    }
}
```

11. Longest Common Prefix : https://leetcode.com/problems/longest-common-prefix/description/

```
class Solution {
    public String longestCommonPrefix(String[] strs) {

        for(int i = 0 ; i < strs[0].length(); i++){
            for(String s : strs){
                if(i == s.length() || s.charAt(i) != strs[0].charAt(i)){
                    return s.substring(0,i);
                }
            }
        }

        return strs[0];
        
    }
}
```

12. Longest Palindromic Substring : https://leetcode.com/problems/longest-palindromic-substring/

```
class Solution {
    public String longestPalindrome(String s) {
        String res = "";
        int resLength = 0;
        int n = s.length();

        for(int i = 0; i < n; i++){
            int l = i;
            int r = i;

            //for odd length
            while(l >= 0 && r < n && s.charAt(l) == s.charAt(r)){
                if(r - l + 1 > resLength){
                    res = s.substring(l, r + 1);
                    resLength = r - l + 1;
                }
                --l;
                ++r;
            }

            //for even length
            l = i;
            r = i+1;
            while(l >= 0 && r < n && s.charAt(l) == s.charAt(r)){
                if(r - l + 1 > resLength){
                    res = s.substring(l, r + 1);
                    resLength = r - l + 1;
                }
                --l;
                ++r;
            }
        }


        return res;       
    }
}
```

13. Longest non repeating substring : https://leetcode.com/problems/longest-substring-without-repeating-characters/

```
class Solution {
    public int lengthOfLongestSubstring(String s) {
        //Two pointer approach
        int res = 0;
        HashSet<Character> set = new HashSet<>();
        int l = 0;

        for(int r = 0; r < s.length(); r++){
            while(set.contains(s.charAt(r))){
                set.remove(s.charAt(l));
                ++l;
            }
            set.add(s.charAt(r));
            res = Math.max(res, r - l + 1);
        }

        return res;
    }
}
```

14. Combination Sum : https://leetcode.com/problems/combination-sum/description/
```
class Solution {
    List<List<Integer>> res;
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        backTrack(nums, target, curr, 0);
        return res;
    }

    private void backTrack(int nums[], int target, List<Integer>curr, int i){
        if(target == 0){
            res.add(new ArrayList<>(curr));
            return;
        }

        if(target < 0 || i >= nums.length){
            return;
        }

        //consider
        curr.add(nums[i]);
        backTrack(nums, target - nums[i], curr, i);

        //not consider
        curr.remove(curr.size() - 1);
        backTrack(nums, target, curr, i+1);

    } 
}
```

15. Hit Counter
https://www.naukri.com/code360/problems/hit-counter_1230785?leftPanelTabValue=PROBLEM

```
import java.util.* ;
import java.io.*; 
public class hitCounter {
    private Queue<Integer> queue;

    public hitCounter(){
        queue = new ArrayDeque<>();
    }

    void hit(int timestamp) {
        queue.offer(timestamp);
    }

    int getHits(int timestamp) {
        while(!queue.isEmpty() && timestamp - queue.peek() > 300){
            queue.poll();
        }
        return queue.size();
    }
}
```

16. Coin Change
https://neetcode.io/problems/coin-change/question

```
class Solution {
    public int coinChange(int[] coins, int amount) {
        int dp[] = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for(int i = 1; i <= amount; i++){
            for(int coin : coins){
                if(i - coin >= 0){
                    dp[i] = Math.min(dp[i], 1 + dp[i-coin]);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
```