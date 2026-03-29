
### Day - 1

1. Nested List Weight Sum

```
class depthSumCalc {
    // Add Your Code Here
  public int depthSum(List<NestedInteger>nestedList){
    return calDepth(nestedList, 1);
  }
  
  private int calDepth(List<NestedInteger>nestedList, int currentDepth){
    int totalSum = 0;
    for(NestedInteger element : nestedList){
      if(element.isInteger()){
        totalSum += element.getInteger() * currentDepth;
      }else {
        totalSum += calDepth(element.getList(), currentDepth + 1);
      }
    }
    
    return totalSum;
  }
}
```

2. Nested List Weight Sum II
```
public class Solution {
    public int weightedDepthSum(List<NestedInteger> nestedList) {
        // find the height and use nested list weight sum i
        int height = findHeight(nestedList);
        return findDepth(nestedList, height);
    }
    
    private int findHeight(List<NestedInteger> nestedList){
        int height = 1;
        for(NestedInteger ni : nestedList){
            if(!ni.isInteger()){
                int tmp = findHeight(ni.getList());
                height = Math.max(tmp+1, height);
            }
        }
        
        return height;
    }
    
    private int findDepth(List<NestedInteger> nestedList, int currentDepth){
        int sum = 0;
        
        for(NestedInteger ni : nestedList){
            if(ni.isInteger()){
                sum += ni.getInteger() * currentDepth;
            }else{
                sum += findDepth(ni.getList(), currentDepth - 1);
            }
        }
        
        return sum;
    }
}
```

Approach - 2: Single Pass
```
public int weightedDepthSum(List<NestedInteger> nestedList) {
        int levelSum = 0;
        int weightedSum = 0;

        Queue<NestedInteger> q = new LinkedList<>(nestedList);

        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0; i < size; i++){
                NestedInteger ni = q.poll();
                if(ni.isInteger()){
                    levelSum += ni.getInteger();
                }else{
                    for(NestedInteger child : ni.getList()){
                        q.offer(child);
                    }
                }
            }
            weightedSum +=levelSum;
        }

        return weightedSum;
    }
```


3. Find the Celebrity

```
public class Solution {
	public static int findCelebrity(int n) {
        //Analyze in Pair and use stack
		Stack<Integer> s = new Stack<>();
		for(int i = 0; i < n; i++){
			s.push(i);
		}

		while(s.size() > 1){
			int i = s.pop();
			int j = s.pop();

			if(Runner.knows(i, j)){
				s.push(j);
			}else{
				s.push(i);
			}
		}

		int celeb = s.pop();
		for(int i = 0; i < n; i++){
			if(i!=celeb && (!Runner.knows(i, celeb) || Runner.knows(celeb, i))){
				return -1;
			}
		}

		return celeb;
    }
}
```

4. All One's Data Structure -> Difficult

```
class AllOne {
    //HashMap with DLL

    //DLL node to keep count and a set of strings with that count

    private class Node{
        int count;
        LinkedHashSet<String> keys;
        Node next;
        Node prev;

        Node (int c){
            this.count = c;
            this.keys = new LinkedHashSet<>();
        }
    }

    //Map to store key->node's addr
    private Map<String, Node> keyCountMap;

    private Node head;
    private Node tail;

    public AllOne() {
        keyCountMap = new HashMap<>();
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        
    }

    private void addNodeAfter(Node prevNode, int c){
        Node newNode = new Node(c);
        newNode.next = prevNode.next;
        newNode.prev = prevNode;
        prevNode.next.prev = newNode;
        prevNode.next = newNode;
    }

    private void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    public void inc(String key) {
        if(!keyCountMap.containsKey(key)){
            if(head.next == tail || head.next.count != 1){
                addNodeAfter(head, 1);
            }

            head.next.keys.add(key);
            keyCountMap.put(key, head.next);
        }else {
            Node currNode = keyCountMap.get(key);
            int currCount = currNode.count;

            if(currNode.next == tail || currNode.next.count != currCount+1){
                addNodeAfter(currNode, currCount+1);
            }

            currNode.next.keys.add(key);
            keyCountMap.put(key, currNode.next);
            currNode.keys.remove(key);
            if(currNode.keys.isEmpty()){
                removeNode(currNode);
            }
        }
        
    }
    
    public void dec(String key) {
        Node currNode = keyCountMap.get(key);
        int currCount = currNode.count;

        currNode.keys.remove(key);

        if(currCount == 1){
            keyCountMap.remove(key);
        }else{
            //Move the key to the prev count node
            if(currNode.prev == head || currNode.prev.count != currCount - 1){
                addNodeAfter(currNode.prev, currCount - 1);
            }

            currNode.prev.keys.add(key);
            keyCountMap.put(key, currNode.prev);
        }

        if(currNode.keys.isEmpty()){
            removeNode(currNode);
        }
        
    }
    
    public String getMaxKey() {
        return tail.prev == head ? "" : tail.prev.keys.iterator().next();
    }
    
    public String getMinKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
 ```

 5. MaxStack
 ```
 class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> maxStack;
    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    /*
     * @param number: An integer
     * @return: nothing
     */    
    public void push(int x) {
        int max = maxStack.isEmpty() ? x : Math.max(x, maxStack.peek());
        stack.push(x);
        maxStack.push(max);
    }

    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    /*
     * @return: An integer
     */    
    public int top() {
        return stack.peek();
    }

    /*
     * @return: An integer
     */    
    public int peekMax() {
        return maxStack.peek();
    }

    /*
     * @return: An integer
     */    
    public int popMax() {
        int max = maxStack.peek();
        Stack<Integer> buffer = new Stack<>();
        while(top() != max){
            buffer.push(pop());
        }

        pop();
        while(!buffer.isEmpty()){
            push(buffer.pop());
        }


        return max;
    }
}
```

