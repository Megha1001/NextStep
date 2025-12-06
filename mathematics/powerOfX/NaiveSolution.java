package mathematics.powerOfX;

/*
 * Time Complexity : Theta(N)
 * Auxiliary Space : Theta(1)
 */

public class NaiveSolution {

    public static void main(String args[]){
        int x = 2, n = 10;
        System.out.println("x^n : "+findPower(x,n));
    }

    public static int findPower(int x, int n){
        int res = 1;

        for(int i=1; i<=n; i++){
            res *= x;
        }

        return res;
    }
    
}
