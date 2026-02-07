# NEETCODE150 : https://neetcode.io/practice/practice/neetcode150
https://neetcode.io/roadmap
1. Best Time to Buy and Sell Stock
- Brute Force - TC : O(N*N)
    - i=0 to n
        - j=i+1 to N
            - Compute Max

- Two Pointer Approach
```
public int maxProfit(int[] prices) {

        //Brute force
        /*int res = 0;
        int n = prices.length;
        for(int i=0; i<n; i++){
            int currMax = 0;
            for(int j=i+1; j<n; j++){
                currMax = Math.max(currMax, prices[j]-prices[i]);
            }
            res = Math.max(res, currMax);
        }

        return res;
        */

        //Two Pointer - Time Complexity : O(N), Auxiliary Space : O(1)
        int buyDay = 0;
        int sellDay = 1;
        int maxProfit = 0;

        while(sellDay < prices.length){
            if(prices[buyDay] < prices[sellDay]){
                maxProfit = Math.max(maxProfit, prices[sellDay] - prices[buyDay]);
            }else if(prices[buyDay] > prices[sellDay]){
                buyDay = sellDay;
            }
            ++sellDay;
        }

        return maxProfit;
        
}
```

2. Valid Parentheses

Use stack

```
class Solution {
    public boolean isValid(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for(char c : s.toCharArray()){
            switch(c){
                case '{':
                case '(':
                case '[':
                    stack.push(c);
                    break;
                case '}':
                case ')':
                case ']':
                    if(!stack.isEmpty() && (((c=='}') && (stack.peek()=='{')) || ((c==')') && (stack.peek()=='(')) || ((c==']') && (stack.peek()=='[')))){
                        stack.pop();
                        break;
                    }else{
                        return false;
                    }
            }
        }

        if(stack.isEmpty()){
            return true;
        }

        return false;
        
    }
}
```

3. Merge Two Sorted Linked Lists
Use Merge sort but maintain two pointer -> head and tail.
Note : Update the tail reference as well
But there is one improved approach to use dummy node

```
public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //optimized one
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while(list1!=null && list2!=null){
            if(list1.val <= list2.val){
                tail.next = list1;
                list1 = list1.next;
            }else{
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        tail.next = list1!=null ? list1 : list2;

        return dummy.next;
        
    }
```

4. Maximum Depth of Binary Tree
-> caculate height
```
public int maxDepth(TreeNode root) {

        if(root==null){
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        
}
```

5. Balanced Binary Tree

```
//Solution 1 : Do Level order traversal and for each node calculate height_lst and height_rst if at any point of time difference >1 return false;

    /*
     Recursion based - TC : O(N*N)
        1. calculate height of every node
    */

    // public boolean isBalanced(TreeNode root) {

    //     if(root==null){
    //         return true;
    //     }

    //     int height_lst = height(root.left);
    //     int height_rst = height(root.right);

    //     return Math.abs(height_lst - height_rst) <=1
    //     && isBalanced(root.left) 
    //     && isBalanced(root.right);     
    // }

    // public int height(TreeNode root){
    //     if(root == null){
    //         return 0;
    //     }

    //     return 1 + Math.max(height(root.left), height(root.right));
    // }

    /* 
    IDEA : Our function will return -1 or height of tree
        -> if return -1 that means its not balanced;
    */
    public boolean isBalanced(TreeNode root) {
        return checkBalanced(root)==-1? false : true;
    }

    public int checkBalanced(TreeNode root) {

        if(root==null){
            return 0;
        }

        int lh = checkBalanced(root.left);
        if(lh == -1){
            return -1;
        }

        int rh = checkBalanced(root.right);
        if(rh == -1){
            return -1;
        }

        if(Math.abs(lh-rh)>1){
            return -1;
        }else{
            return 1 + Math.max(lh, rh);
        }
        
    }
```



6. Subtree of Another Tree

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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(subRoot==null){
            return true;
        }
        if(root==null){ // subRoot!=null
            return false;
        }

        if(isSameTree(root, subRoot)){
            return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);

    }
    

    public boolean isSameTree(TreeNode root, TreeNode subRoot){

        if(root == null && subRoot == null){
            return true;
        }

        if(root==null || subRoot==null){
            return false;
        }

        if(root.val != subRoot.val){
            return false;
        }

        return isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right);

    }
}

```

7. Single Number

```
class Solution {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : nums){
            map.put(i, map.getOrDefault(i, 0)+1); //nums[i], frequency
        }
        
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == 1){
                return entry.getKey();
            }
        }

        return -1;
    }
}
```

8. Number of One Bits

```
class Solution {
    public int hammingWeight(int n) {
        //Brain & Kerningham algorithm
        int res = 0;

        while(n!=0){
            ++res;
            n = n&(n-1);
        }

        return res;
    }
}
```

9. Count Bits
Given an integer n, count the number of 1's in the binary representation of every number in the range [0, n].
Return an array output where output[i] is the number of 1's in the binary representation of i.
```
class Solution {
    public int[] countBits(int n) {
        //for every number apply brain & kerningham algorithm
        int[]res = new int[n+1];

        for(int i=0; i<=n; i++){
            int count=0;
            int temp = i;
            while(temp!=0){
                ++count;
                temp = temp & (temp-1);
            }
            res[i] = count; 
        }
        return res;
    }
}

```

10. Missing Number
Given an array nums containing n integers in the range [0, n] without any duplicates, return the single number in the range that is missing from nums.

```
    public int missingNumber(int[] nums) {
        int res = -1;
        //with  extra space
        int [] temp = new int[1000]; //1 <= nums.length <= 1000
        for(int i : nums){
            ++temp[i];
        }

        for(int i=0; i<1000; i++){
            if(temp[i]==0){
                res = i;
                break;
            }
        }

        return res;
        
    }
```

//without extraspace using xor
```
    public int missingNumber(int[] nums) {
        int xor = nums.length;
        for(int i=0; i<nums.length; i++){
            xor ^= i^ nums[i];
        }

        return xor;
    }
```

11. Meeting Rooms
```
class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {
        //Idea = there should not be any overlapping intervals
        Comparator<Interval> sortByStart = new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                return a.start - b.start;
            }
        };

        Collections.sort(intervals, sortByStart);

        int res = 0;
        int originalLength = intervals.size();
        for(int i=1; i<originalLength; i++){
            if(intervals.get(res).end > intervals.get(i).start){
                return false;
            }else {
                ++res;
            }
        }
        return true;
    }
}
```

Optimized
```
Collections.sort(intervals, Comparator.comparingInt(i->i.start)); // Collections.sort(intervals, (a,b)-> Integer.compare(a.start, b.start));

        for(int i=1; i<intervals.size(); i++){
            Interval I1 = intervals.get(i-1);
            Interval I2 = intervals.get(i);

            if(I1.end > I2.start){
                return false;
            }
        }

        return true;
```

12. Insert Intervals
```
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        int res[][] = new int[intervals.length+1][];
        
        //Find the position where we should insert newInterval
        int idx = intervals.length;
        for(int i=0; i<intervals.length; i++){
            if(intervals[i][0] >= newInterval[0]){
                idx = i;
                break;
            } 
            res[i] = intervals[i];
        }

        res[idx] = newInterval;

        for(int i= idx; i<intervals.length; i++){
            res[i+1] = intervals[i];
        }


        //merged overlapping intervals
        int temp = 0;
        for(int i=1; i<res.length; i++){
            if(res[temp][1] >= res[i][0]){
                res[temp][0] = Math.min(res[temp][0], res[i][0]);
                res[temp][1] = Math.max(res[temp][1], res[i][1]);
            }else {
                ++temp;
                res[temp] = res[i];
            }
        }

        return Arrays.copyOf(res, temp+1);

    }
}
```

13. Merge Intervals

Sorting :
Arrays.sort(intervals, Comparator.comparingInt(a->a[0]));
```
class Solution {
    public int[][] merge(int[][] intervals) {
        //sort the 2D array on start
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        //merging
        int idx = 0;
        for(int i=1; i<intervals.length; i++){
            if(intervals[idx][1] >= intervals[i][0]){
                intervals[idx][0] = Math.min(intervals[idx][0], intervals[i][0]);
                intervals[idx][1] = Math.max(intervals[idx][1], intervals[i][1]);
            }else{
                ++idx;
                intervals[idx] = intervals[i];
            }
        }

        return Arrays.copyOf(intervals, idx+1);
        
    }
}
```

14. Sum of Two Integers without using +, -
```
class Solution {
    public int getSum(int a, int b) {

        while(b!=0){
            int carry = (a&b) << 1;
            a^=b;
            b = carry;
        }

        return a;
        
    }
}
```

15. Count Good Nodes in Binary Tree
//Idea : Maintain a global count and increment whenever we get value > root.val and follow preOrder traversal as we have to consider the present node first.

```
class Solution {
    public static int count = 0;
    public int goodNodes(TreeNode root) {
        //Maintain the max seen so far and do preorder traversal
        count = 0;
        int max = Integer.MIN_VALUE;
        countGoodNodes(root, max);
        return count;
    }

