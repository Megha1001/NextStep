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
// TC : O(M+N), SC : O(k), k intersections
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

33. Reverse Nodes in k-Group
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

 /*
 TC
For each group:
    Count k nodes → O(k)
    Reverse k nodes → O(k)
    Per group work = O(k)
        Number of groups: ≈ N / k
        TC : (N/k) * O(k) = O(N)
 SC : O(N/k) due to recursion stack

 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        /*
        1. Check if K nodes exists
        2. recursively call for rest
        3. reverse current group
        */

        int cnt = 0;
        ListNode temp = head;
        while(cnt < k){
            if(temp == null){
                return head;
            }
            temp = temp.next;
            ++cnt;
        }


        ListNode prev = reverseKGroup(temp, k);

        temp = head;
        cnt = 0;

        while(cnt < k){
            ListNode next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
            ++cnt;
        }

        return prev;
        
    }
}
```

34. Repeat of #30

35. Swap Nodes in Pairs
Same as above just pass k=2;

36.  Rotate List
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
    public ListNode rotateRight(ListNode head, int k) {
        
        if(head == null || head.next == null){
            return head;
        }

        ListNode tail = head;
        int length = 1;

        while(tail.next != null){
            tail = tail.next;
            ++length;
        }

        k = k % length;
        if(k==0){
            return head;
        }

        tail.next = head;

        int steps = length - k - 1;

        ListNode newTail = head;

        for(int i = 0; i < steps; i++){
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
        
    }
}
```

37. Odd Even Linked List

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
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode odd = head;
        ListNode even = odd.next;
        ListNode evenHead = even;

        while(even != null && even.next != null){ //O(N)
            odd.next = even.next;
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;

        return head;
    }
}
```

38. Partition List

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
    public ListNode partition(ListNode head, int x) {

        // //Brute force
        // ListNode head1 = new ListNode(0);
        // ListNode head2 = new ListNode(0);
        // ListNode dummy1 = head1;
        // ListNode dummy2 = head2;
        // ListNode curr = head;

        // while(curr != null){
        //     if(curr.val < x){
        //         dummy1.next = new ListNode(curr.val);
        //         dummy1 = dummy1.next;
        //     }else {
        //         dummy2.next = new ListNode(curr.val);
        //         dummy2 = dummy2.next;
        //     }
        //     curr = curr.next;
        // }

        // dummy1.next = head2.next;

        // return head1.next;

        //Optimal Approach. : TC: O(N), SC : O(1)
        ListNode smallHead = new ListNode(0);
        ListNode bigHead = new ListNode(0);
        ListNode small = smallHead;
        ListNode big = bigHead;

        ListNode curr = head;

        while(curr != null){ //O(N)
            ListNode next = curr.next; //since we have to do curr.next = null;

            if(curr.val < x){
                small.next = curr;
                small = small.next;
            }else{
                big.next = curr;
                big = big.next;
            }
            curr.next = null;
            curr = next;
        }
        small.next = bigHead.next;

        return smallHead.next;

    }
}
```

