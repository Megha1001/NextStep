package bitsMagic.powerOfTwo;

/*
 * Time Complexity : Theta(1)
 * Auxiliary Space : Theta(1)
 */

public class LookuptableSolution {

    private static int tbl[] = new int[256];

    public static void main(String args[]){
        populateTbl();
        int n = 16;
        System.out.println("is given number is power of 2 ? "+isPowerOfTwo(n));
    }


    public static void populateTbl(){

        tbl[0] = 0;
        for(int i = 1; i < 256; i++){
            tbl[i] = tbl[i & (i-1)] + 1;
        }

    }

    public static boolean isPowerOfTwo(int n){

        return (tbl[n & 255]
        + tbl[(n >> 8) & 255]
        + tbl[(n >> 16) & 255]+
        tbl[n>>24]) ==1;

    }
    
}
