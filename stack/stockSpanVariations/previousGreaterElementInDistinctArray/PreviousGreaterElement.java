package stack.stockSpanVariations.previousGreaterElementInDistinctArray;

/*
 * IDEA : Use Stock span logic and find the previousHigh if stack is empty then consider -1 value for current value
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(N)
 */

import java.util.ArrayDeque;

public class PreviousGreaterElement { //elements are distinct

    public static void main(String args[]){
        int arr[] = {15, 10,18,12,4,6,2,8};
        System.out.println("Previous greater element for all the elements in array are : ");
        findPreviousGreater(arr, arr.length);
    }

    public static void findPreviousGreater(int arr[], int n){
        ArrayDeque<Integer> d = new ArrayDeque<>();

        for(int i=0; i<n; i++){
            while(d.size()>0 && d.peek() < arr[i]){
                d.pop();
            }

            if(d.isEmpty()){
                System.out.print(-1 + " ");
            }else {
                System.out.print(d.peek()+ " ");
            }

            d.push(arr[i]);

        }


    }
    
}
