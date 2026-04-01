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
```
//TC : NlogN (bcoz of sorting) SC : If considered Arrays.sort -> Uses Dual Pivot Quick Sort -> O(logN)

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        //Sort + Two Pointer
        int n = nums.length;
        Arrays.sort(nums); //nLogN
        List<List<Integer>> res = new ArrayList<>();

        for(int i = 0; i < n; i++){ //N

            //for skip duplicates
            if(i > 0 && nums[i] == nums[i-1]){ //that means we have already considered same pair with i-1 nums value
                continue;
            }

            //fixing i as first member and applying two pointer for rest
            int left = i+1;
            int right = n - 1;

            while(left < right){
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