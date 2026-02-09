package dynamic_Programming.knapSack0_1;

public class RecursiveSolutionKnapSack_0_1_DP {

    public static void main(String args[]){
        int w[] = {5,4,6,3};
        int v[] = {10,40,30,50};
        int W = 10;

        System.out.println("maximum value that can be make from given input is : "+knapSack(w, v, W, w.length));
    }

    public static int knapSack(int[]w, int []v, int W, int n){
        int dp[][] = new int[n+1][W+1];

        //populate first row
        for(int i = 0; i <= W; i++){
            dp[0][i] = 0;
        }

        //populate first column
        for(int i = 0; i <= n; i++){
            dp[i][0] = 0;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= W; j++){
                if(w[i-1] > j){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = Math.max(v[i-1] + dp[i-1][j - w[i-1]], dp[i-1][j]); //considered and not considered
                }
            }
        }

        return dp[n][W];
    }
    
}
