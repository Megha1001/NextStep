package Arrays.stockSellAndBuy;

/*
 * Time COmplexity : O(N*N*N)
 * Space Complexity : O(N)
 */

public class EfficientSolution {

    public static void main(String args[]){
        int arr[] = {1,5,3,8,12}; //13
        System.out.println("Max profit that can be earned is : "+findProfit(arr, arr.length));
    }

    public static int findProfit(int arr[], int n){
        int profit = 0;
        for(int i=1; i<n;i++){
            if(arr[i-1] < arr[i]){
                profit += arr[i] - arr[i-1];
            }
        }

        return profit;
    }
    
}
