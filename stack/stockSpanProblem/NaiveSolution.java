package stack.stockSpanProblem;

/*
 * TIME COMPLEXITY : O(N*N)
 * AUXILIARY SPACE : O(1)
 */

public class NaiveSolution {

    public static void main(String args[]){
        int price[] = {100,80,60,70,60,75,85};

        System.out.println("The span of stock for each day is : ");
        findSpan(price, price.length);
    }


    public static void findSpan(int price[], int n){

        for(int i=0; i<n; i++){
            int span = 1;
            for(int j=i-1; j>=0 && price[j] <= price[i]; j--){
                ++span;
            }

            System.out.print(span + " ");
        }

    }
    
}
