1. Top K Frequent Elements

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