6. Max Consecutive Ones III
```
class Solution {
    public int longestOnes(int[] nums, int k) {
        //slidling window
        int left = 0;
        int maxLength = 0;
        int zeroCount = 0;

        for(int right = 0; right < nums.length; right++){
            if(nums[right]==0){
                ++zeroCount;
            }

            while(zeroCount > k){
                if(nums[left]==0){
                    --zeroCount;
                }
                ++left;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
        
    }
}
```

7. Search in sorted rotated arrays :

```
class Solution {
    public int search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private int binarySearch(int[]nums, int l , int h, int x){
        while(l <= h){
            int m = l + (h-l)/2;
            
            if(nums[m] == x){
                return m;
            }

            else if(nums[m] <= nums[h]){
                //right
                if(x > nums[m] && x <= nums[h]){
                    l = m+1;
                }else{
                    h = m-1;
                }
            }else{
                if(x >= nums[l] && x < nums[m]){
                    h = m-1;
                }else{
                    l = m+1;
                }
            }
        }

        return -1;
    }
}
```

8. Find Leaves of Binary Tree 
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


public class Solution {
    /*
     * @param root: the root of binary tree
     * @return: collect and remove all leaves
     */
    private Map<Integer, List<Integer>> map = new HashMap<>();
    public List<List<Integer>> findLeaves(TreeNode root) {
        map.clear();
        collectNodeHeight(root);
        List<List<Integer>> result = new ArrayList<>();
        for(List<Integer> nodes : map.values()){
            result.add(nodes);
        }

        return result;
    }

    private int collectNodeHeight(TreeNode root){
        if(root == null){
            return 0;
        }

        int height = 1 + Math.max(collectNodeHeight(root.left)
        , collectNodeHeight(root.right));
        map.computeIfAbsent(height, k -> new ArrayList<>()).add(root.val);

        return height;
    } 
}

