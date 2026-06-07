1. Fractional knapsack : https://www.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1
```
class Solution {
    class Item{
        int val;
        int weight;
        
        public Item(int val, int weight){
            this.val = val;
            this.weight = weight;
        }
    }
    public double fractionalKnapsack(int[] val, int[] wt, int capacity) {
        int n = val.length;
        List<Item> list = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            list.add(new Item(val[i], wt[i]));
        }
        
        //sort
        Collections.sort(list, (a,b)->{
           double r1 = (1.0 * a.val)/a.weight;
           double r2 = (1.0 * b.val)/b.weight;
           return Double.compare(r2, r1); //decending order
        });
        
        double res = 0.0;
        
        for(Item item : list){
            if(item.weight <= capacity){
                res += item.val;
                capacity -= item.weight;
            }else{
                res += (1.0 * item.val/item.weight ) * capacity;
                break;
            }
        }
        
        return res;
        
    }
}
```