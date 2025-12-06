package bitsMagic.countSetBits;

/*
 * Idea : 
 * 1. until n!=0
 * 2. if n >> 1 & 1 !=0 ++count
 * 3. Right shift n by 1 
 */


public class NaiveSolution {

    public static void main(String args[]){
        int n = 13;
        System.out.println("Number of set bits in given number is : "+countSetBits(n));
    }

    public static int countSetBits(int n){
        int count = 0;

        while(n!=0){
            if((n & 1) != 0){
                ++count;
            }
            n = n>>1;
        }

        return count;
    }
    
}