39. Sort List
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
    public ListNode sortList(ListNode head) {
        /*
        1. Find middle
        2. Partition
        3. merge sorted list
        */

        if(head == null || head.next == null){
            return head;
        }

        //1. Find middle
        ListNode slow = head;
        ListNode fast = head.next; // IMP otherwise give infinite recursion

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode mid = slow.next;
        slow.next = null;

        //2. Partition
        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        //3. merge sorted list
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right){
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while(left != null && right != null){
            if(left.val <= right.val){
                tail.next = left;
                left = left.next;
            }else{
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        tail.next = left != null ? left : right;

        return dummy.next;
    }
}
```

40. Merge Intervals
```

```
//TC : O(NlogN), SC : O(N)
class Solution {
    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (a, b)->(a[0] - b[0])); //O(NlogN)

        int idx = 0;

        for(int i = 1; i < intervals.length; i++){ //O(N)
            if(intervals[idx][1] >= intervals[i][0]){
                intervals[idx][0] = Math.min(intervals[idx][0], intervals[i][0]);
                intervals[idx][1] = Math.max(intervals[idx][1], intervals[i][1]);
            }else{
                ++idx;
                intervals[idx] = intervals[i];
            }
        }

        return Arrays.copyOf(intervals, idx+1);//O(N), SC : O(N)
        
    }
}
```

41. Insert Interval
```
TC : O(N), SC : O(N)
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        /*
        --> New interval can be
            -> completely before
            -> completely after
            -> overlapping
        */

        List<int[]> res = new ArrayList<>(); // SC : O(N)

        for(int[] interval : intervals){ //O(N)
            //after
            if(newInterval[0] > interval[1]){
                res.add(interval);
            }
            //before
            else if(newInterval[1] < interval[0]){
                res.add(newInterval);
                newInterval = interval;
            }
            //overlap
            else{
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }
        }
        res.add(newInterval);

        return res.toArray(new int[res.size()][]);
        
    }
}

```

42. Meeting Rooms
```
/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */
//TC : O(NlogN), SC : O(1)
class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {
        /*
        To attend the meeting intervals shouldnot overlap
        */
        Collections.sort(intervals, Comparator.comparingInt(a -> a.start));//O(NlogN)
        for(int i = 1; i < intervals.size(); i++){ //O(N)
            if(intervals.get(i-1).end > intervals.get(i).start){
                return false;
            }
        }

        return true;

    }
}

```
43. Meeting Room - II
```
/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Solution {
    public int minMeetingRooms(List<Interval> intervals) {
        // Approach - 1 | TC : O(NlogN), SC : O(N)
        // TreeMap<Integer, Integer> map = new TreeMap<>();

        // for(int i = 0; i < intervals.size(); i++){
        //     map.put(intervals.get(i).start, map.getOrDefault(intervals.get(i).start, 0) + 1);
        //     map.put(intervals.get(i).end, map.getOrDefault(intervals.get(i).end, 0) - 1);
        // }

        // int count = 0, res = 0;
        // for(int val : map.values()){
        //     count += val;
        //     res = Math.max(count, res);
        // }

        // return res;

        //Approach - 2 : Two Pointers | TC : O(NlogN), SC : O(N)
        // int n = intervals.size();
        // int[] start = new int[n];
        // int[] end = new int[n];

        // for(int i = 0; i < n; i++){// O(N)
        //     start[i] = intervals.get(i).start;
        //     end[i] = intervals.get(i).end;
        // }

        // Arrays.sort(start); // O(NlogN)
        // Arrays.sort(end); // O(NlogN)
        
        // int count = 0, res = 0, s = 0, e = 0;
        // while(s < n){ // O(N)
        //     if(start[s] < end[e]){
        //         ++count;
        //         ++s;
        //     }else{
        //         --count;
        //         ++e;
        //     }
        //     res = Math.max(res, count);
        // }

        // return res;

        //Approach - 3 Priority Queue | TC : O(NLogN), SC : O(N)
        if(intervals.size() == 0){
            return 0;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Collections.sort(intervals, (a, b)-> (a.start - b.start));
        for(Interval interval : intervals){
            if(!pq.isEmpty() && pq.peek() <= interval.start){
                pq.poll(); //free the room
            }
            pq.offer(interval.end); //occupy the room
        }

        return pq.size();

    }
}
```

44. Non-overlapping intervals
```
//TC : O(NLogN), SC : O(1)
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        //Greedy Approach : The interval that ends earliest gives you the maximum room for future intervals
        //sort by end
        int n = intervals.length;

        if(n==0){
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a->a[1])); //O(NlogN) //saves integer overflow
        int prevEnd = intervals[0][1];
        int count = 0;
        for(int i = 1; i < n; i++){ //O(N)
            if(intervals[i][0] < prevEnd){ // not = since[1,2] , [2,3] are non-overlapping
                ++count;
            }else{
                prevEnd = intervals[i][1];
            }
        }

        return count;

    }
}

```

45. Minimum Number of Arrows to Burst Balloons

```
//TC : O(NlogN), SC : O(1)
class Solution {
    public int findMinArrowShots(int[][] points) {
        //pattern : Merge Intervals. Sort on the basis of start coordinate
        Arrays.sort(points, Comparator.comparingInt(a->a[0])); //O(NlogN)

        int count = 1;
        int[] prev = points[0];
        for(int i = 1; i < points.length; i++){ //O(N)
            int currStart = points[i][0];
            int currEnd = points[i][1];

            int prevStart = prev[0];
            int prevEnd = prev[1];

            if(currStart > prevEnd){
                ++count;
                prev = points[i];
            }else{
                //overlap
                prev[0] = Math.max(currStart, prevStart);
                prev[1] = Math.min(currEnd, prevEnd);
            }
        }

        return count;
        
    }
}
```

46. Interval List Intersections

```
// TC : O(M+N), SC : O(k), k intersections
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i = 0;
        int j = 0;
        int m = firstList.length;
        int n = secondList.length;
        List<int[]> res = new ArrayList<>();

        while(i < m && j < n){
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);

            if(end >= start){
                res.add(new int[]{start, end});
            }

            if(firstList[i][1] <= secondList[j][1]){
                ++i;
            }else{
                ++j;
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}
```

47. Binary Search
```
//TC : O(logN), SC : O(1)
class Solution {
    public int search(int[] nums, int target) {
        //Standard binary search

        int l = 0;
        int h = nums.length - 1;

        while(l <= h){
            int mid = l + (h-l)/2;

            if(nums[mid] == target){
                return mid;
            }

            else if(nums[mid] > target){
                h = mid-1;
            }else {
                l = mid+1;
            }
        }

        return -1;
        
    }
}
```

48. Search in rotated Sorted Array
```
//TC : O(N) // Duplicates can destroy the sorted-half property, forcing us to shrink linearly instead of dividing the search space., 
//SC : (1)
class Solution {
    public int search(int[] nums, int target) {
        //Idea : One of the half will always be sorted
        int low = 0;
        int high = nums.length - 1;

        while(low <= high){
            int mid = low + (high - low)/2;

            if(nums[mid] == target){
                return mid;
            }
            //key addition for duplicates
            else if(nums[mid]== nums[low] && nums[mid] == nums[high]){
                ++low;
                --high;
            }
            else if(nums[mid] <= nums[high]){
                //right half is sorted
                if(nums[mid] < target && target <= nums[high]){
                    //go to right
                    low = mid + 1;
                }else{
                    //go to left
                    high = mid - 1;
                }
            }

            else{
                if(nums[mid] > target && target >= nums[low]){
                    //go to left
                    high = mid - 1;
                }else{
                    //go to right
                    low = mid + 1;
                }
            }
        }

        return -1;
        
    }
}
```

49. Find Minimum in Rotated Sorted Array
```
// TC : O(logN), SC : O(1)
class Solution {
    public int findMin(int[] nums) {
        // Idea : Minimum always lies in the unsorted part
        int low = 0;
        int high = nums.length - 1;

        while(low < high){ // O(logN)
            int m = low + (high - low)/2;

            if(nums[m] > nums[high]){
                //right half is not sorted
                low = m + 1;
            }else{
                high = m;
            }
        }
        return nums[low];
        
    }
}
```

50 .  Search a 2D Matrix
```
//TC : O(log(M) + logN), SC : O(1)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //IDEA : Every row is unique non overlapping . target will lie in single row
        /*
        1. Search correct row -> start <=target<=end 
            //if we search for individual row -> O(M) but we dont want that complexity. Hence, will apply BS -> ROWS
        2. Then search in that row using binary search
        */
        
        // //1. Find targeted row

        // int targetRow = -1;
        // int rows = matrix.length;
        // int cols = matrix[0].length;


        // //for rows
        // int l = 0;
        // int h = rows-1;

        // while(l <= h){ //O(log M)
        //     int m = l + (h - l)/2;

        //     //lies in middle row
        //     if(target <= matrix[m][cols-1] && target >= matrix[m][0]){
        //         targetRow = m;
        //         break;
        //     }
        //     //go down;
        //     else if(target > matrix[m][cols-1]){
        //         l = m + 1;
        //     }else{
        //         h = m - 1;
        //     }
        // }
        
        // if (targetRow == -1) return false;

        // //2. Find in column fixing targetRow

        // l = 0;
        // h = cols-1;

        // while(l <= h){ //O(logN)
        //    int m = l + (h - l)/2;

        //    if(matrix[targetRow][m] == target){
        //         return true;
        //    }
        //    else if(matrix[targetRow][m] > target){
        //         h = m - 1;
        //    }else{
        //         l = m + 1;
        //    }
        // }

        // return false;


        //Approach - 2 Flatterned BS | TC : log(M × N) = log M + log N, SC : O(1)
        int rows = matrix.length;
        int cols = matrix[0].length;

        int l = 0;
        int h = rows * cols - 1;

        while(l <= h){
            int m = l + (h - l)/2;

            int row = m / cols;
            int col = m % cols;

            int val = matrix[row][col];

            if(val == target){
                return true;
            }

            else if(val > target){
                h = m - 1;
            }else {
                l = m + 1;
            }
        }

        return false;
        
    }
}

```

51. Search a 2D Matrix II
```
// TC : O(M + N), SC : O(1)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        //apply search in 2d matrix I
        int r = matrix.length;
        int c = matrix[0].length;

        int i = 0; //to check row
        int j = c - 1; //to check column

        while(i < r && j >= 0){
            if(matrix[i][j] == target){
                return true;
            }
            else if(matrix[i][j] > target){
                //move left
                --j;
            }else{
                //move down
                ++i;
            }
        }

        return false;
        
    }
}
```

52.  Median of Two Sorted Arrays 
```
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //Brute force : TC : O(M+N), SC : O(M+N)

        // int m = nums1.length;
        // int n = nums2.length;

        // int i = 0;
        // int j = 0;

        // int temp [] = new int[m+n];
        // int k = 0;

        // while(i < m && j < n){
        //     if(nums1[i] <= nums2[j]){
        //         temp[k++] = nums1[i++];
        //     }else{
        //         temp[k++] = nums2[j++];
        //     }
        // }

        // while(i < m){
        //     temp[k++] = nums1[i++];
        // }

        // while(j < n){
        //     temp[k++] = nums2[j++];
        // }


        // int size = m+n;
        // if(size%2 == 1){
        //     //odd length
        //     return temp[size/2];
        // }

        // return (temp[size/2] + temp[size/2 - 1])/2.0;


        //Approach : 2 : Without extra space
        int m = nums1.length;
        int n = nums2.length;
        int size = m+n;

        int i = 0, j = 0, k = 0;
        
        int idx1 = size/2 - 1;
        int element1 = idx1;

        int idx2 = size/2;
        int element2 = idx2;

        while(i < m && j < n){
            if(nums1[i] <= nums2[j]){
                if(k==idx1){
                    element1 = nums1[i];
                }
                if(k==idx2){
                    element2 = nums2[i];
                }
                ++i;
            }else{
                if(k==idx1){
                    element1 = nums1[j];
                }

                if(k==idx2){
                    element2 = nums2[j];
                }
                ++j;
            }
            ++k;
        }

        while(i<m){
            if(k==idx1){
                element1 = nums1[i];
            }
            if(k==idx2){
                element2 = nums2[i];
            }
            ++i;
            ++k;
        }

        while(j<n){
            if(k==idx1){
                element1 = nums1[j];
            }

            if(k==idx2){
                element2 = nums2[j];
            }
            ++j;
            ++k;
        }

        if(size % 2 == 1){
            
        }
    }
}
```

53. Find First & Last Position
```
//TC : O(logN) , SC : O(1)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        /*
        Idea is find first and last occurrence in two BS
        */

        int n = nums.length;
        
        if(n == 0){
            return new int[]{-1,-1};
        }

        //first occurrence
        int first = -1;
        int l = 0;
        int h = n-1;

        while(l <= h){ // O(logN)
            int m = l + (h - l)/2;

            if(target == nums[m]){
                first = m;
                h = m - 1;
            }else if(target > nums[m]){
                l = m + 1;
            }else{
                h = m - 1;
            }
        }

        if(first == -1){
            return new int[]{-1,-1};
        }

        //last occurrence
        l = 0;
        h = n-1;
        int last = -1;
        
        while(l <= h){ //O(logN)
            int m = l + (h - l)/2;

            if(target == nums[m]){
                last = m;
                l = m + 1;
            }else if(target > nums[m]){
                l = m + 1;
            }else{
                h = m - 1;
            }
        }
        

        return new int[]{first, last};
    }
}
```

54. Peak Element
```
//TC : O(logN), SC : O(1)
class Solution {
    public int minEatingSpeed(int[] piles, int h) {

        int low = 1;
        int high = Arrays.stream(piles).max().getAsInt();

        while (low <= high) {
            int m = low + (high - low) / 2;

            int totalTime = 0;

            for (int pile : piles) {
                totalTime += (pile + m - 1) / m;
            }

            if (totalTime <= h) {
                high = m - 1; // try smaller speed
            } else {
                low = m + 1;
            }
        }

        return low;
        //low → points to FIRST TRUE 
        //high → points to LAST FALSE
    }
}
```

55. Koko Eating Bananas
```
// TC : O(logN), SC : O(1)
class Solution {
    public int minEatingSpeed(int[] piles, int h) {

        int low = 1;
        int high = Arrays.stream(piles).max().getAsInt();
        int res = -1;

        while(low <= high){ // O(logN)
            int m = low + (high - low)/2;
            
            int totalTime = 0;
            for(int pile : piles){
                totalTime += (pile + m -1)/m;
            }

            if(totalTime <= h){
                res = m;
                high = m - 1;
            }else{
                low = m + 1;
            }
        }

        return res;
        
    }
}

```

56. Sqrt(x)
```
// TC : O(logX), SC : O(1)
class Solution {
    public int mySqrt(int x) {

        if(x == 0 || x == 1){
            return x;
        }

        //Search from 0 to x-1 -> Binary search
        int l = 0;
        int h = x;
        int res = 0;

        while(l <= h){ // O(logX)
            int m = l + (h-l)/2;
            
            if(m <= x/m){//to protect overflow
                res = m;
                l = m+1;
            }else {
                h = m-1;
            }
        }

        return res;
        
        
    }
}
```

57. Single Element in Sorted Array
```
// TC : O(logN), SC : O(1)
class Solution {
    public int singleNonDuplicate(int[] nums) {
        /*
        Analogy : Before single element every pair start at even position and end at odd position
        After single element this pattern disturbs --> and we can use binary search to search in either halfs
        */

        int l = 0;
        int h = nums.length - 1;

        while(l < h){ // not = as we need to consider h = m it willl make it infinite loop and if we consider <=h then we need to consider m wiht m==0 and m==n-1 -> complex

            int m = l + (h - l)/2;
            if(m%2 == 1){
                //make it event
                --m;
            }

            if(nums[m] == nums[m+1]){
                // pattern is not broken -> go right
                l = m + 2; //important
            }else {
                h = m; //consider m as well
            }

        }

        return nums[l];
        
    }
}
```

58. Time Based Key-Value Store
```
```

59. Search Insert Position
```
class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int h = nums.length - 1;
        int res = nums.length;

        while(l <= h){//find target so =
            int m = l + (h - l)/2;

            if(nums[m] >= target){
                res = m;
                h = m-1;
            }
            else{
                l = m+1;
            }
        }
        return res;
        
    }
}
```

60. Capacity to Ship Packages Within D Days
```
//TC : O(nLog(sum of weights)), SC : O(1)
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int l = 0;
        int r = 0;

        for(int w : weights){//O(N)
            l = Math.max(l, w);
            r += w;
        }

        int res = r;
        while(l <= r){ //O(log(sum))
            int m = l + (r - l)/2;
            if(canShip(weights, days, m)){
                res = Math.min(res, m);
                r = m-1;
            }else{
                l = m+1;
            }
        }

        return res; 
    }

    private boolean canShip(int []weights, int days, int cap){
        int ship = 1;
        int currCap = cap;
        for(int w : weights){//O(n)
            if(currCap - w < 0){
                ++ship;
                if(ship > days){
                    return false;
                }
                currCap = cap;
            }
            currCap -= w;
        }
        return true;
    }
}
```

61. Binary Tree Level Order Traversal
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

//We visit each node once and store up to one full level at a time.
//TC : O(n), SC : O(N), in complete binary tree last level has O(n/2)nodes
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return res;
        }

        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while(!q.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode curr = q.poll();
                level.add(curr.val);

                if(curr.left != null){
                    q.offer(curr.left);
                }
                if(curr.right != null){
                    q.offer(curr.right);
                }
            }
            res.add(level);
        }

        return res;
    }
}
```

62. Binary Tree Zigzag Level Order
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
//TC : O(N), traversing every node once, SC : O(N) : width of tree , in case of complete BST it can be N/2
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }

        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        boolean leftToRight = true;

        while(!q.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int size = q.size();
            for(int i = 0; i < size; i++){
                if(leftToRight){
                    TreeNode curr = q.pollFirst();
                    list.add(curr.val);
                    if(curr.left != null){
                        q.offerLast(curr.left);
                    }

                    if(curr.right != null){
                        q.offerLast(curr.right);
                    }
                }else{
                    TreeNode curr = q.pollLast();
                    list.add(curr.val);
                    if(curr.right != null){
                        q.offerFirst(curr.right);
                    }
                    if(curr.left != null){
                        q.offerFirst(curr.left);
                    }
                }
            }
            res.add(list);
            leftToRight = !leftToRight;
        }

        return res;
        
    }
}
```

