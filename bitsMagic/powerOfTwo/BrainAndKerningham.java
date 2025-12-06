package bitsMagic.powerOfTwo;

/*
 * Idea : One set bit should be there
 *
 * Time Complexity : Theta(1) when power of 2 , but in case where number is not power of 2 it will be O(d), where d is number of set bits
 * Auxiliary Space : Theta(1)
 */

public class BrainAndKerningham {

    public static void main(String args[]){
        int n = 16;
        System.out.println("Is the given number is power of 2 ? "+isPowerOf2(n));
    }

    public static boolean isPowerOf2(int n ){
        int res = 0;
        while(n > 0){
            n = n & (n-1);
            ++res;
            if(res > 1){
                return false;
            }
        }

        return res==1;
    }
    
}