    public void countGoodNodes(TreeNode root, int max){
        if(root == null){
            return;
        }
        
        if(max <= root.val){
            max = root.val;
            ++count;
        }

        countGoodNodes(root.left, max);
        countGoodNodes(root.right, max);
        
    }
}
```

Without using count as global variable
```
class Solution {
    public int goodNodes(TreeNode root) {
        //Maintain the max seen so far and do preorder traversal
        int max = Integer.MIN_VALUE;
        return countGoodNodes(root, max);
    }

    public int countGoodNodes(TreeNode root, int max){
        if(root == null){
            return 0;
        }
        
        int count = 0;
        if(max <= root.val){
            count = 1;
            max = root.val;
        }

        count += countGoodNodes(root.left, max);
        count += countGoodNodes(root.right, max);
        
        return count;
    }
}
```
Using DFS

```
public int goodNodes(TreeNode root) {
        int count=0;
        //root can never be null; 1 <= number of nodes in the tree <= 100

        ArrayDeque<Pair<TreeNode, Integer>> q = new ArrayDeque<>(); //Node, maxSeenSoFar
        q.offer(new Pair<>(root, Integer.MIN_VALUE));


        while(!q.isEmpty()){
            Pair<TreeNode, Integer> p = q.poll();
            int maxValue = p.getValue();
            TreeNode node = p.getKey();
            if(node.val >= maxValue){
                ++count;
                maxValue = node.val;
            }

            if(node.left != null){
                q.offer(new Pair<>(node.left, maxValue));
            }

            if(node.right != null){
                q.offer(new Pair<>(node.right, maxValue));
            }
        }


        return count;
    }
```

16. Valid Binary Search Tree
```
class Solution {
    public boolean isValidBST(TreeNode root) {
        //Every Node is valid for BST when its exists in valid range
        // root -> -INF, +INF, root.left = -INF, root.val, root.right = root.val, INF

        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isBST(TreeNode root, int left, int right){
        if(root != null){
            if(root.val <= left || root.val >= right){
                return false;
            }

            return isBST(root.left, left, root.val) && isBST(root.right, root.val, right);
        }

        return true;
    }
}
```

17. Remove Node From End of Linked List
Approach-1 : 
1. Find the length of linked list
2. calculate target position , target = len - N; // target's - 1 next node needs to be deleted
    -> If target = 0 -> head needs to be deleted -> corner case
    -> traverse till target-1 and delete the node

```
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //no need to check null as we have atleast one node
        int len = calLength(head);
        int target = len-n;

        //Case-1 Head will change
        if(target==0){
            return head.next;
        }

        //Case-2 Head wont change
        ListNode curr = head;
        int count = 1;

        while(count != target){
            ++count;
            curr = curr.next;
        }

        curr.next = curr.next.next;

        return head;

    }

    public int calLength(ListNode head){
        //no need to check null as we have atleast one node
        ListNode curr = head;
        int count = 0;
        while(curr!=null){
            ++count;
            curr= curr.next;
        }

        return count;
    }
}
```

Approach-2 : Two pointer
IDEA : Maintain N distance b/w left and right reference. So when right reaches the end we need to delete left.next
1. Create a dummy node pointing to the head (helps in deletion of head node)
2. Set two pointers
    -> left = dummy;
    -> right = head;
3. Move right reference by n
4. Now move both are one position until right reaches the end;
5. Now left.next is the node to delete -> skip it by doing left.next = left.next.next
6. return dummy.next;

```
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        //Two Pointer approach
        ListNode dummy = new ListNode(0, head); // pointing to head, will help in delete head node if require
        ListNode left = dummy;
        ListNode right = head;

        //move right by n position to create difference of position of n b/w left and right
        while(n>0){
            right = right.next;
            --n;
        }


        //when right reaches null left.next needs to be deleted
        while(right!=null){
            left = left.next;
            right = right.next;
        }

        left.next = left.next.next;

        return dummy.next;
        
    }
}
```

18. Top K Frequent Elements --> STAR QUESTION

```
public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> count = new HashMap<>();

        List<Integer>[] freq = new List[nums.length+1]; // maximum length when all numbers are same so count == length, +1 is for zero as array is zero indexed;

        //initialize freq
        for(int i=0; i<freq.length; i++){
            freq[i] = new ArrayList<>();
        }

        //populate HashMap
        for(int i:nums){
            count.put(i, count.getOrDefault(i, 0) + 1);
        }


        //use Map to populate list-> where value will be index and key will be value added as list
        for(Map.Entry<Integer,Integer> entry : count.entrySet()){
            freq[entry.getValue()].add(entry.getKey());
        }


        //traverse from last for k elements
        int res[] = new int[k];
        int index = 0;

        for(int i=freq.length-1; i>0 && index <k; i--){ //no need to execute for zero since no element occurs 0 times ;)
            for(int j : freq[i]){//for positions that are not empty
                res[index++] = j;
                if(index == k){
                    return res;
                }
            }

        }
        return res;

    }
```


19. Search a 2D Matrix
```
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //IDEA : We can start from either top right or bottom left
        //If start from top right
        //      -> element == target return
        //      -> element > target -> decrease coloumn
        //      -> element < target -> increase row
        int r = matrix.length;
        int c = matrix[0].length;

        int i=0; 
        int j = c-1;

        while(i<r && j>=0){
            int element = matrix[i][j];
            if(element == target){
                return true;
            }

            else if(element > target){
                --j;
            }else{
                ++i;
            }
        }

        return false;
    }
}

```

20. 3Sum
Fix one element and for other apply two sum - Two Pointer approach
```
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        //sort the nums array that helps to not to consider duplicates
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;

        for(int i=0; i<n-2; i++){ // -3 as we need to consider b,c as well

            if(nums[i]>0){
                break; //since array is sorted if curr element is positive then triplet is not present starting from curr index to rest
            }

            if(i>0 && nums[i]==nums[i-1]){
                //not consider duplicates
                continue;
            }

            twoSum(i+1, n-1, nums[i], res, nums);
        }

        return res;
        
    }

    public void twoSum(int l, int h, int target, List<List<Integer>> list, int [] nums){
        while(l<h){
            int sumThree = nums[l] + nums[h] + target;

            if(sumThree == 0)
            {
                list.add(Arrays.asList(target,nums[l], nums[h]));
                ++l;
                --h;

                while(l < h && nums[l] == nums[l-1]){
                    ++l;
                }

                while(l<h && nums[h] == nums[h+1]){
                    --h;
                }
            }
            else if(sumThree >0){
                --h;
            }else{
                ++l;
            }
        }
    }
}

```

21. Two Integer Sum II

- Two pointer approach
```
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // Two Pointer approach 
        //since array is sorted so no need sorting
        //exactly one valid solution. -> NO duplicates that means hence no need to check for 
        /*
            if(i>0 && numbers[i] == numbers[i-1]){
                continue;
            }
        */

        int l = 0, r = numbers.length - 1;
        while(l < r){
            int sum = numbers[l] + numbers[r];

            if(sum == target){
                return new int[]{l+1, r+1}; // 1-indexed
            }else if(sum > target){
                --r;
            }else{
                ++l;
            }

        }

        return new int[0]; // guaranteed solution exists
        
    }
}
```

22. Last Stone Weight

```
class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        //build max heap
        for(int s : stones){
            minHeap.offer(-s); //store -ve
        }

        while(minHeap.size() > 1){
            int first = minHeap.poll();
            int second = minHeap.poll();

            if(first != second){ // second > first. there can never be condition when first > second since its min heap
                minHeap.offer(first-second);
            }
        }

        minHeap.offer(0);//when all stones are smashed we need to return 0;
        return Math.abs(minHeap.peek()); // remove minus sign
        
    }
}