63. Binary Tree Right Side View
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
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }

        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode curr = q.poll();
                if(i == size - 1){
                    res.add(curr.val);
                }
                if(curr.left != null){
                    q.offer(curr.left);
                }
                if(curr.right != null){
                    q.offer(curr.right);
                }
            }
        }
        return res;
        
    }
}

```
Approach - 2 DFS
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
//TC : O(N), visit every node exactly once, SC : O(H), where H is height of the tree
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer>res = new ArrayList<>();
        if(root == null){
            return res;
        }

        //using dfs
        dfs(root, 0, res);
        return res;
    }

    private void dfs(TreeNode node, int level, List<Integer>res){
        if(node == null){
            return;
        }
        if(level == res.size()){ // get first occurrence of that level when it visits first time i.e. right element
            res.add(node.val);
        }
        dfs(node.right, level+1, res);
        dfs(node.left, level+1, res);
    }
}
```

64. Number of Islands
```
// TC : O(rows * cols), SC : O(rows * cols)[all 1's]
class Solution {
    private int ROWS;
    private int COLS;
    private int directions[][] = {{0,1},{0,-1},{1,0},{-1,0}};
    public int numIslands(char[][] grid) {
        ROWS = grid.length;
        COLS = grid[0].length;
        int islands = 0;

        if(ROWS == 0){
            return islands;
        }

        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLS; c++){
                if(grid[r][c] == '1'){
                    // bfs(grid, r, c);
                    dfs(grid, r, c);
                    ++islands;
                }
            }
        }
        return islands;
    }

    private void dfs(char[][]grid, int r, int c){
        if(r < 0 || c < 0 || r >= ROWS || c >= COLS || grid[r][c] == '0'){
            return;
        }
        grid[r][c] = '0';

        for(int dir [] : directions){
            dfs(grid, r+dir[0], c+dir[1]);
        }
    }

    private void bfs(char [][] grid, int r, int c){
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        grid[r][c] = '0'; //visited

        while(!q.isEmpty()){
            int node[] = q.poll();
            int row = node[0];
            int col = node[1];

            for(int [] dir : directions){
                int nr = row + dir[0];
                int nc = col + dir[1];

                if(nr >=0 && nr < ROWS
                && nc >=0 && nc < COLS
                && grid[nr][nc] == '1'){
                    grid[nr][nc] = '0';
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }
}
```


65. Rotting Oranges
```
//TC : O(rows * cols), SC : O(rows * cols) [all fresh except 1]
class Solution {
    public int orangesRotting(int[][] grid) {
        int ROWS = grid.length;
        int COLS = grid[0].length;

        int fresh = 0;
        int time = 0;
        Deque<int[]> q = new ArrayDeque<>();
        //count fresh and put rotten in queue
        for(int i = 0; i < ROWS; i++){ //O(Rows * cols)
            for(int j = 0; j < COLS; j++){
                if(grid[i][j] == 1){
                    ++fresh;
                }else if (grid[i][j] == 2){
                    q.offer(new int[]{i, j});
                }
            }
        }

        if(fresh == 0){
            return time;
        }
        
        int directions[][] = {{0,1},{0,-1},{1,0},{-1,0}};

        while(fresh > 0 && !q.isEmpty()){
            int size = q.size();

            for(int i = 0; i < size; i++){
                int[]curr = q.poll();

                for(int[] dir : directions){
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];

                    if(nr >= 0 && nr < ROWS
                        && nc >= 0 && nc < COLS
                        && grid[nr][nc] == 1){
                            --fresh;
                            grid[nr][nc] = 2;
                            q.offer(new int[]{nr, nc});
                    }
                }
            }
            ++time;
        }

        return fresh == 0 ? time : -1;
    }
}
```

66. Word Ladder
```
/*
Use BFS because we need the shortest transformation sequence
For each word, try changing each character from 'a' to 'z'
if the generated word exists in the set , push it to queue and remove it from set.
The first time we reach endWord, BFS gurantees the minimum number of steps.
TC : O(N*L*L*26) = O(N*L*L) and SC : O(N), stores all the words at most once
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);//for fast lookup

        if(!set.contains(endWord) || beginWord.length() != endWord.length()){
            return 0;
        }
        
        Queue<String> q = new ArrayDeque<>();
        q.offer(beginWord);
        int steps = 1;

        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0; i < size; i++){ //O(N)
                String curr = q.poll();

                if(curr.equals(endWord)){
                    return steps;
                }

                char[] arr = curr.toCharArray();

                for(int j = 0; j < arr.length; j++){ //O(L)
                    char original = arr[j];
                    
                    for(char ch = 'a'; ch <= 'z'; ch++){ //O(26)
                        arr[j] = ch;
                        String newString = new String(arr); // O(L) => string creation cost O(L)
                        if(set.contains(newString)){
                            q.offer(newString); // SC : O(N)
                            set.remove(newString); //visited
                        }
                    }
                    arr[j] = original; //important
                }
            }
            ++steps;
        }

        return 0;//important
    }
}
```

67. Clone graph
```
/*
// Definition for a Node.
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
//TC : O(V+E), SC : O(V) for hashmap
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null){
            return null;
        }

        Queue<Node> q = new ArrayDeque<>();
        Map<Node, Node> oldToNew = new HashMap<>();

        q.offer(node);
        oldToNew.put(node, new Node(node.val));

        while(!q.isEmpty()){
            Node curr = q.poll();

            for(Node neighbor : curr.neighbors){
                if(!oldToNew.containsKey(neighbor)){
                    oldToNew.put(neighbor, new Node(neighbor.val));
                    q.offer(neighbor);
                }
                oldToNew.get(curr).neighbors.add(oldToNew.get(neighbor));
            }
        }
        
        return oldToNew.get(node);
    }
}
```

68. Shortest Path in Binary Matrix
```
//TC:  O(N*N), SC : O(N*N), The queue may store up to O(N^2) cells in the worst case, so space is also O(N^2).”
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        /*
        Patter : BFS (Shortest, 8 directional visit)
        */
        int n = grid.length;

        if(grid[0][0] == 1 || grid[n-1][n-1] == 1){
            return -1;
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0});
        grid[0][0] = 1; //visited

        int path = 1; // starting cell itself counts as path length 1
        
        int [][] directions = {{0,-1},{0,1},{1,0},{-1,0},{1,-1},{1,1},{-1,1},{-1,-1}};

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int[] curr = q.poll();

                if(curr[0] == n-1 && curr[1] == n-1){
                    return path;
                }

                for(int[] dir : directions){
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];

                    if(nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 0){
                        grid[nr][nc] = 1; //visited
                        q.offer(new int[]{nr, nc});
                    }
                }
            }

            ++path;
        }


        return -1;
        
    }
}
```

