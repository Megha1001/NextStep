package bitsMagic.countSetBits;
/*
 * Assumption : We have 32 bits numbers
 * 
 * => Precompute count for 8 bit number(0 to 255)
 *  tbl[0] = 0
 * for(int i=1; i<256;i++)
 *  tbl[i] = tbl[i&(i-1)] + 1
 * 
 * => Now to count set bits in given number ( & 255 then shift by 8)
 * 
 * Time Complexity : O(1)
 */

public class LookupTableSolution {

    private static int[]tbl = new int[256];

    public static void main(String args[]){
        
        precomputeCountBitsOfNum();
        int n = 5;
        System.out.println("Count of Set bits in given number is : "+countOfSetBits(n));
    }


    public static void precomputeCountBitsOfNum(){

        tbl[0] = 0;
        for(int i=1; i<256; i++){
            tbl[i]  = tbl[i & (i-1)] + 1;
        }

    }

    public static int countOfSetBits(int n){

        return tbl[n & 255]
        + tbl[(n>>8)&255]
        + tbl[(n>>16)&255]
        + tbl[n>>24];

    }
    
}