```

23. Longest Common Subsequence

1. Memoization

```
class Solution {
    //Using memoization

    public static int[][]memo;
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        memo = new int[m+1][n+1]; //need to consider for "" empty strings we well in length

        for(int i=0; i<m+1; i++){
            Arrays.fill(memo[i], -1);
        }

        return lcs(text1, text2, m, n);
    }
    
    public int lcs(String s1, String s2, int m, int n){
        if(memo[m][n]!=-1){
            return memo[m][n];
        }

        if(m==0 || n==0){
            memo[m][n] = 0;
        }

        else{
            if(s1.charAt(m-1)==s2.charAt(n-1)){
                memo[m][n] = 1 + lcs(s1, s2, m-1, n-1);
            }else{
                memo[m][n] = Math.max(lcs(s1, s2, m-1, n), lcs(s1, s2, m, n-1));
            }
        }
        

        return memo[m][n];
    }
}
```

2. Tabulation
```
class Solution {

    //Method-2 Tabulation
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int dp[][] = new int[m+1][n+1]; //+1 to consider 00, 10, 01

        for(int i=0; i<=m; i++){
            //row change
            dp[i][0] = 0;
        }

        for(int i=0; i<=n; i++){
            //column change
            dp[0][i] = 0;
        }

        for(int i=1; i<=m ; i++){
            for(int j=1; j<=n; j++){
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[m][n];
    }
}
```

24. Longest Consecutive Sequence


```
class Solution {
    public int longestConsecutive(int[] nums) {

    //Naive Approach : sort array and find    
    if(nums==null || nums.length == 0){
        return 0;
    }
    Arrays.sort(nums);
    int res = 1;
    int currMax = 1;

    for(int i=1; i<nums.length; i++){
        if(nums[i]==nums[i-1]){
            continue;
        }
        if(nums[i] == nums[i-1]+1){
            ++currMax;
        }else{
            res = Math.max(currMax, res);
            currMax = 1;
        }
    }
        
    return Math.max(res, currMax);
    }
}

```

Better
```
public int longestConsecutive(int[] nums) {
        /*
        IDEA : 1. Create a HashSet and populate it with nums
            2. x can only be the start of sequence when x-1 doesn't exist

        Put all the numbers in set
        For each number 
            -  if num-1 is not in the set -> start a sequence
            - count how long the subsequence goes -> num, num+1, num+2, .....
        */

        if(nums==null || nums.length==0){
            return 0;
        }

        HashSet<Integer> set = new HashSet<>();

        for(int num : nums){
            set.add(num);
        }

        int longest = 0;
        for(int num : set){
            //start of sequence
            if(!set.contains(num-1)){
                int curr = num;
                int count = 1;

                while(set.contains(curr+1)){
                    ++curr;
                    ++count;
                }
                longest = Math.max(longest, count);
            }
        }

        return longest;
    }
```

25. Longest Palindromic Substring
Brute Force
```
class Solution {

    public String longestPalindrome(String s) {
        if(s.length()==0){
            return "";
        }
        if(s.length()==1){
            return s;
        }
        //Naive solution- Find all substring

        String res = "";

        for(int i=0; i<s.length(); i++){
            int currMax = 1;
            String currRes = s.substring(i,i+1);
            for(int j=i+1; j<s.length(); j++){
                if(isPalindrome(s.substring(i, j+1)) && currMax < (j-i+1)){
                    currMax = j-i+1;
                    currRes = s.substring(i, j+1);
                }
            }
            if(res.length() < currMax){
                res = currRes;
            }
        }

        return res;
    }

    public static boolean isPalindrome(String s){
        int l = 0;
        int h = s.length()-1;

        while(l<h){
            if(s.charAt(l)!= s.charAt(h)){
                return false;
            }else{
                ++l;
                --h;
            }
        }

        return true;
    }
}
```

Better approach
```
//Consider  curr element as centre and expand left and right to check
    public String longestPalindrome(String s) {
        int resLength = 0;
        String res = "";
        int n = s.length();

        for(int i=0; i<n; i++){

            //For odd length
            int l = i, r = i;
            while(l>=0 && r < n && s.charAt(l)==s.charAt(r)){
                if(r-l+1 > resLength){
                    res = s.substring(l, r+1);
                    resLength = r-l+1;
                }
                --l;
                ++r;
            }


            //For event length
            l=i; r = i+1;
            while(l>=0 && r<n  && s.charAt(l) == s.charAt(r)){
                if(r-l+1 > resLength){
                    res = s.substring(l, r+1);
                    resLength = r-l+1;
                }
                --l;
                ++r;
            }

        }

        return res;
    }
```

26. Longest substring without repeating characters

Brute force
```
if(s.length()==0){
            return 0;
        }

        HashSet<Character> set = new HashSet<>();

        int len=0;

        for(int i=0; i<s.length(); i++){
            int currMax = 1;
            set = new HashSet<>();
            set.add(s.charAt(i));
            for(int j=i+1; j<s.length(); j++){
                if(set.contains(s.charAt(j))){
                    break;
                }else{
                    set.add(s.charAt(j));
                    ++currMax;
                }
            }

            len = Math.max(currMax, len);
        }

        return len;
```

Efficient approach
```
/Efficient -> Two Pointer approach

        if(s.length()==0){
            return 0;
        }
        
        HashSet<Character> set = new HashSet<>();
        int res = 0;
        int l = 0;

        for(int r = 0; r < s.length(); r++){
            while(set.contains(s.charAt(r))){
                set.remove(s.charAt(l));
                ++l;
            }
            set.add(s.charAt(r));
            res = Math.max(res, r-l+1);
        }

        return res;
```

27. Binary Tree Order Traversal
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
    public List<List<Integer>> levelOrder(TreeNode root) {

        if(root == null){
            return List.of();
        }

        List<List<Integer>> res = new ArrayList<>();
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        ArrayList<Integer> list = new ArrayList<>();
        while(!q.isEmpty()){
            int levelSize = q.size();
            list = new ArrayList<>();
            for(int i=0; i<levelSize; i++){
                TreeNode curr = q.poll();
                list.add(curr.val);
                if(curr.left != null){
                    q.offer(curr.left);
                }
                if(curr.right!=null){
                    q.offer(curr.right);
                }
            }
            res.add(list);
        }

        return res;
        
    }
}

```

28. Binary Tree Right Side View
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
    public List<Integer> rightSideView(TreeNode root) {
        //levelOrder right most child on every level
        List<Integer> res = new ArrayList<>();

        if(root == null){
            return res;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int levelSize = queue.size();

            for(int i=0; i<levelSize; i++){
                TreeNode curr = queue.poll();
                if(i == levelSize-1){
                    res.add(curr.val);
                }

                if(curr.left != null){
                    queue.offer(curr.left);
                }

                if(curr.right != null){
                    queue.offer(curr.right);
                }
            }
        }

        return res;

    }
}

```

29. Kth Smallest

Recursive
```
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        //IDEA : InOrder traversal of binary search tree is sorted
        return inOrderTraversal(root, new int[]{k});
    }

    public int inOrderTraversal(TreeNode root, int [] k){
        if(root == null){
            return -1;
        }
        
        int left = inOrderTraversal(root.left, k);
        if(left != -1){
            return left;
        }
        if(--k[0] == 0){ // 1 indexed
            return root.val;
        }
        return inOrderTraversal(root.right, k);
    }
}
```

Using Stacks
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
    public int kthSmallest(TreeNode root, int k) {
        //Use Stack

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while(curr != null || !stack.isEmpty()){
            //go to left
            while(curr != null){
                stack.push(curr);
                curr = curr.left;
            }

            //visit node
            curr = stack.pop();
            k = k-1;
            if(k == 0){
                return curr.val;
            }

            curr = curr.right;

        }

        return -1;
    }
}
```
Another technique without space : Morris Traversal 

