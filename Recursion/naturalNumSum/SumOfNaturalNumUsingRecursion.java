package Recursion.naturalNumSum;


/*
 * Idea : to use Tail recursion so compiler can do tail call elimination
 * 
 * Time Complexity : Theta(N)
 * Auxiliary Space : Theta(N)
 */

public class SumOfNaturalNumUsingRecursion {

    public static void main(String args[]){
        int n = 5;
        System.out.println("Sum of natural number for given n is :  "+findSum(n, 0));
    }

    public static int findSum(int n, int sum){

        if(n==0){
            return sum;
        }
         return findSum(n-1, sum+n);
    }
    
}