69. Snakes and Ladders
```
//TC : O(N*N), BFS process every node atmost once( N*N*6), SC : O(N*N)
class Solution {
    private int n;
    public int snakesAndLadders(int[][] board) {
        //BFS
        n = board.length;

        boolean visited[][] = new boolean[n][n]; // SC : O(N*N)

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        visited[n-1][0] = true; // you start at square 1 (at row 5, column 0).

        int steps = 0;

        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0; i < size; i++){
                int curr = q.poll();

                if(curr == n*n){
                    return steps;
                }

                //roll
                for(int dice = 1; dice <= 6; dice++){
                    int next = curr + dice;
                    if(next > n*n){
                        break;
                    }

                    int[] coordinates = getCoordinates(next);
                    int r = coordinates[0];
                    int c = coordinates[1];

                    if(visited[r][c]) continue;
                    visited[r][c] = true;

                    if(board[r][c] == -1){
                        q.offer(next);
                    }else{
                        q.offer(board[r][c]);
                    }
                }
            }
            ++steps;
        }

        return -1;
        
    }

    private int[] getCoordinates(int s){
        int row = n - 1 - (s - 1) / n; // n-1 - (row from up to down)
        int col = (s-1) % n; //standard L to R

        //zigzag -> R to L
        if((n % 2 == 0 && row % 2 == 0) || (n % 2 == 1 && row % 2 == 1)){
            col = n - 1 - col;
        }

        return new int[]{row, col};

    }

}
```


70. Flood Fill
```
// TC : O(M*N), SC : O(M*N)
class Solution {
    private int[][]directions = {{0,1},{0,-1},{1,0},{-1,0}};
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        //DFS
        int original = image[sr][sc];
        if(original == color) return image;

        dfs(image, sr, sc, color, original);
        return image;
    }

    private void dfs(int[][]image, int r, int c, int color, int original){
        if(r < 0 || c < 0 || r >= image.length || c >= image[0].length || image[r][c] != original){
            return;
        }

        image[r][c] = color;
        for(int [] dir : directions){
            dfs(image, r + dir[0], c + dir[1], color, original);
        }
    }
}
```

71. Populating Next Right Pointers in Each Node
```
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
//TC : O(N), SC : O(N)
class Solution {
    // public Node connect(Node root) {
    //     //BFS traversal level by level

    //     if(root == null){
    //         return null;
    //     }

    //     Queue<Node> q = new ArrayDeque<>(); // N/2 nodes at last level -> O(N)
    //     q.offer(root);

    //     while(!q.isEmpty()){ // O(N) Visiting every node
    //         int size = q.size();
    //         Node prev = null;
    //         for(int i = 0; i < size; i++){
    //             Node curr = q.poll();
    //             if(prev != null){
    //                 prev.next = curr;
    //             }
    //             prev = curr;

    //             if(curr.left != null) q.offer(curr.left);
    //             if(curr.right != null) q.offer(curr.right);
    //         }
    //     }

    //     return root;
        
    // }

    //TC : O(N), SC : O(1) -> without auxiliary space
    public Node connect(Node root) {
        if(root == null){
            return null;
        }

        Node leftStart = root;
        while(leftStart.left != null){
            Node curr = leftStart;
            while(curr != null){
                curr.left.next = curr.right;

                if(curr.next != null){
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }

            leftStart = leftStart.left;
        }

        return root;
    }
}
```

72. 

```
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        //Approach - 1 : BFS level order traversal: connect nodes within each level using prev pointer
        // if(root == null){
        //     return null;
        // }

        // Queue<Node> q = new ArrayDeque<>();
        // q.offer(root);

        // while(!q.isEmpty()){
        //     int size = q.size();
        //     Node prev = null;
        //     for(int i = 0; i < size; i++){
        //         Node curr = q.poll();
        //         if(prev != null){
        //             prev.next = curr;
        //         }
        //         prev = curr;
        //         if(curr.left != null) q.offer(curr.left);
        //         if(curr.right != null) q.offer(curr.right);
        //     }
        // }

        // return root;

        //Approach-2 : Without using auxiliary space | TC : O(N), SC : O(1)
        if(root == null){
            return null;
        }

        Node curr = root;
        while(curr != null){
            Node dummy = new Node(0);
            Node tail = dummy;

            //build the next ptr of next level while traversing current
            while(curr != null){
                if(curr.left != null){
                    tail.next = curr.left;
                    tail = tail.next;
                }

                if(curr.right != null){
                    tail.next = curr.right;
                    tail = tail.next;
                }
                curr = curr.next;
            }
            curr = dummy.next;
        }
        return root;
        
    }
}
```

73. Surrounded Regions
```
//TC : O(Rows * cols), SC : O(rows * cols)[due to recursion call stack]
class Solution {
    private int ROWS;
    private int COLS;
    private int [][]directions = {{0,1},{0,-1},{1,0},{-1,0}};
    public void solve(char[][] board) {
        // DFS from border
        //Find the DFS for safe 'O' and mark them as 'T'

        ROWS = board.length;
        COLS = board[0].length;

        //for first and last row
        for(int c = 0; c < COLS; c++){
            if(board[0][c] == 'O') dfs(board, 0, c);
            if(board[ROWS - 1][c] == 'O') dfs(board, ROWS - 1, c);
        }

        //for first and last column
        for(int r = 0; r < ROWS; r ++){
            if(board[r][0] == 'O') dfs(board, r, 0);
            if(board[r][COLS - 1] == 'O') dfs(board, r, COLS - 1);
        }

        // iterate through matrix
        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLS; c++){
                if(board[r][c] == 'O'){
                    board[r][c] = 'X';
                }
                if(board[r][c] == 'T'){
                    board[r][c] = 'O';
                }
            }
        }
        
    }

    private void dfs(char[][]board, int r, int c){
        if(r < 0 || r >= ROWS || c < 0 || c >= COLS || board[r][c] != 'O'){
            return;
        }
        board[r][c] = 'T'; //mark as visited

        for(int[]dir : directions){
            dfs(board, r + dir[0], c + dir[1]);
        }
    }

}
```

74. As Far from Land as Possible
```
//TC : O(N*N), SC : O(N*N)
class Solution {
    public int maxDistance(int[][] grid) {

        //Multisource BFS

        int n = grid.length;
        Queue<int[]> q = new ArrayDeque<>();

        for(int r = 0; r < n; r++){
            for(int c = 0; c < n; c++){
                if(grid[r][c] == 1){
                    q.offer(new int[]{r,c});
                }
            }
        }

        //all 0's or all 1's
        if(q.isEmpty() || q.size() == n*n){
            return -1;
        }

        /*
        We initialize distance = -1 because the queue initially contains all land cells, which form level 0. Since distance is increased once per BFS level, starting from -1 ensures that after processing the first level, distance becomes 0, which correctly represents land.
        */
        int distance = -1;
        int [][]directions = {{1,0},{-1,0},{0,-1},{0,1}};

        while(!q.isEmpty()){
            int size = q.size();
            ++distance; //for every level
            for(int i = 0; i < size; i++){
                int[] curr = q.poll();

                for(int[]dir : directions){
                    int nr = dir[0] + curr[0];
                    int nc = dir[1] + curr[1];

                    if(nr >=0 && nc >= 0
                        && nr < n && nc < n
                        && grid[nr][nc] == 0){
                            grid[nr][nc] = 1; //mark as visited
                            q.offer(new int[]{nr, nc});
                        }
                }
            }
        }
        return distance;
        
    }
}
```

75. Pacific Atlantic Water Flow
```
//TC : O(rows * cols), SC : O(rows * cols)
class Solution {
    private int ROWS;
    private int COLS;
    private int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // MUltisource BFS/ DFS
        ROWS = heights.length;
        COLS = heights[0].length;

        boolean [][] pacific = new boolean[ROWS][COLS];
        boolean [][] atlantic = new boolean[ROWS][COLS];

        for(int c = 0 ; c < COLS; c++){
            dfs(heights, 0, c, pacific);
            dfs(heights, ROWS - 1, c, atlantic);
        }

        for(int r = 0; r < ROWS; r++){
            dfs(heights, r, 0, pacific);
            dfs(heights, r, COLS - 1, atlantic);
        }

        List<List<Integer>> res v= new ArrayList<>();
        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLS; c++){
                if(pacific[r][c] && atlantic[r][c]){
                    res.add(Arrays.asList(r, c));
                }
            }
        }

        return res;
        
    }

    private void dfs(int[][]heights, int r, int c, boolean[][]visited){
        if(visited[r][c]) return;
        visited[r][c] = true;

        for(int []dir : directions){
            int nr = r + dir[0];
            int nc = c + dir[1];

            if(nr >= 0 && nc >= 0
            && nr < ROWS && nc < COLS
            && !visited[nr][nc]
            && heights[nr][nc] >= heights[r][c]){ // >= is imp
                dfs(heights, nr, nc , visited);
            }
        }
    }
}
```

76.  Network Delay Time (BFS/Dijkstra)