30. Daily Temperatures

Brute force

```
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {

        //Brute force : calculate for each element
        int days = temperatures.length;
        int [] res = new int[days];
        
        for(int i=0; i<days; i++){
            int count = 0;
            for(int j=i+1; j<days; j++){
                ++count;
                if(temperatures[j] > temperatures[i]){
                    break;
                }
                
            }
            res[i] = count;
        }

        return res;
        
    }
}

```

Efficient using stack

```
public int[] dailyTemperatures(int[] temperatures) {
        //Using Stack that will contains the value waiting for its warmer day
        int n = temperatures.length;
        int res[] = new int[n];

        Deque<int[]> stack = new ArrayDeque<>(); //Pair <temp{index}, index>

        for(int i=0; i<n; i++){
            int t = temperatures[i];
            while(!stack.isEmpty() && t > stack.peek()[0]){
                int [] pair = stack.pop();
                res[pair[i]] = i - pair[i];
            }
            stack.push(new int[]{t, i}); //push equal too
        }

        return res;
        
    }

```

31. Car Fleet

```
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        //Using Stack
        int [][] pairs = new int[position.length][2]; //<Pair<Position, speed>>
        
        for(int i=0; i<position.length; i++){
            pairs[i][0] = position[i];
            pairs[i][1] = speed[i];
        }

        //sort in decending order of position
        Arrays.sort(pairs, (a,b) -> Integer.compare(b[0], a[0]));

        Stack<Double> stack = new Stack<>();

        for(int [] p : pairs){
            stack.push((double)(target - p[0])/p[1]); //time when it reach the target

            //check for adjancent if it collid --> note : Its only one if not while
            //If the current car reaches the target earlier than the car infront of it, it will catchup -> they form one fleet
            if(stack.size() >= 2 && stack.peek() <= stack.get(stack.size() - 2)){
                stack.pop();
            } 
        }

        return stack.size();

    }
}


```


Approach -2  -> Note that we have double
```
public int carFleet(int target, int[] position, int[] speed) {
        //Iterative solution
        int n = position.length;
        int [][]pair = new int[n][2]; // <position, speed>

        for(int i=0; i<n; i++){
            pair[i][0] = position[i];
            pair[i][1] = speed[i];
        }

        //sort in decending order of position
        Arrays.sort(pair, (a, b)-> Integer.compare(b[0], a[0]));

        int fleet = 1;
        double prevTime = (double)(target - pair[0][0])/pair[0][1];

        for(int i=1; i<n; i++){
            double currTime = (double)(target - pair[i][0])/pair[i][1];

            //it will create a new fleet and becomes the front of the new fleet
            if(currTime > prevTime){
                ++fleet;
                prevTime = currTime;
            }
        }

        return fleet;

    }
```

32. Reorder Linked List
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
    public void reorderList(ListNode head) {

        /*
        1. Find middle of LL (Slow and fast approach)
        2. Reverse second half
        3. Merge two LL
        */

        //1. find middle of LL
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }


        //reverse
        ListNode prev = null;
        ListNode second = slow.next; // save head of second half
        slow.next = null;            // detach first half

        while(second != null){
            ListNode next = second.next;
            second.next = prev;
            prev = second;
            second = next;
        }


        //3. Merge two LL
        ListNode first = head;
        second = prev; //head of reverse LL

        while(second != null){
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;

            first.next = second;
            second.next = temp1;

            first = temp1;
            second = temp2;
        }
        
    }
}
```

33. LCA

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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }

        if(root.val== p.val || root.val==q.val){
            return root;
        }

        TreeNode lca_left = lowestCommonAncestor(root.left, p, q);
        TreeNode lca_right = lowestCommonAncestor(root.right, p, q);

        if(lca_left != null && lca_right != null){
            return root;
        }

        if(lca_left!=null){
            return lca_left;
        }else{
            return lca_right;
        }

    }
}


```

34. Construct Binary Tree from Preorder and Inorder Traversal

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

public class Solution {
    public static int preIndex=0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if(preorder.length != inorder.length){
            return null;
        }
        preIndex = 0;
        /*
        IDEA : For every preorder element, 
        search in indorder array and buildTree
        */
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = preorder.length;
        for(int i=0; i<n ; i++){
            map.put(inorder[i], i);
        }

        return buildTree(map, preorder, 0, n-1);
    }

    public TreeNode buildTree(HashMap<Integer, Integer>map, int []pre, int is, int ie){
        //NLR
        if(is > ie){
            return null;
        }

        TreeNode root = new TreeNode(pre[preIndex++]);

        if(root == null){
            return null;
        }
        int inIndex = map.get(root.val);
        root.left = buildTree(map, pre, is, inIndex-1);
        root.right = buildTree(map, pre, inIndex+1, ie);

        return root;
    }

}
```

35. Kth Largest Element in a Stream

```
class KthLargest {

    /*
    To maintain the kth largest we will use minHeap of size k.
    kth largest element is available at root
    */

    int k;
    PriorityQueue<Integer> minHeap;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();

        for(int num : nums){
            this.minHeap.offer(num);
            if(minHeap.size()>k){
                this.minHeap.poll();
            }
        }
        
    }
    
    public int add(int val) {
        minHeap.add(val);
        if(minHeap.size()>k){ //no need of while as we are having k element when we are doing add -  returns the kth largest integer in the stream.
            minHeap.poll();
        }
        return minHeap.peek(); //kth largest
    }
}

```

40. Kth Largest Element in an Array

```
class Solution {
    public int findKthLargest(int[] nums, int k) {
        /*
        Idea : To use minHeap that store the k largest elments and kth largest will be present at root
            -> Iterate through given nums array
                -> for every iteration
                    -> store the value in Heap
                    -> if size of heap > k
                        -> poll from heap -> this will poll smallest element and end up considering other value
                -> return top of heap
        */

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int num : nums){
            minHeap.offer(num);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }

        return minHeap.peek();
        
    }
}

```

41. Number of Islands

Method - 1 : BFS
```
class Solution {

    /*
    Idea : for every grip Use dfs when grid[r][c] == '1'
        -> Enque the positioin int[]{r,c} in queue;
        and make grid[r][c] as visited by setting grip[r][c] = '0'
        -> while q is not empty
            -> pop from queue
            -> int row = pop[0], int col= pop[1]
            -> Run a for loop for all directions = {{1,0},{-1,0},{0,1},{0,-1}} //dont consider diagonal as mentioned in question consider horizontal, vertical
                -> check the position limits && pos[nr][nc]=='1'
                    ->enque in queue
                    ->pos[nr][nc]='0';
    */

    public static int directions[][] = {{1,0},{-1,0},{0,1},{0,-1}};
    public int numIslands(char[][] grid) {
        int islands=0;
        int ROWS = grid.length;
        int COL = grid[0].length;

        for(int r=0; r<ROWS; r++){
            for(int c=0; c<COL; c++){
                if(grid[r][c]=='1'){
                    bfs(grid, r, c);
                    ++islands;
                }
            }
        }

        return islands;
    }

    public void bfs(char[][]grid, int r, int c){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r,c});
        grid[r][c] = '0'; //marking visited

        while(!q.isEmpty()){
            int [] element = q.poll();
            int row = element[0];
            int col = element[1];

            //check in all four directions
            for(int [] dir : directions){
                int nr = row + dir[0];
                int nc = col + dir[1];

                if(nr >= 0 && nr < grid.length
                && nc >= 0 && nc < grid[0].length
                && grid[nr][nc] == '1'){
                    grid[nr][nc] ='0'; //visited
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }
}
```

Method - 2 DFS

```
public int numIslands(char[][] grid) {
        int ROWS = grid.length;
        int COLS = grid[0].length;

        int islands = 0;

        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLS; c++){
                if(grid[r][c]=='1'){
                    bfs(grid, r, c);
                    ++islands;
                }
            }
        }

        return islands;
    }

    public void bfs(int[][]grid, int r, int c){
        if(r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0'){
            return;
        }

        grid[r][c] = '0'; //visited
        for(int [] dir : directions){
            bfs(grid, r + dir[0], c + dir[1]);
        }
    }
