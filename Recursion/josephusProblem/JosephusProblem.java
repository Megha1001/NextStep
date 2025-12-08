package Recursion.josephusProblem;

/*
 * Time Complexity : Theta(N)
 * Auxiliary Space : Theta(N)
 */

public class JosephusProblem {

    public static void main(String args[]){
        int n = 5, k=3;

        System.out.println("The survived person index is : "+findSurvivedPersonIdx(n,k));
    }

    public static int findSurvivedPersonIdx(int n, int k){

        if(n==1){
            return 0;
        }

        return (k+findSurvivedPersonIdx(n-1, k)) %n;

    }
    
}
