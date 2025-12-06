package bitsMagic.powerOfTwo;

/*
 * Idea : Explicitly handle 0
 * Repeatedly divide by 2
 *  -> if(n%2 != 0) return false;
 * 
 * Time Complexity : O(log(N))
 */

public class NaiveSolution {

    public static void main(String args[]){
        int n = 16;
        System.out.println("is given number is power of 2 ? "+isPowerOfTwo(n));
    }

    public static boolean isPowerOfTwo(int n){
        //base case
        if(n==0){
            return false;
        }

        while(n != 1){
            if(n%2 != 0){
                return false;
            }
            n = n/2;
        }

        return true;
    }
    
}