```

42. Max Area of Island

Method - 1 : DFS

```
class Solution {

    public static int directions[][] = {{0,1},{0,-1},{1,0},{-1,0}};
    

    //Method - 1 : DFS : Use count number of island approach
    public int maxAreaOfIsland(int[][] grid) {
        int ROWS = grid.length;
        int COLS = grid[0].length;
        int maxArea = 0;
        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLS; c++){
                //indicate unvisited island
                if(grid[r][c] == 1){
                   maxArea = Math.max(maxArea, dfs(grid, r, c, 0));
                }
            }
        }
        return maxArea;
    }

    public int dfs(int [][]grid, int r, int c, int localMax){
        if(r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0){
            return 0; //dont update area here since it runs when you hit water or bounds, not when the island is fully explored.
        }
        grid[r][c] = 0; //visited
        int area = 1;

        for(int [] dir : directions){
            int row = dir[0];
            int col = dir[1];

            area += dfs(grid, r + row, c + col, localMax);
        }

        return area;
    }


}

```

43. Cloned graph

```
/*
Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    //Method - 1 Use BFS
    public Node cloneGraph(Node node) {
        //base case - node is null
        if(node == null){
            return null;
        }

        Map<Node, Node> cloned = new HashMap<>();
        Queue<Node> q = new ArrayDeque<>();
        q.offer(node);
        cloned.put(node, new Node(node.val));

        while(!q.isEmpty()){
            Node curr = q.poll();

            for(Node neighbor : curr.neighbors){
                //check visited
                if(!cloned.containsKey(neighbor)){ //this creates node
                    cloned.put(neighbor, new Node(neighbor.val));
                    q.offer(neighbor);
                }

                //this creates connection/branch b/w node
                cloned.get(curr).neighbors.add(cloned.get(neighbor));
                /* cloned.get(curr) -> cloneNode
                cloned.get(neighbor) -> clonedNeighbor
                cloneNode.add(cloned.get(neighbor)); // creates connection/branch b/w cloneNode and clonedNeighbor
                */

            }
        }

        return cloned.get(node);

    }
}
```

44. Copy Linked List with Random Pointer

Method - 1: Using HashMap
```
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }

        HashMap<Node, Node> cloned = new HashMap<>();

        //populate all nodes
        for(Node curr = head; curr != null; curr = curr.next){
            cloned.put(curr, new Node(curr.val));
        }

        //attach connections
        for(Node curr = head; curr != null; curr = curr.next){
            Node clone = cloned.get(curr);
            clone.next = cloned.get(curr.next);
            clone.random = cloned.get(curr.random);
        }

        return cloned.get(head);
        
    }
}

```
Method -2 cloned nodes in b/w and add random reference then seperate

```
public Node copyRandomList(Node head) {
        if(head==null){
            return null;
        }

        Node curr = head;
        while(curr != null){
            Node next = curr.next;
            Node random = new Node(curr.val);
            curr.next = random;
            random.next = next;
            curr = next;
        }

        //copy random reference
        for(Node oldHead = head; oldHead != null ; oldHead = oldHead.next.next){
            oldHead.next.random = oldHead.random == null ? null : oldHead.random.next;
        }

        //seperate
        Node randomHead = head.next;
        Node clonedHead = head.next;

        for(Node oldHead = head; oldHead != null ; oldHead = oldHead.next){
            oldHead.next = oldHead.next.next;
            clonedHead.next = clonedHead.next == null ? null : clonedHead.next.next;
            clonedHead = clonedHead.next;
        }

        return randomHead;


    }
```

45. Islands and Treasure

```
class Solution {
    public static int directions [][] = {{0,1},{0,-1},{1,0},{-1,0}};
    /*
    IDEA : Use BFS from desitination to possible sources
        1. Find all the nodes with value 0 (that will be our source) and enqueue in queue
        2. For each element in queue 
            -> check the all the neighbors with value INF and update the value with popNode value + 1
    */
    public void islandsAndTreasure(int[][] grid) {

        //Method -1 BFS
        int ROWS = grid.length;
        int COLS = grid[0].length;

        Queue<int[]> q = new ArrayDeque<>();

        //find all the nodes with val zero
        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLS; c++){
                if(grid[r][c] == 0){
                    q.offer(new int[]{r, c});
                }
            }
        }
        

        while(!q.isEmpty()){
            int [] curr = q.poll();
            int row = curr[0];
            int col = curr[1];

            //traverse all neighbors
            for(int [] dir : directions){
                int nr = row + dir[0];
                int nc = col + dir[1];

                if(nr < 0 || nc < 0 
                || nr >= grid.length || nc >= grid[0].length 
                || grid[nr][nc] != Integer.MAX_VALUE){
                    continue;
                }
                
                grid[nr][nc] = grid[row][col] + 1;
                q.offer(new int[]{nr, nc});
            }
        }
        
    }
}

```

46. Rotting Fruit
```
class Solution {
    public int orangesRotting(int[][] grid) {

        int ROWS = grid.length;
        int COLS = grid[0].length;

        int fresh = 0;
        int time = 0;

        Queue<int[]> q = new ArrayDeque<>();

        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLS; c++){
                if(grid[r][c] == 1){
                    ++fresh;
                }

                else if(grid[r][c] == 2){ //rotten
                    q.offer(new int[]{r, c});
                }
            }
        }
        
        int directions[][] = {{1,0},{-1,0},{0,1},{0,-1}};

        while(fresh > 0 && !q.isEmpty()){
            int levelSize = q.size();

            for(int i = 0; i < levelSize; i++){
                int []p = q.poll();
                int row = p[0];
                int col = p[1];

                //check in directions
                for(int[] dir : directions){
                    int nr = row + dir[0];
                    int nc = col + dir[1];

                    if(nr >=0 && nc>=0 
                    && nr < grid.length && nc < grid[0].length
                    && grid[nr][nc] == 1){
                        grid[nr][nc] = 2; //mark rotten/visited
                        --fresh;
                        q.offer(new int[]{nr, nc});
                    }
                }

            }

            ++time; // IMP : when we have multiple sources it should increment when we discover neighbor of multiple sources
        }

        return fresh == 0 ? time : -1;

    }
}

```
47. Surrounded Regions
```

class Solution {
    /*
    IDEA : Reverse thinking : Find all the areas except unsurrounded
        -> For first row and last row find 'o' and mark its as 'T' and apply dfs in all the directions
        -> For first column and last column find 'O' and mark it as 'T' and apply dfs in all the directions
        -> traverse each cell
            -> if 'O' update to 'X'
            -> if 'T' update to 'O'

    */

    private int ROWS;
    private int COLS;

