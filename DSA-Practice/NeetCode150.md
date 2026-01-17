# NEETCODE150 : https://neetcode.io/practice/practice/neetcode150

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