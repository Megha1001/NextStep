package mathematics.countTrailingZeros;

/*
 * Find trailing zeros
 * Time Complexity : Theta(Trailing Zeros)
 * Auxiliary Space : Theat(1)
 */

public class NaiveSolution {

    public static void main(String args[]){
        int n = 5;
        // System.out.println(findFact(n));
        System.out.println("Number of trailing zeros are : "+findNumOfTrailingZeros(findFact(n)));
    }


    public static int findFact(int n){
        int res = 1;

        for(int i = 2; i <= n; i++){
            res = res * i;
        }

        return res;
    }

    public static int findNumOfTrailingZeros(int n){
        int res = 0;
        /*
        while(n > 0){
            if(n%10 == 0){
                ++res;
                n = n/10;
            }else{
                break;
            }
        }*/

        while(n%10==0){
            res++;
            n=n/10;
        }

        return res;
    }
    
}
