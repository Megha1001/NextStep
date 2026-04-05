******** PATTERN - 1 : TWO POINTER ********

1. Two Sum
Question Link : https://leetcode.com/problems/two-sum/
```
// TC : O(N), SC : O(N)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i ++){
            int key = target - nums[i];
            if(map.containsKey(key)){
                return new int[]{map.get(key),i};
            }
            map.put(nums[i], i);
        }

        return new int[]{};
        
    }
}
```

2. Two Sum II (Sorted Input)
Question Link : https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
Pattern : Two Pointer : Sorted and need to find pair
```
// TC : O(N) [Traversing all elements once ], SC : O(1)
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        //Pattern : Two pointer
        int i = 0;
        int j = numbers.length - 1;

        while(i < j){ //why not = because we can't take same element twice
            int sum = numbers[i] + numbers[j];

            if(sum == target){
                return new int[]{i+1, j+1};
            }
            else if (sum > target){
                --j;
            }else {
                ++i;
            }
        }
        
        return new int[]{};
    }
}
```

3. 3 Sum

Question : https://leetcode.com/problems/3sum/editorial/
```
//TC : N*N SC : If considered Arrays.sort -> Uses Dual Pivot Quick Sort -> O(logN)

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        //Sort + Two Pointer
        int n = nums.length;
        Arrays.sort(nums); //nLogN
        List<List<Integer>> res = new ArrayList<>();

         for(int i = 0; i < nums.length - 2; i++){ //O(N*N)

            //for skip duplicates
            if(i > 0 && nums[i] == nums[i-1]){ //that means we have already considered same pair with i-1 nums value
                continue;
            }

            //fixing i as first member and applying two pointer for rest
            int left = i+1;
            int right = n - 1;

            while(left < right){ // O(N)
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0){
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                   
                    //to skip duplicates
                    while(left < right && nums[left] == nums[left+1]) ++left;
                    while(left < right && nums[right] == nums[right - 1]) --right;

                    ++left;
                    --right;
                }
                else if (sum > 0){
                    --right;
                }else{
                    ++left;
                }
                
            }
        }

        return res;
        
    }
}

```

4. 3Sum Closest
```
//TC : O(N*N) due to for loop and SC : O(logN) due to sorting using dual pivot quick sort
class Solution {
    public int threeSumClosest(int[] nums, int target) {
       
       // sort + fix i then two Sum
       int n = nums.length;
       Arrays.sort(nums);
       int closest = nums[0] + nums[1] + nums[2];

       for(int i = 0; i < n - 2; i++){ //O(N) => O(N*N)
            int left = i+1;
            int right = n-1;

            while(left < right){ //O(N)
                int sum = nums[i] + nums[left] + nums[right];

                if(Math.abs(target-sum) < Math.abs(target-closest)){
                    closest = sum;
                }

                if(sum < target){
                    ++left;
                }else if(sum > target){
                    --right;
                }else{
                    return target;
                }
            }
        }

        return closest;

        
    }
}
```

5. Container With Most Water
```
//TC : O(N), SC : O(1)

class Solution {
    public int maxArea(int[] heights) {
        //Two pointer
        // when we get height[l] < height[r] move l since if we move right then area will decrease but if we move l there
        //can be the possibility of larger area

        int l = 0;
        int r = heights.length - 1;
        int area = Integer.MIN_VALUE;
        while(l < r){
            area = Math.max(area, (r - l) * Math.min(heights[l], heights[r]));

            if(heights[l] <= heights[r]){
                ++l;
            }else{
                --r;
            }
        }

        return area;
        
    }
}
```

6. Trapping Rain Water