    public static int directions[][] = {{1,0},{-1,0},{0,1},{0,-1}};
    public void solve(char[][] board) {
        ROWS = board.length;
        COLS = board[0].length;

        //for first and last row
        for(int c = 0; c < COLS; c++){
            //first row
            if(board[0][c] == 'O'){
                dfs(board, 0, c);
            }

            //last row
            if(board[ROWS-1][c] == 'O'){
                dfs(board, ROWS - 1, c);
            }
        }

        //for first and last column
        for(int r = 0; r < ROWS; r++){
            //first column
            if(board[r][0] == 'O'){
                dfs(board, r, 0);
            }

            //last column
            if(board[r][COLS - 1] == 'O'){
                dfs(board, r, COLS - 1);
            }
        }


        //update O -> X and T -> O
        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLS; c++){
                //order important
                if(board[r][c] == 'O'){
                    board[r][c] = 'X';
                }

                if(board[r][c] == 'T'){
                    board[r][c] = 'O';
                }
            }
        }
    }

    public void dfs(char [][] board, int r, int c){

        //check bounds
        if(r < 0 || c < 0 || r > ROWS-1 || c > COLS-1 || board[r][c] != 'O'){
            return;
        }

        board[r][c] = 'T';
        //dfs in all four directions
        for(int [] dir : directions){
            dfs(board, r + dir[0], c + dir[1]);
        }
    }
}
```

48. Course Schedule
```
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        /*
        IDEA : if there exists a cycle then we wont be able to take all the courses
        do topogical sorting using Kahn's algorithm and with every pop ++ count at end check count == numCourses
        */


        //create adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(numCourses);

        for (int i = 0 ; i < numCourses; i++){
            adj.add(i, new ArrayList<>());
        }

        for(int i = 0 ; i < prerequisites.length; i++){
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]); // 1 -> 0
        }

        //Kahn's algorithm

        int [] indegree = new int[numCourses];
        populateIndegreeArray(indegree, adj);


        //populate Queue with vertex having indegree = 0

        Deque<Integer> q = new ArrayDeque<>();

        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }
        
        //keep track of elements popped/visited
        int count = 0;

        while(!q.isEmpty()){
            int p = q.poll();

            ++count;

            //check adj
            for(int u : adj.get(p)){
                //since p is popped the degree of adjacent decrease by 1
                --indegree[u];
                if(indegree[u] == 0){
                    q.offer(u);
                }
            }
        }


        return count == numCourses ? true : false;


    }

    public void populateIndegreeArray(int [] indegree, ArrayList<ArrayList<Integer>>adj){
        for(int i = 0; i < adj.size(); i++){
            for(int j = 0; j < adj.get(i).size(); j++){
                ++indegree[adj.get(i).get(j)];
            }
        }
    }
}
```

49. Course Schedule II
```
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //create adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(numCourses);

        int [] res = new int[numCourses];
        int idx = 0;

        for (int i = 0 ; i < numCourses; i++){
            adj.add(i, new ArrayList<>());
        }

        for(int i = 0 ; i < prerequisites.length; i++){
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]); // 1 -> 0
        }

        //Kahn's algorithm

        int [] indegree = new int[numCourses];
        populateIndegreeArray(indegree, adj);


        //populate Queue with vertex having indegree = 0

        Deque<Integer> q = new ArrayDeque<>();

        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }
        
        //keep track of elements popped/visited
        int count = 0;

        while(!q.isEmpty()){
            int p = q.poll();
            res[idx++] = p;

            ++count;

            //check adj
            for(int u : adj.get(p)){
                //since p is popped the degree of adjacent decrease by 1
                --indegree[u];
                if(indegree[u] == 0){
                    q.offer(u);
                }
            }
        }

        return count == numCourses ? res : new int[]{};


    }

    public void populateIndegreeArray(int [] indegree, ArrayList<ArrayList<Integer>>adj){
        for(int i = 0; i < adj.size(); i++){
            for(int j = 0; j < adj.get(i).size(); j++){
                ++indegree[adj.get(i).get(j)];
            }
        }
    }
}
```

50. Graph Valid Tree
```
class Solution {
    public boolean validTree(int n, int[][] edges) {

        // IDEA
        /*
        If there is a cycle in the un-directed graph then it can't be graph valid tree
        */

        //create adjacency matrix
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(n);

        for(int i = 0; i < n ; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < edges.length; i++){
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }

        // Pass parent to check for cycle

        boolean []visited = new boolean[n];
        int components = 0;

        for(int i = 0; i < n; i++){
            if(!visited[i]){
                ++components;
                if(isCycleExists(adj, i, -1, visited)){
                    return false;
                }
            }
        }

        return true && components == 1;
    }

    public boolean isCycleExists(ArrayList<ArrayList<Integer>>adj, int curr, int parent, boolean [] visited){
        visited[curr] = true;

        //check for neighbor
        for(int neighbor : adj.get(curr)){
            if(!visited[neighbor]){
                if(isCycleExists(adj, neighbor, curr, visited)){
                    return true;
                }
            }else if (parent != neighbor){
                return true;
            }
        }
        return false;
    }
}
```

51. Add Two Numbers

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        int carry = 0;
        while(l1 != null || l2 != null || carry != 0){
            int v1 = l1!=null ? l1.val : 0;
            int v2 = l2!=null ? l2.val : 0;

            int val = v1+v2+carry;
            carry = val/10;
            val = val%10;
            
            curr.next = new ListNode(val);

            curr = curr.next;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;

        }

        return dummy.next;

    }
}

```

52. Find the Duplicate Number
```
class Solution {
    public int findDuplicate(int[] nums) {
        
        //brute force
        HashSet<Integer> set = new HashSet<>();

        for(int num : nums){
            if(set.contains(num)){
                return num;
            }
            set.add(num);
        }

        return -1;

    }
}

```

Using slow and fast pointer

```
class Solution {
    public int findDuplicate(int[] nums) {
        
        //brute force
        // HashSet<Integer> set = new HashSet<>();

        // for(int num : nums){
        //     if(set.contains(num)){
        //         return num;
        //     }
        //     set.add(num);
        // }

        // return -1;

        //Using slow and fast pointer
        // Treat the array like LL where each index points to next index in the value;
        //first can never form cycle because its index is 0 and numbers are from [1,n]

        int slow = 0;
        int fast = 0;

        while(true){
            slow = nums[slow];
            fast = nums[nums[fast]];
            if(slow == fast){
                break;
            }
        }

        int slow2 = 0;

        while(true){
            slow = nums[slow];
            slow2 = nums[slow2];

            if(slow == slow2){
                return slow;
            }
        }

    }
}
```

53. Koko Eating Bananas

```
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        //Binary search
        int low = 0;
        int high = Arrays.stream(piles).max().getAsInt();
        int res = -1;

        while(low <= high){
            int k = low + (high-low)/2;

            //check for speed k
            int totalTime = 0;
            for(int pile : piles){
                totalTime += Math.ceil((double)(pile)/k);
            }

            if(totalTime <= h){
                res = k;
                high = k-1;
            }else{
                low = k+1;
            }
        }

        return res;

        
    }
}

```

54. Find Minimum in Rotated Sorted Array
```
class Solution {
    public int findMin(int[] nums) {

        int l = 0;
        int r = nums.length - 1;

        int res = nums[0];

        while(l <= r){
            //is sorted part
            if(nums[l] <= nums[r]){
                res = Math.min(nums[l], res);
                break;
            }

            int m = l + (r-l)/2;
            res = Math.min(nums[m], res);
            if(nums[m] >= nums[l]){
                //go to right
                l = m+1;
            }else{
                r = m-1;
            }
        }
        return res;
        
    }
}
```

55. Squares of a Sorted Array

```
class Solution {
    public int[] sortedSquares(int[] nums) {
        /*
        Non decreasing order -> increasing but cnan have duplicate numbers
        Two pointer approach
        */

        int l = 0;
        int h = nums.length-1;

        int res [] = new int[nums.length];
        int idx = nums.length-1;

        while(l<=h){
            if(Math.abs(nums[l]) >= Math.abs(nums[h])){
                res[idx--] = nums[l] * nums[l];
                ++l;
            }else{
                res[idx--] = nums[h] * nums[h];
                --h;
            }
        }

        return res;
        
    }
}
```

56. Search in Rotated Sorted Array
```
class Solution {
    public int search(int[] nums, int target) {

        /*
        Use Binary Search
        -> find the middle , if nums[middle]==target -> return
        -> find the sorted part
            -> check is target lies in range of sorted part
                -> if yes change reference and discard the other part
                -> if no change the reference and choose other part
        */
        int low = 0;
        int high = nums.length - 1;

        while(low <= high){
            int mid = low + (high - low)/2;

            if(nums[mid] == target){
                return mid;
            }

            //if left half is sorted
            else if(nums[mid] >= nums[low]){
                //check if target lies in the sorted range
                if(target < nums[mid] && target >= nums[low]){
                    high = mid - 1;
                }else{
                    //target not lies in sorted part go right
                    low = mid + 1;
                }
            }

            //if right part is sorted
            else if(nums[mid] <= nums[high]){
                //check if target lies in sorted range
                if(target <= nums[high] && target > nums[mid]){
                    low = mid + 1;
                }else{
                    //target not lies in sorted part go left
                    high = mid - 1;
                }
            }
        }

        return -1;

    }
}
```

57. Time Based Key-Value Store

