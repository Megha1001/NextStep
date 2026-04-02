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