77. Binary tree Vertical order Traversal - Lint Code
```
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

/*
BFS : 3 comes before 15(levelwise) and 9 comes before 20 (left to right)
*/

//TC : O(N) [number of nodes], SC : O(N), for complete BST where n/2 nodes at last level
public class Solution {
    /**
     * @param root: the root of tree
     * @return: the vertical order traversal
     */

    class Pair{
        TreeNode node;
        int col;

        Pair(TreeNode node, int col){
            this.node = node;
            this.col = col;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root== null){
            return res;
        }

        TreeMap<Integer, List<Integer>> map = new TreeMap<>(); //to maintain col natural order --> TC : O(NLogN)
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(root, 0));
        
        while(!q.isEmpty()){ //O(N)
            Pair curr = q.poll();
            TreeNode node = curr.node;
            int col = curr.col;

            map.putIfAbsent(col, new ArrayList<>());
            map.get(col).add(node.val);

            if(node.left != null){
                q.offer(new Pair(node.left, col - 1));
            }
            if(node.right != null){
                q.offer(new Pair(node.right, col + 1));
            }
        }

        for(List<Integer> list : map.values()){
            res.add(list);
        }

        return res;
    }
}
```

78.


79. Maximum Depth of Binary Tree
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
//TC:O(N), SC:O(height)
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));  
    }
}
```


80. Invert Binary Tree
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
//TC : O(N) traverse every node
//SC : O(N) in case of Complete binary tree last level has N/2 nodes
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while(!q.isEmpty()){
            TreeNode curr = q.poll();

            TreeNode temp = curr.left; // swapping references not values
            curr.left = curr.right;
            curr.right = temp;

            if(curr.left != null) q.offer(curr.left);
            if(curr.right != null) q.offer(curr.right);
        }
        
        return root;
    }
}
```

81. Same Tree
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
// TC : O(N) : Traversing every node
// SC : O(height) : DFS
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if(p == null && q == null){
            return true;
        }

        if(p == null || q == null){
            return false;
        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        
    }
}

```

82. Symmetric Tree
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
// TC : O(N) : Traverse all the nodes
// SC : O(H), height of tree
class Solution {
    public boolean isSymmetric(TreeNode root) {

        if(root == null){
            return true;
        }

        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode leftNode, TreeNode rightNode){
        if(leftNode == rightNode){
            return true;
        }

        if(leftNode == null || rightNode == null || leftNode.val != rightNode.val){
            return false;
        }

        return isMirror(leftNode.left, rightNode.right) && isMirror(leftNode.right, rightNode.left);
    }
}
```

83. Validate Binary Search Tree
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
//TC : O(N)
// SC : O(Height)
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long min, long max){
        if(root != null){
            if(root.val <= min || root.val >= max){
                return false;
            }
            return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
        }
        return true;
    }
}
```

84. Lowest Common Ancestor of a Binary Tree
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// TC : O(N)
// SC : O(Height) 
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }

        if(root.val == p.val || root.val == q.val){
            return root;
        }

        TreeNode lca_left = lowestCommonAncestor(root.left, p, q);
        TreeNode lca_right = lowestCommonAncestor(root.right, p, q);

        if(lca_left != null && lca_right != null){
            return root;
        }

        if(lca_left != null){
            return lca_left;
        }else {
            return lca_right;
        }
        
    }
}
```

85. Lowest Common Ancestor of a Binary Search Tree
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//TC : O(h) (Balanced : O(logN), O(N) in case of skewed tree)
// SC : O(1)
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //use binary search property
        /*
        1. if both are < root -> go to left
        2. if both are > root -> go to right;
        3. else return root;
        */

        while(root != null){
            if(root.val > p.val && root.val > q.val){
                root = root.left;
            }else if(root.val < p.val && root.val < q.val){
                root = root.right;
            }else{
                return root;
            }
        }
        return null;
    }
}
```

86. Binary Tree Maximum Path Sum
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
//TC : O(N), traverse all nodes at once
//SC : O(height)
class Solution {
    private int maxSum;
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;

        /*
        Case 1 : l + r + root.val // we cant return as we found path below only
        Case 2 : Max(l, r) + root.val
        Case 3 : root.val
        */
        solve(root);
        return maxSum;
        
    }

    private int solve(TreeNode root){
        if(root == null){
            return 0;
        }

        // int l = solve(root.left);
        // int r = solve(root.right);
        int l = Math.max(0, solve(root.left)); // if subtree gives negative sum dont take it
        int r = Math.max(0, solve(root.right));

        //case -1
        int both_side = l + r + root.val;

        //case - 2
        int only_one_side = Math.max(l, r) + root.val;

        //case - 3
        int only_root = root.val;

        maxSum = Math.max(Math.max(Math.max(both_side, only_one_side), only_root), maxSum);

        return Math.max(only_one_side, only_root); //case - 1  we cant return as we found path below only

    }
}
```

86. Diameter of Binary Tree
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
//TC : O(N)
//SC : O(H), height of tree
class Solution {
    private int diameter;
    public int diameterOfBinaryTree(TreeNode root) {
        findHeight(root);
        return diameter;
    }

    private int findHeight(TreeNode root){
        if(root == null){
            return 0;
        }

        int left_height = findHeight(root.left);
        int right_height = findHeight(root.right);
        diameter = Math.max(diameter, left_height + right_height);
        return 1 + Math.max(left_height, right_height);
    }
}

```

88. Subtree of Another Tree
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
 //TC : O(N*M) : checking subtree at every node
 //SC : O(h1 + h2)
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(subRoot == null){
            return true;
        }

        if(root == null){ // && subRoot != null
            return false;
        }

        if(isSameTree(root, subRoot)){
            return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        
    }

    private boolean isSameTree(TreeNode root, TreeNode subRoot){
        if(root == null && subRoot == null){
            return true;
        }

        if(root == null || subRoot == null){
            return false;
        }

        if(root.val != subRoot.val){
            return false;
        }

        return isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right);
    }
}
```

89. Path Sum
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
// TC :  O(N), traverse all node
// SC : O(H), where H is height
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        //DFS
        if(root == null){
            return false;
        }
        targetSum -= root.val;

        //leaf node
        if(root.left == null && root.right == null){
            return targetSum == 0;
        }

        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
        
    }
}
```

90. Path Sum II
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
//TC : O(N + totalLengthOfAllReturnedPaths) traversing all nodes, we copy the path (O(pathLength))
//SC : O(H)
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> path = new ArrayList<>();

        dfs(root, targetSum, path);

        return res;
    }

    private void dfs(TreeNode node, int targetSum, List<Integer>path){
        if(node == null){
            return;
        }
        targetSum -= node.val;
        path.add(node.val);
        if(node.left == null && node.right == null){
            if(targetSum == 0){
                res.add(new ArrayList(path));
            }
        }else{
            dfs(node.left, targetSum, path);
            dfs(node.right, targetSum, path);
        }

        path.remove(path.size() - 1);  
    }
}
```

91. Path Sum III
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
// TC : O(N), traversing all nodes
// SC : O(H), where H is height 
class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        //Prefix Sum + backtracking
        Map<Long, Integer> freq = new HashMap<>(); // sum, count
        freq.put(0L, 1);
        return dfs(root, 0L, targetSum, freq);
    }

    private int dfs(TreeNode node, long runningSum, int targetSum, Map<Long, Integer> freq){
        if(node == null){
            return 0;
        }

        runningSum += node.val;
        int countEndingHere = freq.getOrDefault(runningSum - targetSum , 0);
        freq.put(runningSum, freq.getOrDefault(runningSum, 0) + 1);

        int total = countEndingHere + 
                    dfs(node.left, runningSum, targetSum, freq) +
                    dfs(node.right, runningSum, targetSum, freq);

        freq.put(runningSum, freq.get(runningSum) - 1); // backtracking

        return total;
    }
}
```

91. 

Approach - 1
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
//TC : O(N)
//SC : O(N/2) : root to leaf storing alone  private List<Integer> list = new ArrayList<>();
// = O(N*H) , H due to string creation
class Solution {
    private List<Integer> list = new ArrayList<>();
    public int sumNumbers(TreeNode root) {
        populateList(root, "");
        int res = 0;
        for(Integer num : list){
            res += num;
        }
        return res;
    }

    private void populateList(TreeNode node, String num){
        if(node == null){
            return;
        }
        num += String.valueOf(node.val); // create new String at each step // O(H);
        if(node.left == null && node.right == null){
            list.add(Integer.parseInt(num)); // can overflow if path is long
        }
        populateList(node.left, num);
        populateList(node.right, num);

        num = num.substring(0, num.length() - 1);
    }
}
```

Approach - 2 Better
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
// TC : O(N) 
// SC : O(H)
class Solution {
    public int sumNumbers(TreeNode root) {
        // IDEA : next = curr * 10 + node.val
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int current){
        if(node == null){
            return 0;
        }
        
        int next = current * 10 + node.val;
        //check leaf
        if(node.left == null && node.right == null){
            return next;
        }

        return dfs(node.left , next) + dfs(node.right, next);
    }
}
```

92. Flatten Binary Tree to Linked List
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
//TC : O(N)
//SC : O(H), height of tree
class Solution {
    private TreeNode nextRight;
    public void flatten(TreeNode root) {
        //traverse reverse prerorder
        // right -> left -> node
        if(root == null){
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.left = null;
        root.right = nextRight;
        nextRight = root;   
    }
}
```

