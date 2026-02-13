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