```
//TC : O(N), SC : O(N)

class Solution {
    public int trap(int[] height) {

        int n = height.length;
        int lMax[] = new int[n];
        int rMax[] = new int[n];

        lMax[0] = height[0];
        for(int i = 1; i < n; i++){ //O(N)
            lMax[i] = Math.max(lMax[i-1], height[i]);
        }

        rMax[n-1] = height[n-1];
        for(int j = n-2; j >= 0; j--){ // O(N)
            rMax[j] = Math.max(rMax[j+1], height[j]);
        }

        int res = 0;
        for(int i = 0; i < n; i++){ // O(N)
            res += Math.min(lMax[i], rMax[i]) - height[i];
        }
        
        return res;
    }
}
```

7. Move Zeroes
```

//TC : O(N) due to while loop and SC : O(1)
class Solution {
    public void moveZeroes(int[] nums) {

        int i = -1;
        int j = 0;

        while(j < nums.length){
            if(nums[j] != 0){
                ++i;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
            ++j;
        }
        
    }
}
```

8. Remove Duplicates from Sorted Array
```
//TC : O(N), SC : O(1)

class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;

        if(n == 0){
            return 0;
        }

        int i = 0;
        for(int j = 1; j < n; j++){ //O(N)
            if(nums[i] != nums[j]){
                ++i;
                nums[i] = nums[j];
            }
        }

        return i+1;
        
    }
}
```

9. Sort Colors
```
//TC : O(N), SC : O(1)

class Solution {
    public void sortColors(int[] nums) {
        //Dutch National Flag Algorithm

        int l = -1;
        int m = 0;
        int h = nums.length - 1;

        while(m <= h){
            if(nums[m] == 0){
                int temp = nums[l+1];
                nums[l+1] = nums[m];
                nums[m] = temp;
                ++l;
                ++m;
            }
            else if(nums[m] == 1){
                ++m;
            }else {
                int temp = nums[h];
                nums[h] = nums[m];
                nums[m] = temp;
                --h;
            }
        }
    }
}
```

10. Merge Sorted Array
```
//TC : O(M+N), O(1) : 1
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int last = m+n-1; //last index of nums1;

        while(m > 0 && n > 0){ // m and n are length not index
            if(nums1[m-1] >= nums2[n-1]){
                nums1[last] = nums1[m-1];
                --m;
            } else{
                nums1[last] = nums2[n-1];
                --n;
            }
            --last;
        }

        while(n > 0){
            nums1[last] = nums2[n-1];
            --last;
            --n;
        }
        // we dont need for nums1 as if nums2 is empty then nums1 elements are already at correct position
    }
}
```

11. Valid Palindrome
```
// TC : O(N), SC : O(1)

class Solution {
    public boolean isPalindrome(String s) {
        int l = 0;
        int h = s.length() - 1;

        while(l < h){
            while(l < h && !Character.isLetterOrDigit(s.charAt(l))){
                ++l;
            }
            while(l < h && !Character.isLetterOrDigit(s.charAt(h))){
                --h;
            }

            if(Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(h))){
                return false;
            }

            ++l;
            --h;
        }

        return true;
        
    }
}
```

12. Squares of a Sorted Array
```
//TC : O(N), SC : O(N)
class Solution {
    public int[] sortedSquares(int[] nums) {
        //Two pointer
        int n = nums.length;
        int l = 0;
        int r = n-1;
        int ans [] = new int[n];
        int idx = n-1;

        while(l <= r){
            if(Math.abs(nums[l]) <= Math.abs(nums[r])){
                ans[idx] = nums[r] * nums[r];
                --r;
            }else {
                ans[idx] = nums[l] * nums[l];
                ++l;
            }
            --idx;
        }

        return ans;
        
    }
}
```

13. Interval List Intersection
```
// TC : O(M+N), SC : O(1)
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        // Two pointer
        int i = 0;
        int j = 0;
        List<int[]> res = new ArrayList<>();
        int m = firstList.length;
        int n = secondList.length;

        while(i < m && j < n){
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);

            if(start <= end){
                res.add(new int[]{start, end});
            }
            
            /*
            firstList[i][1] < secondList[j][1] then it can never intersect with firstList[i+1][0] since its disjoint as given in question
            and never intersect with secondList[j+1] since secondList[j] is disjoint to secondList[j+1] so to intersect with secondList[j+1]
            firstList[i][1] >= secondList[j+1][0] which is not the case here hence ++i
            */
            if(firstList[i][1] < secondList[j][1]){
                ++i;
            }else{
                ++j;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
```