93. Construct Binary Tree from Preorder and Inorder Traversal
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
    private int preIndex;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        /*
        IDEA : For every preorder element search in inorder array and buidtree
        */
        if(preorder.length != inorder.length){
            return null;
        }

        int n = preorder.length;
        preIndex = 0;
        Map<Integer, Integer>map = new HashMap<>();
        for(int i = 0; i < n; i++){
            map.put(inorder[i], i);
        }

        return buildTree(map, preorder, 0, n-1);
    }

    private TreeNode buildTree(Map<Integer, Integer>map, int[]pre, int is, int ie){
        if(is > ie){
            return null;
        }

        TreeNode root = new TreeNode(pre[preIndex++]);
        int inIndex = map.get(root.val);
        root.left = buildTree(map, pre, is, inIndex - 1);
        root.right = buildTree(map, pre, inIndex + 1, ie);
        return root;
    } 
}
```

94. Construct Binary Tree from Preorder and Inorder Traversal
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*
Time: O(N) serialize + O(N) deserialize
Space: O(N) for the string/tokens, plus recursion O(H)
*/ 
public class Codec {
    /*
    Logic : DFS with preorder traversal
    */

    private String NIL = "#";
    private String SEP = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        build(root, sb);
        return sb.toString();
    }

    private void build(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append(NIL).append(SEP);
            return;
        }
        sb.append(root.val).append(SEP);
        build(root.left, sb);
        build(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] tokens = data.split(SEP);
        int[] idx = new int[1]; // to share it across states
        return parse(tokens, idx);
    }

    private TreeNode parse(String[]tokens, int[]idx){
        String t = tokens[idx[0]++];
        if(t.equals(NIL)){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(t));
        root.left = parse(tokens, idx); //idx is already updated in line number 42
        root.right = parse(tokens, idx);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
```


95. Serialize and Deserialize Binary Tree
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*
Time: O(N) serialize + O(N) deserialize
Space: O(N) for the string/tokens, plus recursion O(H)
*/ 
public class Codec {
    /*
    Logic : DFS with preorder traversal
    */

    private String NIL = "#";
    private String SEP = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        build(root, sb);
        return sb.toString();
    }

    private void build(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append(NIL).append(SEP);
            return;
        }
        sb.append(root.val).append(SEP);
        build(root.left, sb);
        build(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> q = new ArrayDeque<>(Arrays.asList(data.split(SEP)));
        return parse(q);
    }

    private TreeNode parse(Deque<String> q){
        String t = q.removeFirst();
        if(t.equals(NIL)){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(t));
        root.left = parse(q);
        root.right = parse(q);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
```

96. Kth Smallest Element in a BST
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
//TC : O(H+K) can go to O(N) skewed and k = N
// SC : O(H)
class Solution {

    public int kthSmallest(TreeNode root, int k) {
        /*
        Use stack
        - Push in stack until we go to left
        - Pop from stack and decrement k and check
        - Push right
        - repeat
        */
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while(curr != null || !stack.isEmpty()){
            while(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if(--k == 0){
                return curr.val;
            }
            curr = curr.right;
        }

        return -1;
    }

    // private int k;
    // private int ans;
    // public int kthSmallest(TreeNode root, int k) {
    //     this.k = k;
    //     dfs(root);
    //     return ans;
    // }

    // private void dfs(TreeNode node){
    //     if(node == null){
    //         return;
    //     }
    //     dfs(node.left);
    //     if(--k == 0){
    //         this.ans = node.val; 
    //         return;
    //     }
    //     dfs(node.right);
    // }
}

```

97. Delete Node in a BST
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
 //TC : O(H)
 // SC : O(H)
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        /*
         - Node with value k is leaf delete it make value null;
         - node has only one child -> replace it with the children value
         - node has two child -> find successor or predecessor and delete the succ or predecessor node
        */

        if(root == null){
            return null;
        }

        if(root.val > key){
            //go to left
            root.left = deleteNode(root.left, key);
        }
        else if(root.val < key){
            //go to right
            root.right = deleteNode(root.right, key);
        }

        else{
            // found key
            //have zero child or have only right child
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;

            //two childrens - find successor
            TreeNode succ = findSuccessor(root.right);
            root.val = succ.val;
            root.right = deleteNode(root.right, succ.val);
        }

        return root;
        
    }

    private TreeNode findSuccessor(TreeNode curr){
        while(curr.left != null) curr = curr.left;
        return curr;
    }
}
```
98. 
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
 // TC : O(N)
 //SC : O(logN) = O(height)
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[]nums, int l, int r){
        if(l > r){
            return null;
        }
        int m = l + (r-l)/2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = sortedArrayToBST(nums, l, m-1);
        root.right = sortedArrayToBST(nums, m+1, r);
        return root;
    }
}
```

99. Number of Provinces
```
//TC : O(N*N)
//SC : O(N) + O(h)
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for(int r = 0; r < n; r++){
           if(!visited[r]){
                dfs(isConnected, r, visited);
                ++provinces;
            }
        }
        return provinces;
    }

    private void dfs(int[][]isConnected, int r, boolean [] visited){
        visited[r] = true;

        //find the connected components to this
        for(int i = 0; i < isConnected.length; i++){
            if(isConnected[r][i] == 1 && !visited[i]){
                dfs(isConnected, i, visited);
            }
        }
    }
}
```
100. Number of Connected Components in an Undirected Graph
```
//TC : O(N+M) : building adj matrix + O(N+M) : each node visited once and each edge visited twice
//SC : O(N+M) : building adj matrix + O(N) : for visiting + O(N) : for recursion call stack
class Solution {
    public int countComponents(int n, int[][] edges) {
        //DFS traversal
        //SC : O(N+M)
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        //TC : because of below two loops : O(M+N)
        for(int i = 0; i < n; i++){ // O(N)
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < edges.length; i++){ //O(M), where m is number of edges
            int u = edges[i][0];
            int v = edges[i][1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        //SC : O(N)
        boolean[]visited = new boolean[n]; //for vertex
        int components = 0;
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                dfs(adj, i, visited);
                ++components;
            }
        }
        return components;
    }


    private void dfs(ArrayList<ArrayList<Integer>>adj, int i, boolean[] visited){
        visited[i] = true; //marked

        //get the neighbor
        //SC : O(N) : recursion call stack
        //TC : O(N+M) : each node visited once and each edge visited twice
        for(int neighbor : adj.get(i)){
            if(!visited[neighbor]) dfs(adj, neighbor, visited);
        }

    }
}
```

101. Is Graph Bipartite?
```
//TC : O(V+E) : (each node/edge processed once)
//SC : O(V)
class Solution {
    public boolean isBipartite(int[][] graph) {
        /*
        If graph has odd cycle then bipartite
        */
        int n = graph.length;
        int [] color = new int[n];
        Arrays.fill(color, -1);

        for(int i = 0; i < n; i++){
            if(color[i] == -1){
                if(!dfs(graph, i, 0, color)){
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(int[][]graph, int curr, int currColor, int[]color){
        color[curr] = currColor;

        for(int nei : graph[curr]){
            if(color[nei] == -1){
                if(!dfs(graph, nei, 1 - currColor, color)){
                    return false;
                }
            } else if (color[nei] == currColor){
                return false;
            }
        }

        return true;
    }
}
```
Variaty - 2
```
import java.util.* ;
import java.io.*; 
public class Solution {
    public static Boolean isBipartite(int n, int edges[][]) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int [] color = new int[n];
        Arrays.fill(color,-1);

        for(int i = 0; i < n; i++){
            if(color[i] == -1){
                if(!dfs(adj, color, i, 0)){
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean dfs(ArrayList<ArrayList<Integer>> adj, int color[], int curr, int currColor){
        color[curr] = currColor;

        for(int neighbor : adj.get(curr)){
            if(color[neighbor] == -1){
                if(!dfs(adj, color, neighbor, 1 - currColor)){
                    return false;
                }
            }else if (color[neighbor] == currColor){
                return false;
            }
        }

        return true;
        
    }
}
```
102. Graph Valid Tree
```
// TC : O(V+E)
// SC : O(V+E) : due to adjacency list
class Solution {
    public boolean validTree(int n, int[][] edges) {
        /*
        Tree is valid when
        -> it has no cycle
        -> all nodes are reachable from node 0;
        -> edges == n-1
        */

        if(edges.length != n-1){
            return false;
        }

        //build adjacency matrix
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(); // O(V+E)
        for(int i = 0; i < n; i++){ //O(V)
            adj.add(new ArrayList<>());
        }

        for(int [] e : edges){ //O(E)
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        boolean [] visited = new boolean[n];
        if(hasCycle(adj, 0, -1, visited)){
            return false;
        }

        for(boolean v : visited){
            if(!v){
                return false;
            }
        }

        return true;

    }

    private boolean hasCycle(ArrayList<ArrayList<Integer>> adj, int curr, int parent, boolean[]visited){
        visited[curr] = true;
        for(int u : adj.get(curr)){
            if(!visited[u]){
                if(hasCycle(adj, u, curr, visited)){
                    return true;
                }
            }else if(u != parent){
                return true;
            }
        }
        return false;
    }
}
```

103. Course Schedule
```
//TC : O(V+E)
//SC : O(V+E)
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //If we able to do topological sort then we are able to finish the course

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }

        for(int []edge : prerequisites){
            adj.get(edge[1]).add(edge[0]); // 1 --> 0
        }

        //find indegree
        int []indegree = new int[numCourses];
        for(int i = 0; i < adj.size(); i++){
            for(int j = 0; j < adj.get(i).size(); j++){
                ++indegree[adj.get(i).get(j)];
            }
        }

        //Enque vertex with indegree 0
        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }

        int count = 0;
        while(!q.isEmpty()){
            int curr = q.poll();
            ++count;
            for(int u : adj.get(curr)){
                --indegree[u];
                if(indegree[u] == 0){
                    q.offer(u);
                }
            }
        }

        return count == numCourses;
        
    }
}
```

