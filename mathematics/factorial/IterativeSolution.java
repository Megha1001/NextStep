package mathematics.factorial;

/*
 * Find factorial of a number
 * Time Complexity : Theta(n)
 * Auxiliary Space : Theta(1)
 */

public class IterativeSolution {

    public static void main(String args[]){
        int n = 5;
        System.out.println("Factorial of n is : "+findFactorial(n));
    }

    public static int findFactorial(int n){
        int res = 1;

        for(int i = n; i > 0; i--){
            res = res * i;
        }

        return res ; 
    }
    
}
