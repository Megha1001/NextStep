package bitsMagic.countSetBits;

/*
 * Time Complexity : Theta(Set bits)
 * Auxiliary space : Theta(1)
 */

public class BrainAndKerninghamAlgo {

    public static void main(String args[]){
        int n = 13;
        System.out.println("Number of set bits in given number is : "+countSetBits(n));
    }

    public static int countSetBits(int n){
        int res = 0;

        while(n > 0){
            n = n & (n-1);
            ++res;
        }

        return res;

    }
    
}
