1. Top K Frequent Elements
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