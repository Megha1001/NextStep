package dynamic_Programming.editDistance;

public class EditDistanceDPSolution {

    public static void main(String args[]){
        String s1 = "cut";
        String s2 = "cat";
        System.out.println("Edit distance for the two given strings is : "+findEditDistance(s1, s2, s1.length(), s2.length()));
    }

    public static int findEditDistance(String s1, String s2, int m, int n){
        int dp[][] = new int[m+1][n+1];

        //for first row
        for(int i = 0; i <= m; i++){
            dp[0][i] = i;
        }

        //for first column
        for(int i = 0; i <= n; i++){
            dp[i][0] = i;
        }


        //for remaining position
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = 1 +  Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]);
                }
            }
        }


        return dp[m][n];

    }
    
}
