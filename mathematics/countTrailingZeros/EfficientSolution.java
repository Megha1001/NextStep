package mathematics.countTrailingZeros;

/*
 * Idea : We need to find how many 2, 5 combination are there in prime factorization of the number
 * => Num of 5 < num of 2 
 * => find number of 5
 * 
 * Time Complexity : Theta(logN)
 * Auxiliary Space : O(1)
 */

public class EfficientSolution {


    public static void main(String args[]){
        int n = 251;
        System.out.println("Number of trailing zeros in factorial of number are : " + findTrailingZeroInFactorial(n));

    }

    public static int findTrailingZeroInFactorial(int n ){

        int res = 0;
        
        for(int i = 5; i <= n; i = i*5){
            res += (n/i);
        }

        return res;
    }
    
}
