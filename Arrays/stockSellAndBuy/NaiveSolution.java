package Arrays.stockSellAndBuy;

/*
 * Time COmplexity : O(N*N*N)
 * Space Complexity : O(N)
 */

public class NaiveSolution {

    public static void main(String args[]){
        int arr[] = {1,5,3,8,12}; //13
        System.out.println("Max profit that can be earned is : "+findProfit(arr, 0, arr.length-1));
    }

    public static int findProfit(int arr[], int start, int end){

        if(start >= end){
            return 0;
        }

        int profit = 0;

        for(int i=start; i<end; i++){ //Note: i<end -> i<n-1 => i<=n-2, since there is no point of calculating on last day
            for(int j = i+1; j<=end; j++){
                if(arr[j] > arr[i]){
                    int currProfit = arr[j] - arr[i]
                                    +findProfit(arr, start, i-1)
                                    + findProfit(arr, j+1, end);
                    profit = Math.max (profit, currProfit);
                }
            }
        }

        return profit;
    }
    
}