```
class TimeMap {

    Map<String, TreeMap<Integer, String>> keyStore;

    public TimeMap() {
        keyStore = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        keyStore.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        if(!keyStore.containsKey(key)){
            return "";
        }

        TreeMap<Integer, String>timeStamp = keyStore.get(key);
        Map.Entry<Integer, String> entry = timeStamp.floorEntry(timestamp);
        return entry==null ? "" : entry.getValue();
    }
}

```

58. Container with most water

```
class Solution {
    public int maxArea(int[] height) {

        //Brute force -> get all possible area and maintain max one -> TLE
        // int res = 0;
        // int n = height.length;
        // for(int i = 0; i < n; i++){
        //     int currMax = 0;
        //     for(int j = i+1; j < n; j++){
        //         currMax = Math.max(currMax, (j-i) * Math.min(height[i], height[j]));
        //     }
        //     res = Math.max(res, currMax);
        // }

        // return res;

        //Two Pointer approach
        int l = 0;
        int r = height.length-1;

        int res = 0;

        while(l < r){
            int area = (r-l) * Math.min(height[l], height[r]);
            res = Math.max(res, area);

            if(height[l] <= height[r]){
                ++l;
            }else{
                --r;
            }
        }

        return res;
        
    }
}
```

59. Trapping Rain Water

```
class Solution {
    public int trap(int[] height) {
        
        /*
        Maintain two arrays
        -> lMax : keep the max seen so far from left
        -> rMax : keep the max seen so far from right
        -> traverse from 0 to n - 1
            -> res += Math.min(lMax[i], rMax[i]) - arr[i]
        */
        ta
        int res = 0;
        int n = height.length;
        int lMax[] = new int[n];
        int rMax[] = new int[n];

        lMax[0] = height[0];
        for(int i = 1; i < n; i++){
            lMax[i] = Math.max(height[i], lMax[i-1]);
        }

        rMax[n-1] = height[n-1];
        for(int i = n - 2; i >= 0; --i){
            rMax[i] = Math.max(height[i], rMax[i+1]);
        }

        for(int i = 0; i < n; i++){
            res += Math.min(lMax[i], rMax[i]) - height[i];
        }

        return res;

    }
}
```

60.Rotate Image
```
class Solution {

    private int ROWS;
    private int COLS;

    public void rotate(int[][] matrix) {

        /*
        Idea : 
        1. take the transpose
        2. Swap columns -> first with last , second with second last and so on
        */

        ROWS = matrix.length;
        COLS = matrix[0].length;

        if(ROWS==1 && COLS == 1){
            return;
        }

        transpose(matrix);
        swapColumns(matrix);
    }

    public void transpose(int [][] matrix){
        for(int i = 0; i < ROWS; i++){
            for(int j = i+1; j < COLS; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public void swapColumns(int [][]matrix){
        for(int col = 0; col < COLS/2; col++){
            for(int row = 0; row< ROWS; row++){
                int temp = matrix[row][col];
                matrix[row][col] = matrix[row][COLS - 1 - col];
                matrix[row][COLS - 1 - col] = temp;
            }
        }
    }
}
```

61.Set Matrix Zeroes

```
class Solution {

    private int ROWS;
    private int COLS;
    public void setZeroes(int[][] matrix) {

        /*
        IDEA
        1. Maintain two boolean 1D array
            -> row 
            -> col
        2. Traverse the original matrix and populate row and col matrix if we encounter zero
        3. Traverse row matrix and make corresponding row as 0 when row[i] = true;
        4. Traverse col matrix and make corresponding column as 0 when col[i] = true;
        */

        ROWS = matrix.length;
        COLS = matrix[0].length;

        boolean row [] = new boolean[ROWS];
        boolean col [] = new boolean[COLS];

        populateRowAndColMatrix(matrix, row, col);

        traverseRowAndUpdateMatrix(matrix, row);

        traverseColAndUpdateMatrix(matrix, col);
        
    }

    public void populateRowAndColMatrix(int [][]matrix, boolean []row, boolean []col){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                if(matrix[i][j] == 0){
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
    }

    public void traverseRowAndUpdateMatrix(int [][]matrix , boolean [] row){
        for(int i = 0; i < ROWS; i++){
            if(row[i] == true){
                //make entire ROW as true
                for(int j = 0; j < COLS; j++){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void traverseColAndUpdateMatrix(int [][]matrix , boolean [] col){
        for(int i = 0; i < COLS; i++){
            if(col[i] == true){
                //make entire COL as true
                for(int j = 0; j < ROWS; j++){
                    matrix[j][i] = 0;
                }
            }
        }
    }
}
```

62. Non-Cyclical Number

```
class Solution {
    public boolean isHappy(int n) {

        HashSet<Integer> set = new HashSet<>();

        while(true){
            int res = 0;
            while(n != 0){
                res = res + (n%10)*(n%10);
                n = n/10;
            }
            if(res == 1){
                return true;
            }
            else if(set.contains(res)){
                return false;
            }
            set.add(res);
            n = res;
        }
    }
}

```

Use Slow and Fast pointers

```
class Solution {
    public boolean isHappy(int n) {
        //Use fast and slow pointer -> Cycle detection
        int slow = n;
        int fast = n;

        do{
            slow = sumOfSquareOfDigits(slow);
            fast = sumOfSquareOfDigits(sumOfSquareOfDigits(fast));
        }while(slow != fast);

        slow = n;

        while(slow != fast){
            slow = sumOfSquareOfDigits(slow);
            fast = sumOfSquareOfDigits(fast);
        }

        return fast == 1;
    }

    public int sumOfSquareOfDigits(int n){
        int res = 0;
        while(n != 0){
            res += (n%10) * (n%10);
            n /= 10;
        }
        return res;
    }
}
```

64. Spiral Matrix
```
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        List<Integer> res = new ArrayList<>();

        int top = 0; //move in columns
        int right = COLS - 1; //move in rows
        int bottom = ROWS - 1; //move in columns
        int left = 0; //move in rows

        while(top <= bottom && left <= right){

            //first row
            for(int i = left; i <= right; i++){
                res.add(matrix[top][i]);
            }
            ++top;

            //last column
            for(int i = top; i <= bottom; i++){
                res.add(matrix[i][right]);
            }
            --right;

            //last row
            //for single row -> if condition
            if(top <= bottom){
                for(int i = right; i >= left; i--){
                    res.add(matrix[bottom][i]);
                }
            }
            
            --bottom;

            //first column
            //for single column -> if condition
            if(left <= right){
                for(int i = bottom; i >= top; i--){
                    res.add(matrix[i][left]);
                }
            }
            ++left;
            
        }

        return res;
        
    }
}

```

65. Plus One
```
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int i = n-1; i >= 0; i--){
            if(digits[i] < 9){
                ++digits[i];
                return digits;
            }
            digits[i] = 0;
        }

        //reach here -> means all are 9's
        int res[] = new int[digits.length + 1];
        res[0] = 1; //all others would be 9 and after adding 1 becomes 0
        return res;
    }
}
```

66. PowerOf2
```
Brute force : -> TLE
class Solution {
    public double myPow(double x, int n) {

        if(n == 0){ // any digit ^0 == 1
            return 1.0;
        }

        
        double res = x;
        int count = 1;
        if(n >= 0){
            while(count < n){
                ++count;
                res = res * x;
            }
        }else{
            while(count > n){
                --count;
                res = res / x;
            }
        }
        

        return res;
        
    }
}

```

Brute force -> simplified -> TLE

```
class Solution {
    public double myPow(double x, int n) {

        /*
        Brute force
        */

        if(x == 0){
            return 0.0;
        }

        if(n == 0){
            return 1.0;
        }

        double res = 1;

        for(int i = 0; i< Math.abs(n); i++){
            res = res * x;
        }

        return n >=0 ? res : 1/res;
        
    }
}
```

Efficient solution

Formula
if(n%2 == 0) return myPow(x, n/2) * myPow(x, n/2)
else return x * myPow(x, n/2) * myPow(x, n/2)