15. Reverse String
```
// TC : O(N), SC: O(1)

class Solution {
    public void reverseString(char[] s) {

        int i = 0;
        int j = s.length - 1;

        while(i < j){
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            ++i;
            --j;
        }
        
    }
}
```

******** PATTERN - 2 : SLIDING WINDOW ********

16. Longest Substring Without Repeating Characters

```
//TC : O(N), SC : O(N) : HashSet
class Solution {
    public int lengthOfLongestSubstring(String s) {
        //Two pointer
        int n = s.length();
        int res = 0;
        Set<Character> set = new HashSet<>();

        int left = 0;
        for(int right = 0; right < n; right++){ //grow
            while(set.contains(s.charAt(right))){ //shrink
                set.remove(s.charAt(left));
                ++left;
            }
            set.add(s.charAt(right));
            res = Math.max(res, right-left+1);
        }
        return res;
    }
}

17. Minimum Window Substring
```
//TC : Theta(N), SC : Theta(t.length())

class Solution {
    public String minWindow(String s, String t) {

        //Pattern : HashMap + Sliding window

        int requiredCount = t.length();
        int n = s.length();

        if(requiredCount > n){
            return "";
        }

        Map<Character, Integer> map = new HashMap<>();

        for(char ch : t.toCharArray()){ // Theta(requiredCount)
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int i = 0;
        int j = 0;
        int start_i = 0;
        int min_window = Integer.MAX_VALUE;

        while(j < n){ //expand --> Overall Theta(N)
            char ch = s.charAt(j);
            
            if(map.containsKey(ch) && map.get(ch) > 0){
                --requiredCount;
            }

            map.put(ch, map.getOrDefault(ch, 0) - 1);

            while(requiredCount == 0){
                int curr_window = j - i + 1;

                if(min_window > curr_window){
                    min_window = curr_window;
                    start_i = i;
                }

                //shrink
                char start_char = s.charAt(i);
                map.put(start_char , map.getOrDefault(start_char,0) + 1);

                if(map.get(start_char) > 0){
                    ++requiredCount;
                }

                ++i;
            }
            ++j;
        }


        return min_window == Integer.MAX_VALUE ? "" : s.substring(start_i, start_i + min_window);

        
    }
}
```

```

18. Find All Anagrams in a String
```
// TC : O(M-N), SC : O(N)

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        //Pattern : Sliding window
        List<Integer> res = new ArrayList<>(); //O(N)
        int m = s.length();
        int n = p.length();

        if(n > m){
            return res;
        }

        int [] need = new int[26];
        for(char c : p.toCharArray()){ // O(N)
            need[c - 'a']++;
        }

        int [] window = new int[26];
        for(int i = 0; i < n; i++){ //O(N)
            window[s.charAt(i) - 'a']++;
        }

        if(Arrays.equals(need, window)){
            res.add(0);
        }


        //sliding window
        for(int start = 1; start <= m-n; start++){ //O(M-N)
            window[s.charAt(start-1) -'a']--;
            window[s.charAt(start+n - 1) - 'a']++;
            if(Arrays.equals(need, window)){
                res.add(start);
            }
        }

        return res;
    }
}
```

19. Permutation in String
```
TC : O(N), SC : O(1)/ O(26)
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length();//smaller
        int n = s2.length();//larger

        if(m > n){
            return false;
        }

        int need [] = new int[26];
        int window [] = new int[26];

        for(char c : s1.toCharArray()){ //O(M)
            need[c - 'a']++;
        }

        for(int i = 0; i < m; i++){ //O(M)
            window[s2.charAt(i) - 'a']++;
        }

        if(Arrays.equals(need, window)){
            return true;
        }
        

        //sliding window
        for(int right = 1; right <= n-m; right++){ //O(n-m * 26)
            window[s2.charAt(right - 1) - 'a']--;
            window[s2.charAt(right + m - 1) - 'a']++;
            if(Arrays.equals(window, need)){ //O(26)
                return true;
            }
        }

        return false;
    }
}
```

20. Minimum Size Subarray Sum

Note : 
```
When all numbers are positive:
Expanding window (right++) → sum always increases
Shrinking window (left++) → sum always decreases
This makes behavior predictable (monotonic)

What breaks with Negative Numbers?
With negatives:
Expanding window → sum can increase OR decrease
Shrinking window → sum can increase OR decrease
```
```
//TC : O(N), SC : O(1)
class Solution {
    public int minSubArrayLen(int target, int[] nums) {

        int left = 0;
        int res = Integer.MAX_VALUE;
        int sum = 0;
        for(int right = 0; right < nums.length; right++){ //O(N)
            sum += nums[right]; //include in current window first
            while(sum >= target){ //then check result then shrink
                res = Math.min(res, right - left + 1);
                sum -= nums[left];
                ++left;
            }
            
        }

        return res == Integer.MAX_VALUE ? 0 : res;
        
    }
}
```

21. Sliding Window Maximum
```
//TC : O(N)[elements are added/removed once in queue], SC : O(N-K+K) = O(N)

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //Sliding Window with Deque(maintaining max at first)
        int n = nums.length;
        int res[] = new int[n - k + 1];
        int idx = 0;
        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < k; i++){ //O(k)
            while(q.size() > 0 && nums[q.peekLast()] <= nums[i]){
                q.pollLast();
            }
            q.offerLast(i);
        }

        for(int i = k; i < n; i++){ //O(n-k)
            res[idx++] = nums[q.peekFirst()];

            while(q.size() > 0 && q.peekFirst() < i - k + 1){
                q.pollFirst();
            }

            while(q.size() > 0 && nums[q.peekLast()] <= nums[i]){
                q.pollLast();
            }
            q.offerLast(i);
        }

        res[idx] = nums[q.peekFirst()];

        return res;
        
    }
}
```
22. Longest Repeating Character Replacement
```
//TC : O(N), SC : O(1)
class Solution {
    public int characterReplacement(String s, int k) {
        // Slidling window with maxFlogic(dont increase unless we get > since window_size - maxF <= k)
        // if we want to incrase window_size we have to increase maxF

        int []count = new int[26]; //s consists of only uppercase English letters.
        int left = 0;
        int res = 0;
        int maxF = 0;

        for(int right = 0; right < s.length(); right++){
            count[s.charAt(right) - 'A']++;
            maxF = Math.max(maxF, count[s.charAt(right) - 'A']);
            while((right - left + 1) - maxF > k){
                count[s.charAt(left) - 'A']--;
                ++left;
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
        
    }
}
```

23. Subarray Sum Equals K
```
class Solution {
    public int subarraySum(int[] nums, int k) {
        // int count = 0;
        // int n = nums.length;

        // for(int i = 0; i < n; i++){
        //     int sum = 0;
        //     for(int j = i; j < n; j++){
        //         sum += nums[j];
        //         if(sum == k){
        //             ++count;
        //         }
        //     }
        // }

        // return count;

        //Pattern : Prefix Sum
        //TC : Theta(N), SC : O(N)
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);//0 sum ki ek subarray hai
        int res = 0;
        int currSum = 0;

        for(int num : nums){
            currSum += num;
            int diff = currSum - k;
            res += prefixSum.getOrDefault(diff, 0);
            prefixSum.put(currSum, prefixSum.getOrDefault(currSum, 0)+1);
        }
        return res;
    }
}
```

24. Linked List Cycle
```
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 //TC : O(N), SC : O(1)
public class Solution {
    public boolean hasCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }

        return false;
        
    }
}
```

25. Linked List Cycle II
```
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 //TC : O(N), SC : O(1)
public class Solution {
    public ListNode detectCycle(ListNode head) {
        //Floyd's algorithm

        //detech cycle
        ListNode slow = head;
        ListNode fast = head;

        boolean isCycleExists = false;

        while(fast != null && fast.next != null){ // O(N)
            slow = slow.next;
            fast = fast.next.next;

            //cycle exists
            if(slow == fast){
                isCycleExists = true;
                break;
            }
        }

        if(!isCycleExists){
            return null;
        }

        slow = head;

        while(slow != fast){ // O(N) 1->2->3->4->5->4
            slow = slow.next;
            fast = fast.next;
        }

        return slow;


    }
}
```

26. Middle of the linked list
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
// TC : O(N), SC : O(1)
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){ // O(N)
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
```

27. Palindrome Linked List
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
//TC : O(N), SC : O(1)
class Solution {
    public boolean isPalindrome(ListNode head) {
        /*
        1. Find middle of the LL
        2. Reverse second half
        3. Compare both the LL
        */

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){ // O(N)
            slow = slow.next;
            fast = fast.next.next;
        }

        //slow is at middle

        ListNode curr = slow;
        ListNode prev = null;

        while(curr != null){ //O(N/2)
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        ListNode head1 = head;
        ListNode head2 = prev;

        while(head2 != null){ //O(N/2)
            if(head1.val != head2.val){
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }

        return true;
        
    }
}
```

28. Find the duplicate number
```
// TC : O(N), SC : O(1)
class Solution {
    public int findDuplicate(int[] nums) {
        //Since we need to use constant extra space -> Linked List cycle detection
        int slow = 0;
        int fast = 0;

        while(true){ // O(N)
            slow = nums[slow];
            fast = nums[nums[fast]];
            if(slow == fast){
                break;
            }
        }

        slow = 0;

        while(true){ //O(N)
            slow = nums[slow];
            fast = nums[fast];
            if(slow == fast){
                return slow;
            }
        }
        
    }
}
```

29. Happy Number
```
//TC : O(logN)
/*
We process each digit of n
Number of digits ≈ log₁₀(n)

SC : O(1)
*/
class Solution {
    public boolean isHappy(int n) {
        //Linked List cycle detection
        int slow = n;
        int fast = n;

        //always end up in cycle either of 1 or else
        while(true){
            slow = sqrOfDigits(slow);
            fast = sqrOfDigits(sqrOfDigits(fast));
            if(slow == fast){
                break;
            }
        }

        return slow == 1;
        
    }

    private int sqrOfDigits(int n){
        int res = 0;
        while(n != 0){
            int digit = n%10;
            res += (digit * digit);
            n = n/10;
        }
        return res;
    }
}
```

30. Reorder List

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
//TC: O(N), SC : O(1)
class Solution {
    public void reorderList(ListNode head) {
        /*
        1. Find middle of list
        2. reverse second half
        3. form reorder list
        */
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){ //O(N/2)
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode head2 = slow.next;
        slow.next = null; //detach

        //reverse
        ListNode curr = head2;
        ListNode prev = null;
        while(curr != null){ //O(N/2)
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head2 = prev;
        ListNode head1 = head;
        
        while(head2 != null){ //O(N)
            ListNode temp1 = head1.next;
            ListNode temp2 = head2.next;

            head1.next = head2;
            head2.next = temp1;

            head1 = temp1;
            head2 = temp2;
        }
    }
}
```
31. Reverse Linked List
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
//TC : O(N), SC : O(1)
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
}
```

32. Reverse Linked List - II
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
 //TC : O(N), SC : O(1)
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0, head);
        ListNode curr = head;
        ListNode leftPrev = dummy;

        for(int i = 0; i < left - 1; i++){
            leftPrev = curr;
            curr = curr.next;
        }

        //curr is at left and leftPrev is just before it
        ListNode prev = null;
        for(int i = 0; i < right - left + 1; i++){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        leftPrev.next.next = curr;
        leftPrev.next = prev;

        return dummy.next;
        
    }
}
```