
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