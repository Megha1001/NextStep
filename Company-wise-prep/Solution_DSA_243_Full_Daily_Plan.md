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