104. Course Schedule II
```
//TC : O(V+E)
//SC : O(V+E)
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int[] res = new int[numCourses];
        int idx = 0;

        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }

        for(int [] e : prerequisites){
            adj.get(e[1]).add(e[0]);
        }

        int[]indegree = new int[numCourses];
        for(int i = 0; i < adj.size(); i++){
            for(int j = 0; j < adj.get(i).size(); j++){
                ++indegree[adj.get(i).get(j)];
            }
        }

        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }
        
        while(!q.isEmpty()){
            int curr = q.poll();
            res[idx++] = curr;

            for(int u : adj.get(curr)){
                --indegree[u];
                if(indegree[u] == 0){
                    q.offer(u);
                }
            }
        }


        return idx == numCourses ? res : new int[]{};
    }
}
```


105. Alien Dictionary
```
//TC : O(C) : Total characters across all words
//SC : O(U+E) : NUmber of unique characters + number of unique edges
class Solution {
    public String foreignDictionary(String[] words) {
        // TOPOLOGICAL SORT
        Map<Character, Set<Character>>graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        //1. Build vertices
        for(String w : words){
            for(char ch : w.toCharArray()){
                graph.putIfAbsent(ch, new HashSet<>());
                indegree.putIfAbsent(ch, 0);
            }
        }

        //2. Build edges
        for(int i = 0; i < words.length - 1; i++){
            String w1 = words[i];
            String w2 = words[i+1];

            if(w1.length() > w2.length() && w1.startsWith(w2)){
                return "";//invalid
            }
            int len = Math.min(w1.length(), w2.length());
            for(int j = 0; j < len; j++){
                char a = w1.charAt(j);
                char b = w2.charAt(j);
                if(a != b){
                    // a->b
                    if(graph.get(a).add(b)){
                        indegree.put(b, indegree.get(b) + 1);
                    }
                    break; // only first differing char matters
                }
            }
        }

        //3. Topo sort(Kahn)
        Deque<Character> q = new ArrayDeque<>();
        for(char ch : indegree.keySet()){
            if(indegree.get(ch) == 0){
                q.offer(ch);
            }
        }

        StringBuilder result = new StringBuilder();
        while(!q.isEmpty()){
            char curr = q.poll();
            result.append(curr);

            for(char nei : graph.get(curr)){
                indegree.put(nei, indegree.get(nei) - 1);
                if(indegree.get(nei) == 0){
                    q.offer(nei);
                }
            }
        }

        // if cycle exist not all the characters processed
        return result.length() == indegree.size() ? result.toString() : "";
      
    }
}
```

106. Minimum Height Trees
```
/*
Easy intuition: “Best root is the middle”
If you stand at the middle of the tree, the farthest places are closer.
If you stand at an end/leaf, the farthest place is very far.

So the best root(s) must be the center of the tree.

And a tree can have:

1 center (like odd-length chain: 0-1-2-3-4 center is 2)
or 2 centers (even-length chain: 0-1-2-3 centers are 1 and 2)
It can’t have 3 centers.
How to find the center without “guessing”?
Here’s the onion trick:

Step A: Find all leaves
Leaves are nodes with degree 1 (only one neighbor).

Step B: Remove all leaves together
If leaves can’t be the center (they’re at the edge), delete them.
After removing them, some nodes become new leaves.

Step C: Repeat
Keep removing “outer layers”.

Eventually, you’ll be left with:

1 node → that’s the center
or 2 nodes → those are the centers
Those remaining nodes are exactly the answers (minimum height roots).
*/

//TC : O(N)
//SC : O(N)
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1){
            return List.of(0);
        }

        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){ //O(N)
            adj.add(new ArrayList<>());
        }

        int deg[] = new int[n];
        for(int e[] : edges){ //O(E) = O(N-1) as we have edges in tree = N-1
            int u = e[0];
            int v = e[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
            ++deg[u];
            ++deg[v];
        }
        
        Deque<Integer> q = new ArrayDeque<>(); //SC : O(N)
        //get the nodes with degree  = 1;
        for(int i = 0; i < n; i++){ //O(N)
            if(deg[i] == 1){
                q.offer(i);
            }
        }

        int remaining = n;
        while(remaining > 2){
            int size = q.size();
            remaining -= size;
            for(int i = 0; i < size; i++){
                int leaf = q.poll();

                for(int neighbor : adj.get(leaf)){
                    if(--deg[neighbor] == 1){ //new leaf
                        q.offer(neighbor);
                    }
                }
            }
        }

        return new ArrayList<>(q);


    }
}
```

107. Parallel Courses
```
You have n courses labeled 1..n and relations relations[i] = [prev, next] meaning prev must be taken before next. In one semester you can take any number of courses as long as prerequisites are satisfied. Return the minimum number of semesters to finish all courses, or -1 if impossible.

Interview-thinking prompts (no code yet)

What does “impossible” mean in graph terms?
If it is possible, what graph concept equals “minimum semesters”: shortest path or longest path?
If multiple courses can be taken in the same semester, how would you simulate semester-by-semester using indegrees?

```

```

//TC : O(N+m), where m is length of relation
//SC : O(N+m), where m is length of relation
public class Solution {
    /**
     * @param n: the number of courses
     * @param relations: the relationship between all courses
     * @return: ehe minimum number of semesters required to complete all courses
     */
    public int minimumSemesters(int n, int[][] relations) {
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i <= n; i++){ // <= is important as indexing is from 1..n
            adj.add(new ArrayList<>());
        }
        
        int [] indegree = new int[n+1]; // n+1 as indexing is from 1..n
        for(int relation[] : relations){
            int prev = relation[0];
            int next = relation[1];
            adj.get(prev).add(next);
            ++indegree[next];
        }

      
        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= n; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }
        int semester = 0;
        int taken = 0;
        while(!q.isEmpty()){
            int size = q.size();
            ++semester;
            for(int i = 0; i < size; i++){
                int node = q.poll();
                ++taken;
                for(int nei : adj.get(node)){
                    if(--indegree[nei] == 0){
                        q.offer(nei);
                    }
                }
            }
        }

        return taken == n ? semester : -1;
    }
}
```

108. Number of Islands (alt approach)


109. Number of Connected Components in an Undirected Graph


110. Redundant Connection

Approach - 1 :DFS
```
//TC : O((V+E) * E); // *E for each edge we run hasPath = E~V = O(V*V) = O(N*N)
//SC : O(V+E)
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i <= n; i++){ // =n since 1...n  // TC : O(V), SC : (V)
            adj.add(new ArrayList<>());
        }

        for(int e[] : edges){ // TC : O(E) for each edge we run hasPath
            int u = e[0];
            int v = e[1];

            boolean[]visited = new boolean[n+1]; // n+1 since 1...n , SC : (V)

            if(!adj.get(u).isEmpty() && !adj.get(v).isEmpty() && hasPath(adj, u, v, visited)){
                return e;
            }
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        return new int[0];
        
    }

    private boolean hasPath(List<List<Integer>>adj, int u, int v, boolean[]visited){
        if(u == v){
            return true;
        }
        visited[u] = true;
        for(int nei : adj.get(u)){ // SC : O(E)
            if(!visited[nei]){
                if(hasPath(adj, nei, v, visited)){
                    return true;
                }
            }
        }

        return false;
    }
}

```

Approach - 2 BFS
```
//TC : O((V+E) * E); // *E for each edge we run hasPath = E~V = O(V*V) = O(N*N)
//SC : O(V+E)
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i <= n; i++){ // =n since 1...n  // TC : O(V), SC : (V)
            adj.add(new ArrayList<>());
        }

        for(int e[] : edges){ // TC : O(E) for each edge we run hasPath
            int u = e[0];
            int v = e[1];

            boolean[]visited = new boolean[n+1]; // n+1 since 1...n , SC : (V)

            if(!adj.get(u).isEmpty() && !adj.get(v).isEmpty() && hasPath(adj, u, v, visited)){
                return e;
            }
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        return new int[0];
        
    }

    //DFS
    // private boolean hasPath(List<List<Integer>>adj, int u, int v, boolean[]visited){
    //     if(u == v){
    //         return true;
    //     }
    //     visited[u] = true;
    //     for(int nei : adj.get(u)){ // SC : O(E)
    //         if(!visited[nei]){
    //             if(hasPath(adj, nei, v, visited)){
    //                 return true;
    //             }
    //         }
    //     }

    //     return false;
    // }

    //BFS
    private boolean hasPath(List<List<Integer>>adj, int u, int v, boolean[]visited){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(u);

        while(!q.isEmpty()){
            int curr = q.poll();
            visited[curr] = true;
            if(curr == v){
                return true;
            }
            for(int nei : adj.get(curr)){
                if(!visited[nei]){
                    q.offer(nei);
                }
            }
        }

        return false;
        
    }
}
```