```

9. Shortest Word Distance
```
public class Solution {
    /**
     * @param words: a list of words
     * @param word1: a string
     * @param word2: a string
     * @return: the shortest distance between word1 and word2 in the list
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        int index1 = -1, index2 = -1;
        int minDist = Integer.MAX_VALUE;

        for(int i = 0; i < words.length; i++){
            if(words[i].equals(word1)){
                index1 = i;
            }
            else if(words[i].equals(word2)){
                index2 = i;
            }
            
            if(index1 != -1 && index2 != -1){
                minDist = Math.min(minDist, Math.abs(index1- index2));
            }
        }

        return minDist;
    }
}
```

10. Maximum Product Subarray
```
class Solution {
    public int maxProduct(int[] nums) {
        //kadanes
        int res = nums[0];
        int curr_max = 1;
        int curr_min = 1;

        for(int num : nums){
            int tmp = curr_max * num;
            curr_max = Math.max(Math.max(tmp, num * curr_min), num);
            curr_min = Math.min(Math.min(tmp, num * curr_min), num);
            res = Math.max(curr_max, res);
        }

        return res;
        
    }
}
```

11. Maximum Subarray
```
class Solution {
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int curr_max = nums[0];

        for(int i = 1; i < nums.length; i++){
            curr_max = Math.max(curr_max + nums[i], nums[i]);
            res = Math.max(res, curr_max);
        }

        return res;
        
    }
}
```

12. Valid Parentheses
```
class Solution {
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for(char c : s.toCharArray()){
            switch(c){
                case '{' :
                case '[' :
                case '(' :
                    stack.push(c);
                    break;

                case '}' :
                case ']' :
                case ')' :
                    if(!stack.isEmpty() && (
                        (stack.peek()=='{' && c=='}')
                        ||
                        (stack.peek()=='[' && c==']')
                        ||
                        (stack.peek()=='(' && c==')')
                    )){
                        stack.pop();
                        break;
                    }else{
                        return false;
                    }
            }
        }
        if(!stack.isEmpty()){
            return false;
        }

        return true;
        
    }
}
```

13. Number of Islands
```
class Solution {
    private int directions[][] = {{1,0},{-1,0},{0,1},{0,-1}};
    private int ROWS, COLS;
    public int numIslands(char[][] grid) {

        //Way -1 BFS

        int islands = 0;
        ROWS = grid.length;
        COLS = grid[0].length;

        if(ROWS == 0){
            return 0;
        }

        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                if(grid[i][j] == '1'){
                    // bfs(grid, i, j);
                    dfs(grid, i, j);
                    ++islands;
                }
            }
        }

        return islands;
    }

    private void dfs(char[][]grid, int r, int c){
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == '0'){
            return;
        }

        grid[r][c] = '0';
        for(int dir[] : directions){
            dfs(grid, r + dir[0], c + dir[1]);
        }
    }

    private void bfs(char[][]grid, int r, int c){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        grid[r][c] = '0'; //visited

        while(!q.isEmpty()){
            int[] entry = q.poll();
            int row = entry[0];
            int col = entry[1];

            for(int[] dir : directions){
                int nr = row + dir[0];
                int nc = col + dir[1];

                if(nr >= 0 && nr < ROWS 
                && nc >= 0 && nc < COLS
                && grid[nr][nc] == '1'){
                    q.offer(new int[]{nr, nc});
                    grid[nr][nc] = '0';
                }
            }
        }
    }
}
```

14. Longest Palindromic Subsequence
```
class Solution {
    public String longestPalindrome(String s) {
        int resLength = 0;
        String res = "";
        int n = s.length();

        for(int i = 0; i < n; i++){
            
            //odd
            int l = i;
            int r = i;
            while(l >=0 && r < n && s.charAt(l) == s.charAt(r)){
                if(r-l+1 > resLength){
                    resLength = r-l+1;
                    res = s.substring(l, r+1);
                }
                --l;
                ++r;
            }

            //even
            l = i; r = i+1;
            while(l>=0 && r < n && s.charAt(l)==s.charAt(r)){
                if(r-l+1 > resLength){
                    resLength = r-l+1;
                    res = s.substring(l, r+1);
                }
                --l;
                ++r;
            }
        }

        return res;
        
    }
}
```

15. Shortest Word Distance II

```
import java.util.*;

class WordDistance {
	private Map<String, List<Integer>> map;

    // Constructor to initialize the data structure with the given word array.
    public WordDistance(List<String> wordsDict) {
       map = new HashMap<>();
        for(int i = 0; i < wordsDict.size(); i++){
       		map.computeIfAbsent(wordsDict.get(i), k-> new ArrayList<>()).add(i);
       }
      
    }

    // Calculates the shortest distance between two given words in the dictionary.
    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        //two pointer
        int i = 0, j = 0;
        int minDist = Integer.MAX_VALUE;
      	
      	while(i < list1.size() && j < list2.size()){
        	int idx1 = list1.get(i);
            int idx2 = list2.get(j);
          	
          	minDist = Math.min(minDist, Math.abs(idx1 - idx2));
          
            if(idx1 < idx2){
            	++i;
            }else{
            	++j;
            }
        }
      
        return minDist;
    }
}

// Driver code to test the WordDistance class
public class Main {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("hello", "geek", "gfg", "coding", "geek");
        WordDistance wordDistance = new WordDistance(words);

        System.out.println("Shortest distance between 'coding' and 'hello': "
                + wordDistance.shortest("coding", "hello"));
        System.out.println("Shortest distance between 'geek' and 'coding': "
                + wordDistance.shortest("geek", "coding"));
    }
}
```
16. Validate Binary Search Tree
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
    public boolean isValidBST(TreeNode root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(TreeNode root, int min, int max){
        if(root != null){
            if(root.val <= min || root.val >= max){
                return false;
            }
            return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
        }
        return true;
    }
}
```

17. Lowest Common Ancestor in Binary Search Tree
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

        if(p.val == root.val || q.val == root.val){
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

18. Symmetric Tree
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

19. Binary Tree Level Order Traversal
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
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while(!q.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode node = q.poll();
                list.add(node.val);
                if(node.left != null){
                    q.offer(node.left);
                }

                if(node.right != null){
                    q.offer(node.right);
                }
            }
            res.add(list);
        }
        return res;
        
    }
}

```

20. Maximum Depth of Binary Tree
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
    public int maxDepth(TreeNode root) {

        if(root == null){
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        
    }
}

```

21. LRU Cache

Brute Force

```

//Brute force
class LRUCache {
    private List<int[]> cache;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new ArrayList<>();
    }
    
    public int get(int key) {
        for(int i = 0; i < cache.size(); i++){
            if(cache.get(i)[0] == key){
                int temp[] = cache.get(i);
                cache.remove(i);
                cache.add(temp);
                return temp[1];
            }
        }

        return -1;
    }
    
    public void put(int key, int value) {
        for(int i = 0 ; i < cache.size(); i++){
            if(cache.get(i)[0] == key){
                cache.remove(i);
                cache.add(new int[]{key, value});
                return;
            }
        }

        if(cache.size() == capacity){
            cache.remove(0);
        }

        cache.add(new int[]{key, value});
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
 ```

 22. LRU
 ```

//Brute force
// class LRUCache {
//     private List<int[]> cache;
//     private int capacity;

//     public LRUCache(int capacity) {
//         this.capacity = capacity;
//         this.cache = new ArrayList<>();
//     }
    
//     public int get(int key) {
//         for(int i = 0; i < cache.size(); i++){
//             if(cache.get(i)[0] == key){
//                 int temp[] = cache.get(i);
//                 cache.remove(i);
//                 cache.add(temp);
//                 return temp[1];
//             }
//         }

//         return -1;
//     }
    
//     public void put(int key, int value) {
//         for(int i = 0 ; i < cache.size(); i++){
//             if(cache.get(i)[0] == key){
//                 cache.remove(i);
//                 cache.add(new int[]{key, value});
//                 return;
//             }
//         }

//         if(cache.size() == capacity){
//             cache.remove(0);
//         }

//         cache.add(new int[]{key, value});
//     }
// }

class LRUCache {

    private class Node{
        int key, value;
        Node prev, next;

        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    Map<Integer, Node> cache;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        head = new Node(0,0);
        tail = new Node(0,0);
        
        head.next = tail;
        tail.prev = head;
    }

    private void remove(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void insertAtFront(Node node){
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    private void makeMostRecentlyUsed(Node node){
        remove(node);
        insertAtFront(node);
    }
    
    public int get(int key) {
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            makeMostRecentlyUsed(node);
            return node.value;

        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            node.value = value;
            makeMostRecentlyUsed(node);
        } else{
            Node node = new Node(key, value);
            cache.put(key, node);
            insertAtFront(node);
            --capacity;
        }

        if(capacity < 0){
            Node lru = tail.prev;
            remove(lru);
            cache.remove(lru.key);
            ++capacity;
        }

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
 ```

 23. Insert Delete Get Random O(1)

 ```
 class RandomizedSet {

    private List<Integer> list;
    private Map<Integer, Integer> map; //val, index;
    private Random random;

    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }
    
    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }

        map.put(val, list.size());
        list.add(val);
        return true;
        
    }
    
    //tricky
    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }

        int index = map.get(val); //return index;
        int lastElement = list.get(list.size() - 1);

        //override present element -> a kind of swapping
        list.set(index, lastElement);
        map.put(lastElement, index);
        map.remove(val);
        list.remove(list.size() - 1);

        return true;
    }
    
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
 ```

 24. Insert Delete GetRandom O(1) — Duplicates

 ```
 class RandomizedCollection {
    /*
    Idea : In no duplicates
        val -> index 
        here
        val -> Set of indices
    */

