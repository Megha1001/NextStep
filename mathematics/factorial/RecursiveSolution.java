package mathematics.factorial;

/*
 * Time COmplexity : Theta(N)
 * Auxiliary Space : Theta(N)
 */

public class RecursiveSolution {

    public static void main(String args[]){
        int n = 5;
        System.out.println("Factorial of n is : "+findFactorial(n));
    }

    public static int findFactorial(int n){

        if(n==0){
            return 1;
        }

        return findFactorial(n-1) * n;
    }
    
}
