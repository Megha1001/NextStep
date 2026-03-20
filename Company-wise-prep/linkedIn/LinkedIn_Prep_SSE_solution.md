
### Day - 1

1. Nested List Weight Sum

```
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
class NestedInteger {

    private List<NestedInteger> list;
    private Integer integer;

    public NestedInteger(List<NestedInteger> list) {
        this.list = list;
    }

    public void add(NestedInteger nestedInteger) {
        // Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
        if (this.list != null) {
            this.list.add(nestedInteger);
        } else {
            this.list = new ArrayList();
            this.list.add(nestedInteger);
        }
    }

    public void setInteger(int num) {
        // Set this NestedInteger to hold a single integer equal to value.
        this.integer = num;
    }

    public NestedInteger(Integer integer) {
        this.integer = integer;
    }

    public NestedInteger() {
        this.list = new ArrayList();
    }

    public boolean isInteger() {
        return integer != null;
    }

    public Integer getInteger() {
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        return integer;
    }

    public List<NestedInteger> getList() {
        return list;
    }

    public static String printNi(NestedInteger thisNi, StringBuilder sb) {
        if (thisNi.isInteger()) {
            sb.append(thisNi.integer);
            sb.append(",");
        }
        sb.append("[");
        for (NestedInteger ni : thisNi.list) {
            if (ni.isInteger()) {
                sb.append(ni.integer);
                sb.append(",");
            } else {
                printNi(ni, sb);
            }
        }
        sb.append("]");
        return sb.toString();
    }

}

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

public class MainTest {
    @Test
    public void test1() {
        // List : [1, [4, [6]]]

        depthSumCalc ds = new depthSumCalc();

        NestedInteger list2_Node1 = new NestedInteger();
        list2_Node1.setInteger(1);

        NestedInteger list2_Node2 = new NestedInteger();

        NestedInteger list2_Node2_Sub1 = new NestedInteger();
        list2_Node2_Sub1.setInteger(4);

        NestedInteger list2_Node2_Sub2 = new NestedInteger();
        NestedInteger list2_Node2_Sub22 = new NestedInteger();
        list2_Node2_Sub22.setInteger(6);
        list2_Node2_Sub2.add(list2_Node2_Sub22);

        list2_Node2.add(list2_Node2_Sub1);
        list2_Node2.add(list2_Node2_Sub2);

        List<NestedInteger> list2 = Arrays.asList(list2_Node1,list2_Node2);

        assertTrue(ds.depthSum(list2) == 27);
    }
    
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MainTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }

}
```