```
class Solution {
    public double myPow(double x, int n) {

        //BRUTE FORCE
        // if(n == 0){ // any digit ^0 == 1
        //     return 1.0;
        // }

        
        // double res = x;
        // int count = 1;
        // if(n >= 0){
        //     while(count < n){
        //         ++count;
        //         res = res * x;
        //     }
        // }else{
        //     while(count > n){
        //         --count;
        //         res = res / x;
        //     }
        // }
        

        // return res;

        if (n == 0){
            return 1.0;
        }

        if(x==0){
            return 0;
        }

        double res = helper(x, Math.abs((long)n));
        return n>=0 ? res : 1/res;
    }

    private double helper(double x, long n){
        if(n==0){
            return 1.0;
        }
        double half = helper(x, n/2);
        return n%2 == 0 ? half*half : x * half * half;
    }
}
```

67. Reverse Integer
```
class Solution {
    public int reverse(int x) {

        int res = 0;
        
        while(x != 0){
            int digit = x%10;
            x = x/10;

            //overflow check
            /*
            later we are going to multiple res by 10.
            Hence, we are checking if res >  Integer.MAX_VALUE/10 then when we multiple it later it will overflow
            Similarly for digit it can happen -> the last digit for Integer.MAX_VALUE is 7
            */
            if(res > Integer.MAX_VALUE/10 ||
                res == Integer.MAX_VALUE/10 && digit > 7){
                return 0;
            }

            //check for underflow
            if(res < Integer.MIN_VALUE/10 ||
                res == Integer.MIN_VALUE/10 && digit < -8){
                return 0; 
            }

            //safe to multiple
            res = res * 10 + digit;

        }

        return res;
    }
}
```

68. Task Scheduler

```
class Solution {
    public int leastInterval(char[] tasks, int n) {
        /*
        Intuition : Consider the high freq characters first to make the tasks completion time minimum
        To have high freq chars use maxHeap, and to keep tracks of remaining part of task and its next scheduled time use Queue
        */

        int count [] = new int[26];

        for(char task : tasks){
            ++count[task - 'A'];
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); //to create maxHeap, by default its minHeap

        for(int cnt : count){
            if(cnt > 0){
                maxHeap.offer(cnt);
            }
        }

        int time = 0;
        Queue<int []> q = new ArrayDeque<>();

        while(!maxHeap.isEmpty() || !q.isEmpty()){
            ++time;

            if(maxHeap.isEmpty()){
                time = q.peek()[1]; //optimization
            }
            else {
                int cnt = maxHeap.poll() - 1;
                if(cnt > 0){
                    q.offer(new int[]{cnt, time + n}); // remaining task, next scheduled time
                }
            }

            //check if in queue we have task that can be scheduled
            if(!q.isEmpty() && q.peek()[1] == time){
                maxHeap.offer(q.poll(e)[0]);
            }
        }

        return time;
        
    }
}

```

69. Edit Distance - Recursive

```
class Solution {
    public int minDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        return editDistance(word1, word2, m , n);
        
    }

    public int editDistance(String s1, String s2, int m , int n){

        if(m == 0){
            return n; //n insertions
        }

        if(n == 0){
            return m; //m deletions
        }

        if(s1.charAt(m-1) == s2.charAt(n-1)){
            return editDistance(s1, s2, m-1, n-1);
        }

        else {
            return 1 + Math.min(Math.min(editDistance(s1, s2, m, n-1), //insert
                            editDistance(s1, s2, m-1, n)),//delete
                            editDistance(s1, s2, m-1, n-1)) ; //replace
                            //1 is for the operations
        }


    }
}

```

Edit distance -> DP solution as earlier gives TLE

```
class Solution {
    public int minDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        return editDistanceUsingDP(word1, word2, m, n);
    }

    //TLE
    public int editDistance(String s1, String s2, int m, int n){

        if(m == 0){
            return n; //n insertions
        }

        if(n == 0){
            return m; //m deletions
        }

        if(s1.charAt(m - 1) == s2.charAt(n - 1)){
            return editDistance(s1, s2, m - 1, n - 1);
        }else{
            return 1 + Math.min(Math.min(editDistance(s1, s2, m, n-1), 
            editDistance(s1, s2, m-1, n)),
            editDistance(s1, s2, m-1, n-1));
        }


    }

    //Tabulation
    public int editDistanceUsingDP(String s1, String s2, int m , int n){
        int dp[][] = new int[m+1][n+1];

        //first row
        for(int i = 0 ; i <=n ; i++){
            dp[0][i] = i;
        }

        //first column
        for(int i = 0; i <= m ; i++){
            dp[i][0] = i;
        }

        for(int i = 1; i <=m; i++){
            for(int j = 1; j <= n; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j-1],  //insertion
                    dp[i-1][j]),  //delete
                    dp[i-1][j-1]); //replace
                }
            }
        }

        return dp[m][n];

    }
}
```

70. Longest Increasing Subsequence
```
class Solution {
    //By Dynamic programming
    /*
    For Ex : {1,2,4,3}  = idx : 0,1,2,3
    //LIS[idx]
    LIS[3] = 1;
    LIS[2] = Max(1 , 1+LIS[3]-> second one is not allowed, bcoz 4 !< 3)
    LIS[1] = Max(1, 1 + LIS[2], 1 + LIS[3] = 1, 1+1, 1+1 ) = 2
    LIS[0] = Max(1, 1 + LIS[1], 1 + LIS[2], 1 + LIS[3] = 1, 1+2, 1+1 ) = 3
    */
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 1){
            return 1;
        }

        int n = nums.length;
        int LIS[] = new int[n];
        Arrays.fill(LIS, 1); // for every single element LIS is 1

        for(int i = n-1; i >= 0; i--){
            for(int j = i+1; j < n; j++){
                if(nums[i] < nums[j]){
                    LIS[i] = Math.max(LIS[i], 1 + LIS[j]);
                }
            }
        }

        return Arrays.stream(LIS).max().getAsInt();
        
    }
}
```

Binary Search
```
//Using binarySearch
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int tail [] = new int[n];
        tail[0] = nums[0];
        int len = 1;

        for(int i = 1; i < n; i++){
            if(nums[i] > tail[len - 1]){
                tail[len] = nums[i];
                ++len;
            }else{
                int cIdx = ceilIdx(tail, 0, len - 1, nums[i]);
                tail[cIdx] = nums[i];
            }
        }

        return len;
    }

    public int ceilIdx(int []tail, int l, int r, int x){
        while(r > l){
            int m = l + (r - l)/2;

            if(tail[m] >= x){
                //ceil
                r = m;
            }else{
                l = m+1;
            }
        }

        return r;
    }
```

Using binary search with ArrayList

```
//using binary search
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if(n == 1){
            return 1;
        }

        ArrayList<Integer> list = new ArrayList<>();

        list.add(nums[0]);

        for(int i = 1; i < n ;i++){
            if(nums[i] > list.get(list.size()-1)){
                list.add(nums[i]);
            }else{
                int ceilIdx = findCeil(list, 0, list.size()-1, nums[i]);
                list.set(ceilIdx, nums[i]);
            }
        }

        return list.size();
    }

    public int findCeil(ArrayList<Integer> list, int l, int r, int x){
        while(r > l){
            int m = l + (r-l)/2;
            if(list.get(m) >= x){
                r = m;
            }else{
                l = m+1;
            }
        }

        return r;
    }
```

71. Coin Change
```
You are given an integer array coins representing coins of different denominations (e.g. 1 dollar, 5 dollars, etc) and an integer amount representing a target amount of money.

Return the fewest number of coins that you need to make up the exact target amount. If it is impossible to make up the amount, return -1.

You may assume that you have an unlimited number of each coin.
```

Using DP
```
class Solution {
    public int coinChange(int[] coins, int amount) {

        int dp [] = new int[amount + 1];
        Arrays.fill(dp, amount + 1); //amount+1 can never be possible
        dp[0] = 0; // we need 0 coins to make 0 amount
        /*
        default value Integer.MAX_VAL can cause overflow
        default value -1 can cause invalid results
        */

        for(int i = 1; i <= amount; i++){
            for(int coin : coins){
                if(i - coin >= 0){
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);// 1 + dp[i - coin] because we have considered coin as valid selection
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
        
    }
}

```