    private List<Integer> list;
    private Map<Integer, Set<Integer>> map;
    private Random random;

    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }
    
    public boolean insert(int val) {
       boolean notPresent = !map.containsKey(val);
       //duplicates are allowed hence can't return directly
        //add in list and map both
        map.putIfAbsent(val, new HashSet<>());
        map.get(val).add(list.size());
        list.add(val);

        return notPresent;
    }
    
    //Tricky
    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }

        //any index
        int index = map.get(val).iterator().next();
        int lastIndex = list.size() - 1;
        int lastElement = list.get(lastIndex);
        
        //Remove index from val first
        map.get(val).remove(index);
        if(index != lastIndex){
            //update lastElement -> list and map
            list.set(index, lastElement);
            //orider matter
            map.get(lastElement).remove(lastIndex);
            map.get(lastElement).add(index);
        }

        if(map.get(val).isEmpty()){
            map.remove(val);
        }

        list.remove(lastIndex);

        return true;
    }
    
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
 ```

 25. CLone Graph

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
    public Node cloneGraph(Node node) {

        //BFS
        if(node == null){
            return node;
        }


        Queue<Node> q = new ArrayDeque<>();
        HashMap<Node, Node> oldToNew = new HashMap<>();

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

26. Course Schedule
```
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //topological sort -> if possible then we can finish otherwise not

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(numCourses);

        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < prerequisites.length; i++){
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]); // 1 -> 0
        }

        int [] indegree = new int[numCourses];
        
        //calculate indegree
        for(int i = 0; i < adj.size(); i++){
            for(int j = 0; j < adj.get(i).size(); j++){
                ++indegree[adj.get(i).get(j)];
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        
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

27. Longest Increasing Subsequence

```
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int LIS[] = new int[n];
        Arrays.fill(LIS,1);

        for(int i = n-1; i >=0; i--){
            for(int j = i; j < n; j++){
                if(nums[j] > nums[i]){
                    LIS[i] = Math.max(LIS[i], 1+LIS[j]);
                }
            }
        }
        
        return Arrays.stream(LIS).max().getAsInt();
    }
}
```

28. Word Break
```
// class Solution {
//     // Memoization + Recursion - Top Down
//     private Boolean [] memo;
//     private int n;
//     public boolean wordBreak(String s, List<String> wordDict) {
//         n = s.length();
//         memo = new Boolean[n];

//         return solve(s, 0, wordDict);
//     }

//     private boolean solve(String s, int idx, List<String> wordDict){
//         if(idx == n){
//             return true;
//         }

//         if(memo[idx] != null){
//             return memo[idx];
//         }

//         for(int endIdx = idx+1; endIdx <= n; endIdx++){
//             String split = s.substring(idx, endIdx);

//             if(wordDict.contains(split) && solve(s, endIdx, wordDict)){
//                 return memo[idx] = true;
//             }
//         }
//         return memo[idx] = false;
//     }
// }


class Solution {
    // Memoization + Recursion - Top Down
    private Boolean [] memo;
    private int n;
    private HashSet<String> set;

    public boolean wordBreak(String s, List<String> wordDict) {
        n = s.length();
        memo = new Boolean[n]; //why Boolean -> because we need three state -> true, false, not yet computed(null)
        set = new HashSet<>(wordDict);
        return solve(s, 0, set);
    }

    private boolean solve(String s, int idx, HashSet set){
        if(idx == n){
            return true;
        }

        if(memo[idx] != null){
            return memo[idx];
        }

        for(int end = idx+1; end <= n; end++){
            if(set.contains(s.substring(idx, end)) && solve(s, end, set)){
                return memo[idx] = true;
            }
        }

        return memo[idx] = false;
    }

}
```


29. Minimum Window Substring
```
class Solution {
    public String minWindow(String s, String t) {
        //https://www.youtube.com/watch?v=3Bp3OVD1EGc
        //Sliding window

        int n = s.length();
        int requiredCount = t.length();

        if(requiredCount > n){
            return "";
        }

        HashMap<Character, Integer> map = new HashMap<>();

        for(char ch : t.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int i = 0;
        int j = 0;
        int start_i = 0;
        int minWindow = Integer.MAX_VALUE;

        while(j < n){
            char ch = s.charAt(j);

            if(map.containsKey(ch) && map.get(ch) > 0){
                --requiredCount;
            }

            map.put(ch, map.getOrDefault(ch, 0)-1);

            while(requiredCount == 0){
                int currWindow = j-i+1;

                if(minWindow > currWindow){
                    minWindow = currWindow;
                    start_i = i;
                }

                char start_char = s.charAt(i);
                map.put(start_char, map.getOrDefault(start_char, 0) + 1);
                if(map.containsKey(start_char) && map.get(start_char) > 0){
                    ++requiredCount;
                }

                ++i;

            }
            ++j;
        }

        return minWindow == Integer.MAX_VALUE ? "" : s.substring(start_i, start_i + minWindow);
        
    }
}
```
30. Merge Two Sorted Lists
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

        ListNode dummy = new ListNode(0);
        ListNode head = dummy;

        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                dummy.next = list1;
                list1 = list1.next;
            }else{
                dummy.next = list2;
                list2 = list2.next;
            }
            dummy = dummy.next;
        }

        dummy.next = list1 != null ? list1 : list2;

        return head.next;
        
    }
}
```

31. Merge k Sorted Lists