package stack.stockSpanProblem;

/*
 * Span : Number of previous consecutive days where price <= today's price
 * 
 * ANALOGY
 * 1. for every day we can calculate span = i(current_idx) - previousHigh(get it from stack)
 * if stack is empty then span = i+1;
 * 
 * 2. Use Stack = to store the previousHigh
 * 
 * TIME COMPLEXITY : O(N) -> inner while loop
 * AUXILIARY SPACE : O(N)
 */

import java.util.ArrayDeque;

public class EfficientSolutionUsingStack {

    public static void main(String args[]){
        int price[] = {100,80,60,70,60,75,85};

        System.out.println("The span of stock for each day is : ");
        findSpan(price, price.length);
    }


    public static void findSpan(int price[], int n){
        ArrayDeque<Integer> previouHigh = new ArrayDeque<>();
        int res[] = new int[price.length];

        for(int i=0; i<n; i++){
            while(previouHigh.size() > 0 &&  price[previouHigh.peek()] <= price[i]){
                previouHigh.pop();
            }

            if(previouHigh.isEmpty()){
                res[i] = i+1;
            }else{
                res[i] = i-previouHigh.peek();
            }

            previouHigh.push(i);
        }

        for(int i=0; i<n; i++){
            System.out.print(res[i] + " ");
        }

    }
    
}
