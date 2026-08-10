1. LRU Cache
```
class LRUCache {

    private static class Node{
        int key;
        int val;
        Node prev;
        Node next;

        Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    private int capacity;
    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node node = cache.get(key);
        if(node == null){
            return -1;
        }

        moveToFront(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            node.val = value;
            moveToFront(node);
            return;
        }

        if(cache.size() == capacity){
            Node lru = tail.prev;
            removeNode(lru);
            cache.remove(lru.key);
        }

        Node node = new Node(key, value);
        addToFront(node);
        cache.put(key, node);
    }

    private void moveToFront(Node node){
        removeNode(node);
        addToFront(node);
    }

    private void removeNode(Node node){
       node.prev.next = node.next;
       node.next.prev = node.prev;
    }

    private void addToFront(Node node){
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
 ```


2. Top K Frequent Elements

Method 1 : DLL + HashMap
```
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        //Method 1 Priority Queue
        Map<Integer, Integer> freq = new HashMap<>();

        for(int num : nums){
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for(Map.Entry<Integer, Integer> entry : freq.entrySet()){
            int val = entry.getKey();
            int count = entry.getValue();

            pq.offer(new int[]{count, val});
            if(pq.size() > k){
                pq.poll();
            }
        }

        int[] res = new int[k];
        int idx = 0;
        while(!pq.isEmpty()){
            res[idx ++] = pq.poll()[1];
        }
        return res;
    }
}
```

Method : 2 Bucket

```
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        //Bucket
        int n = nums.length;

        Map<Integer, Integer> freq = new HashMap<>();

        for(int num : nums){
            freq.put(num, freq.getOrDefault(freq, 0) + 1);
        }

        List<Integer> [] bucket = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++){
            bucket[i] = new ArrayList<>();
        }

        for(Map.Entry<Integer, Integer> entry : freq.entrySet()){
            int num = entry.getKey();
            int val = entry.getValue();
            bucket[val].add(num);
        }

        int [] res = new int[k];
        int idx = 0;

        for(int i = n; i>= 0 && idx < k; i--){
            for(int num : bucket[i]){
                res[idx ++] = num;
                if(idx == k){
                    break;
                }
            }
        }

        return res;
        
    }
}
```