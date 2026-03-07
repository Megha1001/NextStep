**Problem #1: Maximum Twin Sum of Linked List**

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
    public int pairSumBrute(ListNode head) {
        //Brute force
        /*
        Convert into list and then find maximum twin sum
        */

        List<Integer> list = new ArrayList<>();

        ListNode curr = head;
        while(curr != null){
            list.add(curr.val);
            curr = curr.next;
        }

        int res = 0; //number are +ve so minimum sum can be 0
        int n = list.size();

        for(int i = 0; i < n/2; i++){
            res = Math.max(res, list.get(i) + list.get(n - 1 - i));
        }

        return res;
        
    }


    //Approach -2 
    /*
    1. Find middle of LL -> using slow and fast pointers
    2. reverse second half of the LL
    3. then find max
    */
    public int pairSum(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;


        //Find middle - slow would be at mid
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        //reverse
        ListNode prev = null; //would be head
        ListNode curr = slow;

        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        ListNode first = head;
        ListNode second = prev;
        int res = 0;

        while(second != null){
            res = Math.max(res, first.val + second.val);
            first = first.next;
            second = second.next;
        }

        return res;


    }


}
```

2. Sorting Points by Weight with Minimum Operations

```
public class Solution {

    public int minOperations(int[]weight, int []dist){
        int n = weight.length;
        Integer [] idx = new Integer[n];

        for(int i = 0; i < n; i++){
            idx[i] = i;
        }

        Arrays.sort(idx, (a, b) -> weight[a] - weight[b]);

        int []pos = new int[n];
        for(int i = 0; i < n; i++){
            pos[i] = i;
        }

        int operations = 0;
        for(int i = 1; i < n; i++){
            int curr = idx[i];
            int prev = idx[i-1];

            while(pos[curr] <= pos[prev]){
                pos[curr] += dist[curr];
                ++operations;
            }
        }

        return operations;
    }
    
}

```