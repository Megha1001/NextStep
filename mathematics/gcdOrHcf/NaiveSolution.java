package mathematics.gcdOrHcf;

/*
 * Find GCD(Greatest Common Divisor) or HCF(Highest Common factor) of two given number
 * Time Complexity : Theta(Min(n1, n2))
 */

public class NaiveSolution {

    public static void main(String args[]){
        int n1 = 4, n2 = 6;
        System.out.println("GCD or HCF of two given number is : "+findGCD(n1, n2));
    }


    public static int findGCD(int n1, int n2){
        int res = 1;

        //base cases
        if(n1%n2 == 0){
            return n2;
        }

        if(n2%n1 == 0){
            return n1;
        }

        int limit = Math.min(n1, n2);

        for(int i = 2; i < limit; i++){
            if(n1%i==0 && n2%i ==0){
                res = i;
            }
        }

        return res;

    }
    
}