Approach - 3 Disjoint Union Sets
```
class Solution {
    // public int[] findRedundantConnection(int[][] edges) {
        
    //     int n = edges.length;
    //     List<List<Integer>> adj = new ArrayList<>();

    //     for(int i = 0; i <= n; i++){
    //         adj.add(new ArrayList<>());
    //     }


    //     for(int e[] : edges){
    //         int u = e[0];
    //         int v = e[1];
    //         boolean [] visited = new boolean[n+1];
    //         if(!adj.get(u).isEmpty() && !adj.get(v).isEmpty() && hasPath(adj, u, v, visited)){
    //             return e;
    //         }

    //         adj.get(u).add(v);
    //         adj.get(v).add(u);
    //     }
    //     return new int[0];   
    // }
    // //Using DFS
    // // private boolean hasPath(List<List<Integer>> adj, int u, int v, boolean[] visited){
    // //     if(u == v){
    // //         return true;
    // //     }
    // //     visited[u] = true;

    // //     for(int nei : adj.get(u)){
    // //         if(!visited[nei]){
    // //             if(hasPath(adj, nei, v, visited)){
    // //                 return true;
    // //             }
    // //         }
    // //     }

    // //     return false;
    // // }

    // //Using BFS
    // private boolean hasPath(List<List<Integer>> adj, int u, int v, boolean[] visited){
    //     Queue<Integer> q = new ArrayDeque<>();
    //     q.offer(u);

    //     while(!q.isEmpty()){
    //         int curr = q.poll();
    //         if(curr == v){
    //             return true;
    //         }
    //         visited[curr] = true;
    //         for(int nei : adj.get(curr)){
    //             if(!visited[nei]){
    //                 q.offer(nei);
    //             }
    //         }
    //     }

    //     return false;
    // }

    //Union and find

    class DSU{
        int parent[];
        int rank[];

        public DSU(int n){
            parent = new int[n+1];
            rank = new int[n+1];

            for(int i = 0; i <= n; i++){
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x){
            if(x != parent[x]){
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public void union(int x, int y){
            int x_parent = find(x);
            int y_parent = find(y);
            if(x_parent == y_parent){
                return;
            }

            if(rank[x_parent] > rank[y_parent]){
                parent[y_parent] = x_parent;
            }else if(rank[x_parent] < rank[y_parent]){
                parent[x_parent] = y_parent;
            }else {
                parent[y_parent] = x_parent;
                ++rank[x_parent];
            }
        }
    }
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        DSU dsu = new DSU(n);
        for(int [] e : edges){
            int u = e[0];
            int v = e[1];
            if(dsu.find(u) == dsu.find(v)){
                return e;
            }
            dsu.union(u, v);
        }

        return new int[0];
        
    }

}
```

111 Accounts Merge
```



112. Satisfiability of Equality Equations
```
class Solution {
    //Union find
    int parent[];
    int rank[];

    public int find(int x){
        if(x != parent[x]){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    

    public void union(int x, int y){
        int p_x = find(x);
        int p_y = find(y);

        if(p_x != p_y){
            if(rank[p_x] > rank[p_y]){
                parent[p_y] = p_x;
            }else if (rank[p_x] < rank[p_y]){
                parent[p_x] = p_y;
            }else {
                parent[p_y] = p_x;
                ++rank[p_x];
            }
        }
    }

    public boolean equationsPossible(String[] equations) {

        parent = new int[26];
        rank = new int[26];

        for(int i = 0; i < 26; i++){
            parent[i] = i;
            rank[i] = 1;
        }

        for(String s : equations){
            if(s.charAt(1) == '='){
                union(s.charAt(0) - 'a', s.charAt(3) - 'a');
            }
        }

        for(String s : equations){
            if(s.charAt(1) == '!'){
                if(find(s.charAt(0) - 'a') == find(s.charAt(3) - 'a')){
                    return false;
                }
            }
        }

        return true;
        
    }
}
```

113. Min Cost to Connect All Points
```
class Solution {
    public int minCostConnectPoints(int[][] points) {

        int V = points.length;

        List<List<int[]>> adj = new ArrayList<>();

        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < V; i++){
            for(int j = i+1; j < V; j++){
                int distance = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                adj.get(i).add(new int[]{j, distance});
                adj.get(j).add(new int[]{i, distance});
            }
        }
        

        return prims(adj, V);
    }

    private int prims(List<List<int[]>>adj, int V){
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0,0}); //wt, node
        boolean [] isMST = new boolean[V];
        int sum = 0;
        while(!pq.isEmpty()){
            int curr[] = pq.poll();
            int wt = curr[0];
            int node = curr[1];


            if(isMST[node]) continue;
            isMST[node] = true;
            sum += wt;

            for(int [] e : adj.get(node)){
                int nei = e[0];
                int neiWt = e[1];

                if(!isMST[nei]) pq.offer(new int[]{neiWt, nei});
            }
        }

        return sum;
    }
}
```

114. Number of Provinces
```
//TC : O(N*N)
//SC : O(N) + O(h)
class Solution {
    // public int findCircleNum(int[][] isConnected) {
    //     int n = isConnected.length;
    //     boolean[] visited = new boolean[n];
    //     int provinces = 0;

    //     for(int r = 0; r < n; r++){
    //        if(!visited[r]){
    //             dfs(isConnected, r, visited);
    //             ++provinces;
    //         }
    //     }
    //     return provinces;
    // }

    // private void dfs(int[][]isConnected, int r, boolean [] visited){
    //     visited[r] = true;

    //     //find the connected components to this
    //     for(int i = 0; i < isConnected.length; i++){
    //         if(isConnected[r][i] == 1 && !visited[i]){
    //             dfs(isConnected, i, visited);
    //         }
    //     }
    // }


    // by using 
    //TC : O(N*N), SC : O(N)

    class DSU{
        int parent[];
        int rank[];

        public DSU(int n ){
            parent = new int[n]; // SC : O(N)
            rank = new int[n];

            for(int i = 0; i < n; i++){ // TC : O(N)
                parent[i] = i;
                rank[i] = 1;
            }
        }

        int find(int x){
            if(x != parent[x]){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int x , int y){
            int p_x = find(x);
            int p_y = find(y);

            if(p_x == p_y){
                return false;
            }

            if(rank[p_x] > rank[p_y]){
                parent[p_y] = p_x;
            }else if (rank[p_x] < rank[p_y]){
                parent[p_x] = p_y;
            }else {
                parent[p_y] = p_x;
                ++rank[p_x];
            }

            return true;
        }

    }
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int count = n;
        DSU dsu = new DSU(n);

        for(int i = 0; i < n; i++){ // TC : O(N*N)
            for(int j = i+1; j < n; j++){
                if(isConnected[i][j] == 1){
                    if(dsu.union(i, j)){
                        --count;
                    }
                }
            }
        }

        return count;
    }
}
```

115. Daily Temperatures
```
//TC : O(N)
//SC : O(N)
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // Monotonic Stack -> decreasing R to L;
        int n = temperatures.length;
        int res [] = new int[n];
        Deque<Integer> st = new ArrayDeque<>(); // SC : O(N)
        for(int i = n-1; i >= 0; i--){ // TC : O(N), every element is pushing and popping once in stack
            while(!st.isEmpty() && temperatures[st.peek()] <= temperatures[i]){
                st.pop();
            }
            res[i] = !st.isEmpty() ? st.peek() - i : 0;
            st.push(i);
        }

        return res;
        
    }
}
```


116. Largest Rectangle in Histogram
```
//TC : O(N)
//SC : O(N)
class Solution {
    public int largestRectangleArea(int[] heights) {
        // Monotonic stack
        int n = heights.length;
        int res = 0;
        int l[] = new int[n];
        int r[] = new int[n];
        Deque<Integer> st = new ArrayDeque<>();


        //for left smaller
        for(int i = 0; i < n; i++){
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]) st.pop();
            l[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        while(!st.isEmpty()) st.pop();

        //for right smaller
        for(int i = n - 1; i >= 0; i--){
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]) st.pop();
            r[i]  = st.isEmpty() ? n : st.peek(); // n for elements that has no right boundary
            st.push(i);
        }

        for(int i = 0; i < n; i++){
            int width = r[i] - l[i] - 1;
            int area = heights[i] * width;
            res = Math.max(area, res);
        }

        return res;
        
    }
}
```


117. Next Greater Element I

```
// TC : O(N1 + N2)
// SC : O(N2)
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //Monotonic stack -> decreasing from R to L

        int n1 = nums1.length;
        int n2 = nums2.length;
        Map<Integer, Integer> map = new HashMap<>(n2); //nums2 contains unique elements.
        Deque<Integer> st = new ArrayDeque<>();

        for(int i = n2 - 1; i >= 0; i--){
            while(!st.isEmpty() && st.peek() <= nums2[i]){
                st.pop();
            }

            map.put(nums2[i], st.isEmpty() ? -1 : st.peek());
            st.push(nums2[i]);
        }

        int res [] = new int[n1];
        for(int i = 0; i < n1; i++){
            res[i] = map.get(nums1[i]);
        }

        return res;

        
    }
}
```

118. Next Greater Element II
```
// TC : O(N)
// SC : O(N)
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        // For circular array will pretend the array repeated twice
        //1, 2, 4, 1 -> 1, 2, 4, 1  int x = nums[i%n];
        // Monotonic decreasing from R to L

        int n = nums.length;
        Deque<Integer> st = new ArrayDeque<>();
        int res[] = new int[n];

        for(int i = 2*n - 1; i >= 0; i--){
            int x = nums[i % n];
            while(!st.isEmpty() && st.peek() <= x){
                st.pop();
            }
            if(i < n){
                res[i] = st.isEmpty() ? -1 : st.peek();
            }
            
            st.push(x);
        }

        return res;
        
    }
}
```