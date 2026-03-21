
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
        while(buffer.isEmpty()){
            push(buffer.pop());
        }


        return;
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