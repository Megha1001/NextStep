1. Top K Frequent Elements --> STAR QUESTION
https://neetcode.io/problems/top-k-elements-in-list/question
Check solutions if not clicked

```
public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> count = new HashMap<>();

        List<Integer>[] freq = new List[nums.length+1]; // maximum length when all numbers are same so count == length, +1 is for zero as array is zero indexed;

        //initialize freq
        for(int i=0; i<freq.length; i++){
            freq[i] = new ArrayList<>();
        }

        //populate HashMap
        for(int i:nums){
            count.put(i, count.getOrDefault(i, 0) + 1);
        }


        //use Map to populate list-> where value will be index and key will be value added as list
        for(Map.Entry<Integer,Integer> entry : count.entrySet()){
            freq[entry.getValue()].add(entry.getKey());
        }


        //traverse from last for k elements
        int res[] = new int[k];
        int index = 0;

        for(int i=freq.length-1; i>0 && index <k; i--){ //no need to execute for zero since no element occurs 0 times ;)
            for(int j : freq[i]){//for positions that are not empty
                res[index++] = j;
                if(index == k){
                    return res;
                }
            }

        }
        return res;

    }
```

2. Last Stone Weight

```
class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        //build max heap
        for(int s : stones){
            minHeap.offer(-s); //store -ve
        }

        while(minHeap.size() > 1){
            int first = minHeap.poll();
            int second = minHeap.poll();

            if(first != second){ // second > first. there can never be condition when first > second since its min heap
                minHeap.offer(first-second);
            }
        }

        minHeap.offer(0);//when all stones are smashed we need to return 0;
        return Math.abs(minHeap.peek()); // remove minus sign
        
    }
}

```

3. Car Fleet
```
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        //Using Stack
        int [][] pairs = new int[position.length][2]; //<Pair<Position, speed>>
        
        for(int i=0; i<position.length; i++){
            pairs[i][0] = position[i];
            pairs[i][1] = speed[i];
        }

        //sort in decending order of position
        Arrays.sort(pairs, (a,b) -> Integer.compare(b[0], a[0]));

        Stack<Double> stack = new Stack<>();

        for(int [] p : pairs){
            stack.push((double)(target - p[0])/p[1]); //time when it reach the target

            //check for adjancent if it collid --> note : Its only one if not while
            //If the current car reaches the target earlier than the car infront of it, it will catchup -> they form one fleet
            if(stack.size() >= 2 && stack.peek() <= stack.get(stack.size() - 2)){
                stack.pop();
            } 
        }

        return stack.size();

    }
}

```

Approach -2  -> Note that we have double
```
public int carFleet(int target, int[] position, int[] speed) {
        //Iterative solution
        int n = position.length;
        int [][]pair = new int[n][2]; // <position, speed>

        for(int i=0; i<n; i++){
            pair[i][0] = position[i];
            pair[i][1] = speed[i];
        }

        //sort in decending order of position
        Arrays.sort(pair, (a, b)-> Integer.compare(b[0], a[0]));

        int fleet = 1;
        double prevTime = (double)(target - pair[0][0])/pair[0][1]; //note the brackets otherwise it will do int division first then truncate the decimal

        for(int i=1; i<n; i++){
            double currTime = (double)(target - pair[i][0])/pair[i][1];

            //it will create a new fleet and becomes the front of the new fleet
            if(currTime > prevTime){
                ++fleet;
                prevTime = currTime;
            }
        }

        return fleet;

    }
```
4. Max Area of island
Method 2 : BFS

```
class Solution {

    public static int directions[][] = {{-1,0},{1,0},{0,1},{0,-1}};
    //Method - BFS
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int ROWS = grid.length;
        int COLS = grid[0].length;

        for(int r=0; r<ROWS; r++){
            for(int c=0; c<COLS; c++){
                if(grid[r][c] == 1){
                    maxArea = Math.max(maxArea, bfs(grid, r, c));
                }
            }
        }
        return maxArea;
    }

    public int bfs(int[][]grid, int r, int c){
        Queue<int[]> q = new ArrayDeque<>(); // queue contains cell with value 1 even though we are setting it as 0 
        //once that is visited but it was originally 1 and we are enqueue the index so value doesn't matter
        q.offer(new int[]{r,c});
        grid[r][c] = 0;
        int area = 0;

        while(!q.isEmpty()){
            int [] p = q.poll();
            ++area;
            int row = p[0];
            int col = p[1];
            
            for(int [] dir : directions){
                int nr = row + dir[0];
                int nc = col + dir[1];

                if(nr >= 0 && nr < grid.length
                && nc >= 0 && nc < grid[0].length
                && grid[nr][nc] == 1){
                    grid[nr][nc] = 0;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        return area;
    }
}
```
A node should be marked visited at the moment it is discovered, not when it is processed.



5. Pacific Atlantic Water Flow
```
class Solution {

    public static int directions[][] = {{0,1},{0,-1},{1,0},{-1,0}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int ROWS = heights.length;
        int COLS = heights[0].length;

        boolean [][] pac = new boolean[ROWS][COLS];
        boolean [][] atl = new boolean[ROWS][COLS];

        //for first row and last row
        for(int c = 0 ; c < COLS; c++){
            dfs(0, c, pac, heights);
            dfs(ROWS-1, c, atl, heights);
        }

        //for first and last column
        for(int r = 0; r < ROWS; r++){
            dfs(r, 0, pac, heights);
            dfs(r, COLS-1, atl, heights);
        }

        List<List<Integer>> res = new ArrayList<>();
        //get common
        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLS; c++){
                if(pac[r][c] && atl[r][c]){
                    res.add(Arrays.asList(r, c));
                }
            }
        }

        return res;
        
    }

    public void dfs(int r, int c, boolean[][]oceans, int [][]heights){
        oceans[r][c] = true; //visited;
        for(int[] dir : directions){
            int row = r + dir[0];
            int col = c + dir[1];

            if(row >= 0 && row < heights.length
            && col >= 0 && col < heights[0].length
            && !oceans[row][col]&& heights[row][col] >= heights[r][c]){//last condition is imp that is reverse of original as we are trying to check what all can go to pacific/atlantic ocean
                dfs(row, col, oceans, heights);
            }
        }
    }
}

```